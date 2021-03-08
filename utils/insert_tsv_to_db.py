from typing import Dict
from collections import OrderedDict
import argparse
import csv
import glob
from io import StringIO
import re
import pymysql.cursors
from pymysql import Connection
from dotenv import load_dotenv
import os

load_dotenv(".env")


def insert_tsvdata(conn: Connection, data_dir: str, file_str: str, lang: str):
    # check authors table exist
    data_dir = data_dir + "/" if data_dir[-1] != "/" else data_dir
    files = glob.glob("{}{}*".format(data_dir, file_str))
    author_rule = re.compile(r"(\(.+\))|♠")
    cursor = conn.cursor()
    for fpath in files:
        year = int(fpath.replace(".tsv", "")[-4:])
        print(year)
        query = "INSERT IGNORE INTO year VALUES(0, %s)"
        cursor.execute(query, year)
        conn.commit()

        with open(fpath, "r") as f:
            content = f.read()
            content = content.replace("\0", "")
            tsv = csv.DictReader(StringIO(content), delimiter="\t")
            rows = [row for row in tsv]
        paper_authors = [
            [
                author_rule.sub("", author).replace("\b", "")
                for author in row["authors"].split(",")
            ]
            for row in rows
        ]

        # insert author names
        authors = list(
            set([author for paper_author in paper_authors for author in paper_author])
        )
        query = "INSERT IGNORE INTO authors VALUES(0, %s)"
        cursor.executemany(query, authors)
        conn.commit()

        # insert paper task
        query = "INSERT IGNORE INTO task VALUES(0, %s, %s)"
        tasks = list(
            {row["task"]: row["class"] for row in rows if row["task"] != ""}.items()
        )
        cursor.executemany(query, tasks)
        conn.commit()

        # insert paper informations
        query = "INSERT IGNORE INTO papers\
            (paper_id, year, label, task,\
            session, title, url, introduction, conference, lang) \
            VALUES (0, {0}, %s, %s, %s, %s, %s, %s, '{1}', '{2}')\
            ".format(
            year, file_str.upper(), lang
        )
        data = [
            [
                row["class"],
                row["task"],
                row["session"],
                row["title"],
                row["url"],
                row["introduction"],
            ]
            for row in rows
        ]
        cursor.executemany(query, data)
        conn.commit()

        # insert information of authors writing papers
        query = "INSERT IGNORE INTO paper_written_author(author_id, paper_id)\
            SELECT authors.author_id, papers.paper_id\
            from authors, papers\
            where authors.name = %s and papers.title = %s"
        for author, insert_data in zip(paper_authors, data):
            for name in author:
                cursor.execute(query, [name, insert_data[3]])


if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument(
        "-d",
        "--data_dir",
        default="data/",
        type=str,
        help="data directory. default data/",
    )
    parser.add_argument(
        "-s", "--str_rule", default="nlp", type=str, help="file name. default `NLP`"
    )
    parser.add_argument(
        "-l",
        "--language",
        default="english",
        type=str,
        help="language. use in insert language information. \
                        default `english`",
    )
    args = parser.parse_args()
    port = os.environ.get("DB_PORT")
    host = os.environ.get("DB_HOST")
    db = os.environ.get("DB_NAME")
    if port == "":
        port = 33036
    else:
        port = int(os.environ.get("DB_PORT"))
    if host == "":
        host = "localhost"
    if db == "":
        db = "research_paper_db"
    conn = pymysql.connect(
        host=host,
        user=os.environ.get("DB_ROOT_USER"),
        password=os.environ.get("DB_ROOT_PASSWORD"),
        db=db,
        charset="utf8mb4",
        port=port,
        cursorclass=pymysql.cursors.DictCursor,
    )
    query = "INSERT IGNORE INTO conference VALUES(0, %s)"
    cursor = conn.cursor()
    cursor.execute(query, str(args.str_rule).upper())  # str_rule = conference name
    conn.commit()
    insert_tsvdata(conn, args.data_dir, args.str_rule, args.language)
    conn.close()

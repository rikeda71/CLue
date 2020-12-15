import React, { useState, useEffect } from "react";
import styled from "styled-components";
import { AuthorType } from "../../types";
import { createGetRequestUrl } from "../../utils";
import { useParams } from "react-router";
import Paper from "../Paper/Paper";
import { AUTHOR_ENDPOINT } from "../../constants";

const AuthorPageStyle = styled.div`
  margin-right: auto;
  margin-left: auto;
  width: 60%;
`;

const AuthorPageName = styled.h1`
  font-size: x-large;
  font-weight: bold;
  margin: 1rem 0;
`;

const AuthorPagePapers = styled.div``;

const AuthorPage: React.FC = () => {
  const { id } = useParams<{ id: string }>();
  const [author, setAuthor] = useState<AuthorType>();
  const requestUrl = createGetRequestUrl(`${AUTHOR_ENDPOINT}/${id}`);

  useEffect(() => {
    async function getAuthor() {
      await fetch(requestUrl, {
        method: "GET",
        mode: "cors",
        headers: { "Content-Type": "application/json; charset=utf-8" },
      })
        .then(res => res.json())
        .then(res => {
          return res;
        })
        .then(res => setAuthor(res));
    }
    getAuthor();
  }, []);

  return (
    <AuthorPageStyle>
      {!!author && (
        <React.Fragment>
          <AuthorPageName>{author.name}</AuthorPageName>
          <AuthorPagePapers>
            論文リスト
            {author.papers
              .sort((x, y) => y.year - x.year)
              .map(paper => (
                <Paper paper={paper} key={paper.paperId} />
              ))}
          </AuthorPagePapers>
        </React.Fragment>
      )}
    </AuthorPageStyle>
  );
};

export default AuthorPage;

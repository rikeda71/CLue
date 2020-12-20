import React, { useState, useEffect } from "react";
import styled from "styled-components";
import { AuthorType } from "../../types";
import { useParams } from "react-router";
import Paper from "../Molecules/Paper";
import { API_URL, AUTHOR_ENDPOINT } from "../../constants";
import { FetchAPIService } from "../../api";

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
  const fetchApiService = new FetchAPIService(API_URL, `${AUTHOR_ENDPOINT}/${id}`);

  useEffect(() => {
    fetchApiService
      .fetchAPI("GET")
      .then(res => {
        return res.json();
      })
      .then(res => setAuthor(res));
  }, []);

  return <AuthorPageStyle>{!!author && <AuthorPageTemplate author={author} />}</AuthorPageStyle>;
};

export const AuthorPageTemplate: React.FC<{ author: AuthorType }> = props => {
  return (
    <React.Fragment>
      <AuthorPageName>{props.author.name}</AuthorPageName>
      <AuthorPagePapers>
        論文リスト
        {props.author.papers
          .sort((x, y) => y.year - x.year)
          .map(paper => (
            <Paper paper={paper} key={paper.paperId} />
          ))}
      </AuthorPagePapers>
    </React.Fragment>
  );
};

export default AuthorPage;

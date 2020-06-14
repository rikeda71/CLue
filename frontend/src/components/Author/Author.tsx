import React from "react";
import styled from "styled-components";
import { AuthorType } from "../../types";
import Paper from "../Paper/Paper";

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

const Author: React.FC<AuthorType> = props => {
  return (
    <AuthorPageStyle>
      <AuthorPageName>{props.name}</AuthorPageName>
      {!!props.papers && (
        <AuthorPagePapers>
          論文リスト
          {props.papers
            .sort((x, y) => y.year - x.year)
            .map(paper => (
              <Paper {...paper} key={paper.paperId} />
            ))}
        </AuthorPagePapers>
      )}
    </AuthorPageStyle>
  );
};

export default Author;

import React from "react";
import styled from "styled-components";
import { AuthorType } from "../../types";

const AuthorPageStyle = styled.div``;

const AuthorPageName = styled.h1``;

const AuthorPagePapers = styled.div``;

const AuthorPagePaper = styled.div``;

const Author: React.FC<AuthorType> = props => {
  return (
    <AuthorPageStyle>
      <AuthorPageName>{props.name}</AuthorPageName>
      {!!props.papers && (
        <AuthorPagePapers>
          論文リスト
          {props.papers.map(paper => (
            <AuthorPagePaper key={paper.paperId}>{paper.title}</AuthorPagePaper>
          ))}
        </AuthorPagePapers>
      )}
    </AuthorPageStyle>
  );
};

export default Author;

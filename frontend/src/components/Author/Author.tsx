import React from "react";
import styled from "styled-components";
import { AuthorType } from "../../types";

const AuthorStyle = styled.div``;

const Name = styled.h1``;

const Papers = styled.div``;

const Paper = styled.div``;

const Author: React.FC<AuthorType> = props => {
  return (
    <AuthorStyle>
      <Name>{props.name}</Name>
      {!!props.papers && (
        <Papers>
          論文リスト
          {props.papers.map(paper => (
            <Paper>{paper.title}</Paper>
          ))}
        </Papers>
      )}
    </AuthorStyle>
  );
};

export default Author;

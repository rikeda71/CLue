import React from "react";
import styled from "styled-components";
import { PaperType } from "../../types";

export const PaperStyle = styled.div`
  border: solid;
  border-radius: 1rem;
  border-width: thin;
  margin-top: 1rem;
  padding-top: 0.5rem;
  padding-bottom: 0.5rem;
  padding-left: 1rem;
  padding-right: 1rem;
`;

export const Title = styled.h1`
  font-size: large;
  display: inline-block;
`;

export const Tags = styled.div`
  display: flex;
  flex-basis: 100px;
`;

export const Conference = styled.div``;

export const Task = styled.div`
  margin-left: 1rem;
`;

export const Authors = styled.div`
  display: flex;
  margin-bottom: 0.2rem;
`;

export const Author = styled.div`
  margin-right: 1rem;
`;

const Paper: React.FC<PaperType> = props => {
  return (
    <PaperStyle>
      <Title>
        <a href={props.url}>{props.title}</a>
      </Title>
      {!!props.authors && (
        <Authors>
          {props.authors.map(author => (
            <Author key={author.authorId}>{author.name}</Author>
          ))}
        </Authors>
      )}
      <Tags>
        <Conference>
          {props.conference}
          {props.year}
        </Conference>
        {!!props.task && <Task>{props.task}</Task>}
      </Tags>
    </PaperStyle>
  );
};

export default Paper;

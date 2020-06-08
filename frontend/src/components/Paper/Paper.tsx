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

export const PaperTitle = styled.h1`
  font-size: large;
  display: inline-block;
`;

export const PaperTags = styled.div`
  display: flex;
  flex-basis: 100px;
`;

export const PaperConference = styled.div``;

export const PaperTask = styled.div`
  margin-left: 1rem;
`;

export const PaperAuthors = styled.div`
  display: flex;
  margin-bottom: 0.2rem;
`;

export const PaperAuthor = styled.div`
  margin-right: 1rem;
`;

const Paper: React.FC<PaperType> = props => {
  return (
    <PaperStyle>
      <PaperTitle>
        <a href={props.url}>{props.title}</a>
      </PaperTitle>
      {!!props.authors && (
        <PaperAuthors>
          {props.authors.map(author => (
            <PaperAuthor key={author.authorId}>{author.name}</PaperAuthor>
          ))}
        </PaperAuthors>
      )}
      <PaperTags>
        <PaperConference>
          {props.conference}
          {props.year}
        </PaperConference>
        {!!props.task && <PaperTask>{props.task}</PaperTask>}
      </PaperTags>
    </PaperStyle>
  );
};

export default Paper;

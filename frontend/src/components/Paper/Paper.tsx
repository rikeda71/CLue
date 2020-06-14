import React from "react";
import styled from "styled-components";
import { PaperType } from "../../types";
import { Link } from "react-router-dom";

export const PaperStyle = styled.div`
  border: solid;
  border-radius: 1rem;
  border-width: thin;
  margin-top: 1rem;
  margin-left: auto;
  margin-right: auto;
  padding-top: 0.5rem;
  padding-bottom: 0.5rem;
  padding-left: 1rem;
  padding-right: 1rem;
  width: 60%;
`;

export const PaperTitle = styled.h1`
  font-size: large;
  display: inline-block;
  margin-bottom: 0.6rem;
`;

export const PaperTags = styled.div`
  display: flex;
  flex-basis: 100px;
`;

export const PaperConference = styled.div`
  display: inline;
  border: solid;
  border-radius: 1rem;
  background: #1e90ff;
  border-color: #ffffff;
  padding: 3px 10px;
  color: #fafafa;
  font-size: small;
  font-weight: bold;
`;

export const PaperTask = styled.div`
  display: inline;
  margin-left: 1rem;
  border: solid;
  border-radius: 1rem;
  background: #ffb6c1;
  border-color: #ffffff;
  padding: 3px 10px;
  color: #ffffff;
  font-size: small;
  font-weight: bold;
`;

export const PaperAuthors = styled.div`
  display: flex;
  margin-bottom: 0.5rem;
  margin-left: 0.4rem;
`;

export const PaperAuthor = styled.div`
  margin-right: 1rem;
  font-size: small;
  a {
    color: #0f0f0f;
  }
`;

export const PaperLink = styled.div`
  padding: 3px 0;
  margin-left: auto;
  margin-right: 10px;
`;

const Paper: React.FC<PaperType> = props => {
  return (
    <PaperStyle>
      <PaperTitle>
        <Link to={`/paper/${props.paperId}`}>{props.title}</Link>
      </PaperTitle>
      {!!props.authors && (
        <PaperAuthors>
          {props.authors.map(author => (
            <PaperAuthor key={author.authorId}>
              <Link to={`/author/${author.authorId}`}>{author.name}</Link>
            </PaperAuthor>
          ))}
        </PaperAuthors>
      )}
      <PaperTags>
        <PaperConference>
          {props.conference}
          {props.year}
        </PaperConference>
        {!!props.task && <PaperTask>{props.task}</PaperTask>}
        <PaperLink>
          <a href={props.url}>pdf</a>
        </PaperLink>
      </PaperTags>
    </PaperStyle>
  );
};

export default Paper;

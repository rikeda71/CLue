import React from "react";
import styled from "styled-components";
import { Link } from "react-router-dom";
import { PaperType, PaperSearchConditionType } from "../../types";
import { PaperTitle } from "../Atoms/PaperTitle";
import { ConferenceTag } from "../Atoms/ConferenceTag";
import { TaskTag } from "../Atoms/TaskTag";

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
  width: 80%;
`;

export const PaperTags = styled.div`
  display: flex;
  flex-basis: 100px;
`;

export const PaperAuthors = styled.div`
  display: flex;
  margin-bottom: 0.5rem;
  margin-left: 0.2rem;
`;

export const PaperAuthor = styled.div`
  margin-right: 1rem;
  a {
    color: #0f0f0f;
  }
`;

export const PaperLink = styled.div`
  padding: 3px 0;
  margin-left: auto;
  margin-right: 10px;
`;

type PaperPropsType = {
  paper: PaperType;
  getPapers?: (queryParam?: PaperSearchConditionType) => Promise<void>;
};

const Paper: React.FC<PaperPropsType> = props => {
  return (
    <PaperStyle>
      <PaperTitle>
        <Link to={`/paper/${props.paper.paperId}`}>{props.paper.title}</Link>
      </PaperTitle>
      {!!props.paper.authors && (
        <PaperAuthors>
          {props.paper.authors.map(author => (
            <PaperAuthor key={author.authorId}>
              <Link to={`/author/${author.authorId}`}>{author.name}</Link>
            </PaperAuthor>
          ))}
        </PaperAuthors>
      )}
      <PaperTags>
        <ConferenceTag onClick={() => props.getPapers({ conference: props.paper.conference, year: props.paper.year })}>
          {props.paper.conference}
          {props.paper.year}
        </ConferenceTag>
        {!!props.paper.task && (
          <TaskTag onClick={() => props.getPapers({ task: props.paper.task })}>{props.paper.task}</TaskTag>
        )}
        <PaperLink>
          <a href={props.paper.url}>pdf</a>
        </PaperLink>
      </PaperTags>
    </PaperStyle>
  );
};

export default Paper;

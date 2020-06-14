import React from "react";
import styled from "styled-components";
import { PaperType } from "../../types";
import { PaperTags, PaperConference, PaperTask, PaperLink, PaperAuthors, PaperAuthor } from "./Paper";
import { Link } from "react-router-dom";

const PaperPageStyle = styled.section`
  margin-right: auto;
  margin-left: auto;
  width: 60%;
`;

const PaperPageTitleStyle = styled.h1`
  font-weight: bold;
  font-size: large;
`;

const PaperPageAuthorsStyle = styled.section`
  display: flex;
`;

const PaperPageAuthorStyle = styled.div`
  margin: 1rem;
`;

const PaperPageIntroductionStyle = styled.div`
  padding: 1rem;
  margin: 0.5rem 0.2rem;
  border: solid;
  border-radius: 1rem;
  border-width: thin;
  word-wrap: break-word;
`;

const PaperDetail: React.FC<PaperType> = props => {
  console.log(props);
  console.log("aaa");
  return (
    <PaperPageStyle>
      {!!props.title && (
        <PaperPageTitleStyle>
          <a href={props.url}>{props.title}</a>
        </PaperPageTitleStyle>
      )}
      <PaperAuthors>
        {props.authors.map(author => (
          <PaperAuthor key={author.authorId}>
            <Link to={`/author/${author.authorId}`}>{author.name}</Link>
          </PaperAuthor>
        ))}
      </PaperAuthors>
      <PaperTags>
        <PaperConference>
          {props.conference}
          {props.year}
        </PaperConference>
        {!!props.task && <PaperTask>{props.task}</PaperTask>}
      </PaperTags>
      {!!props.introduction && <PaperPageIntroductionStyle>{props.introduction}</PaperPageIntroductionStyle>}
    </PaperPageStyle>
  );
};

export default PaperDetail;

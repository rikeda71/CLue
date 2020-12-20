import React, { useState, useEffect } from "react";
import { useParams } from "react-router";
import { PaperType } from "../../types";
import { API_URL, PAPER_ENDPOINT } from "../../constants";
import { FetchAPIService } from "../../api";
import { PaperTags, PaperConference, PaperTask, PaperAuthors, PaperAuthor } from "../Molecules/Paper";
import styled from "styled-components";
import { Link } from "react-router-dom";

const PaperDetail: React.FC = () => {
  const { id } = useParams<{ id: string }>();
  const [paper, setPaper] = useState<PaperType>();
  const fetchApiService = new FetchAPIService(API_URL, `${PAPER_ENDPOINT}/${id}`);

  useEffect(() => {
    async function getPaper() {
      fetchApiService
        .fetchAPI("GET")
        .then(res => {
          return res.json();
        })
        .then(res => setPaper(res));
    }
    getPaper();
  }, []);

  return <PaperDetailTemplate {...paper} />;
};

const PaperPageStyle = styled.section`
  margin-right: auto;
  margin-left: auto;
  width: 80%;
`;

const PaperPageTitleStyle = styled.h1`
  font-weight: bold;
  font-size: x-large;
  margin-bottom: 0.5rem;
`;

const PaperPageIntroductionStyle = styled.div`
  padding: 1rem;
  margin: 0.5rem 0.2rem;
  border: solid;
  border-radius: 1rem;
  border-width: thin;
  word-wrap: break-word;
  line-height: initial;
`;

export const PaperDetailTemplate: React.FC<PaperType> = props => {
  console.log(props);
  return (
    <PaperPageStyle>
      {!!props.title && (
        <PaperPageTitleStyle>
          <a href={props.url}>{props.title}</a>
        </PaperPageTitleStyle>
      )}
      <PaperAuthors>
        {!!props.authors &&
          props.authors.map(author => (
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

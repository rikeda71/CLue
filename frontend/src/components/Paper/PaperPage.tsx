import React, { useState, useEffect } from "react";
import styled from "styled-components";
import { PaperPageProps, PaperType } from "../../types";
import { createGetRequestUrl } from "../../utils";

const PaperPageStyle = styled.section``;

const PaperPageTitleStyle = styled.h1``;

const PaperPageAuthorsStyle = styled.section``;

const PaperPageAuthorStyle = styled.div``;

const PaperPageIntroductionStyle = styled.div``;

const PaperPage: React.FC<PaperPageProps> = props => {
  const [paper, setPaper] = useState<PaperType>();
  const requestUrl = createGetRequestUrl(`/api/v1/papers/${props.paperId}`);

  useEffect(() => {
    async function getPaper() {
      await fetch(requestUrl, {
        method: "GET",
        mode: "cors",
        headers: { "Content-Type": "application/json; charset=utf-8" },
      })
        .then(res => res.json())
        .then(res => {
          return res;
        })
        .then(res => setPaper(res));
    }
    getPaper();
  }, []);

  if (!paper) {
    return <div></div>;
  }

  return (
    <PaperPageStyle>
      <PaperPageTitleStyle>{paper.title}</PaperPageTitleStyle>
      <PaperPageAuthorsStyle>
        {paper.authors.map(author => {
          return <PaperPageAuthorStyle>{author.name}</PaperPageAuthorStyle>;
        })}
      </PaperPageAuthorsStyle>
      <PaperPageIntroductionStyle>{paper.introduction}</PaperPageIntroductionStyle>
    </PaperPageStyle>
  );
};

export default PaperPage;

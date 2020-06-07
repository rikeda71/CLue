import React, { useState, useEffect } from "react";
import styled from "styled-components";
import { PapersType, PaperPageProps } from "../../types";
import Papers from "./Papers";
import { createGetRequestUrl } from "../../utils";

const PaperPageStyle = styled.div``;

const PaperPage: React.FC<PaperPageProps> = props => {
  const [papers, setPapers] = useState<PapersType>({ papers: [] });
  const requestUrl = createGetRequestUrl("/api/v1/papers", props.queryParams);
  console.log(requestUrl);
  if (props.papers != null) {
    setPapers({ papers: props.papers });
  }

  useEffect(() => {
    async function getPapers() {
      await fetch(requestUrl, {
        method: "GET",
        mode: "cors",
        headers: { "Content-Type": "application/json; charset=utf-8" },
      })
        .then(res => res.json())
        .then(res => {
          return res;
        })
        .then(res => setPapers({ papers: res }));
    }
    // if (papers != { papers: [] }) {
    getPapers();
    // }
  }, []);

  return (
    <PaperPageStyle>
      <Papers {...papers} />
    </PaperPageStyle>
  );
};

export default PaperPage;

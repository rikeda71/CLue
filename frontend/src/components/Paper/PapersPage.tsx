import React, { useState, useEffect } from "react";
import styled from "styled-components";
import { PapersType, PapersPageProps } from "../../types";
import Papers from "./Papers";
import { createGetRequestUrl } from "../../utils";

const PapersPageStyle = styled.div``;

const PapersPage: React.FC<PapersPageProps> = props => {
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
    <PapersPageStyle>
      <Papers {...papers} />
    </PapersPageStyle>
  );
};

export default PapersPage;

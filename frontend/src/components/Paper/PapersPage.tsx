import React, { useState, useEffect } from "react";
import styled from "styled-components";
import { PapersType, PapersPageProps, PaperSearchConditionType } from "../../types";
import Papers from "./Papers";
import SearchBox from "../SearchBox/SearchBox";
import { createGetRequestUrl } from "../../utils";

const PapersPageStyle = styled.div``;

const PapersPage: React.FC<PapersPageProps> = props => {
  const [papers, setPapers] = useState<PapersType>({ papers: [] });

  async function getPapers(queryParam?: PaperSearchConditionType) {
    const requestUrl = createGetRequestUrl("/api/v1/papers", queryParam);
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

  if (props.papers != null) {
    setPapers({ papers: props.papers });
  }

  useEffect(() => {
    getPapers();
  }, []);

  return (
    <PapersPageStyle>
      <SearchBox onButtonClickFunction={getPapers} />
      <Papers {...papers} />
    </PapersPageStyle>
  );
};

export default PapersPage;

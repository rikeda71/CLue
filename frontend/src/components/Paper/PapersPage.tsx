import React, { useState, useEffect } from "react";
import styled from "styled-components";
import { PapersType, PapersPageProps, PaperSearchConditionType, PaperType } from "../../types";
import Papers from "./Papers";
import SearchBox from "../Search/SearchBox";
import { createGetRequestUrl } from "../../utils";
import SearchModal from "../Search/SearchModal";
import MainButton from "../MainButton";

const PapersPageStyle = styled.div``;

const DetailSearchButtonDiv = styled.div`
  margin: 10px 0;
  text-align: center;
`;

const PapersPage: React.FC<PapersPageProps> = props => {
  const [papers, setPapers] = useState<Array<PaperType>>([]);
  const [modalIsOpen, setModalIsOpen] = useState(false);

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
      .then(res => setPapers(res));
  }

  if (props.papers != null) {
    setPapers(props.papers);
  }

  useEffect(() => {
    getPapers();
  }, []);

  return (
    <PapersPageStyle>
      <SearchBox onButtonClickFunction={getPapers} />
      <DetailSearchButtonDiv>
        <MainButton onClick={e => setModalIsOpen(true)}>詳細検索</MainButton>
      </DetailSearchButtonDiv>
      <SearchModal isOpen={modalIsOpen} onRequestClose={() => setModalIsOpen(false)} setPapers={setPapers} />
      <Papers papers={papers} getPapers={getPapers} />
    </PapersPageStyle>
  );
};

export default PapersPage;

import React, { useState, useEffect } from "react";
import styled from "styled-components";
import { PaperSearchConditionType, PaperType } from "../../types";
import Papers from "../Organisms/Papers";
import SearchBox from "../Molecules/SearchBox";
import SearchModal from "../Organisms/SearchModal";
import MainButton from "../Atoms/MainButton";
import { FetchPaperAPIService } from "../../api";
import { API_URL } from "../../constants";

const PapersPageStyle = styled.div``;

const DetailSearchButtonDiv = styled.div`
  margin: 10px 0;
  text-align: center;
`;
export type PapersPageProps = {
  papers?: Array<PaperType>;
  queryParams?: PaperSearchConditionType;
};

const PapersPage: React.FC<PapersPageProps> = props => {
  const [papers, setPapers] = useState<Array<PaperType>>([]);
  const [modalIsOpen, setModalIsOpen] = useState(false);
  const [isLoading, setIsLoading] = useState(true);
  const fetchPaperApiService = new FetchPaperAPIService();

  async function getPapers(queryParam?: PaperSearchConditionType) {
    await fetchPaperApiService
      .fetchPaperAPI("GET", {}, queryParam)
      .then(res => {
        return res.json();
      })
      .then(res => setPapers(res));
    setIsLoading(false);
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
        <MainButton onClick={() => setModalIsOpen(true)}>詳細検索</MainButton>
      </DetailSearchButtonDiv>
      <SearchModal
        isOpen={modalIsOpen}
        onRequestClose={() => {
          setModalIsOpen(false);
        }}
        setPapers={setPapers}
        setIsLoading={setIsLoading}
      />
      <Papers papers={papers} getPapers={getPapers} isLoading={isLoading} />
    </PapersPageStyle>
  );
};

export default PapersPage;

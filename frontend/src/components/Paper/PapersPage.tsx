import React, { useState, useEffect } from "react";
import styled from "styled-components";
import { PaperSearchConditionType, PaperType } from "../../types";
import Papers from "./Papers";
import SearchBox from "../Search/SearchBox";
import { getAuthTokenFromCookie, getUrlParameter, mapToObject } from "../../utils";
import SearchModal from "../Search/SearchModal";
import MainButton from "../MainButton";
import { OAUTH_TOKEN_KEY } from "../../constants";
import { FetchPaperAPIService } from "../../api";

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

  const jwtToken = getUrlParameter("token");
  if (jwtToken !== "") {
    localStorage.setItem(OAUTH_TOKEN_KEY, jwtToken);
  } else {
    const authToken = getAuthTokenFromCookie();
    if (authToken !== "") {
      localStorage.setItem(OAUTH_TOKEN_KEY, authToken);
    }
  }

  const mapHeaders = new Map<string, string>();
  mapHeaders.set("Content-Type", "application/json; charset=utf-8");
  if (!!localStorage.getItem(OAUTH_TOKEN_KEY)) {
    mapHeaders.set("Authorization", "Bearer " + localStorage.getItem(OAUTH_TOKEN_KEY));
  }
  const headers = mapToObject(mapHeaders);

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
        <MainButton onClick={e => setModalIsOpen(true)}>詳細検索</MainButton>
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

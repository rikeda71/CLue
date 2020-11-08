import React, { useState, useEffect } from "react";
import styled from "styled-components";
import { PaperSearchConditionType, PaperType } from "../../types";
import Papers from "./Papers";
import SearchBox from "../Search/SearchBox";
import { createGetRequestUrl, getAuthTokenFromCookie, mapToObject } from "../../utils";
import SearchModal from "../Search/SearchModal";
import MainButton from "../MainButton";
import { authTokenKey } from "../../config";

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

  const authToken = getAuthTokenFromCookie();
  if (authToken !== "") {
    localStorage.setItem(authTokenKey, authToken);
  }

  const mapHeaders = new Map<string, string>();
  mapHeaders.set("Content-Type", "application/json; charset=utf-8");
  if (!!localStorage.getItem(authTokenKey)) {
    mapHeaders.set("Authorization", "Bearer " + localStorage.getItem(authTokenKey));
  }
  const headers = mapToObject(mapHeaders);

  async function getPapers(queryParam?: PaperSearchConditionType) {
    const requestUrl = createGetRequestUrl("/api/v1/papers", queryParam);
    await fetch(requestUrl, {
      method: "GET",
      mode: "cors",
      headers: headers,
    })
      .then(res => res.json())
      .then(res => {
        return res;
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

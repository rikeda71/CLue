import React, { useState, useEffect } from "react";
import { PaperType } from "../../types";
import { useParams } from "react-router";
import PaperDetail from "./PaperDetail";
import { API_URL, PAPER_ENDPOINT } from "../../constants";
import { FetchAPIService } from "../../api";

const PaperPage: React.FC = () => {
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

  return <PaperDetail {...paper} />;
};

export default PaperPage;

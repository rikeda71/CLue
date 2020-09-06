import React, { useState, useEffect } from "react";
import { PaperType } from "../../types";
import { createGetRequestUrl } from "../../utils";
import { useParams } from "react-router";
import PaperDetail from "./PaperDetail";

const PaperPage: React.FC = () => {
  const { id } = useParams();
  const [paper, setPaper] = useState<PaperType>();
  const requestUrl = createGetRequestUrl(`/api/v1/papers/${id}`);

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

  return <PaperDetail {...paper} />;
};

export default PaperPage;

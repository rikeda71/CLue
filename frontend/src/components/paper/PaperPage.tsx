import React, { useState, useEffect } from "react";
import styled from "styled-components";
import { PapersType, PaperPageProps } from "../../types";
import Papers from "./Papers";
import { apiUrl } from "../../config";

const PaperPageStyle = styled.div``;

const PaperPage: React.FC<PaperPageProps> = props => {
  const [papers, setPapers] = useState<PapersType>({ papers: [] });
  if (props.papers != null) {
    setPapers({ papers: props.papers });
  }

  useEffect(() => {
    async function getPapers() {
      await fetch(apiUrl + "/api/v1/papers", {
        method: "GET",
        mode: "cors",
        headers: { "Content-Type": "application/json; charset=utf-8" },
      })
        .then(res => res.json())
        .then(res => {
          console.log(res);
          return res;
        })
        .then(res => setPapers({ papers: res }));
    }
    getPapers();
  }, []);

  return (
    <PaperPageStyle>
      <Papers {...papers} />
    </PaperPageStyle>
  );
};

export default PaperPage;

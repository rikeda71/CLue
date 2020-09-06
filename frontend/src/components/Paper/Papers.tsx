import React from "react";
import styled from "styled-components";
import Paper from "./Paper";
import { PaperType, PaperSearchConditionType } from "../../types";

export const PapersStyle = styled.div`
  overflow-x: hidden;
  overflow-y: scroll;
`;

type PapersPropsType = {
  papers: Array<PaperType>;
  getPapers: (queryParam?: PaperSearchConditionType) => Promise<void>;
};

const Papers: React.FC<PapersPropsType> = props => {
  return (
    <PapersStyle>
      {props.papers.length > 0 &&
        props.papers.map(paper => {
          return <Paper paper={paper} getPapers={props.getPapers} key={paper.paperId} />;
        })}
    </PapersStyle>
  );
};

export default Papers;

import React from "react";
import styled from "styled-components";
import Paper from "../Molecules/Paper";
import Loading from "../Atoms/Loading";
import { PaperType, PaperSearchConditionType } from "../../types";

export const PapersStyle = styled.div`
  overflow-x: hidden;
  overflow-y: scroll;
`;

type PapersPropsType = {
  papers: Array<PaperType>;
  getPapers?: (queryParam?: PaperSearchConditionType) => Promise<void>;
  isLoading: boolean;
};

const Papers: React.FC<PapersPropsType> = props => {
  return (
    <PapersStyle>
      {props.papers.length > 0 &&
        props.papers.map(paper => {
          return <Paper paper={paper} getPapers={props.getPapers} key={paper.paperId} />;
        })}
      <Loading isShown={props.isLoading} />
    </PapersStyle>
  );
};

export default Papers;

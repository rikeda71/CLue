import React from "react";
import styled from "styled-components";
import Paper from "./Paper";
import { PapersType } from "../../types";

export const PapersStyle = styled.div`
  overflow-x: hidden;
  overflow-y: scroll;
`;

const Papers: React.FC<PapersType> = props => {
  return (
    <PapersStyle>
      {props.papers.map(paper => {
        return <Paper {...paper} />;
      })}
    </PapersStyle>
  );
};

export default Papers;

import React from "react";
import styled from "styled-components";
import Paper from "./Paper";
import { PapersProps } from "../types";

export const PapersStyle = styled.div`
  overflow-x: hidden;
  overflow-y: scroll;
`;

const Papers: React.FC<PapersProps> = props => {
  return (
    <PapersStyle>
      {props.papers.map(paper => {
        return <Paper {...paper} />;
      })}
    </PapersStyle>
  );
};

export default Papers;

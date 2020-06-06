import React from "react";
import styled from "styled-components";
import { PaperProps } from "../types";

export const PaperStyle = styled.div`
  border: solid;
  border-radius: 1rem;
  border-width: thin;
  margin-top: 1rem;
  padding-top: 0.5rem;
  padding-bottom: 0.5rem;
  padding-left: 1rem;
  padding-right: 1rem;
`;

export const Title = styled.h1`
  font-size: large;
  display: inline-block;
`;

export const Tags = styled.div`
  display: flex;
  flex-basis: 100px;
`;

export const Conference = styled.div``;

export const Task = styled.div`
  margin-left: 1rem;
`;

const Paper: React.FC<PaperProps> = props => {
  return (
    <PaperStyle>
      <Title>
        <a href={props.url}>{props.title}</a>
      </Title>
      <Tags>
        <Conference>
          {props.conference}
          {props.year}
        </Conference>
        {props.task != undefined && <Task>{props.task}</Task>}
      </Tags>
    </PaperStyle>
  );
};

export default Paper;

import React from "react";
import styled from "styled-components";
import { Link } from "react-router-dom";
import SearchBox from "../SearchBox/SearchBox";

export const HeaderStyle = styled.header`
  padding: 1.2rem 0 1.2rem 3rem;
  background-color: #f1f1f1;
  display: flex;
`;

export const Title = styled.h1`
  font-weight: bold;
  font-family: serif;
  font-size: xx-large;
  a {
    color: black;
    text-decoration: none;
  }
`;

const Header: React.FC = () => {
  return (
    <HeaderStyle>
      <Title>
        <Link to={"/"}>CLue</Link>
      </Title>
    </HeaderStyle>
  );
};

export default Header;

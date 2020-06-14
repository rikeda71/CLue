import React from "react";
import styled from "styled-components";
import { Link } from "react-router-dom";

export const HeaderStyle = styled.header`
  margin: 1rem 0 3rem 3rem;
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

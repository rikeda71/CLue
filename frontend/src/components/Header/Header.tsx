import React from "react";
import styled from "styled-components";

export const HeaderStyle = styled.header`
  margin: 1rem 0 3rem 3rem;
`;

export const Title = styled.h1`
  font-weight: bold;
  font-family: serif;
  font-size: xx-large;
`;

const Header: React.FC = () => {
  return (
    <HeaderStyle>
      <Title>CLue</Title>
    </HeaderStyle>
  );
};

export default Header;

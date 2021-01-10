import React from "react";
import styled from "styled-components";
import { Link } from "react-router-dom";
import LoginButton from "../Molecules/LoginButton";
import UserBadge from "../Atoms/UserBadge";
import LogoutButton from "../Molecules/LogoutButton";

export const HeaderStyle = styled.header`
  padding: 2rem 0 1.2rem 5rem;
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

interface HeaderType {
  isLogined: boolean;
  email?: string;
}

const Header: React.FC<HeaderType> = props => {
  return (
    <HeaderStyle>
      <Title>
        <Link to={"/"}>CLue</Link>
      </Title>
      {props.isLogined ? (
        <React.Fragment>
          {!!props.email && <UserBadge email={props.email} />}
          <LogoutButton />
        </React.Fragment>
      ) : (
        <LoginButton />
      )}
    </HeaderStyle>
  );
};

export default Header;

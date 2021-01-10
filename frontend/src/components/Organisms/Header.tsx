import React, { useState } from "react";
import styled from "styled-components";
import { Link } from "react-router-dom";
import LoginButton from "../Molecules/LoginButton";
import { OAuthFetchAPIService } from "../../api";
import { API_URL, USER_ENDPOINT } from "../../constants";
import { UserType } from "../../types";
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

const Header: React.FC = () => {
  const [email, setEmail] = useState<string>("");
  const userFetchService = new OAuthFetchAPIService(API_URL, USER_ENDPOINT);
  userFetchService.updateJwtToken();
  const isLoggined = userFetchService.isLoggined();
  if (isLoggined) {
    const getLogginedUser = async () => {
      await userFetchService
        .fetchAPIWithAuth()
        .then(res => {
          return res.json();
        })
        .then((res: UserType) => setEmail(res.email));
    };
    getLogginedUser();
  }
  return (
    <HeaderStyle>
      <Title>
        <Link to={"/"}>CLue</Link>
      </Title>
      {isLoggined ? (
        <React.Fragment>
          <UserBadge email={email} />
          <LogoutButton />
        </React.Fragment>
      ) : (
        <LoginButton />
      )}
    </HeaderStyle>
  );
};

export default Header;

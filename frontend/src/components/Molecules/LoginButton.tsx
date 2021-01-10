import React from "react";
import styled from "styled-components";
import { API_URL, OAUTH_ENDPOINT } from "../../constants";
import MainButtonStyle from "../Atoms/MainButton";

const LoginBadge = styled(MainButtonStyle)`
  margin: 0 0 0 auto;
`;

const LoginButton: React.FC = () => {
  const handleClick = () => {
    const oauthUrl = API_URL + OAUTH_ENDPOINT;
    window.open(oauthUrl, "_self");
  };
  return <LoginBadge onClick={handleClick}>ログイン</LoginBadge>;
};

export default LoginButton;

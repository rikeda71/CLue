import React from "react";
import styled from "styled-components";
import { OAUTH_TOKEN_KEY } from "../../constants";
import MainButtonStyle from "../Atoms/MainButton";

const LogoutButtonStyle = styled(MainButtonStyle)``;

const LogoutButton: React.FC = () => {
  const handleClick = () => {
    document.cookie = `${OAUTH_TOKEN_KEY}=; expires=0;`;
    window.open("http://localhost:3000", "_self");
  };
  return <LogoutButtonStyle onClick={handleClick}>ログアウト</LogoutButtonStyle>;
};

export default LogoutButton;

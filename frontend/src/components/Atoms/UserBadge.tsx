import React from "react";
import styled from "styled-components";
import MainButtonStyle from "./MainButton";

const UserBadgeDiv = styled(MainButtonStyle)`
  margin-left: auto;
  margin-right: 10px;
`;

interface IUserBadge {
  email: string;
}

const UserBadge: React.FC<IUserBadge> = props => {
  const userName = props.email.substring(0, props.email.indexOf("@"));
  return <UserBadgeDiv>{userName}</UserBadgeDiv>;
};

export default UserBadge;

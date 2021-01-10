import React from "react";
import styled from "styled-components";
import Modal from "react-modal";
import LogoutButton from "./LogoutButton";

interface UserModalType {
  isOpen: boolean;
  onRequestClose: () => void;
}

const UserModal: React.FC<UserModalType> = props => {
  return (
    <Modal isOpen={props.isOpen} onRequestClose={props.onRequestClose}>
      <LogoutButton />
    </Modal>
  );
};

export default UserModal;

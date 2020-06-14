import React from "react";
import styled from "styled-components";

const FooterStyle = styled.footer`
  margin: 3rem 0;
  text-align: center;
`;

const Footer: React.FC = () => {
  return (
    <FooterStyle>
      <p>© {new Date().getFullYear()} Ryuya Ikeda. All Rights Reserved.</p>
    </FooterStyle>
  );
};

export default Footer;

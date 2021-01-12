import React, { useState } from "react";
import styled from "styled-components";

const PostPaperPageStyle = styled.div``;

const YearForm = styled.input``;

const TitleForm = styled.input``;

const IntroForm = styled.input``;

const LabelForm = styled.select``;

const LangForm = styled.select``;

const ConfForm = styled.input``;

const AuthorForm = styled.input``;

// 論文情報を追加するページ
const PostPaperPage: React.FC = () => {
  const [year, setYear] = useState(0);
  const [title, setTitle] = useState("");
  const [intro, setIntro] = useState("");
  const [label, setLabel] = useState("");
  const [lang, setLang] = useState("");
  const [conf, setConf] = useState("");
  const [author, setAuthor] = useState([""]);

  const handleYearFormChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setYear(Number.parseInt(event.target.value));
  };

  const handleTitleFormChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setTitle(event.target.value);
  };

  const handleIntroFormChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setIntro(event.target.value);
  };

  const handleAuthorFormChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setAuthor([event.target.value]);
  };

  return (
    <PostPaperPageStyle>
      <YearForm onChange={handleYearFormChange} />
      <TitleForm onChange={handleTitleFormChange} />
      <IntroForm onChange={handleIntroFormChange} />
      <AuthorForm onChange={handleAuthorFormChange} />
    </PostPaperPageStyle>
  );
};

export default PostPaperPage;

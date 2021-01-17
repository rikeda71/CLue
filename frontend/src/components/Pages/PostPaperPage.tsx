import React, { useState } from "react";
import styled from "styled-components";
import { OAuthFetchAPIService } from "../../api";
import { API_URL, PAPER_ENDPOINT } from "../../constants";
import MainButtonStyle from "../Atoms/MainButton";

const PostPaperPageStyle = styled.div`
  display: block;
`;

const YearForm = styled.input`
  margin: 0 auto;
`;

const TitleForm = styled.input``;

const IntroForm = styled.input``;

const LabelForm = styled.select``;

const LangForm = styled.select``;

const ConfForm = styled.input``;

const AuthorForm = styled.input``;

const UrlForm = styled.input``;

const PostButton = styled(MainButtonStyle)``;

// 論文情報を追加するページ
const PostPaperPage: React.FC = () => {
  const [year, setYear] = useState(0);
  const [title, setTitle] = useState("");
  const [intro, setIntro] = useState("");
  const [label, setLabel] = useState("");
  const [lang, setLang] = useState("");
  const [conf, setConf] = useState("");
  const [authors, setAuthors] = useState([""]);
  const [url, setUrl] = useState("");

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
    if (authors.length > 0) {
      authors[0] = event.target.value;
    } else {
      authors.push(event.target.value);
    }
    setAuthors([...authors]);
  };

  const handleUrlFormChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setUrl(event.target.value);
  };

  const handleClick = () => {
    const oauthFetchService = new OAuthFetchAPIService(API_URL, PAPER_ENDPOINT);
    const body = {
      year: year,
      title: title,
      introduction: intro,
      authorNames: ["name"], // TODO: 可変にする
      url: "https://google.com", // TODO: 可変にする
    };
    oauthFetchService.fetchPostWithAuth(body).then(res => {
      console.log(res);
    });
  };

  return (
    <PostPaperPageStyle>
      <tbody>
        <tr>
          <td>年</td>
          <td>
            <YearForm onChange={handleYearFormChange} />
          </td>
        </tr>
        <tr>
          <td>タイトル</td>
          <td>
            <TitleForm onChange={handleTitleFormChange} />
          </td>
        </tr>
        <tr>
          <td>イントロダクション</td>
          <td>
            <IntroForm onChange={handleIntroFormChange} />
          </td>
        </tr>
        <tr>
          <td>著者</td>
          <td>
            <AuthorForm onChange={handleAuthorFormChange} />
          </td>
        </tr>
        <tr>
          <td>URL</td>
          <td>
            <UrlForm onChange={handleUrlFormChange} />
          </td>
        </tr>
      </tbody>
      <PostButton onClick={handleClick} />
    </PostPaperPageStyle>
  );
};

export default PostPaperPage;

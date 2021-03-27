import React, { useState } from "react";
import styled from "styled-components";
import { OAuthFetchAPIService } from "../../api";
import { API_URL, PAPER_ENDPOINT } from "../../constants";
import MainButtonStyle from "../Atoms/MainButton";

const PostPaperPageStyle = styled.div`
  display: block;
`;

const ButtonsDiv = styled.div`
  margin: 10px 0;
  text-align: center;
  display: flex;
`;

const YearForm = styled.input`
  margin: 0 auto;
`;

const LabelForm = styled.select``;

const TaskForm = styled.select``;

const TitleForm = styled.input``;

const IntroForm = styled.input``;

const LangForm = styled.select``;

const ConfForm = styled.input``;

const AuthorForm = styled.input``;

const UrlForm = styled.input``;

const PostButton = styled(MainButtonStyle)``;

// 論文情報を追加するページ
const PostPaperPage: React.FC = () => {
  const [year, setYear] = useState(0);
  const [label, setLabel] = useState("nanzoya");
  const [task, setTask] = useState("");
  const [title, setTitle] = useState("");
  const [intro, setIntro] = useState("");
  const [lang, setLang] = useState("");
  const [conf, setConf] = useState("");
  const [authors, setAuthors] = useState([""]);
  const [url, setUrl] = useState("");

  const handleYearFormChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setYear(Number.parseInt(event.target.value));
  };

  const handleLabelFormSelect = (event: React.ChangeEvent<HTMLSelectElement>) => {
    setLabel(event.target.value);
  };
  
  const handleTaskFormSelect = (event: React.ChangeEvent<HTMLInputElement>) => {
    setTask(event.target.value);
  };

  const handleTitleFormChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setTitle(event.target.value);
  };

  const handleIntroFormChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setIntro(event.target.value);
  };

  const handleLangFormSelect = (event: React.ChangeEvent<HTMLInputElement>) => {
    setLabel(event.target.value);
  };

  const handleConfFormChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setLabel(event.target.value);
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
      <ButtonsDiv>
        <tbody>
        <tr>
            <td>年</td>
            <td>
              <YearForm onChange={handleYearFormChange} />
            </td>
          </tr>
          <tr>
            <td>ラベル</td>
            <td>
              <select name="label" onChange={handleLabelFormSelect}>
                <option value={2019}>
                  2019
                </option>
              </select>
              {/* <LabelForm onChange={handleLabelFormChange} /> */}
            </td>
          </tr>
          <tr>
            <td>タスク</td>
            <td>
              <TaskForm onChange={handleTaskFormChange} />
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
      </ButtonsDiv>
      <PostButton onClick={handleClick}>追加</PostButton>
    </PostPaperPageStyle>
  );
};

export default PostPaperPage;

import React, { useState } from "react";
import Modal from "react-modal";
import styled from "styled-components";
import MainButton from "../MainButton";
import { createGetRequestUrl } from "../../utils";
import { PaperSearchConditionType } from "../../types";

const ModalTableStyle = styled.table`
  border-collapse: collapse;
  border-spacing: 10;
`;

const TitleTd = styled.td`
  padding-right: 10px;
  padding-bottom: 15px;
`;

const DetailSearchButtonDiv = styled.div`
  margin: 10px 0;
  text-align: center;
`;

const customModalStyle = {
  content: {
    top: "50%",
    left: "50%",
    right: "auto",
    bottom: "auto",
    marginRight: "-50%",
    transform: "translate(-50%, -50%)",
  },
};

type SearchModalPropsType = {
  isOpen: boolean;
  onRequestClose: () => void;
  setPapers: (papers: PapersType) => void;
};

const getYearsFrom2014 = (): number[] => {
  const today = new Date();
  const years: (number | null)[] = [null];
  for (let y = 2014; y <= today.getFullYear(); y++) {
    years.push(y);
  }
  return years;
};

const getConferenceList = (): (string | null)[] => {
  // TODO: APIから取得する
  return [null, "NLP", "ACL", "NAACL", "EMNLP"];
};

const getTaskList = (): (string | null)[] => {
  // TODO: APIから取得する
  return [null, "Sentiment Analysis", "Machine Translation"];
};

Modal.setAppElement("#root");
const SearchModal: React.FC<SearchModalPropsType> = props => {
  const [year, setYear] = useState<number | null>(null);
  const [conference, setConference] = useState<string | null>(null);
  const [label, setLabel] = useState<string | null>(null);
  const [task, setTask] = useState<string | null>(null);
  const [title, setTitle] = useState<string>("");
  const [intro, setIntro] = useState<string | null>(null);

  const searchPapers = () => {
    const queryParam: PaperSearchConditionType = {
      year: year,
      label: label,
      task: task,
      conference: conference,
      title: title,
      introduction: intro,
    };
    getpapers(queryParam);
    props.onRequestClose();
  };

  async function getpapers(queryParam: PaperSearchConditionType) {
    const requestUrl = createGetRequestUrl("/api/v1/papers", queryParam);
    await fetch(requestUrl, {
      method: "GET",
      mode: "cors",
      headers: { "Content-Type": "application/json; charset=utf-8" },
    })
      .then(res => res.json())
      .then(res => {
        return res;
      })
      .then(res => props.setPapers({ papers: res }));
  }

  const onSelectYear = (e: React.ChangeEvent<HTMLSelectElement>) => {
    setYear(parseInt(e.target.value));
  };

  const onSelectConference = (e: React.ChangeEvent<HTMLSelectElement>) => {
    setConference(e.target.value);
  };

  const onSelectTask = (e: React.ChangeEvent<HTMLSelectElement>) => {
    setTask(e.target.value);
  };

  const onSetTitle = (e: React.ChangeEvent<HTMLInputElement>) => {
    setTitle(e.target.value);
  };

  const onIntroChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setIntro(e.target.value);
  };

  return (
    <Modal isOpen={props.isOpen} onRequestClose={props.onRequestClose} style={customModalStyle}>
      <ModalTableStyle>
        <tbody>
          <tr>
            <TitleTd>公開年</TitleTd>
            <td>
              <select name="year" onChange={onSelectYear}>
                {getYearsFrom2014().map((year, i) => {
                  return (
                    <option value={year} key={i}>
                      {year}
                    </option>
                  );
                })}
              </select>
            </td>
          </tr>
          <tr>
            <TitleTd>Conference</TitleTd>
            <td>
              <select name="conference" onChange={onSelectConference}>
                {getConferenceList().map((conf, i) => {
                  return (
                    <option value={conf} key={i}>
                      {conf}
                    </option>
                  );
                })}
              </select>
            </td>
          </tr>
          <tr>
            <TitleTd>タスク名</TitleTd>
            <td>
              <select name="task" onChange={onSelectTask}>
                {getTaskList().map((task, i) => {
                  return (
                    <option value={task} key={i}>
                      {task}
                    </option>
                  );
                })}
              </select>
            </td>
          </tr>
          <tr>
            <TitleTd>論文タイトル</TitleTd>
            <td>
              <input onChange={onSetTitle} />
            </td>
          </tr>
          <tr>
            <TitleTd>アブストに含まれる単語</TitleTd>
            <td>
              <input onChange={onIntroChange} />
            </td>
          </tr>
        </tbody>
      </ModalTableStyle>
      <DetailSearchButtonDiv>
        <MainButton onClick={searchPapers}>検索</MainButton>
      </DetailSearchButtonDiv>
    </Modal>
  );
};

export default SearchModal;

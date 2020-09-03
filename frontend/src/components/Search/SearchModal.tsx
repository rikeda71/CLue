import React, { useState } from "react";
import Modal from "react-modal";
import styled from "styled-components";
import SearchBox from "./SearchBox";

const SearchModalStyle = styled.div``;

type SearchModalPropsType = {
  isOpen: boolean;
};

const getYearsFrom2014 = (): number[] => {
  const today = new Date();
  const years: number[] = [];
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

const SearchModal: React.FC<SearchModalPropsType> = props => {
  const [year, setYear] = useState<number | null>(null);
  const [conference, setConference] = useState<string | null>(null);
  const [label, setLabel] = useState<string | null>(null);
  const [task, setTask] = useState<string | null>(null);
  const [title, setTitle] = useState<string>("");
  const [intro, setIntro] = useState<string | null>(null);

  const searchPapers = () => {
    console.log("search");
  };

  const onSelectYear = (e: React.ChangeEvent<HTMLSelectElement>) => {
    setYear(parseInt(e.target.value));
  };

  return (
    <Modal isOpen={props.isOpen}>
      <table>
        <tbody>
          <tr>
            <td>年</td>
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
            <td>会議</td>
            <td>
              <select name="conference">
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
            <td>タスク</td>
            <td>
              <select name="task">
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
            <td>タイトル</td>
            <td>
              <input />
            </td>
          </tr>
          <tr>
            <td>introduction / abstract</td>
            <td>
              <input />
            </td>
          </tr>
        </tbody>
      </table>
    </Modal>
  );
};

export default SearchModal;

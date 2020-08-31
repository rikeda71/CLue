import React, { useState } from "react";
import styled from "styled-components";
import { PaperSearchConditionType } from "../../types";

const MainColor = "#3879D9";

const SearchBoxStyle = styled.div`
  position: relative;
  box-sizing: border-box;
  border: 0.1px solid ${MainColor};
  padding: 6px 15px;
  border-radius: 20px;
  height: 2.5em;
  width: 25rem;
  overflow: hidden;
  :-webkit-input-placeholder {
    color: ${MainColor};
  }
  margin: 5px auto;
`;

const FormStyle = styled.input`
  border: none;
  height: 1.8em;
  width: 230px;
  :focus {
    outline: 0;
  }
  background-color: inherit;
`;

const SearchButtonStyle = styled.input`
  cursor: pointer;
  font-family: FontAwesome;
  border: none;
  background: #3879d9;
  color: #fff;
  position: absolute;
  width: 4em;
  height: 3.6em;
  right: 0px;
  top: -5px;
  outline: none;
`;

type SearchBoxPropsType = {
  onButtonClickFunction: Function;
};

const SearchBox: React.FC<SearchBoxPropsType> = props => {
  const [query, setQuery] = useState("");

  const onFormChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setQuery(e.target.value);
  };

  const onButtonClick = (e: React.MouseEvent<HTMLButtonElement, MouseEvent>) => {
    const queryParam: PaperSearchConditionType = { title: query };
    props.onButtonClickFunction(queryParam);
  };

  return (
    <SearchBoxStyle>
      <FormStyle onChange={onFormChange} placeholder="キーワード"></FormStyle>
      <SearchButtonStyle onClick={onButtonClick} type="submit" value="検索" />
    </SearchBoxStyle>
  );
};

export default SearchBox;

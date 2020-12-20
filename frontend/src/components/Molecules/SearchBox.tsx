import React, { useState } from "react";
import styled from "styled-components";
import { PaperSearchConditionType } from "../../types";
import { SearchForm } from "../Atoms/SearchForm";
import { SearchButton } from "../Atoms/SearchButton";

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

type SearchBoxPropsType = {
  onButtonClickFunction: Function;
  placeholder?: string;
};

const SearchBox: React.FC<SearchBoxPropsType> = props => {
  const [query, setQuery] = useState("");
  const placeholder = !!props.placeholder ? props.placeholder : "キーワード";

  const onFormChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setQuery(e.target.value);
  };

  const onButtonClick = (e: React.MouseEvent<HTMLButtonElement, MouseEvent>) => {
    const queryParam: PaperSearchConditionType = { title: query };
    props.onButtonClickFunction(queryParam);
  };

  const onKeyPress = (e: React.KeyboardEvent<HTMLInputElement>) => {
    if (e.which === 13) {
      const queryParam: PaperSearchConditionType = { title: query };
      props.onButtonClickFunction(queryParam);
    }
  };

  return (
    <SearchBoxStyle>
      <SearchForm onChange={onFormChange} onKeyPress={onKeyPress} placeholder={placeholder}></SearchForm>
      <SearchButton onClick={onButtonClick} type="submit" value="検索" />
    </SearchBoxStyle>
  );
};

export default SearchBox;

import React, { useState } from "react";
import styled from "styled-components";
import { PaperSearchConditionType } from "../../types";

const SearchBoxStyle = styled.div`
  display: flex;
  margin: 10px auto;
  justify-content: center;
`;

const FormStyle = styled.input`
  display: block;
`;

const SearchButtonStyle = styled.button`
  display: block;
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
      <FormStyle onChange={onFormChange}></FormStyle>
      <SearchButtonStyle onClick={onButtonClick}>検索</SearchButtonStyle>
    </SearchBoxStyle>
  );
};

export default SearchBox;

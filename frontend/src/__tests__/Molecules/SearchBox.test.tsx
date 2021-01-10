import React from "react";
import SearchBox from "../../components/Molecules/SearchBox";
import { fireEvent, render, screen } from "@testing-library/react";
import { PaperSearchConditionType } from "../../types";

jest.spyOn(console, "log");

describe("render test", () => {
  const placeholderText = "placeholder";
  const buttonClickFunc = (param: PaperSearchConditionType) => {
    console.log(param);
  };
  it("looks test", () => {
    render(<SearchBox placeholder={placeholderText} onButtonClickFunction={buttonClickFunc} />);

    // ボタン
    const searchBox = screen.getByText(/検索/);
    expect(searchBox).toBeInTheDocument();
    // 検索ボックス
    // placeholder が反映されているか
    const placeholder = screen.getByPlaceholderText(placeholderText);
    expect(placeholder).toBeInTheDocument();
  });
  it("role test", () => {
    render(<SearchBox placeholder={placeholderText} onButtonClickFunction={buttonClickFunc} />);
    // ボタンがクリックできるか
    const searchBox = screen.getByText(/検索/);
    fireEvent.click(searchBox);
    expect(console.log).toBeCalledTimes(1);
    // フォームに入力できるか
    const textForm = screen.getByPlaceholderText(placeholderText);
    fireEvent.input(textForm);
  });
});

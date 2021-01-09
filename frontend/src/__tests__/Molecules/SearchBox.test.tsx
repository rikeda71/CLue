import React from "react";
import SearchBox from "../../components/Molecules/SearchBox";
import { render, screen } from "@testing-library/react";
import { PaperSearchConditionType } from "../../types";

jest.spyOn(console, "log");

describe("render test", () => {
  it("looks test", () => {
    const placeholderText = "placeholder";
    render(
      <SearchBox
        placeholder={placeholderText}
        onButtonClickFunction={(param: PaperSearchConditionType) => {
          console.log(param);
        }}
      />
    );

    const searchBox = screen.getByText(/検索/);
    expect(searchBox).toBeInTheDocument();

    const placeholder = screen.getByPlaceholderText(placeholderText);
    expect(placeholder).toBeInTheDocument();
  });
});

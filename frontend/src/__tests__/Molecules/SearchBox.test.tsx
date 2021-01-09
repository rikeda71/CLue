import React from "react";
import SearchBox from "../../components/Molecules/SearchBox";
import { render, screen } from "@testing-library/react";
import { PaperSearchConditionType } from "../../types";

jest.spyOn(console, "log");

describe("render test", () => {
  it("looks test", () => {
    render(
      <SearchBox
        placeholder="placeholder"
        onButtonClickFunction={(param: PaperSearchConditionType) => {
          console.log(param);
        }}
      />
    );

    const placeholder = screen.getByText(/placeholder/);
    expect(placeholder).toBeInTheDocument();
  });
});

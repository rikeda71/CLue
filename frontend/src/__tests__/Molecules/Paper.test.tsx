import React from "react";
import Paper from "../../components/Molecules/Paper";
import { fireEvent, render, screen } from "@testing-library/react";
import { MemoryRouter } from "react-router";
import { PaperSearchConditionType } from "../../types";

jest.spyOn(console, "log");

const paper = {
  paperId: 1,
  title: "Distributed Representations of Words and Phrases and their Compositionality",
  conference: "NIPS",
  year: 2013,
  task: "Word-level Semantics",
  predicted: false,
  url:
    "https://papers.nips.cc/paper/5021-distributed-representations-of-words-and-phrases-and-their-compositionality.pdf",
  authors: [
    { authorId: 1, name: "author1" },
    { authorId: 2, name: "author2" },
  ],
};

const getPapersMethodMock = (param: PaperSearchConditionType): Promise<void> => {
  return new Promise(() => console.log(param));
};

describe("render test", () => {
  it("looks test", () => {
    render(
      <MemoryRouter initialEntries={[""]}>
        <Paper paper={paper} />
      </MemoryRouter>
    );
    // paper component title
    const title = screen.getByText(/Distributed Representations/);
    expect(title).toBeInTheDocument();
    expect(title).toContainHTML('a href="/paper/1"');

    // authors
    expect(screen.getByText(/author1/)).toBeInTheDocument();
    expect(screen.getByText(/author2/)).toBeInTheDocument();
    expect(screen.getByText(/author2/)).toContainHTML('a href="/author/2"');

    // paper tags
    expect(screen.getByText(/NIPS/)).toBeInTheDocument();
    expect(screen.getByText(/2013/)).toBeInTheDocument();

    // paper link
    const pdfLink = screen.getByText("pdf");
    expect(pdfLink).toBeInTheDocument();
    expect(pdfLink).toContainHTML(
      'a href="https://papers.nips.cc/paper/5021-distributed-representations-of-words-and-phrases-and-their-compositionality.pdf"'
    );
  });
  it("button click test", async () => {
    render(
      <MemoryRouter initialEntries={[""]}>
        <Paper paper={paper} getPapers={getPapersMethodMock} />
      </MemoryRouter>
    );
    // button click
    const inputs = screen.getAllByText(/NIPS|Word-level/);
    await inputs.map(elem => fireEvent.click(elem));
    // ボタンクリック時の挙動はconsole.logを使ってモック化
    expect(console.log).toBeCalledTimes(2);
  });
  // TODO: React Router によるリンクのクリックについてのテストができるならテストする
  // it("redirect link test", () => {
  //   const wrapper = render(
  //     <MemoryRouter initialEntries={[""]}>
  //       <Paper paper={paper} />
  //     </MemoryRouter>
  //   );

  //   const title = wrapper.getByText(/Distributed Representations/);
  //   expect(fireEvent.click(title)).toBe("/paper/1");
  // });
});

import React from "react";
import { PaperDetailTemplate } from "../../components/Pages/PaperDetail";
import { PaperType } from "../../types";
import { storiesOf } from "@storybook/react";
import { MemoryRouter } from "react-router";

const paper: PaperType = {
  paperId: 1,
  title: "BERT: Pre-training of Deep Bidirectional Transformers for Language Understanding",
  conference: "NAACL",
  year: 2019,
  task: "Word-level Semantics",
  predicted: true,
  url: "https://www.aclweb.org/anthology/N19-1423.pdf",
  authors: [
    { authorId: 1, name: "author1" },
    { authorId: 2, name: "author2" },
    { authorId: 3, name: "author3" },
    { authorId: 4, name: "author4" },
  ],
  introduction:
    "introduction;introduction;introduction;introduction;introduction;introduction;introduction;introduction;",
};

storiesOf("Pages", module)
  .addDecorator(story => <MemoryRouter initialEntries={["/"]}>{story()}</MemoryRouter>)
  .add("PaperDetail", () => <PaperDetailTemplate {...paper} />);

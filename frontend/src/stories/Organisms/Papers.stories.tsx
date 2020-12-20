import React from "react";
import Papers from "../../components/Organisms/Papers";
import { storiesOf } from "@storybook/react";
import { MemoryRouter } from "react-router";

const papers = [
  {
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
  },
  {
    paperId: 2,
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
  },
];
storiesOf("Organisms", module)
  .addDecorator(story => <MemoryRouter initialEntries={["/", "posts"]}>{story()}</MemoryRouter>)
  .add("Papers", () => <Papers papers={papers} isLoading={false}></Papers>);

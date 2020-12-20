import React from "react";
import PapersPage from "../../components/Pages/PapersPage";
import { storiesOf } from "@storybook/react";
import { MemoryRouter } from "react-router";
import fetchMock from "fetch-mock";
import { API_URL, CONFERENCE_ENDPOINT, PAPER_ENDPOINT, TASK_ENDPOINT, YEAR_ENDPOINT } from "../../constants";

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
    title: "End-to-end Sequence Labeling via Bi-directional LSTM-CNNs-CRF",
    conference: "ACL",
    year: 2016,
    task: "Parsing",
    predicted: false,
    url: "https://www.aclweb.org/anthology/P16-1101.pdf",
    authors: [
      { authorId: 5, name: "Xuezhe Ma" },
      { authorId: 6, name: "Eduard Hovy" },
    ],
  },
];

storiesOf("Pages", module)
  .addDecorator(story => <MemoryRouter initialEntries={["/", "posts"]}>{story()}</MemoryRouter>)
  .add("PapersPage", () => {
    fetchMock
      .restore()
      .getOnce(`${API_URL}${PAPER_ENDPOINT}?limit=100&offset=0`, papers)
      .getOnce(`${API_URL}${TASK_ENDPOINT}`, [])
      .getOnce(`${API_URL}${YEAR_ENDPOINT}`, [])
      .getOnce(`${API_URL}${CONFERENCE_ENDPOINT}`, []);
    return <PapersPage />;
  });

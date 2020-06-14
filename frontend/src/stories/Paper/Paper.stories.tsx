import React from "react";
import Paper, { PaperTitle, PaperTags, PaperConference, PaperTask } from "../../components/Paper/Paper";
import { storiesOf } from "@storybook/react";
import { MemoryRouter } from "react-router";

storiesOf("1_atoms", module)
  .add("PaperTitle", () => <PaperTitle>paper title</PaperTitle>)
  .add("PaperTags", () => <PaperTags>task name</PaperTags>)
  .add("PaperConference", () => <PaperConference>conference name</PaperConference>)
  .add("PaperTask", () => <PaperTask>Information Extraction</PaperTask>);

storiesOf("2_molecules", module)
  .addDecorator(story => <MemoryRouter initialEntries={["/", "posts"]}>{story()}</MemoryRouter>)
  .add("NAACL", () => (
    <Paper
      paperId={1}
      title="BERT: Pre-training of Deep Bidirectional Transformers for Language Understanding"
      conference="NAACL"
      year={2019}
      task="Word-level Semantics"
      predicted={true}
      url="https://www.aclweb.org/anthology/N19-1423.pdf"
      authors={[
        { authorId: 1, name: "author1" },
        { authorId: 2, name: "author2" },
        { authorId: 3, name: "author3" },
        { authorId: 4, name: "author4" },
      ]}
    />
  ))
  .add("Other", () => (
    <Paper
      paperId={1}
      title="Distributed Representations of Words and Phrases and their Compositionality"
      conference="NIPS"
      year={2013}
      task="Word-level Semantics"
      predicted={false}
      url="https://papers.nips.cc/paper/5021-distributed-representations-of-words-and-phrases-and-their-compositionality.pdf"
      authors={[
        { authorId: 1, name: "author1" },
        { authorId: 2, name: "author2" },
      ]}
    />
  ));

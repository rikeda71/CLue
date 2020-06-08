import React from "react";
import Paper, { PaperTitle, PaperTags, PaperConference } from "../../components/Paper/Paper";
import { storiesOf } from "@storybook/react";

storiesOf("1_atoms", module)
  .add("PaperTitle", () => <PaperTitle>paper title</PaperTitle>)
  .add("PaperTags", () => <PaperTags>task name</PaperTags>)
  .add("PaperConference", () => <PaperConference>conference name</PaperConference>);

storiesOf("2_molecules", module)
  .add("NAACL", () => (
    <Paper
      paperId={1}
      title="BERT: Pre-training of Deep Bidirectional Transformers for Language Understanding"
      conference="NAACL"
      year={2019}
      task="Word-level Semantics"
      predicted={true}
      url="https://www.aclweb.org/anthology/N19-1423.pdf"
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
    />
  ));

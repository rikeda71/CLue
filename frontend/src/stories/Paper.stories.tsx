import React from "react";
import Paper, { Title, Tags, Conference } from "../components/paper/Paper";
import { storiesOf } from "@storybook/react";

storiesOf("1_atoms", module)
  .add("Title", () => <Title>paper title</Title>)
  .add("Tags", () => <Tags>task name</Tags>)
  .add("Conference", () => <Conference>conference name</Conference>);

storiesOf("2_molecules", module)
  .add("NAACL", () => (
    <Paper
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
      title="Distributed Representations of Words and Phrases and their Compositionality"
      conference="NIPS"
      year={2013}
      task="Word-level Semantics"
      predicted={false}
      url="https://papers.nips.cc/paper/5021-distributed-representations-of-words-and-phrases-and-their-compositionality.pdf"
    />
  ));

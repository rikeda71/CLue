import React from "react";
import Paper from "../components/Paper.js";
import { storiesOf } from "@storybook/react";

storiesOf("Paper", module)
  .add("ACL", () => <Paper title="paperTitle" conference="ACL" year="2019" />)
  .add("EMNLP", () => <Paper title="paperTitle" conference="EMNLP" />)
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

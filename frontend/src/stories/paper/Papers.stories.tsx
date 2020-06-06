import React from "react";
import Papers from "../../components/paper/Papers";
import { PapersProps } from "../../components/types";
import { storiesOf } from "@storybook/react";

const papers: PapersProps = {
  papers: [
    {
      title: "BERT: Pre-training of Deep Bidirectional Transformers for Language Understanding",
      conference: "NAACL",
      year: 2019,
      task: "Word-level Semantics",
      predicted: true,
      url: "https://www.aclweb.org/anthology/N19-1423.pdf",
    },
    {
      title: "Distributed Representations of Words and Phrases and their Compositionality",
      conference: "NIPS",
      year: 2013,
      task: "Word-level Semantics",
      predicted: false,
      url:
        "https://papers.nips.cc/paper/5021-distributed-representations-of-words-and-phrases-and-their-compositionality.pdf",
    },
    {
      title: "BERT: Pre-training of Deep Bidirectional Transformers for Language Understanding",
      conference: "NAACL",
      year: 2019,
      task: "Word-level Semantics",
      predicted: true,
      url: "https://www.aclweb.org/anthology/N19-1423.pdf",
    },
    {
      title: "Distributed Representations of Words and Phrases and their Compositionality",
      conference: "NIPS",
      year: 2013,
      task: "Word-level Semantics",
      predicted: false,
      url:
        "https://papers.nips.cc/paper/5021-distributed-representations-of-words-and-phrases-and-their-compositionality.pdf",
    },
    {
      title: "BERT: Pre-training of Deep Bidirectional Transformers for Language Understanding",
      conference: "NAACL",
      year: 2019,
      task: "Word-level Semantics",
      predicted: true,
      url: "https://www.aclweb.org/anthology/N19-1423.pdf",
    },
    {
      title: "Distributed Representations of Words and Phrases and their Compositionality",
      conference: "NIPS",
      year: 2013,
      task: "Word-level Semantics",
      predicted: false,
      url:
        "https://papers.nips.cc/paper/5021-distributed-representations-of-words-and-phrases-and-their-compositionality.pdf",
    },
  ],
};
storiesOf("3_organisms", module).add("Papers", () => <Papers {...papers}></Papers>);

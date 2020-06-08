// import React from "react";
// import CardPaper from "../components/CardPaper";
// import { storiesOf } from "@storybook/react";
//
// storiesOf("CardPaper", module)
//   .add("ACL", () => <CardPaper title="paperTitle" conference="ACL" year="2019" />)
//   .add("EMNLP", () => <CardPaper title="paperTitle" conference="EMNLP" />)
//   .add("NAACL", () => (
//     <CardPaper
//       title="BERT: Pre-training of Deep Bidirectional Transformers for Language Understanding"
//       conference="NAACL"
//       year={2019}
//       task="Word-level Semantics"
//       predicted={true}
//       url="https://www.aclweb.org/anthology/N19-1423.pdf"
//       abstruct="We introduce a new language representation model called BERT, which stands for Bidirectional Encoder Representations from Transformers. Unlike recent language representation models, BERT is designed to pre-train deep bidirectional representations from unlabeled text by jointly conditioning on both left and right context in all layers. As a result, the pre-trained BERT model can be fine-tuned with just one additional output layer to create state-of-the-art models for a wide range of tasks, such as question answering and language inference, without substantial task-specific architecture modifications.
// BERT is conceptually simple and empirically powerful. It obtains new state-of-the-art results on eleven natural language processing tasks, including pushing the GLUE score to 80.5% (7.7% point absolute improvement), MultiNLI accuracy to 86.7% (4.6% absolute improvement), SQuAD v1.1 question answering Test F1 to 93.2 (1.5 point absolute improvement) and SQuAD v2.0 Test F1 to 83.1 (5.1 point absolute improvement)."
//     />
//   ))
//   .add("Other", () => (
//     <CardPaper
//       title="Distributed Representations of Words and Phrases and their Compositionality"
//       conference="NIPS"
//       year={2013}
//       task="Word-level Semantics"
//       predicted={false}
//       url="https://papers.nips.cc/paper/5021-distributed-representations-of-words-and-phrases-and-their-compositionality.pdf"
//       abstruct="The recently introduced continuous Skip-gram model is an efficient method for
// learning high-quality distributed vector representations that capture a large number of precise syntactic and semantic word relationships. In this paper we present
// several extensions that improve both the quality of the vectors and the training
// speed. By subsampling of the frequent words we obtain significant speedup and
// also learn more regular word representations. We also describe a simple alternative to the hierarchical softmax called negative sampling.
// An inherent limitation of word representations is their indifference to word order
// and their inability to represent idiomatic phrases. For example, the meanings of
// “Canada” and “Air” cannot be easily combined to obtain “Air Canada”. Motivated
// by this example, we present a simple method for finding phrases in text, and show
// that learning good vector representations for millions of phrases is possible."
//     />
//   ));
//

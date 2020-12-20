import React from "react";
import { PaperTitle, PaperTags, PaperConference, PaperTask } from "../../components/Paper/Paper";
import { storiesOf } from "@storybook/react";

storiesOf("Atoms", module)
  .add("PaperTitle", () => <PaperTitle>paper title</PaperTitle>)
  .add("PaperTags", () => <PaperTags>task name</PaperTags>)
  .add("PaperConference", () => <PaperConference>conference name</PaperConference>)
  .add("PaperTask", () => <PaperTask>Information Extraction</PaperTask>);

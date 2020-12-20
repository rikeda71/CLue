import React from "react";
import { PaperTags } from "../../components/Molecules/Paper";
import { storiesOf } from "@storybook/react";
import { PaperTitle } from "../../components/Atoms/PaperTitle";
import { ConferenceTag } from "../../components/Atoms/ConferenceTag";
import { TaskTag } from "../../components/Atoms/TaskTag";

storiesOf("Atoms", module)
  .add("PaperTitle", () => <PaperTitle>paper title</PaperTitle>)
  .add("PaperTags", () => <PaperTags>task name</PaperTags>)
  .add("PaperConference", () => <ConferenceTag>conference name</ConferenceTag>)
  .add("PaperTask", () => <TaskTag>Information Extraction</TaskTag>);

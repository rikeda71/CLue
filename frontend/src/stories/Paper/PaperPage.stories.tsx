import React from "react";
import PaperPage from "../../components/Paper/PaperPage";
import { storiesOf } from "@storybook/react";
import Paper from "../../components/Paper/Paper";

const queryParams = { lang: "english", title: "CRF" };
// const queryParams = { title: "CRF" };
storiesOf("4_templates", module)
  .add("PaperPage", () => <PaperPage />)
  .add("PaperPageWithQueryParams", () => <PaperPage queryParams={queryParams} />);

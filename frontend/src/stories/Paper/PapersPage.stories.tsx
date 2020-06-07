import React from "react";
import PapersPage from "../../components/Paper/PapersPage";
import { storiesOf } from "@storybook/react";

const queryParams = { lang: "english", title: "CRF" };
storiesOf("4_templates", module)
  .add("PapersPage", () => <PapersPage />)
  .add("PapersPageWithQueryParams", () => <PapersPage queryParams={queryParams} />);

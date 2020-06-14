import React from "react";
import PapersPage from "../../components/Paper/PapersPage";
import { storiesOf } from "@storybook/react";
import { MemoryRouter } from "react-router";

const queryParams = { lang: "english", title: "CRF" };
storiesOf("4_templates", module)
  .addDecorator(story => <MemoryRouter initialEntries={["/", "posts"]}>{story()}</MemoryRouter>)
  .add("PapersPage", () => <PapersPage />)
  .add("PapersPageWithQueryParams", () => <PapersPage queryParams={queryParams} />);

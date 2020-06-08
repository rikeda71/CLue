import React from "react";
import { App } from "../index";
import { storiesOf } from "@storybook/react";
import { MemoryRouter } from "react-router";

storiesOf("5_pages", module)
  .addDecorator(story => <MemoryRouter initialEntries={["/", "paper/:id", "author/:id"]}>{story()}</MemoryRouter>)
  .add("Page", () => <App />);

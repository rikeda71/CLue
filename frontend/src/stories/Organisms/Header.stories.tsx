import React from "react";
import Header from "../../components/Organisms/Header";
import { storiesOf } from "@storybook/react";
import { MemoryRouter } from "react-router";

storiesOf("Organisms", module)
  .add("Header when logined", () => (
    <MemoryRouter>
      <Header isLogined={true} email="example@gmail.com" />
    </MemoryRouter>
  ))
  .add("Header when not logined", () => (
    <MemoryRouter>
      <Header isLogined={false} />
    </MemoryRouter>
  ));

import React from "react";
import Header from "../../components/Organisms/Header";
import { storiesOf } from "@storybook/react";
import { BrowserRouter as Router } from "react-router-dom";

storiesOf("Organisms", module).add("Header", () => (
  <Router>
    <Header />
  </Router>
));

import React from "react";
import PaperPage from "../../components/Paper/PaperPage";
import { storiesOf } from "@storybook/react";

storiesOf("4_templates", module).add("PaperPage", () => <PaperPage paperId={1} />);

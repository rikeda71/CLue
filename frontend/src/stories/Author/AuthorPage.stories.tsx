import React from "react";
import AuthorPage from "../../components/Author/AuthorPage";
import { storiesOf } from "@storybook/react";

storiesOf("4_templates", module).add("AuthorPage", () => <AuthorPage authorId={1} />);

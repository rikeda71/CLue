import { storiesOf } from "@storybook/react";
import React from "react";
import SearchBox from "../../components/Molecules/SearchBox";
import { PaperSearchConditionType } from "../../types";

storiesOf("Molecules", module).add("SearchBox", () => (
  <SearchBox
    placeholder="placeholder"
    onButtonClickFunction={(param: PaperSearchConditionType) => {
      console.log(param);
    }}
  />
));

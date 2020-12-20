import React from "react";
import Loading from "../../components/Atoms/Loading";
import { storiesOf } from "@storybook/react";

storiesOf("Atoms", module).add("Loading", () => <Loading isShown={true} />);

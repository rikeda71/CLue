import React from "react";
import { storiesOf } from "@storybook/react";
import UserBadge from "../../components/Atoms/UserBadge";

storiesOf("Atoms", module).add("UserBadge", () => <UserBadge email={"example@gmail.com"} />);

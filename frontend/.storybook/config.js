import { configure } from "@storybook/react";
import { setConsoleOptions } from "@storybook/addon-console";

setConsoleOptions({
  panelExclude: [],
});

function loadStories() {
  let req = require.context("../src/stories", true, /\.(js|jsx|tsx)$/);
  req.keys().forEach(fname => req(fname));
}

configure(loadStories, module);

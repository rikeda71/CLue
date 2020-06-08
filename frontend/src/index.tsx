import React from "react";
import ReactDOM from "react-dom";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";

import AuthorPage from "./components/Author/AuthorPage";
import PaperPage from "./components/Paper/PaperPage";
import PapersPage from "./components/Paper/PapersPage";
import Header from "./components/Header/Header";
import Footer from "./components/Footer/Footer";

export const App: React.FC = () => {
  return (
    <Router>
      <Header />
      <Switch>
        <Route exact path="/" component={PapersPage} />
        <Route exact path="/paper/:id" component={PaperPage} />
        <Route exact path="/author/:id" component={AuthorPage} />
      </Switch>
      <Footer />
    </Router>
  );
};

ReactDOM.render(<App />, document.getElementById("root") as HTMLElement);

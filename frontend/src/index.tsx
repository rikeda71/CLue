import React from "react";
import ReactDOM from "react-dom";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";

import AuthorPage from "./components/Author/AuthorPage";
import PaperDetail from "./components/Paper/PaperPage";
import PapersPage from "./components/Paper/PapersPage";
import Header from "./components/Organisms/Header";
import Footer from "./components/Organisms/Footer";
import styled from "styled-components";

const AppStyle = styled.div`
  width: 85%;
  margin: 0 auto;
`;

const MainPageStyle = styled.div`
  margin: 1rem 0;
`;

export const App: React.FC = () => {
  return (
    <AppStyle>
      <Router>
        <Header />
        <MainPageStyle>
          <Switch>
            <Route exact path="/" component={PapersPage} />
            <Route exact path="/paper/:id" component={PaperDetail} />
            <Route exact path="/author/:id" component={AuthorPage} />
          </Switch>
        </MainPageStyle>
        <Footer />
      </Router>
    </AppStyle>
  );
};

ReactDOM.render(<App />, document.getElementById("root") as HTMLElement);

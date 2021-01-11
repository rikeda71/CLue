import React, { useState } from "react";
import ReactDOM from "react-dom";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import AuthorPage from "./components/Pages/AuthorPage";
import PaperDetail from "./components/Pages/PaperDetail";
import PapersPage from "./components/Pages/PapersPage";
import Header from "./components/Organisms/Header";
import Footer from "./components/Organisms/Footer";
import styled from "styled-components";
import { API_URL, USER_ENDPOINT } from "./constants";
import { UserType } from "./types";
import { OAuthFetchAPIService } from "./api";
import PostPaperPage from "./components/Pages/PostPaperPage";

const AppStyle = styled.div`
  width: 85%;
  margin: 0 auto;
`;

const MainPageStyle = styled.div`
  margin: 1rem 0;
`;

export const App: React.FC = () => {
  const [email, setEmail] = useState<string>("");
  const userFetchService = new OAuthFetchAPIService(API_URL, USER_ENDPOINT);
  const isLogined = userFetchService.isLogined();
  if (isLogined) {
    const getLoginedUser = async () => {
      await userFetchService
        .fetchAPIWithAuth()
        .then(res => {
          return res.json();
        })
        .then((res: UserType) => setEmail(res.email));
    };
    getLoginedUser();
  }
  return (
    <AppStyle>
      <Router>
        <Header isLogined={isLogined} email={email} />
        <MainPageStyle>
          <Switch>
            <Route exact path="/" component={PapersPage} />
            <Route exact path="/paper/:id" component={PaperDetail} />
            <Route exact path="/author/:id" component={AuthorPage} />
            <Route exact path="/paper/add" component={PostPaperPage} />
          </Switch>
        </MainPageStyle>
        <Footer />
      </Router>
    </AppStyle>
  );
};

ReactDOM.render(<App />, document.getElementById("root") as HTMLElement);

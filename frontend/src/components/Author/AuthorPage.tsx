import React, { useState, useEffect } from "react";
import { AuthorPageProps, AuthorType } from "../../types";
import { createGetRequestUrl } from "../../utils";
import Author from "./Author";
import { useParams } from "react-router";

const AuthorPage: React.FC<AuthorPageProps> = props => {
  const { id } = useParams();
  const [author, setAuthor] = useState<AuthorType>();
  const requestUrl = createGetRequestUrl(`/api/v1/authors/${id}`);

  useEffect(() => {
    async function getAuthor() {
      await fetch(requestUrl, {
        method: "GET",
        mode: "cors",
        headers: { "Content-Type": "application/json; charset=utf-8" },
      })
        .then(res => res.json())
        .then(res => {
          return res;
        })
        .then(res => setAuthor(res));
    }
    getAuthor();
  }, []);

  return <Author {...author} />;
};

export default AuthorPage;
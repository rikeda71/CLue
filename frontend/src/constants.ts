// API周りの定数
// export const API_URL = "http://localhost:8080";
export const API_URL = process.env.REACT_APP_BACKEND_URL ? process.env.REACT_APP_BACKEND_URL : "http://localhost:8080";
export const OAUTH_TOKEN_KEY = "oauth2_jwt_token";
export const PAPER_ENDPOINT = "/api/v1/papers";
export const AUTHOR_ENDPOINT = "/api/v1/authors";
export const CONFERENCE_ENDPOINT = "/api/v1/conference";
export const TASK_ENDPOINT = "/api/v1/task";
export const YEAR_ENDPOINT = "/api/v1/year";
export const OAUTH_ENDPOINT = "/oauth2/authorize/google";
export const USER_ENDPOINT = "/api/v1/user";

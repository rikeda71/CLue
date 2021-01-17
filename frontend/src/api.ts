import { OAUTH_TOKEN_KEY, PAPER_ENDPOINT } from "./constants";
import { PaperSearchConditionType } from "./types";
import { getUrlParameter } from "./utils";

// API call を行うサービス
export class FetchAPIService {
  private rootURL: string;
  private endPoint: string;
  protected targetURL: string;

  constructor(rootURL: string, endPoint: string) {
    this.rootURL = rootURL;
    this.endPoint = endPoint;
    this.targetURL = rootURL + endPoint;
  }

  public fetchGetRequest(queryParams = {}, headers?: HeadersInit): Promise<Response> {
    const params = this.createGetRequestParams(queryParams);
    return this.fetchAPI(this.targetURL + params, "GET", headers);
  }

  public fetchPostRequest(body = {}, headers?: HeadersInit): Promise<Response> {
    return this.fetchAPI(this.targetURL, "POST", headers, JSON.stringify(body));
  }

  /**
   * APIにリクエストするメソッド
   * @param method http method
   * @param headers http header
   * @param body request body
   */
  private fetchAPI(url: string, method: string, headers: HeadersInit = {}, body?: BodyInit): Promise<Response> {
    headers["Content-Type"] = "application/json; charset=utf-8";
    const call = async () => {
      return await fetch(url, {
        method: method,
        mode: "cors",
        headers: headers,
        body: body,
      });
    };
    return call();
  }

  protected createGetRequestParams(queryParams?: PaperSearchConditionType): string {
    const trueQueryParams: PaperSearchConditionType = {};
    if (queryParams && this.endPoint === PAPER_ENDPOINT) {
      for (const k in queryParams) {
        if (queryParams[k]) {
          trueQueryParams[k] = queryParams[k];
        }
      }
      return encodeURI(
        "?" +
          Object.entries(trueQueryParams)
            .map(map => `${map[0]}=${map[1]}`)
            .join("&") +
          "&limit=50&offset=0"
      );
    } else {
      return "";
    }
  }
}

// jwtによる認証情報を加えてリクエストしてくれるサービス
export class OAuthFetchAPIService extends FetchAPIService {
  private jwtToken: string;
  private authTokenKey: string;

  constructor(rootURL: string, endPoint: string, authTokenKey: string = OAUTH_TOKEN_KEY) {
    super(rootURL, endPoint);
    this.authTokenKey = authTokenKey;
    this.updateJwtToken();
  }

  public fetchGetWithAuth(queryParams = {}, headers?: HeadersInit): Promise<Response> {
    headers = headers ? headers : {};
    if (this.jwtToken !== "") {
      headers["Authorization"] = "Bearer " + this.jwtToken;
      headers["Access-Control-Allow-Origin"] = "*";
      headers["Access-Control-Allow-Headers"] = "Authorization";
      headers["Access-Control-Allow-Methods"] = "GET";
    }
    return this.fetchGetRequest(queryParams, headers);
  }

  /**
   * 認証情報を加えてpostリクエスト
   * @param headers http header
   * @param url endpoint url(指定されなければ初期化時に渡したurlとendpointが対象)
   */
  public fetchPostWithAuth(body = {}, headers?: HeadersInit): Promise<Response> {
    headers = headers ? headers : {};
    if (this.jwtToken !== "") {
      headers["Authorization"] = "Bearer " + this.jwtToken;
      headers["Access-Control-Allow-Origin"] = "*";
      headers["Access-Control-Allow-Headers"] = "Authorization";
      headers["Access-Control-Allow-Methods"] = "POST";
    }
    return this.fetchPostRequest(body, headers);
  }

  /**
   * ログインしているかを返却する
   */
  public isLogined(): boolean {
    return this.jwtToken !== "";
  }

  public updateJwtToken(): void {
    // cookie から token を取得
    this.jwtToken = this.getAuthTokenFromCookie(this.authTokenKey);
    // cookieになければURLパラメータから token を取得
    if (this.jwtToken === "") {
      this.jwtToken = getUrlParameter("token");
      document.cookie = `${OAUTH_TOKEN_KEY}=${this.jwtToken}; `;
    }
  }

  private getAuthTokenFromCookie(authTokenKey: string): string {
    const cookies = document.cookie.split("; ");

    for (const c of cookies) {
      //一つ一つ取り出して
      const cArray = c.split("="); //さらに=で分割して配列に
      if (cArray[0] == authTokenKey) {
        return cArray[1];
      }
    }
    return "";
  }
}

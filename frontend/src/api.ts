import { API_URL, OAUTH_TOKEN_KEY, PAPER_ENDPOINT } from "./constants";
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

  /**
   * APIにリクエストするメソッド
   * @param method http method
   * @param headers http header
   * @param url endpoint url(指定されなければ初期化時に渡したURLとendpointが対象)
   */
  public fetchAPI(method = "GET", headers: HeadersInit = {}, url: string = this.targetURL): Promise<Response> {
    headers["Content-Type"] = "application/json; charset=utf-8";
    const call = async () => {
      return await fetch(url, {
        method: method,
        mode: "cors",
        headers: headers,
      });
    };
    return call();
  }
}

// /api/v1/papers を叩くサービス
export class FetchPaperAPIService extends FetchAPIService {
  constructor() {
    super(API_URL, PAPER_ENDPOINT);
  }

  public fetchPaperAPI(
    method = "GET",
    headers: HeadersInit = {},
    queryParams?: PaperSearchConditionType
  ): Promise<Response> {
    headers["Content-Type"] = "application/json; charset=utf-8";
    const url = this.createGetRequestUrl(queryParams);
    return this.fetchAPI(method, headers, url);
  }

  private createGetRequestUrl(queryParams?: PaperSearchConditionType): string {
    const trueQueryParams: PaperSearchConditionType = {};
    if (queryParams) {
      for (const k in queryParams) {
        if (queryParams[k]) {
          trueQueryParams[k] = queryParams[k];
        }
      }
      return encodeURI(
        this.targetURL +
          "?" +
          Object.entries(trueQueryParams)
            .map(map => `${map[0]}=${map[1]}`)
            .join("&") +
          "&limit=50&offset=0"
      );
    } else {
      return this.targetURL + "?limit=100&offset=0";
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

  /**
   * 認証情報を加えてAPIにリクエスト
   * @param method http method
   * @param headers http header
   * @param url endpoint url(指定されなければ初期化時に渡したURLとendpointが対象)
   */
  public fetchAPIWithAuth(method = "GET", headers: HeadersInit = {}, url: string = this.targetURL): Promise<Response> {
    if (this.jwtToken !== "") {
      headers["Authorization"] = "Bearer " + this.jwtToken;
    }
    return this.fetchAPI(method, headers, url);
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

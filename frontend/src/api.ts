import { API_URL, PAPER_ENDPOINT } from "./constants";
import { PaperSearchConditionType } from "./types";

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

  public fetchAPI(method: string = "GET", headers: HeadersInit = {}, url: string = this.targetURL): Promise<Response> {
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
    method: string = "GET",
    headers: HeadersInit = {},
    queryParams?: PaperSearchConditionType
  ): Promise<Response> {
    headers["Content-Type"] = "application/json; charset=utf-8";
    const url = this.createGetRequestUrl(queryParams);
    return this.fetchAPI(method, headers, url);
  }

  private createGetRequestUrl(queryParams?: PaperSearchConditionType): string {
    const trueQueryParams: PaperSearchConditionType = {};
    if (!!queryParams) {
      for (let k in queryParams) {
        if (!!queryParams[k]) {
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
export class OAuthFetchAPIService extends FetchAPIService {}

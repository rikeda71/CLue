import { apiUrl } from "./config";
import { PaperSearchConditionType } from "./types";

export function createGetRequestUrl(urlPath: string, queryParams?: PaperSearchConditionType): string {
  const trueQueryParams: PaperSearchConditionType = {};
  if (!!queryParams) {
    for (let k in queryParams) {
      if (!!queryParams[k]) {
        trueQueryParams[k] = queryParams[k];
      }
    }
    return encodeURI(
      apiUrl +
        urlPath +
        "?" +
        Object.entries(trueQueryParams)
          .map(map => `${map[0]}=${map[1]}`)
          .join("&") +
        "&limit=50&offset=0"
    );
  } else {
    return apiUrl + urlPath + "?limit=100&offset=0";
  }
}

export function getAuthTokenFromCookie(authTokenKey: string = "oauth2_jwt_token"): string {
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

export function getUrlParameter(key: string): string {
  const arr = new Array(...new URL(location.href).searchParams.entries());
  for (let obj of arr) {
    if (obj[0] === key) {
      return obj[1];
    }
  }
  return "";
}

export function mapToObject(map: Map<any, any>) {
  return Array.from(map.entries()).reduce((main, [key, value]) => ({ ...main, [key]: value }), {});
}

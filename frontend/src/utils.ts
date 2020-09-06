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

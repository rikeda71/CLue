import { apiUrl } from "./config";
import { PaperSearchConditionType } from "./types";

export function createGetRequestUrl(urlPath: string, queryParams?: PaperSearchConditionType): string {
  if (!!queryParams) {
    return encodeURI(
      apiUrl +
        urlPath +
        "?" +
        Object.entries(queryParams)
          .map(map => `${map[0]}=${map[1]}`)
          .join("&")
    );
  } else {
    return apiUrl + urlPath + "?limit=100&offset=0";
  }
}

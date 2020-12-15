export class FetchAPIService {
  rootURL: string;
  endPoint: string;

  constructor(rootURL: string, endPoint: string) {
    this.rootURL = rootURL;
    this.endPoint = endPoint;
  }

  fetchAPI(method: string, headers: HeadersInit): Promise<Response> {
    const call = async () => {
      return await fetch(this.rootURL + this.endPoint, {
        method: method,
        mode: "cors",
        headers: headers,
      });
    };
    return call();
  }
}

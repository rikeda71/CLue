import { OAUTH_TOKEN_KEY } from "./constants";

export function getAuthTokenFromCookie(authTokenKey: string = OAUTH_TOKEN_KEY): string {
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

export function getUrlParameter(key: string): string {
  const arr = new Array(...new URL(location.href).searchParams.entries());
  for (const obj of arr) {
    if (obj[0] === key) {
      return obj[1];
    }
  }
  return "";
}

export function mapToObject(map: Map<any, any>) {
  return Array.from(map.entries()).reduce((main, [key, value]) => ({ ...main, [key]: value }), {});
}

import React from "react";
import { fireEvent, render, screen } from "@testing-library/react";
import LoginButton from "../../components/Molecules/LoginButton";

describe("render test", () => {
  it("looks test", () => {
    render(<LoginButton />);
    // ログインという文字列がボタンに埋め込まれている
    expect(screen.getByText("ログイン")).toBeInTheDocument();
  });
  it("role test", () => {
    // window.open() を mock 化
    global.open = jest.fn();

    render(<LoginButton />);
    const loginButton = screen.getByText("ログイン");
    // 遷移先は unit test では特定できないので window.open を呼び出すことを確認
    fireEvent.click(loginButton);
    expect(global.open).toBeCalledTimes(1);
  });
});

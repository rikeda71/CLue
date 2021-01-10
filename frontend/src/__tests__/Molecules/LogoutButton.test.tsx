import React from "react";
import { fireEvent, render, screen } from "@testing-library/react";
import LogoutButton from "../../components/Molecules/LogoutButton";

describe("render test", () => {
  it("looks test", () => {
    render(<LogoutButton />);
    // ログインという文字列がボタンに埋め込まれている
    expect(screen.getByText("ログアウト")).toBeInTheDocument();
  });
  it("role test", () => {
    // window.open() を mock 化
    global.open = jest.fn();

    render(<LogoutButton />);
    const logoutButton = screen.getByText("ログアウト");
    // 遷移先は unit test では特定できないので window.open を呼び出すことを確認
    fireEvent.click(logoutButton);
    expect(global.open).toBeCalledTimes(1);
  });
});

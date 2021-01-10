import React from "react";
import { fireEvent, render, screen } from "@testing-library/react";
import Header from "../../components/Organisms/Header";
import { MemoryRouter } from "react-router";

describe("render test", () => {
  // window.open() を mock 化
  global.open = jest.fn();

  it("when not logined", () => {
    render(
      <MemoryRouter>
        <Header isLogined={false} />
      </MemoryRouter>
    );

    // headerにアプリ名がある
    const appName = screen.getByText("CLue");
    expect(appName).toBeInTheDocument();

    // ログインボタンがある
    const loginButton = screen.getByText("ログイン");
    expect(loginButton).toBeInTheDocument();
    // クリックしたら windows.open() が呼び出される
    fireEvent.click(loginButton);
    expect(window.open).toBeCalled();
  });
  it("when logined", () => {
    const email = "example@gmail.com";
    render(
      <MemoryRouter>
        <Header isLogined={true} email={email} />
      </MemoryRouter>
    );
    // headerにアプリ名がある
    const appName = screen.getByText("CLue");
    expect(appName).toBeInTheDocument();

    // emailアドレスから "@gmail.com" が除かれたバッチがある
    expect(screen.getByText("example")).toBeInTheDocument();

    // ログアウトボタンがある
    const loginButton = screen.getByText("ログアウト");
    expect(loginButton).toBeInTheDocument();
    // クリックしたら windows.open() が呼び出される
    fireEvent.click(loginButton);
    expect(global.open).toBeCalled();
  });
});

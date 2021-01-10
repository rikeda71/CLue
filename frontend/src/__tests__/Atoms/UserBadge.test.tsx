import React from "react";
import UserBadge from "../../components/Atoms/UserBadge";
import { render, screen } from "@testing-library/react";

describe("render test", () => {
  const email = "example@gmail.com";
  it("looks test", () => {
    render(<UserBadge email={email} />);
    // email の @以降を削除する
    expect(screen.getByText("example")).toBeInTheDocument();
  });
});

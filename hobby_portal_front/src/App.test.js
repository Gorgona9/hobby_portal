import { render, screen } from "@testing-library/react";
import App from "./App";
import React from "react";

it("renders without crashing", () => {
  render(<App />);

  expect(screen.getByText(/Ready to have fun?/i)).toBeInTheDocument();
});

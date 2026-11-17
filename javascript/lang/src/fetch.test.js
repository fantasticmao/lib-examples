/**
 * Fetch API - Web APIs | MDN https://developer.mozilla.org/en-US/docs/Web/API/Fetch_API
 * Using Fetch - Web APIs | MDN https://developer.mozilla.org/en-US/docs/Web/API/Fetch_API/Using_Fetch
 * WindowOrWorkerGlobalScope.fetch() - Web APIs | MDN https://developer.mozilla.org/en-US/docs/Web/API/WindowOrWorkerGlobalScope/fetch
 */
import { expect, test } from "vitest";

test("fetch get", () => {
  fetch("https://httpbin.org/get", {
    headers: {
      Accept: "application/json",
    },
  }).then((response) => expect(response.ok).toBeTruthy());
});

test("fetch post", () => {
  fetch("https://httpbin.org/post", {
    method: "POST",
    headers: {
      Accept: "application/json",
    },
    body: JSON.stringify({
      name: "John Doe",
      email: "john.doe@example.com",
    }),
  }).then((response) => expect(response.ok).toBeTruthy());
});

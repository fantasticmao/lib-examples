/**
 * Fetch API - Web APIs | MDN https://developer.mozilla.org/en-US/docs/Web/API/Fetch_API
 * Using Fetch - Web APIs | MDN https://developer.mozilla.org/en-US/docs/Web/API/Fetch_API/Using_Fetch
 * WindowOrWorkerGlobalScope.fetch() - Web APIs | MDN https://developer.mozilla.org/en-US/docs/Web/API/WindowOrWorkerGlobalScope/fetch
 */

import fetch from 'node-fetch';
import fetchMock from 'fetch-mock';

test('fetch get', (done) => {
  fetchMock.mock({
    name: 'http://httpbin.org/get',
    method: 'GET',
  }, {
    status: 200,
    body: 'OK',
  });
  fetch('http://example.com')
      .then((response) => expect(response.ok).toBeTruthy())
      .then(done)
      .catch((error) => done.fail(error));
});

test('fetch post', (done) => {
  fetchMock.mock({
    name: 'http://httpbin.org/get',
    method: 'POST',
  }, {
    status: 200,
    body: 'OK',
  });
  fetch('http://example.com', {
    method: 'POST',
  }).then((response) => expect(response.ok).toBeTruthy())
      .then(done)
      .catch((error) => done.fail(error));
});

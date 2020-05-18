/*
 * Promise - JavaScript | MDN https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Promise
 * 《ES6 入门教程》 - Promise 对象 https://es6.ruanyifeng.com/#docs/promise
 */

test('Promise resolve', () => {
  Promise.resolve('Promise resolve')
      .then(console.log);
});

test('Promise reject', () => {
  Promise.reject('Promise reject')
      .catch(console.error);
});

test('PromiseConstructor resolve', () => {
  new Promise((resolve) => {
    resolve('PromiseConstructor resolve');
  }).then(console.log)
      .catch(console.error);
});

test('PromiseConstructor reject', () => {
  new Promise((resolve, reject) => {
    reject('PromiseConstructor reject');
  }).then(console.log)
      .catch(console.error);
});

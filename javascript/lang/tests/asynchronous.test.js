/*
 * 在 JavaScript 的异步编程模型中，实现阻塞调用 task1 -> task2 -> task3 三个异步任务的方式如下：
 * 1. 使用 callback 模式
 * 2. 使用 Promise API
 * 3. 使用 async + await 语法糖
 */

import {useAsyncWithAwait, useCallback, usePromise} from '../src/asynchronous.js';

const task1 = (mode) => console.log(`[${mode}] 执行 task1`);
const task2 = (mode) => console.log(`[${mode}] 执行 task2`);
const task3 = (mode) => console.log(`[${mode}] 执行 task3`);

jest.setTimeout(10000);

test('use callback mode', (done) => {
// callback 模式会导致 callback hell
  useCallback((mode) => {
    task1(mode);
    useCallback((mode) => {
      task2(mode);
      useCallback((mode) => {
        task3(mode);
        done();
      }, 3000);
    }, 2000);
  }, 1000);
});

test('use Promise', () => {
  return usePromise(1000).then(task1)
      .then(() => usePromise(2000)).then(task2)
      .then(() => usePromise(3000)).then(task3);
});

test('use async + await', async () => {
  await useAsyncWithAwait(1000).then(task1);
  await useAsyncWithAwait(2000).then(task2);
  await useAsyncWithAwait(3000).then(task3);
});

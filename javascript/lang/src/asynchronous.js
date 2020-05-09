/**
 * 在 JavaScript 的异步编程模型中，实现阻塞调用 task1 -> task2 -> task3 三个异步任务的方式如下：
 * 1. 使用 callback 模式
 * 2. 使用 Promise API
 * 3. 使用 async + await 语法糖
 */
const task1 = (mode) => console.log(`[${mode}] 执行 task1`);
const task2 = (mode) => console.log(`[${mode}] 执行 task2`);
const task3 = (mode) => console.log(`[${mode}] 执行 task3`);

// callback 模式
// 注册事件 addEventListener() 属于 callback 模式
function useCallback(task, timeout) {
    setTimeout(task, timeout, 'callback');
}

// callback 模式会导致 callback hell
useCallback(mode => {
    task1(mode);
    useCallback(mode => {
        task2(mode);
        useCallback(mode => {
            task3(mode);
        }, 3000);
    }, 2000);
}, 1000);

// Promise API
// Fetch 属于 Promise API
function usePromise(timeout) {
    return new Promise(resolve => {
        setTimeout(resolve, timeout, 'promise')
    });
}

usePromise(1000).then(task1)
    .then(() => usePromise(2000)).then(task2)
    .then(() => usePromise(3000)).then(task3);

// async + await
function useAsyncWithAwait(timeout) {
    return new Promise(resolve => {
        setTimeout(resolve, timeout, 'async + await')
    });
}

(async function () {
    await useAsyncWithAwait(1000).then(task1);
    await useAsyncWithAwait(2000).then(task2);
    await useAsyncWithAwait(3000).then(task3);
})();
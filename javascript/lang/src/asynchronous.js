/**
 * callback 模式，注册事件 addEventListener() 属于 callback 模式
 * @param {function} task
 * @param {number} timeout
 */
export function useCallback(task, timeout) {
  setTimeout(task, timeout, 'callback');
}

/**
 * Promise，Fetch API 属于 Promise
 * @param {number} timeout
 * @return {Promise<unknown>}
 */
export function usePromise(timeout) {
  return new Promise((resolve) => {
    setTimeout(resolve, timeout, 'promise');
  });
}

/**
 * async + await
 * @param {number} timeout
 * @return {Promise<unknown>}
 */
export function useAsyncWithAwait(timeout) {
  return new Promise((resolve) => {
    setTimeout(resolve, timeout, 'async + await');
  });
}

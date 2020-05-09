// Promise - JavaScript | MDN https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Promise
// 《ES6 入门教程》 - Promise 对象 https://es6.ruanyifeng.com/#docs/promise

const message = 'Hello World';
const reason = 'some error';

function print(message) {
    const stack = new Error().stack;
    console.log(`${message} ${stack.split('\n')[2]}`);
}

Promise.resolve(message)
    .then(message => print(message));

Promise.reject(reason)
    .catch(reason => print(reason));

new Promise(resolve => {
    print(`resolve ${message}`);
    resolve(message);
}).then(message => print(message))
    .catch(reason => print(reason));

new Promise((resolve, reject) => {
    print(`reject ${reason}`);
    reject(reason);
}).then(message => print(message))
    .catch(reason => print(reason));
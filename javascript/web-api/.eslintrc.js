module.exports = {
  'env': {
    'browser': true,
    'node': true,
    'es6': true,
    'jest': true,
  },
  'extends': [
    'eslint:recommended',
    'google',
  ],
  'globals': {
    'Atomics': 'readonly',
    'SharedArrayBuffer': 'readonly',
  },
  'parserOptions': {
    'ecmaVersion': 11,
    'sourceType': 'module',
  },
  'rules': {},
};

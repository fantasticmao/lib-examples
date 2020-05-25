/**
 * https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array/filter
 */
test('filter', () => {
  const arr = [1, 2, 3, 4, 5, 6].filter((element) => {
    return element > 3;
  });
  expect(arr).toEqual([4, 5, 6]);
});

/**
 * https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array/map
 */
test('map', () => {
  const arr = [1, 2, 3, 4, 5, 6].map((element) => {
    return element * 100;
  });
  expect(arr).toEqual([100, 200, 300, 400, 500, 600]);
});

/**
 * https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array/Reduce
 */
test('reduce', () => {
  const sum = [1, 2, 3, 4, 5, 6].reduce((accumulator, currentValue) => {
    return accumulator + currentValue;
  }, 0);
  expect(sum).toBe(21);
});

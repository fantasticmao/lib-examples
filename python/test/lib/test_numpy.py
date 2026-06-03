import math

import numpy as np


def test_create():
    a = np.array([0, 1, 2, 3, 4, 5, 6, 7, 8])
    print(a.reshape(3, 3))
    a = np.array([[0, 1, 2], [3, 4, 5], [6, 7, 8]])
    print(a)

    b = np.zeros((4, 4))
    print(b)
    b = np.ones((4, 4), dtype=np.int16)
    print(b)
    b = np.arange(0, 10, 2)
    print(b)
    b = np.linspace(0, 1, 5)
    print(b)


def test_basic_operate():
    # 矩阵加减
    a = np.array([20, 30, 40, 50])
    b = np.arange(4)
    c = a - b
    np.testing.assert_array_equal(c, [20, 29, 38, 47])

    # 矩阵数乘
    d = b * 2
    np.testing.assert_array_equal(d, [0, 2, 4, 6])

    a = np.array([[1, 1], [0, 1]])
    b = np.array([[2, 0], [3, 4]])
    c = a * b
    np.testing.assert_array_equal(c, [[2, 0], [0, 4]])

    # 矩阵乘法，是向量内积的推广
    d = a @ b
    np.testing.assert_array_equal(d, [[5, 4], [3, 4]])
    d = a.dot(b)
    np.testing.assert_array_equal(d, [[5, 4], [3, 4]])

    a = np.ones((2, 3), dtype=np.int16)
    a *= 3
    np.testing.assert_array_equal(a, [[3, 3, 3], [3, 3, 3]])
    a += 2
    np.testing.assert_array_equal(a, [[5, 5, 5], [5, 5, 5]])

    a = np.arange(5, dtype=np.int16)
    assert a.sum() == 10
    assert a.min() == 0
    assert a.max() == 4

    a = np.arange(12).reshape(3, 4)
    np.testing.assert_array_equal(a, [[0, 1, 2, 3], [4, 5, 6, 7], [8, 9, 10, 11]])
    np.testing.assert_array_equal(a.sum(axis=0), [12, 15, 18, 21])
    np.testing.assert_array_equal(a.sum(axis=1), [6, 22, 38])


def test_function():
    a = np.arange(3)
    b = np.exp(a)
    np.testing.assert_allclose(b, [1, np.e, math.pow(np.e, 2)])
    c = np.sqrt(a)
    np.testing.assert_allclose(c, [0, 1, math.sqrt(2)])
    d = np.add(a, np.array([2, -1, 4]))
    np.testing.assert_equal(d, [2, 0, 6])


def test_slice():
    a = np.arange(10) ** 3
    assert a[2] == 8
    np.testing.assert_array_equal(a[2:5], [8, 27, 64])

    b = np.fromfunction(lambda x, y: 10 * x + y, (4, 4), dtype=np.int16)
    assert b[2, 3] == 23
    np.testing.assert_array_equal(b[:3, :3], [[0, 1, 2], [10, 11, 12], [20, 21, 22]])
    np.testing.assert_array_equal(b[:, 3], [3, 13, 23, 33])

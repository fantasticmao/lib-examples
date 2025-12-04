# https://docs.python.org/zh-cn/3/tutorial/datastructures.html
from collections import deque

from math import pi


def test_list():
    fruits = ["orange", "apple", "pear", "banana", "kiwi", "apple", "banana"]
    assert 2 == fruits.count("apple")
    assert 0 == fruits.count("tangerine")
    assert 3 == fruits.index("banana")
    assert 6 == fruits.index("banana", 4)

    fruits.reverse()
    assert ["banana", "apple", "kiwi", "banana", "pear", "apple", "orange"] == fruits

    fruits.append("grape")
    assert ["banana", "apple", "kiwi", "banana", "pear", "apple", "orange", "grape"] == fruits

    fruits.sort()
    assert ["apple", "apple", "banana", "banana", "grape", "kiwi", "orange", "pear"] == fruits

    assert "pear" == fruits.pop()


def test_list_stack():
    stack = [3, 4, 5]
    stack.append(6)
    stack.append(7)
    assert [3, 4, 5, 6, 7] == stack

    assert 7 == stack.pop()
    assert [3, 4, 5, 6] == stack

    assert 6 == stack.pop()
    assert 5 == stack.pop()
    assert [3, 4] == stack


def test_list_queue():
    queue = deque(["Eric", "John", "Michael"])
    queue.append("Terry")
    queue.append("Graham")
    assert "Eric" == queue.popleft()
    assert "John" == queue.popleft()

    assert ["Michael", "Terry", "Graham"] == list(queue)


def test_list_comprehensions():
    squares = []
    for x in range(10):
        squares.append(x ** 2)
    assert [0, 1, 4, 9, 16, 25, 36, 49, 64, 81] == squares

    # 列表推导式的方括号内包含以下内容：一个表达式，后面为一个 for 子句，然后，是零个或多个 for 或 if 子句。
    # 结果是由表达式依据 for 和 if 子句求值计算而得出一个新列表。
    squares = [x ** 2 for x in range(10)]
    assert [0, 1, 4, 9, 16, 25, 36, 49, 64, 81] == squares

    combs = [(x, y) for x in [1, 2, 3] for y in [3, 1, 4] if x != y]
    assert [(1, 3), (1, 4), (2, 3), (2, 1), (2, 4), (3, 1), (3, 4)] == combs

    vec = [-4, -2, 0, 2, 4]
    assert [-8, -4, 0, 4, 8] == [x * 2 for x in vec]
    # 过滤列表以排除负数
    assert [0, 2, 4] == [x for x in vec if x >= 0]
    # 对所有元素应用一个函数
    assert [4, 2, 0, 2, 4] == [abs(x) for x in vec]

    freshfruit = ["  banana", "  loganberry ", "passion fruit  "]
    # 在每个元素上调用一个方法
    assert ["banana", "loganberry", "passion fruit"] == [weapon.strip() for weapon in freshfruit]

    assert [(0, 0), (1, 1), (2, 4), (3, 9), (4, 16), (5, 25)] == [(x, x ** 2) for x in range(6)]

    vec = [[1, 2, 3], [4, 5, 6], [7, 8, 9]]
    # 使用两个 'for' 来展平嵌套的列表
    assert [1, 2, 3, 4, 5, 6, 7, 8, 9] == [num for elem in vec for num in elem]

    assert ["3.1", "3.14", "3.142", "3.1416", "3.14159"] == [
        str(round(pi, x)) for x in range(1, 6)
    ]


def test_del():
    a = [-1, 1, 66.25, 333, 333, 1234.5]

    del a[0]
    assert [1, 66.25, 333, 333, 1234.5] == a

    del a[2:4]
    assert [1, 66.25, 1234.5] == a

    del a[:]
    assert [] == a

    del a


def test_tuples():
    t = 12345, 54321, "hello!"
    assert 12345 == t[0]
    assert (12345, 54321, "hello!") == t

    # 元组可以嵌套
    u = t, (1, 2, 3, 4, 5)
    assert ((12345, 54321, "hello!"), (1, 2, 3, 4, 5)) == u

    # 元组是不可变对象
    try:
        t[0] = 8888
    except TypeError:
        assert True

    # 元组是 immutable 不可变的，一般包含异质元素，通过解包或索引访问。
    # 列表是 mutable 可变的，一般包含同质元素，可迭代访问。
    v = ([1, 2, 3], [3, 2, 1])
    v[0].append(4)
    v[1].append(5)
    assert ([1, 2, 3, 4], [3, 2, 1, 5]) == v

    # 元组解包，多重赋值其实只是元组打包和序列解包的组合
    x, y, z = t
    assert x == 12345
    assert y == 54321
    assert z == "hello!"

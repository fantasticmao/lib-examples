# https://docs.python.org/zh-cn/3/tutorial/datastructures.html
from collections import deque

import math


def test_list():
    fruits = ["orange", "apple", "pear", "banana", "kiwi", "apple", "banana"]
    assert fruits.count("apple") == 2
    assert fruits.count("tangerine") == 0
    assert fruits.index("banana") == 3
    assert fruits.index("banana", 4) == 6

    fruits.reverse()
    assert fruits == ["banana", "apple", "kiwi", "banana", "pear", "apple", "orange"]

    fruits.append("grape")
    assert fruits == ["banana", "apple", "kiwi", "banana", "pear", "apple", "orange", "grape"]

    fruits.sort()
    assert fruits == ["apple", "apple", "banana", "banana", "grape", "kiwi", "orange", "pear"]

    assert fruits.pop() == "pear"


def test_list_stack():
    stack = [3, 4, 5]
    stack.append(6)
    stack.append(7)
    assert stack == [3, 4, 5, 6, 7]

    assert 7 == stack.pop()
    assert stack == [3, 4, 5, 6]

    assert stack.pop() == 6
    assert stack.pop() == 5
    assert stack == [3, 4]


def test_list_queue():
    queue = deque(["Eric", "John", "Michael"])
    queue.append("Terry")
    queue.append("Graham")
    assert queue.popleft() == "Eric"
    assert queue.popleft() == "John"

    assert list(queue) == ["Michael", "Terry", "Graham"]


def test_list_comprehensions():
    squares = []
    for x in range(10):
        squares.append(x ** 2)
    assert squares == [0, 1, 4, 9, 16, 25, 36, 49, 64, 81]

    # 列表推导式的方括号内包含以下内容：一个表达式，后面为一个 for 子句，然后，是零个或多个 for 或 if 子句。
    # 结果是由表达式依据 for 和 if 子句求值计算而得出一个新列表。
    squares = [x ** 2 for x in range(10)]
    assert squares == [0, 1, 4, 9, 16, 25, 36, 49, 64, 81]

    combs = [(x, y) for x in [1, 2, 3] for y in [3, 1, 4] if x != y]
    assert combs == [(1, 3), (1, 4), (2, 3), (2, 1), (2, 4), (3, 1), (3, 4)]

    vec = [-4, -2, 0, 2, 4]
    assert [x * 2 for x in vec] == [-8, -4, 0, 4, 8]
    # 过滤列表以排除负数
    assert [x for x in vec if x >= 0] == [0, 2, 4]
    # 对所有元素应用一个函数
    assert [abs(x) for x in vec] == [4, 2, 0, 2, 4]

    freshfruit = ["  banana", "  loganberry ", "passion fruit  "]
    # 在每个元素上调用一个方法
    assert [weapon.strip() for weapon in freshfruit] == ["banana", "loganberry", "passion fruit"]

    assert [(x, x ** 2) for x in range(6)] == [(0, 0), (1, 1), (2, 4), (3, 9), (4, 16), (5, 25)]

    vec = [[1, 2, 3], [4, 5, 6], [7, 8, 9]]
    # 使用两个 for 来展平嵌套的列表
    assert [num for elem in vec for num in elem] == [1, 2, 3, 4, 5, 6, 7, 8, 9]

    assert [str(round(math.pi, x)) for x in range(1, 6)] == [
        "3.1",
        "3.14",
        "3.142",
        "3.1416",
        "3.14159",
    ]


def test_del():
    a = [-1, 1, 66.25, 333, 333, 1234.5]

    del a[0]
    assert a == [1, 66.25, 333, 333, 1234.5]

    del a[2:4]
    assert a == [1, 66.25, 1234.5]

    del a[:]
    assert a == []

    del a


def test_tuples():
    t = 12345, 54321, "hello!"
    assert t[0] == 12345
    assert t == (12345, 54321, "hello!")

    # 元组可以嵌套
    u = t, (1, 2, 3, 4, 5)
    assert u == ((12345, 54321, "hello!"), (1, 2, 3, 4, 5))

    # 元组是不可变对象
    try:
        t[0] = 8888
        assert False
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


def test_set():
    # 创建集合用花括号或 set() 函数。注意，创建空集合只能用 set()，不能用 {}，因为使用 {} 创建的结果是空字典
    basket = {"apple", "orange", "apple", "pear", "orange", "banana"}
    assert {"apple", "orange", "pear", "orange", "banana"} == basket

    assert "orange" in basket
    assert "crabgrass" not in basket

    a = set("abracadabra")
    b = set("alacazam")

    # 差集：存在于 a 中但不存在于 b 中的字母
    assert a - b == {"b", "r", "d"}
    # 或运算：存在于 a 或 b 中或两者中皆有的字母
    assert a | b == {"a", "b", "c", "d", "r", "l", "m", "z"}
    # 与运算：同时存在于 a 和 b 中的字母
    assert a & b == {"a", "c"}
    # 异或运算：
    assert a ^ b == {"b", "d", "r", "l", "m", "z"}

    # 集合推导式
    a = {x for x in "abracadabra" if x not in "abc"}
    assert a == {"r", "d"}


def test_dict():
    # 字典是以键来索引的，键可以是任何不可变类型，字符串和数字总是可以作为键。
    # 元组在其仅包含字符串、数字或元组时也可以作为键，如果一个元组直接或间接地包含了任何可变对象，则不可以用作键。
    tel = {"jack": 4098, "sape": 4139}
    tel["guido"] = 4127
    assert tel == {"jack": 4098, "sape": 4139, "guido": 4127}
    assert tel["jack"] == 4098

    # 使用 d[key] 提取不存在的键的值会引发 KeyError
    # 使用 get(key) 提取不存在的键的值会返回 None
    try:
        print(tel["irv"])
        assert False
    except KeyError:
        assert True
    assert tel.get("irv") is None

    # 通过 del 可以删除键值对
    del tel["sape"]
    tel["irv"] = 4127
    assert tel == {"jack": 4098, "guido": 4127, "irv": 4127}

    # 使用 list(d) 返回该字典中所有键的列表，按插入次序排列
    assert list(tel) == ["jack", "guido", "irv"]
    assert sorted(tel) == ["guido", "irv", "jack"]

    # 使用关键字 in 检查字典里是否存在某个键
    assert "guido" in tel
    assert "sape" not in tel

    # dict() 构造函数可以直接用键值对序列创建字典
    assert dict([("sape", 4139), ("guido", 4127), ("jack", 4098)]) == {
        "jack": 4098,
        "sape": 4139,
        "guido": 4127,
    }
    assert dict(jack=4098, sape=4139, guido=4127) == {"jack": 4098, "sape": 4139, "guido": 4127}

    # 字典推导式
    assert {x: x ** 2 for x in (2, 4, 6)} == {2: 4, 4: 16, 6: 36}


def test_looping():
    # 循环字典时，使用 items() 方法同时提取键及其对应的值
    knights = {"gallahad": "the pure", "robin": "the brave"}
    result = [k + ": " + v for k, v in knights.items()]
    assert result == ["gallahad: the pure", "robin: the brave"]

    # 循环序列时，使用 enumerate() 函数可以同时取出位置索引和对应的值
    result = [str(i) + ": " + v for i, v in enumerate(["tic", "tac", "toe"])]
    assert result == ["0: tic", "1: tac", "2: toe"]

    # 循环两个或多个序列时，使用 zip() 函数可以将其内的元素一一匹配
    questions = ["name", "quest", "favorite color"]
    answers = ["lancelot", "the holy grail", "blue"]
    result = [q + ": " + a for q, a in zip(questions, answers)]
    assert result == ["name: lancelot", "quest: the holy grail", "favorite color: blue"]

    # 逆向循环序列时，使用 reversed() 函数
    result = [i for i in reversed(range(1, 10, 2))]
    assert result == [9, 7, 5, 3, 1]

    # 指定顺序循环序列时，使用 sorted() 函数，在不改动原序列的基础上，返回一个重新的序列
    basket = ["apple", "orange", "apple", "pear", "orange", "banana"]
    result = [i for i in sorted(basket)]
    assert result == ["apple", "apple", "banana", "orange", "orange", "pear"]

    # 去重循环序列时，使用 set() 函数
    basket = ["apple", "orange", "apple", "pear", "orange", "banana"]
    result = sorted(set(basket))
    assert result == ["apple", "banana", "orange", "pear"]

    # 一般来说，在循环中修改列表的内容时，创建新列表比较简单，且安全
    raw_data = [56.2, float("NaN"), 51.7, 55.3, 52.5, float("NaN"), 47.8]
    filtered_data = []
    for value in raw_data:
        if not math.isnan(value):
            filtered_data.append(value)
    assert filtered_data == [56.2, 51.7, 55.3, 52.5, 47.8]


def test_conditions():
    # 运算符 in 和 not in 用于执行确定一个值是否存在（或不存在）于某个容器中的成员检测。
    # 运算符 is 和 is not 用于比较两个对象是否是同一个对象。
    # 比较操作可以用布尔运算符 and 和 or 组合，并且比较操作（或其他布尔运算）的结果都可以用 not 取反。
    pass

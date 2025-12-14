# https://docs.python.org/zh-cn/3/tutorial/controlflow.html#defining-functions

from lang import fibo


def test_fib():
    fib_100 = fibo.fib(100)
    assert fib_100 == [0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89]


def f(pos1, pos2, /, pos_or_kwd, *, kwd1, kwd2):
    # pos1, pos2: 仅限位置参数
    # pos_or_kwd: 位置或关键字参数
    # *args: 可变位置参数，包含形参列表之外的位置参数元组
    # kwd1, kwd2: 仅限关键字参数
    # kwargs: 可变关键字参数，包含形参列表之外的关键字参数字典
    pass


def in_clause(collection: list[str], *args, left="(", right=")", separator=",", **kwargs) -> str:
    # 默认值只计算一次，默认值为列表、字典或类实例等可变对象时，会产生与该规则不同的结果。
    for arg in args:
        collection.append(str(arg))

    text = separator.join(collection)

    text = left + text + right

    if kwargs.get("end") is not None:
        text = text + kwargs.get("end")
    return text


def test_in_clause():
    text = in_clause(["Tom", "Bob", "Anni"])
    assert text == "(Tom,Bob,Anni)"

    text = in_clause(["Tom", "Bob", "Anni"], 250)
    assert text == "(Tom,Bob,Anni,250)"

    text = in_clause(["Tom", "Bob", "Anni"], 250, left="[", right="]", separator=", ")
    assert text == "[Tom, Bob, Anni, 250]"

    text = in_clause(["Tom", "Bob", "Anni"], 250, separator="-", end="\n")
    assert text == "(Tom-Bob-Anni-250)\n"


def test_unpacking():
    # 函数调用要求独立的位置参数，但实参在列表或元组里时，要执行相反的操作。
    # 如果这些参数不是独立的，则要在调用函数时，使用 * 操作符把实参从列表或元组解包出来
    args = [3, 6]
    s = sum(range(*args))
    assert s == 3 + 4 + 5

    # 使用 ** 操作符把实参从字典解包出来
    kwargs = {"left": "[", "right": "]", "separator": ", "}
    text = in_clause(["Tom", "Bob", "Anni"], **kwargs)
    assert text == "[Tom, Bob, Anni]"


def make_incrementor(n):
    return lambda x: x + n


def test_lambda():
    foo = make_incrementor(42)
    assert foo(0) == 42
    assert foo(1) == 43

    pairs = [(1, "one"), (2, "two"), (3, "three"), (4, "four")]
    pairs.sort(key=lambda e: e[1])
    assert pairs == [(4, "four"), (1, "one"), (3, "three"), (2, "two")]

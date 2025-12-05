# https://docs.python.org/zh-cn/3/tutorial/introduction.html#using-python-as-a-calculator


def test_numbers():
    assert 2 + 2 == 4
    assert 50 - 5 * 6 == 20

    # / 运算符总是返回一个浮点数
    assert (50 - 5 * 6) / 4 == 5.0
    assert 8 / 5 == 1.6

    assert not 17 / 3 == 5
    # 向下取整除法运算会丢弃小数部分
    assert 17 // 3 == 5
    # % 运算符计算余数
    assert 17 % 3 == 2

    # ** 运算符计算幂
    assert 5 ** 2 == 25
    assert 2 ** 10 == 1024

    width = 20
    height = 5 * 9
    assert width * height == 900

    # 混合类型运算数的运算会把整数转换为浮点数
    assert 4 * 3.75 - 1 == 14.0


def test_text():
    print("spam eggs")
    print("Paris rabbit got your back :)! Yay!")
    print("1975")

    # 使用 \ 转义特殊字符
    print("doesn't")
    print("doesn't")
    print('"Yes," they said.')
    print('"Yes," they said.')
    print('"Isn\'t," they said.')

    print("First line.\nSecond line.")
    # 如果不希望前置 \ 的字符转义成特殊字符，可以使用 原始字符串，在引号前添加 r
    print("C:\some\name")
    print(r"C:\some\name")

    # 三重引号的字符串字面值，可以跨越多行
    print(
        """\
Usage: thingy [OPTIONS]
-h                        Display this usage message
-H hostname               Hostname to connect to
    """
    )

    # 字符串可以用 + 合并（粘到一起），也可以用 * 重复
    assert (3 * "un" + "ium") == "unununium"

    # 相邻的两个或多个字符串字面值，会自动合并
    assert "Py" "thon" == "Python"
    print("Put several strings within parentheses " "to have them joined together.")

    prefix = "Py"
    assert prefix + "thon" == "Python"

    # 字符串支持索引 （下标访问）
    assert "Python"[0] == "P"
    assert "Python"[5] == "n"
    assert "Python"[-1] == "n"
    assert "Python"[-2] == "o"
    assert "Python"[0:2] == "Py"
    assert "Python"[2:5] == "tho"
    assert "Python"[:2] == "Py"
    assert "Python"[4:] == "on"
    assert "Python"[-2:] == "on"
    assert "Python"[4:42] == "on"
    assert "Python"[42:] == ""

    # len() 返回字符串的长度
    assert len("Python") == 6


def test_lists():
    squares = [1, 4, 9, 16, 25]

    # 列表也支持索引和切片
    assert squares[0] == 1
    assert squares[-1] == 25
    assert squares[-3:] == [9, 16, 25]

    # 列表支持合并操作
    assert squares + [36, 49, 64, 81, 100] == [1, 4, 9, 16, 25, 36, 49, 64, 81, 100]

    #  列表是可变类型，其内容可以改变
    cubes = [1, 8, 27, 65, 125]
    cubes[3] = 64
    assert cubes == [1, 8, 27, 64, 125]

    cubes.append(216)  # 添加 6 的立方
    cubes.append(7 ** 3)  # 和 7 的立方
    assert cubes == [1, 8, 27, 64, 125, 216, 343]

    # 将一个列表赋值给一个变量时，该变量将引用现有的列表。通过一个变量对列表所做的任何更改，都会被引用它的所有其他变量看到。
    rgb = ["Red", "Green", "Blue"]
    rgba = rgb
    assert id(rgb) == id(rgba)

    # 切片操作返回包含请求元素的新列表
    correct_rgba = rgba[:]
    correct_rgba[-1] = "Alpha"
    assert correct_rgba == ["Red", "Green", "Alpha"]
    assert rgba == ["Red", "Green", "Blue"]

    # 为切片赋值可以改变列表大小，甚至清空整个列表
    letters = ["a", "b", "c", "d", "e", "f", "g"]
    letters[2:5] = ["C", "D", "E"]
    assert letters == ["a", "b", "C", "D", "E", "f", "g"]
    letters[2:5] = []
    assert letters == ["a", "b", "f", "g"]
    letters[:] = []
    assert letters == []

    # # len() 返回列表的长度
    letters = ["a", "b", "c", "d"]
    assert len(letters) == 4

    # 列表支持嵌套
    a = ["a", "b", "c"]
    n = [1, 2, 3]
    x = [a, n]
    assert x == [["a", "b", "c"], [1, 2, 3]]
    assert x[0] == ["a", "b", "c"]
    assert x[0][1] == "b"

# https://docs.python.org/zh-cn/3/tutorial/introduction.html#using-python-as-a-calculator


def test_numbers():
    assert 4 == 2 + 2
    assert 20 == 50 - 5 * 6

    # / 运算符总是返回一个浮点数
    assert 5.0 == (50 - 5 * 6) / 4
    assert 1.6 == 8 / 5

    assert not 5 == 17 / 3
    # 向下取整除法运算会丢弃小数部分
    assert 5 == 17 // 3
    # % 运算符计算余数
    assert 2 == 17 % 3

    # ** 运算符计算幂
    assert 25 == 5 ** 2
    assert 1024 == 2 ** 10

    width = 20
    height = 5 * 9
    assert 900 == width * height

    # 混合类型运算数的运算会把整数转换为浮点数
    assert 14.0 == 4 * 3.75 - 1


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
    assert "unununium" == (3 * "un" + "ium")

    # 相邻的两个或多个字符串字面值，会自动合并
    assert "Python" == "Py" "thon"
    print("Put several strings within parentheses " "to have them joined together.")

    prefix = "Py"
    assert "Python" == prefix + "thon"

    # 字符串支持索引 （下标访问）
    assert "P" == "Python"[0]
    assert "n" == "Python"[5]
    assert "n" == "Python"[-1]
    assert "o" == "Python"[-2]
    assert "Py" == "Python"[0:2]
    assert "tho" == "Python"[2:5]
    assert "Py" == "Python"[:2]
    assert "on" == "Python"[4:]
    assert "on" == "Python"[-2:]
    assert "on" == "Python"[4:42]
    assert "" == "Python"[42:]

    # len() 返回字符串的长度
    assert 6 == len("Python")


def test_lists():
    squares = [1, 4, 9, 16, 25]

    # 列表也支持索引和切片
    assert 1 == squares[0]
    assert 25 == squares[-1]
    assert [9, 16, 25] == squares[-3:]

    # 列表支持合并操作
    assert [1, 4, 9, 16, 25, 36, 49, 64, 81, 100] == squares + [36, 49, 64, 81, 100]

    #  列表是可变类型，其内容可以改变
    cubes = [1, 8, 27, 65, 125]
    cubes[3] = 64
    assert [1, 8, 27, 64, 125] == cubes

    cubes.append(216)  # 添加 6 的立方
    cubes.append(7 ** 3)  # 和 7 的立方
    assert [1, 8, 27, 64, 125, 216, 343] == cubes

    # 将一个列表赋值给一个变量时，该变量将引用现有的列表。通过一个变量对列表所做的任何更改，都会被引用它的所有其他变量看到。
    rgb = ["Red", "Green", "Blue"]
    rgba = rgb
    assert id(rgb) == id(rgba)

    # 切片操作返回包含请求元素的新列表
    correct_rgba = rgba[:]
    correct_rgba[-1] = "Alpha"
    assert ["Red", "Green", "Alpha"] == correct_rgba
    assert ["Red", "Green", "Blue"] == rgba

    # 为切片赋值可以改变列表大小，甚至清空整个列表
    letters = ["a", "b", "c", "d", "e", "f", "g"]
    letters[2:5] = ["C", "D", "E"]
    assert ["a", "b", "C", "D", "E", "f", "g"] == letters
    letters[2:5] = []
    assert ["a", "b", "f", "g"] == letters
    letters[:] = []
    assert [] == letters

    # # len() 返回列表的长度
    letters = ["a", "b", "c", "d"]
    assert 4 == len(letters)

    # 列表支持嵌套
    a = ["a", "b", "c"]
    n = [1, 2, 3]
    x = [a, n]
    assert [["a", "b", "c"], [1, 2, 3]] == x
    assert ["a", "b", "c"] == x[0]
    assert "b" == x[0][1]

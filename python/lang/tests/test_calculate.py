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
    assert 25 == 5**2
    assert 1024 == 2**10

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
    print(3 * "un" + "ium")

    # 相邻的两个或多个字符串字面值，会自动合并
    print("Py" "thon")
    print("Put several strings within parentheses " "to have them joined together.")

    prefix = "Py"
    print(prefix + "thon")

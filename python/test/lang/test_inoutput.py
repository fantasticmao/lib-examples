import math


def test_formatted_str_literal():
    text = f"The value of pi is approximately {math.pi:.3f}."
    assert text == "The value of pi is approximately 3.142."

    # 在 ':' 后传递整数，为该字段设置最小字符宽度，常用于列对齐
    table = {"Sjoerd": 4127, "Jack": 4098, "Dcab": 7678}
    text = [f"{name:10} ==> {phone:10d}" for name, phone in table.items()]
    assert text == [
        "Sjoerd     ==>       4127",
        "Jack       ==>       4098",
        "Dcab       ==>       7678",
    ]

    animals = "eels"
    text = f"My hovercraft is full of {animals}."
    assert text == "My hovercraft is full of eels."

    text = f"My hovercraft is full of {animals!r}."
    assert text == "My hovercraft is full of 'eels'."

    # = 说明符可被用于将一个表达式扩展为表达式文本、等号再加表达式求值结果的形式。
    bugs = "roaches"
    count = 13
    area = "living room"
    text = f"Debugging {bugs=} {count=} {area=}"
    assert text == "Debugging bugs='roaches' count=13 area='living room'"


def test_str_format():
    text = "{0} and {1}".format("spam", "eggs")
    assert text == "spam and eggs"

    text = "{1} and {0}".format("spam", "eggs")
    assert text == "eggs and spam"

    text = "This {food} is {adjective}.".format(food="spam", adjective="absolutely horrible")
    assert text == "This spam is absolutely horrible."

    text = "The story of {0}, {1}, and {other}.".format("Bill", "Manfred", other="Georg")
    assert text == "The story of Bill, Manfred, and Georg."

    table = {"Sjoerd": 4127, "Jack": 4098, "Dcab": 8637678}
    text = "Jack: {0[Jack]}; Sjoerd: {0[Sjoerd]}; Dcab: {0[Dcab]}".format(table)
    assert text == "Jack: 4098; Sjoerd: 4127; Dcab: 8637678"

    text = "Jack: {Jack}; Sjoerd: {Sjoerd}; Dcab: {Dcab}".format(**table)
    assert text == "Jack: 4098; Sjoerd: 4127; Dcab: 8637678"

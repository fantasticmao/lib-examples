# https://docs.python.org/zh-cn/3/tutorial/classes.html


import dataclasses

from lang import clazz


def test_complex():
    x = clazz.Complex(3.0, -4.5)
    assert x.r == 3.0
    assert x.i == -4.5


def test_dog():
    d = clazz.Dog("Fido")
    e = clazz.Dog("Buddy")
    d.add_trick("roll over")
    e.add_trick("play dead")

    assert d.name == "Fido"
    assert e.name == "Buddy"
    assert d.tricks == ["roll over"]
    assert e.tricks == ["play dead"]


def test_warehouse():
    w1 = clazz.Warehouse()
    assert w1.purpose == "storage"
    assert w1.region == "west"

    w2 = clazz.Warehouse()
    w2.region = "east"
    assert w2.region == "east"


def test_employee():
    john = clazz.Employee("john", "computer lab", 1000)
    assert john.dept == "computer lab"
    assert john.salary == 1000

    try:
        john.salary = 2000
        assert False
    except dataclasses.FrozenInstanceError:
        assert True

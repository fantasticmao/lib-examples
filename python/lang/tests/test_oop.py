import lang.oop as t


def test_person():
    person = t.Person("毛毛", 24, 177)
    person.to_string()
    assert "毛毛" == person.name
    # print(person.__age)
    print(person.height)
    assert 177 == person.height
    print(person._how_old_are_you())
    # print(person.__who_do_you_love())  # 调用 private 方法
    print(t.Person.class_name())  # 调用 static 方法
    # print(person.job())  # 调用 abstract 方法
    person._email = "maomao8017@gamil.com"  # 动态添加属性
    print(person._email)

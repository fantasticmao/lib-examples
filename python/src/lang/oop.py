# Python 练习 - 面向对象


class Person:
    # 通过 __slots__ 限定 Person 类的属性，只对当前类有效（对子类无效）
    __slots__ = ("name", "__age", "_height", "_phone", "_email")

    def __init__(self, name, age, height):
        """
        1. __init__ 相当于构造方法
        2. Python 的属性和方法只有 public 和 private 两种作用域
        3. 通过在属性/方法名称前加两个下划线（__），表示属性的 private 作用域
        4. 人为约定在属性/方法名称前加一个下划线（_），表示属性的 protected 作用域
        """
        self.name = name  # public 属性
        self.__age = age  # private 属性
        self._height = height  # protected 属性

    # 定义 getter 方法
    @property
    def height(self):
        return self._height

    # 定义 setter 方法
    @height.setter
    def height(self, height):
        self._height = height

    # 定义 public 方法
    def to_string(self):
        attributes = [attr for attr in dir(self) if not attr.startswith("__")]
        return f"{self.__class__.__name__}: {', '.join(attributes)}"

    # 定义 protected 方法
    def _how_old_are_you(self):
        return self.__age

    # 定义 private 方法
    def __who_do_you_love(self):
        return "Anni"

    # 定义 static 方法
    @staticmethod
    def class_name():
        return "Person"

    # 定义 abstract 方法
    def job(self):
        raise NotImplementedError


class Adult(Person):
    def __init__(self, person, job):
        # 调用父类构造方法
        super().__init__(person.name, -1, person.height)
        self._job = job

    # override 方法
    @property
    def job(self):
        return self._job


def main():
    person = Person("毛毛", 24, 177)
    person.to_string()
    print(person.name)
    # print(person.__age)
    print(person.height)
    person.height = 180
    print(person.height)
    print(person._how_old_are_you())
    # print(person.__who_do_you_love())  # 调用 private 方法
    print(Person.class_name())  # 调用 static 方法
    # print(person.job())  # 调用 abstract 方法
    person._email = "maomao8017@gamil.com"  # 动态添加属性
    print(person._email)

    adult = Adult(person, "programmer")
    adult.to_string()  # 调用 override 方法
    print(adult.job)


if __name__ == "__main__":
    main()

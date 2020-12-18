# Python 练习 - 字符串和常用数据结构

import math


# hello world
def hello():
    username = str(input("请输入用户名称: "))
    print("你好，{}".format(username))


# 计算圆的周长和面积
def calculate_circle():
    radius = float(input("请输入圆的半径: "))
    print("圆的周长: {:.2f}".format(2 * math.pi * radius))
    print("圆的面积: {:.2f}".format(math.pi * radius ** 2))


# 判断是否为闰年
def is_leap_year():
    year = int(input("请输入年份: "))
    is_leap_year = (year % 4 == 0 and year % 100 != 0) or year % 400 == 0
    if is_leap_year:
        print("{} 是闰年".format(year))
    else:
        print("{} 不闰年".format(year))


# 循环输出，默认值参数
def loop_print_number(count=10):
    for n in range(count):
        print("n: {}".format(n))


# 可变参数
def add(*arg):
    total = 0
    for v in arg:
        total += v
    print("total: {}".format(total))


# 字符串操作
def print_str():
    message = "hello world"
    print((message + " ") * 3)
    print(message + r"\n")
    print("in: {}".format("ll" in message))
    print("str[2]: {}".format(message[2]))
    print("str[2:5]: {}".format(message[2:5]))
    print("len: {}".format(len(message)))
    print("title: {}".format(message.title()))
    print("upper: {}".format(message.upper()))
    print("find: {}".format(message.find("ll")))
    print("index: {}".format(message.index("ll")))
    print("startswith: {}".format(message.startswith("hello")))
    print("endswith: {}".format(message.endswith("world")))


# 列表操作
def print_list():
    list = [1, 3, 5, 7]
    print(list * 3)
    print("len(list): {}".format(len(list)))
    print("list[0]: {}".format(list[0]))
    print("list[-1]: {}".format(list[-1]))
    for i in range(len(list)):
        print("by index: {}".format(list[i]))
    for e in list:
        print("by element: {}".format(e))
    list.append(9)
    print("list.append(9): {}".format(list))
    list.extend([11, 13])
    # list = list + [11, 13]
    print("list.extend([11, 13]): {}".format(list))
    list.remove(9)
    print("list.remove(9): {}".format(list))
    list.pop(0)
    print("list.pop(0): {}".format(list))
    print("sorted(list, reverse=True): {}".format(sorted(list, reverse=True)))
    print("推导式语法创建列表: {}".format([x for x in range(10)]))


# 元组操作
def print_tuple():
    """
    1. tuple 内部的元素是不可变的
    2. 可以将 tuple 转换成 list，再修改内部元素
    3. tuple 在创建时间和占用空间上面都优于 list
    """
    tuple = ("毛毛", 24, 177)
    print(tuple)
    print(list(tuple))


# 集合操作
def print_set():
    """
    1. set 内部元素是不可重复的
    2. set 支持 & 、|、-、^、<=、>= 元算
    """
    set = {1, 2, 3, 4}
    print(set)
    print("len(set): {}".format(len(set)))
    set.add(4)
    print("set.add(4): {}".format(set))
    set.add(5)
    print("set.add(5): {}".format(set))
    set.pop()
    print("set.pop(): {}".format(set))
    set.discard(3)
    print("set.discard(3): {}".format(set))
    print("推导式语法创建集合: {}".format({x for x in range(10)}))
    print("{{1, 2}} & {{2, 3}}: {}".format({1, 2} & {2, 3}))
    print("{{1, 2}} | {{2, 3}}: {}".format({1, 2} | {2, 3}))
    print("{{1, 2}} - {{2, 3}}: {}".format({1, 2} - {2, 3}))
    print("{{1, 2}} ^ {{2, 3}}: {}".format({1, 2} ^ {2, 3}))
    print("{{1, 2}} <= {{2}}: {}".format({1, 2} <= {2}))
    print("{{1, 2}} >= {{2}}: {}".format({1, 2} >= {2}))


# 字典操作
def print_dict():
    """
    1. dict 内部元素是 key-value 键值对
    """
    dict = {"name": "毛毛", "age": 24, "height": 177}
    print(dict)
    print('dict["name"]: {}'.format(dict["name"]))
    dict.popitem()
    print("dict.popitem(): {}".format(str(dict)))
    dict.pop("name")
    print('dict.pop("name"): {}'.format(dict))
    print("推导式语法创建字典: {}".format({x: x * 2 for x in range(10)}))


# main 函数
def main():
    print_dict()


# 定义模块入口函数
if __name__ == "__main__":
    main()

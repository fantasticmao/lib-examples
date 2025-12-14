# Python 练习 - 文件

import time

path = "../../resources/test.txt"


# 相当于 try-catch-finally 读取文件
def read_file_1():
    try:
        file = open(path, "r", encoding="utf-8")
        print(file.read())
    except FileNotFoundError:
        print("{} 文件不存在".format(path))
    finally:
        file.close()


# 相当于 try()-catch 读取文件
def read_file_2():
    try:
        with open(path, "r", encoding="utf-8") as file:
            print(file.read())
    except FileNotFoundError:
        print("{} 文件不存在".format(path))


def read_file_3():
    try:
        with open(path, "r", encoding="utf-8") as file:
            for line in file:
                print(line, end="")
                time.sleep(0.1)
    except FileNotFoundError:
        print("{} 文件不存在".format(path))


# 相当于 java.nio.file.Files.readAllLines()
def read_file_4():
    try:
        with open(path, "r", encoding="utf-8") as file:
            lines = file.readlines()
        print(len(lines))
    except FileNotFoundError:
        print("{} 文件不存在".format(path))


# truncate 形式写入文件
def write_file_1():
    try:
        with open(path, "w", encoding="utf-8") as file:
            file.write("hello world\n")
    except FileNotFoundError:
        print("{} 文件不存在".format(path))


# append 形式写入文件
def write_file_2():
    try:
        with open(path, "a", encoding="utf-8") as file:
            file.write("hello world\n")
    except FileNotFoundError:
        print("{} 文件不存在".format(path))


def main():
    write_file_1()


if __name__ == "__main__":
    main()

# Python 练习 - 标准库

import math
import os
import random
import re
import sys
from datetime import datetime


def main():
    print(f"当前工作目录: {os.getcwd()}")
    print("执行系统命令 cal: ")
    os.system("cal")
    print(f"命令行参数: {sys.argv}")
    sys.stdout.write("sys.stdout.write\n")
    sys.stderr.write("sys.stderr.write\n")
    print("math.pi: {:.8f}".format(math.pi))
    print("random: {}".format(random.choice(range(10))))
    print("re.match: {}".format(re.findall(r"\d", "1a2b3c4d5e6f7g8")))
    print("datetime: {}".format(datetime.now()))


if __name__ == "__main__":
    main()

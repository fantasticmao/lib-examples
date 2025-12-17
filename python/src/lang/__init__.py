# https://docs.python.org/zh-cn/3/tutorial/modules.html

# 模块是包含 Python 定义和语句的文件，其文件名是模块名加后缀名 `.py`。
# 在模块内部，通过全局变量 `__name__` 可以获取模块名。

# 当导入一个模块时，解释器首先会在 sys.builtin_module_names 中搜索具有该名称的内置模块。
# 如果未找到时，它将在 sys.path 所给出的目录列表中搜索对应名称的 .py 文件。
# sys.path 是从这些位置初始化的:
#   1. 被命令行直接运行的脚本所在的目录（或未指定文件时的当前目录）
#   2. PYTHONPATH（目录列表，与 shell 变量 PATH 的语法一样）
#   3. 依赖于安装的默认值（按照惯例包括一个 site-packages 目录，由 site 模块处理）

# 包是通过使用「带点号模块名」来构造 Python 模块命名空间的一种方式。
# 需要有 `__init__.py` 文件才能让 Python 将包含该文件的目录当作包来处理。

# 使用 `from package import item` 时，item 可以是包的子模块或子包，也可以是包中定义的函数、类或变量等其他名称。
# `import` 语句首先测试包中是否定义了 item；如果未在包中定义，则假定 item 是模块，并尝试加载。如果找不到 item，则触发 ImportError 异常。

# 使用 `import item.subitem.subsubitem` 句法时，除最后一项外，每个 item 都必须是包，最后一项可以是模块或包。

# 包的显式索引：如果包的 `__init__.py` 代码定义了列表`__all__`，运行 `from package import *` 时，它就是被导入的模块名列表。
__all__ = ["fibo"]

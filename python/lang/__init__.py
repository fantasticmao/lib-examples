# https://docs.python.org/zh-cn/3/tutorial/modules.html

# 模块是包含 Python 定义和语句的文件，其文件名是模块名加后缀名 `.py`。
# 在模块内部，通过全局变量 `__name__` 可以获取模块名。

# 包是通过使用「带点号模块名」来构造 Python 模块命名空间的一种方式。
# 需要有 `__init__.py` 文件才能让 Python 将包含该文件的目录当作包来处理。

# 使用 `from package import item` 时，item 可以是包的子模块或子包，也可以是包中定义的函数、类或变量等其他名称。
# `import` 语句首先测试包中是否定义了 item；如果未在包中定义，则假定 item 是模块，并尝试加载。如果找不到 item，则触发 ImportError 异常。

# 使用 `import item.subitem.subsubitem` 句法时，除最后一项外，每个 item 都必须是包，最后一项可以是模块或包。

# 包的显式索引：如果包的 `__init__.py` 代码定义了列表`__all__`，运行 `from package import *` 时，它就是被导入的模块名列表。
__all__ = ["fibo"]

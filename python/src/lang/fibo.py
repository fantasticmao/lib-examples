def fib(n: int) -> list:
    # 函数内的第一条语句是字符串时，该字符串就是文档字符串。
    """Return a list containing the Fibonacci series up to n."""

    # 函数在执行时使用函数局部变量符号表，所有函数变量赋值都存在局部符号表中；
    # 引用变量时，首先在局部符号表里查找变量，然后是外层函数的局部符号表，再是全局符号表，最后是内置的名称符号表。

    # 在调用函数时,会将实际参数引入到被调用函数的局部符号表中；
    # 实参是使用按值调用来传递的,其中的值始终是对象的引用,而不是对象的值。

    result = []
    a, b = 0, 1
    while a < n:
        result.append(a)
        a, b = b, a + b
    return result

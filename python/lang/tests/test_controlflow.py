# https://docs.python.org/zh-cn/3/tutorial/controlflow.html

def test_if():
    def f(x: int):
        if x < 0:
            return "Negative changed to zero"
        elif x == 0:
            return "Zero"
        elif x == 1:
            return "Single"
        else:
            return "More"

    assert "Zero" == f(0)
    assert "More" == f(3)


def test_for():
    words = ["cat", "window", "defenestrate"]
    for w in words:
        print(w, len(w))


def test_range():
    nums = []
    for i in range(5):
        nums.append(i)
    assert [0, 1, 2, 3, 4] == nums

    nums = []
    for i in range(5, 10):
        nums.append(i)
    assert [5, 6, 7, 8, 9] == nums

    nums = []
    for i in range(0, 10, 3):
        nums.append(i)
    assert [0, 3, 6, 9] == nums

    text = ""
    a = ["Mary", "had", "a", "little", "lamb"]
    for i in range(len(a)):
        if i != len(a) - 1:
            text = text + a[i] + " "
        else:
            text = text + a[i]
    assert "Mary had a little lamb" == text

    count = sum(range(5))
    assert 10 == count


def test_break_else():
    prime_nums = []
    for n in range(2, 10):
        for x in range(2, n):
            if n % x == 0:
                print(f"{n} equals {x} * {n // x}")
                break
        # 在循环中 break 语句可能对应一个 else 子句。如果循环在未执行 break 的情况下结束，else 子句将会执行。
        else:
            # 循环到底未找到一个因数
            prime_nums.append(n)
    assert [2, 3, 5, 7] == prime_nums


def test_pass():
    def foo():
        pass

    foo()


def test_match():
    def http_error(status):
        match status:
            case 400:
                return "Bad request"
            case 401 | 403 | 404:
                return "Not allowed"
            case 418:
                return "I'm a teapot"
            case _:
                return "Something's wrong with the internet"

    assert "Not allowed" == http_error(404)
    assert "Something's wrong with the internet" == http_error(500)

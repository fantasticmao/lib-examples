# https://docs.python.org/zh-cn/3/tutorial/stdlib2.html#multi-threading
# https://docs.python.org/zh-cn/3/library/threading.html
#
# threading 模块提供了一种在单个进程内部并发地运行多个线程 (从进程分出的更小单位) 的方式。线程是一种对于非顺序依赖的多个任务进行解耦的技术。
# 多线程应用面临的主要挑战是，相互协调的多个线程之间需要共享数据或其他资源。为此，threading 模块提供了多个同步操作原语，包括线程锁、事件、条件变量和信号量。
# 尽管这些工具非常强大，但微小的设计错误却可以导致一些难以复现的问题。因此，实现多任务协作的首选方法是将所有对资源的请求集中到一个线程中，然后使用 queue 模块向该线程供应来自其他线程的请求。


import threading

from lang import concurrent


def test_safe_counter():
    count = 100
    times = 1000
    counter = concurrent.SafeCounter()

    def func():
        for _ in range(0, times):
            counter.increment()

    threads = [threading.Thread(target=func) for _ in range(0, count)]
    for t in threads:
        t.start()
    for t in threads:
        t.join()

    assert counter.count == count * times

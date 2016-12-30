并发
---
#### JDK5线程池框架

#### 捕获异常 
线程的本质特性，导致不能捕获从线程中逃逸的异常。为了解决这个问题，可以修改Executor生产线程的方式。
创建新的ThreadFactory，为每个新创建的线程绑定Thread.UncaughtExceptionHandler接口。
此接口是JDK1.5中提供，允许在每个线程绑定异常处理器。Thread.UncaughtExceptionHandler.uncaughtException()在线程因未捕获异常而终止时被调用。

#### 线程之间的协作
- wait()与notifyAll()
    1. wait()通常由另一个进程调用，使当前进程进入等待状态
    2. wait()释放锁，而sleep()和yield()并不释放锁
    3. wait()、notify()、notifyAll()是属于基类Object的一部分,且得写在同步代码块中
    4. 其本质是判断两个进程敏感的特定条件，并在条件不满足的情况下返回到wait()中。惯用while来编写
- notify()与notifyAll()
- 生产者与消费者
- 生产者-消费者与队列
- 任务间使用管道进行输入/输出

#### 死锁
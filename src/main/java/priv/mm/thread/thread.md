并发
---
#### JDK5线程池框架

#### 线程之间的协作
1. wait()与notifyAll()
- wait()通常由另一个进程调用，使当前进程进入等待状态
- wait()释放锁，而sleep()和yield()并不释放锁
- wait()、notify()、notifyAll()是属于基类Object的一部分,且得写在同步代码块中
- 其本质是判断两个进程敏感的特定条件，并在条件不满足的情况下返回到wait()中。惯用while来编写
2. notify()与notifyAll()<br>
3. 生产者与消费者<br>
4. 生产者-消费者与队列<br>
5. 任务间使用管道进行输入/输出<br>

#### 死锁
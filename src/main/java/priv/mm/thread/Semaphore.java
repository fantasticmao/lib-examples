package priv.mm.thread;

/**
 * Semaphore 信号量机制
 *
 * @author maodh
 * @see <a href="https://en.wikipedia.org/wiki/Semaphore_(programming)">wikipedia</a>
 * @see java.util.concurrent.Semaphore JDK 版实现
 * @since 08/02/2018
 */
public final class Semaphore {
    private int sem;

    public Semaphore(int sem) {
        this.sem = sem;
    }

    public int getSem() {
        return sem;
    }

    // PV 操作具有原子性

    public synchronized void p() {
        sem--;
        if (sem < 0) { // 占用资源，可能导致阻塞
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void v() {
        sem++;
        if (sem <= 0) {  // 释放资源，唤醒等待的线程
            this.notify();
        }
    }
}

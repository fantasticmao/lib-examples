package cn.fantasticmao.demo.java.lang.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * CallableAndFuture
 *
 * @author fantasticmao
 * @since 2016.10.01
 */
public class CallableAndFuture {
    private static class TaskWithResult implements Callable<String> {
        private int id;

        TaskWithResult(int id) {
            this.id = id;
        }

        @Override
        public String call() {
            return "result of TaskWithResult " + id;
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        List<Future<String>> results = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            results.add(exec.submit(new TaskWithResult(i)));
        }
        for (Future<String> fs : results) {
            try {
                System.out.println(fs.get());
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        exec.shutdownNow();
    }
}

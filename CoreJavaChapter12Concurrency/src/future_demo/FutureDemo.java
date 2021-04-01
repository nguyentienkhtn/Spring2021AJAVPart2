package future_demo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureDemo {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<Long> callable  = new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return fib(50);
            }
            public Long fib(int value) {
                if (value == 0 || value == 1)
                    return 1L;
                else
                    return fib(value - 1) + fib(value - 2);
            }
        };
        FutureTask<Long> futureTask = new FutureTask<>(callable);
        Thread thread = new Thread(futureTask);
        thread.start();

        System.out.println(futureTask.get());
        System.out.println("helloooooooooooo");




    }
}

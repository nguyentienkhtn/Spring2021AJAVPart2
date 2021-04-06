package fork_join_demo;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.function.DoublePredicate;

public class ForkJoinTest {
    public static void main(String[] args) {
        final int SIZE = 1_000_000;
        var numbers = new double[SIZE];
        for(int i = 0; i < SIZE; i++)
            numbers[i] = Math.random();
        var counter = new Counter(numbers, 0, numbers.length, x -> (x > 0.5));
        var pool = new ForkJoinPool();
        pool.invoke(counter);
        System.out.println(counter.join());
    }


    private static class Counter extends RecursiveTask<Integer> {
        public static final int THRESH_HOLD = 1000;
        private double [] values;
        private int to;
        private int from;
        private DoublePredicate filter;
        public Counter(double[] numbers, int from, int to, DoublePredicate filter) {
            this.values = numbers;
            this.from = from;
            this.to = to;
            this.filter = filter;
        }

        @Override
        protected Integer compute() {
            if( to - from < THRESH_HOLD) {
                int count = 0;
                for (int i = from; i < to; i++)
                    if(filter.test(values[i]))
                        count++;
                return count;
            }
            else
            {
                int mid = (from + to)/2;
                var first = new Counter(values, from, mid, filter);
                var last = new Counter(values, mid, to, filter);
                invokeAll(first,last);
                return first.join() + last.join();

        }
    }
}
}

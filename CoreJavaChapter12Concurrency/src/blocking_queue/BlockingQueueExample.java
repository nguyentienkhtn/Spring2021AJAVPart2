package blocking_queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

public class BlockingQueueExample {
   static BlockingQueue<Integer> blockingQueue;
    public static void main(String[] args) {
      blockingQueue
                = new LinkedBlockingQueue<>(10);

        Runnable intProducer = () -> {
            try {
                while (true) {
                    blockingQueue.put(ThreadLocalRandom.current().nextInt());
                    Thread.sleep(20);
                }
            }
            catch (InterruptedException e)
            {}
        };
        Thread producer = new Thread(intProducer);
        producer.start();

        for (int i = 0; i < 1000000; i++)
        {
            Runnable consumer = () ->{
                try {
                    while (true) {
                        System.out.println(Thread.currentThread().getName() + " " +
                                blockingQueue.take());
                        Thread.sleep(20);
                    }
                }
                catch (InterruptedException e){}


            };
            new Thread(consumer).start();
        }
    }
}

package executor_demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class CallableDemo {
    public static void main(String[] args) {
        Callable<Long> searchTask = new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return occurence(new File("abc.txt"), "hello");
            }
        };
    }
    public static Long occurence(File f, String keyword) {
        try(Scanner sc = new Scanner(f)){
            Long count = 0L;
            while(sc.hasNextLine())
                if(sc.nextLine().equals(keyword)) count += 1;
            return count;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return  0L;
        }


    }

}

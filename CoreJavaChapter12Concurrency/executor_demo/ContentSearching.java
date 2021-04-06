package executor_demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.*;
import java.util.concurrent.*;

public class ContentSearching {
    public static void main(String[] args) {
        try(Scanner in = new Scanner(System.in)){
            System.out.println("Input a directory: ");
            String dir = in.nextLine();
            System.out.println("Input a keyword to search: ");
            String keyword = in.nextLine();
            Set<File> filesToSearch = listFileName(dir);
            System.out.println("The number of files to search: " + filesToSearch.size());
            var tasks = new ArrayList<Callable<Long>>(filesToSearch.size());
            for(File file: filesToSearch)
            {
                Callable<Long> task = () -> occurrence(file, keyword);
                tasks.add(task);
            }
            ExecutorService executorService = Executors.newFixedThreadPool(2);
            List<Future<Long>> results = executorService.invokeAll(tasks);
            long total = 0;
            for(Future<Long> result: results)
                total += result.get();
            System.out.println("total occurences: " + total);
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("hello");
                }
            });


        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static Long occurrence(File file, String keyword)  {
        try( var in = new Scanner(file))
        {
            long count = 0;
            while(in.hasNext())
                if(in.next().equals(keyword))
                    count++;
                return count;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return 0L;
        }
    }

    private static Set<File> listFileName(String dirName) throws InterruptedException {
        Set<File> result = new HashSet<>();
        File dir = new File(dirName);
        File[] files = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String filename) {
                return filename.endsWith(".txt");
            }
        });
        for (File file : files)
            result.add(file);
        return  result;

    }
}

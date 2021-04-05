package executor_demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class ContentSearching {
    private static final BlockingQueue<File> fileBlockingQueue = new ArrayBlockingQueue<File>(10);
    private static final File DUMMY = new File(""); // end signal

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Input a directory: ");
            String path = scanner.nextLine();
            System.out.println("Input a content to search");
            String keyword = scanner.nextLine();
            Runnable searchTask = () -> {
                try {
                    listFileName(path);
                    fileBlockingQueue.put(DUMMY);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            };
            new Thread(searchTask).start(); // start file listing thread
            List<Callable<String>> tasks = new ArrayList<>(10);
            for (int i = 0; i < tasks.size(); i++) {
                Callable<String> task = new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        File file = fileBlockingQueue.take();
                        if (file.equals(DUMMY)) {
                            fileBlockingQueue.put(DUMMY);
                            return "";
                        } else return search(file, keyword);
                    }
                };
                tasks.add(task);
            }
            ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
            List<Future<String>> results = executor.invokeAll(tasks);
            for (Future<String> result : results)
                System.out.println(result.get());


        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }

    private static String search(File file, String keyword) {
        System.out.println(" searching: " + file.toURI().toString());
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.contains(keyword)) {

                    return "Found in the file " + file.toURI();
                }

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "not found";
    }

    private static void listFileName(String dirName) throws InterruptedException {
        File dir = new File(dirName);
        File[] files = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String filename) {
                return filename.endsWith(".txt");
            }
        });
        for (File file : files)
            fileBlockingQueue.put(file);

    }
}

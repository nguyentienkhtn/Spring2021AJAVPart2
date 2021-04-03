package blocking_queue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;

import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ContentSearching {
    private static BlockingQueue<File> fileBlockingQueue=  new ArrayBlockingQueue<File>(10);
    private static File DUMMY = new File(""); // end signal
    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(System.in)){
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
            for(int i = 0; i < Runtime.getRuntime().availableProcessors(); i++)
            {
                Runnable searcher = () -> {
                    try {
                        boolean done = false;
                        while(!done){
                            File file = fileBlockingQueue.take();

                            if(file.equals(DUMMY))
                            {
                                done = true;
                                fileBlockingQueue.put(new File(""));
                            }
                            else
                                search(file, keyword);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                };
                new Thread(searcher).start();
            }
        }

    }

    private static void search(File file, String keyword) {
        System.out.println(" searching: " + file.toURI().toString());
        try(Scanner sc = new Scanner(file)){
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                if (line.contains(keyword)){
                    System.out.println("Found in the file " + file.toURI());
                    return;
                }

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void listFileName(String dirName) throws InterruptedException {
        File dir = new File(dirName);
        File[] files =  dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String filename)
            { return filename.endsWith(".txt"); }
        } );
        for(File file: files)
            fileBlockingQueue.put(file);

    }
}

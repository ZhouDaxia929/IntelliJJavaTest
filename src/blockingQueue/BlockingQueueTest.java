package blockingQueue;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;
/**
 * Created by ZhouDaxia on 2016-10-18.
 * 枚举所有子目录下的文件并把它们放在一个阻塞队列中。
 * 我们使用了一个小技巧在工作结束后终止这个应用程序，
 * 为了发出完成信号，枚举线程放置了一个虚拟对象到队列中。当搜索线程取到这个虚拟对象时，线程将其放回并终止。
 */


public class BlockingQueueTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter base directory (e.g. /usr/local/jdk1.6.0/src): ");
        String directory = in.nextLine();
        System.out.println("Enter keyword (e.g. volatile): ");
        String keyword = in.nextLine();

        final int FILE_QUEUE_SIZE = 10;
        final int SEARCH_THREADS = 100;

        BlockingQueue<File> queue = new ArrayBlockingQueue<>(FILE_QUEUE_SIZE);

        FileEnumerationTask enumerator = new FileEnumerationTask(queue, new File(directory));
        new Thread(enumerator).start();
        for(int i = 1; i <= SEARCH_THREADS; i++)
            new Thread(new SearchTask(queue, keyword)).start();
    }
}

//枚举出该路径及其子路径下的所有文件
class FileEnumerationTask implements Runnable{
    public static File DUMMY = new File("");
    private BlockingQueue<File> queue;
    private File startingDirectory;

    public FileEnumerationTask(BlockingQueue<File> queue, File startingDirectory){
        this.queue = queue;
        this.startingDirectory = startingDirectory;
    }

    //递归枚举所有文件
    public void enumerate(File directory) throws InterruptedException{
        File[] files = directory.listFiles();
        for(File file : files){
            if(file.isDirectory())
                enumerate(file);
            else
                queue.put(file);
        }
    }

    public void run(){
        try{
            enumerate(startingDirectory);
            queue.put(DUMMY);
        }
        catch(InterruptedException e){

        }
    }
}

//在文件中找关键字
class SearchTask implements Runnable{
    private BlockingQueue<File> queue;
    private String keyword;

    public SearchTask(BlockingQueue<File> queue, String keyword){
        this.queue = queue;
        this.keyword = keyword;
    }

    public void search(File file) throws IOException{
        try(Scanner in = new Scanner(file)){
            int lineNumber = 0;
            while (in.hasNextLine()){
                lineNumber++;
                String line = in.nextLine();
                if(line.contains(keyword))
                    System.out.printf("%s:%d:%s%n", file.getPath(), lineNumber, line);
            }
        }
    }

    public void run(){
        try{
            boolean done = false;
            while(!done){
                File file = queue.take();
                if(file == FileEnumerationTask.DUMMY){
                    queue.put(file);
                    done = true;
                }
                else
                    search(file);
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        catch(InterruptedException e){

        }
    }
}


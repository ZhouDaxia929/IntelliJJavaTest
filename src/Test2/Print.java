package Test2;

/**
 * Created by ggggg on 2016/10/23.
 */
public class Print {

    public int OK = 0;

    private static int num = 1;
    public synchronized void Print_1to52() throws InterruptedException{
        if(OK == 2)
            wait();
        else{
            System.out.print(num++);
            OK = (OK + 1) % 3;
            notifyAll();
        }
    }

    private static char word = 'a';
    public synchronized void Print_AtoZ() throws InterruptedException{
        if(OK == 0 || OK == 1)
            wait();
        else{
            System.out.print(word);
            word++;
            OK = (OK + 1) % 3;
            notifyAll();
        }
    }
}

package Test2;

/**
 * Created by ggggg on 2016/10/23.
 */
public class PrintNum implements Runnable {
    Print pt;
    public PrintNum(Print pt){
        this.pt = pt;
    }
    public void run(){
        try{
            int i = 0;
            while(i < 78){
                pt.Print_1to52();
                i++;
            }
        }
        catch (InterruptedException e){

        }
    }
}

package Test2;
/**
 * Created by ggggg on 2016/10/23.
 */
public class PrintWord implements Runnable{

    Print pt;
    PrintWord(Print pt){
        this.pt = pt;
    }

    public  void run(){
        try{
            int i = 0;
            while(i < 52){
                pt.Print_AtoZ();
                i++;
            }
        }
        catch (InterruptedException e){

        }
    }
}

package Test2;
<<<<<<< HEAD

public class TestJava {
    public static void main(String[] args){
        Print pt = new Print();
        PrintWord PW = new PrintWord(pt);
        Thread t = new Thread(PW);
        t.start();

        PrintNum PN = new PrintNum(pt);
        Thread t2 = new Thread(PN);
        t2.start();
=======
public class TestJava{
    public volatile static int count = 0;
    public static void inc(){
        //延时1毫秒，使得结果明显
        try{
            Thread.sleep(1);
        }
        catch(InterruptedException e){

        }
        count++;
    }

    public static void main(String[] args){
        //同时启动1000个线程，去进行i++计算，看看实际结果
        for(int i = 0; i < 1000; i++){
            new Thread(new Runnable(){
                @Override
                public void run(){
                    TestJava.inc();
                }
            }).start();
        }

        //这里每次运行的值都可能不同，可能为1000
        System.out.println("运行结果：Counter.count = " + TestJava.count);
>>>>>>> origin/master
    }
}

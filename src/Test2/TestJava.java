package Test2;

public class TestJava {
    public static void main(String[] args){
        Print pt = new Print();
        PrintWord PW = new PrintWord(pt);
        Thread t = new Thread(PW);
        t.start();

        PrintNum PN = new PrintNum(pt);
        Thread t2 = new Thread(PN);
        t2.start();
    }
}

package unsynch;

/**
 * Created by ZhouDaxia on 2016-10-10.
 */
public class TransferRunnable implements Runnable {
    private Bank bank;
    private int fromAccount;
    private double maxAmount;
    private int DELAY = 10;

    public TransferRunnable(Bank b, int from, double max){
        bank = b;
        fromAccount = from;
        maxAmount = max;
    }

    public void run(){
        try{
            int i = 0;
            while(i++ < 1000){
                int toAccount = (int)(bank.size() * Math.random());
                double amount = maxAmount * Math.random();
                bank.transfer(fromAccount, toAccount, amount);
                Thread.sleep((int)(DELAY * Math.random()));
            }
        }
        catch (InterruptedException e){

        }
    }
}

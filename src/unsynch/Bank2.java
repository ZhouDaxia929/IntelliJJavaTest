package unsynch;
import java.util.concurrent.locks.*;
//使用内部锁实现
/**
 * Created by ZhouDaxia on 2016-10-10.
 */
public class Bank2 {
    private final double[] accounts;

    public Bank2(int n, double initialBalance){
        accounts = new double[n];
        for(int i = 0; i < accounts.length; i++)
            accounts[i] = initialBalance;
    }

    public synchronized double getTotalBalance(){
            double sum = 0;
            for(double a : accounts)
                sum += a;
            return sum;
    }

    public synchronized void transfer(int from, int to, double amount) throws InterruptedException{
            while(accounts[from] < amount)
                wait();
            System.out.print(Thread.currentThread());
            accounts[from] -= amount;
            System.out.printf(" %10.2f from %d to %d", amount, from, to);
            accounts[to] += amount;
            System.out.printf(" Total Balance: %10.2f%n",getTotalBalance());
            notifyAll();
    }

    public int size(){
        return accounts.length;
    }

}


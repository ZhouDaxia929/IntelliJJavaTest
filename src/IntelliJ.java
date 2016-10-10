import java.math.*;
import java.util.ArrayList;
import java.util.Iterator;
public class IntelliJ{
    public static void main(String args[]){
        BigInteger a = new BigInteger("1");
        BigInteger b = new BigInteger("1");
        BigInteger c = new BigInteger("1");
        BigInteger d = new BigInteger("1");
        ArrayList arr = new ArrayList(200);
        for(int i = 1; i <= 100; i++){
            arr.add(a);
            b = b.add(c);
            a = a.multiply(b);
        }
        Iterator iter = arr.iterator();
        while (iter.hasNext()) {
            c = c.add((BigInteger)iter.next());
        }
        System.out.println(c.subtract(d));
    }
}
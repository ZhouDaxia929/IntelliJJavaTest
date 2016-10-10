package CollectionTest.priorityQueue;
import java.util.*;
/**
 * Created by ZhouDaxia on 2016-10-03.
 */
public class PriorityQueueTest {
    public static void main(String[] args) {
        PriorityQueue<GregorianCalendar> pq = new PriorityQueue<>();
        //pq.add
        System.out.println("Iteratin(new GregorianCalendar(1905,Calendar.DECEMBER,9));\n" +
                "        pq.add(new GregorianCalendar(1815,Calendar.DECEMBER,10));\n" +
                "        pq.add(new GregorianCalendar(1903,Calendar.DECEMBER,3));\n" +
                "        pq.add(new GregorianCalendar(1910,Calendar.JUNE,22));\ng over elements...");
        for(GregorianCalendar date : pq)
            System.out.println(date.get(Calendar.YEAR));
        System.out.println("Removing elements...");
        while(!pq.isEmpty())
            System.out.println(pq.remove().get(Calendar.YEAR));
    }
}

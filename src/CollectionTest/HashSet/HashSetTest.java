package CollectionTest.HashSet;
import java.util.*;
/**
 * Created by ZhouDaxia on 2016-10-03.
 */
public class HashSetTest {
    public static void main(String[] args) {
        SortedSet<String> sorter = new TreeSet<>(); //TreeSet implements SortedSet
        sorter.add("Bob");
        sorter.add("Amy");
        sorter.add("Carl");
        for(String s : sorter)
            System.out.println(s);
    }
}

package CollectionTest.treeSet;
import java.util.*;
/**
 * Created by ZhouDaxia on 2016-10-03.
 */
public class TreeSetTest {
    public static void main(String[] args) {
        SortedSet<Item> parts = new TreeSet<>();
        parts.add(new Item("Toaster", 1234));
        parts.add(new Item("Widget", 4562));
        parts.add(new Item("Modem", 9912));
        System.out.println(parts);

        SortedSet<Item> sortByDescription = new TreeSet<>(new Comparator<Item>() {
            @Override
            public int compare(Item a, Item b) {
                String descA = a.getDescription();
                String descB = b.getDescription();
                return descA.compareTo(descB);
            }
        });
        sortByDescription.addAll(parts);
        System.out.println(sortByDescription);
    }
}

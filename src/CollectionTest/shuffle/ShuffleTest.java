package CollectionTest.shuffle;
import java.util.*;
/**
 * Created by ZhouDaxia on 2016-10-06.
 */
public class ShuffleTest {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        for(int i = 1; i <= 49; i++)
            numbers.add(i);
        Collections.shuffle(numbers);
        List<Integer> winningCombination = numbers.subList(0, 6);
        Collections.sort(winningCombination);
        System.out.println(winningCombination);
        Stack<Integer> st = new Stack<>();
        st.push(2);
        st.push(3);
        System.out.println(st.peek());
        st.insertElementAt(1,1);
        System.out.println(st);
    }
}

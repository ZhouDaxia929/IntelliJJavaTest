package Test1;
import java.util.Scanner;
/**
 * Created by ZhouDaxia on 2016-11-16.
 */
public class GuessNumberTest {
    public static void guessNumber() {
        Scanner sc = new Scanner(System.in);
        int a = 0, b = 0;
        String strA = "1234";
        String strB = sc.next();

        for(int i=0 ; i<strA.length() ; ++i) {
            for(int j=0 ; j<strA.length() ; ++j) {
                if(strA.charAt(i) == strB.charAt(i)) {
                    ++a;
                    break;
                }
                if(strA.charAt(i) == strB.charAt(j)) {
                    ++b;
                    break;
                }
            }
        }
        System.out.println(a + "A" + b + "B");
    }
}







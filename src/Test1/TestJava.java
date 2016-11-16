package Test1;

/**
 * Created by ZhouDaxia on 2016-11-11.
 */
public class TestJava {
    public static void main(String[] args) {
        test1(); //测试第一道题
        test2(); //测试第二道题
        test3(args[0]);
    }

    public static void test1(){
        String s = "ZhouDaxia";
        System.out.println(StringTest.changeUpperOrLower(s));
        StringTest.searchIndex(s, "a");
        System.out.println(StringTest.chooseNum("asdjfe124.23vjie124.45"));
        System.out.println(StringTest.searchNum("aab", "abc"));
    }

    public static void test2(){
        GuessNumberTest.guessNumber();
    }

    public static void test3(String str){
        CountDaysTest.countDays(str);
    }
}

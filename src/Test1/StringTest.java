package Test1;
import java.util.*;
/**
 * Created by ZhouDaxia on 2016-11-16.
 */
public class StringTest {
    public static int index = 0;
    /**
     *��Сдת��
     */
    public static String changeUpperOrLower(String str){
        char a[] = str.toCharArray();
        for(int i = 0; i < a.length; i++){
            if(Character.isLowerCase(a[i]))
                a[i] = Character.toUpperCase(a[i]);
            else if(Character.isUpperCase(a[i]))
                a[i] = Character.toLowerCase(a[i]);
        }
        return String.valueOf(a);
    }
    /*
    * �����Ӵ������˼����Լ�λ��
    */
    public static void searchIndex(String str, String s){
        int sum = 0;
        while(index != -1){
            index = str.indexOf(s, index + 1);
            sum++;
            if(index != -1)
                System.out.println("��" + sum + "����" + s + ", λ��" + index);
        }
        System.out.println("�ܹ�������" + (sum - 1) + "��");
    }
    /*
    * ���Ҳ���ȡ�ַ����е�����
    */
    public static StringBuffer chooseNum(String str){
        StringBuffer s = new StringBuffer("");
        Scanner input = new Scanner(str);
        input.useDelimiter("[^0123456789.]+");
        while(input.hasNext()){
            try{
                s.append(input.nextDouble());
            }
            catch(InputMismatchException e){

            }
        }
        return s;
    }
    /*
    * �����ظ����ַ�����
    */
    public static int searchNum(String str1, String str2){
        int sum = 0;
        char a[] = str1.toCharArray();
        char b[] = str2.toCharArray();
        Set<Character> set1 = new HashSet<>();
        Set<Character> set2 = new HashSet<>();
        for(int i = 0; i < a.length; i++){
            set1.add(a[i]);
        }
        for(int i = 0; i < b.length; i++){
            set2.add(b[i]);
        }
        Iterator<Character> pos = set1.iterator();
        while(pos.hasNext()){
            if(set2.contains(pos.next()))
                sum++;
        }
        return sum;
    }
}

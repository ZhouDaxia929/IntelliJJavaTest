package Test1;
import java.util.*;
/**
 * Created by ZhouDaxia on 2016-11-16.
 */
public class CountDaysTest {
    public static void countDays(String year){
        Random random = new Random();
        Calendar c,d;
        int a = random.nextInt(13);
        int b = random.nextInt(13);
        CalendarBean cb = new CalendarBean();
        cb.setYear(Integer.parseInt(year));
        cb.setMonth(a);
        c = cb.getCalendar();
        System.out.println();
        cb.setMonth(b);
        d = cb.getCalendar();
        System.out.println("\n" + "相差天数:");
        if(a>b)
            System.out.println((c.getTimeInMillis()-d.getTimeInMillis())/(1000 * 60 * 60 * 24));
        else
            System.out.println((d.getTimeInMillis()-c.getTimeInMillis())/(1000 * 60 * 60 * 24));
    }
}

class CalendarBean{
    int year = 0,month = 0;
    public void setYear(int year){
        this.year = year;
    }
    public void setMonth(int month){
        this.month = month;
    }
    public Calendar getCalendar(){
        char[] str = "日一二三四五六".toCharArray();
        for(char c:str){
            System.out.printf("%3c",c);
        }
        String[] a = new String[42];
        Calendar rili = Calendar.getInstance();
        rili.set(year,month-1,1);
        int weekDay = rili.get(Calendar.DAY_OF_WEEK)-1;
        int day = 0;
        if(month == 1||month == 3||month == 5||month == 7 ||month == 8||month == 10||month == 12)
            day = 31;
        if(month == 4||month == 6|| month == 9||month == 11)
            day = 30;
        if(month == 2){
            if(((year%4==0)&&(year%100!=0))||(year%400==0))
                day = 29;
            else day = 28;
        }
        for(int i = 0; i < weekDay ; i++){
            a[i] = " ";
        }
        for(int i = weekDay,n=1; i < weekDay+day; i++){
            a[i] = String.valueOf(n);
            n++;
        }

        for(int i = weekDay+day; i < a.length ; i++)
            a[i] = " ";

        for(int i = 0; i < a.length; i++){
            if( i % 7 == 0 )
                System.out.println("");
            System.out.printf("%4s",a[i]);
        }
        return rili;
    }
}








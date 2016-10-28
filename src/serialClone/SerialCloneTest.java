package serialClone;
import java.io.*;
import java.util.*;
/**
 * Created by ZhouDaxia on 2016-10-28.
 */
public class SerialCloneTest {
    public static void main(String[] args) {
        Employee harry = new Employee("Harry Hacker", 35000, 1989, 10, 1);
        //clone harry
        Employee harry2 = (Employee)harry.clone();
        //mutate harry 转换，改变
        harry.raiseSalary(10);
        //mow harry and the clone are different
        System.out.println(harry);
        System.out.println(harry2);
    }
}

/**
 * A class whose clone method uses serialization
 */
class SerialCloneable implements Cloneable, Serializable{
    public Object clone(){
        try{
            //sava the object to a byte array
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bout);
            out.writeObject(this);
            out.close();

            //read a clone of the object from the byte array
            ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
            ObjectInputStream in = new ObjectInputStream(bin);
            Object ret = in.readObject();
            in.close();

            return ret;
        }
        catch(Exception e){
            return null;
        }
    }
}

class Employee extends SerialCloneable{
    private String name;
    private double salary;
    private Date hireDay;

    public Employee(String n, double s, int year, int month, int day){
        name = n;
        salary = s;
        GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
        hireDay = calendar.getTime();
    }

    public String getName(){
        return name;
    }

    public double getSalary(){
        return salary;
    }

    public Date getHireDay(){
        return hireDay;
    }

    public void raiseSalary(double byPercent){
        double raise = salary * byPercent / 100;
        salary += raise;
    }

    public String toString(){
        return getClass().getName() + "[name=" + name + ",salary=" + salary + ",hireDay=" + hireDay + "]";
    }
}
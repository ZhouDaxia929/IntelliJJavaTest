package randomAccess;
import textFile.Employee;

import java.io.*;
import java.util.*;
/**
 * Created by ZhouDaxia on 2016-10-28.
 */
public class RandomAccessTest {
    public static void main(String[] args) throws IOException {
        Employee[] staff = new Employee[3];
        staff[0] = new Employee("Carl Cracker", 75000, 1987, 12, 15);
        staff[1] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        staff[2] = new Employee("Tony Testers", 40000, 1990, 3, 15);

        try(DataOutputStream out = new DataOutputStream(new FileOutputStream("employee.dat"))){
            //save all employee records to the file employee.dat
            for(Employee e : staff)
                writeData(out, e);
        }
        try(RandomAccessFile in = new RandomAccessFile("employee.dat", "r")){
            //retrieve all records into a new array
            //compute the array size
            int n = (int)(in.length() / 68);
            //System.out.println(in.length());
            Employee[] newStaff = new Employee[n];

            //read employees in reverse order
            for(int i = n - 1; i >= 0; i--){
                newStaff[i] = new Employee();
                in.seek(i * 68);
                newStaff[i] = readData(in);
            }

            //print the newly read employee records
            for(Employee e : newStaff)
                System.out.println(e);
        }
    }

    public static void writeData(DataOutput out, Employee e) throws IOException{
        DataIO.writeFixedString(e.getName(), Employee.NAME_SIZE, out);
        out.writeDouble(e.getSalary());

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(e.getHireDay());
        out.writeInt(calendar.get(Calendar.YEAR));
        out.writeInt(calendar.get(Calendar.MONTH) + 1);
        out.writeInt(calendar.get(Calendar.DAY_OF_MONTH));
    }

    public static Employee readData(DataInput in) throws IOException{
        String name = DataIO.readFixedString(Employee.NAME_SIZE, in);
        double salary = in.readDouble();
        int y = in.readInt();
        int m = in.readInt();
        int d = in.readInt();
        return new Employee(name, salary, y, m - 1, d);
    }
}

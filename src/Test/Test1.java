package Test;

/**
 * Created by ggggg on 2016/9/25.
 */
public class Test1 {
    public static void main(String[] args) {
        Manager ceo = new Manager("Gus Greedy", 800000, 2003, 12, 15);
        Manager cfo = new Manager("Sid Sneaky", 600000, 2003, 12, 15);
        Pair<Manager> managerBuddies = new Pair<>(ceo, cfo);
        Pair<? extends Employee> wildcardBuddies = managerBuddies;
        System.out.println(wildcardBuddies.getFirst().toString());
        Employee ee = new Employee("Test", 80, 2013, 12, 15);
        Employee mm = new Employee("Si", 60, 2003, 12, 15);
        Employee cto = new Employee("Sid Sneaky", 600000, 2003, 12, 15);
        /*wildcardBuddies.setFirst(ee);
        wildcardBuddies.setFirst(mm);
        managerBuddies.setFirst(mm);
        managerBuddies.setFirst(ee);
        //wildcardBuddies.getFirst().toString();
        //wildcardBuddies.
        */
        Pair<Employee> pe = new Pair<>(ee, mm);
        Pair<? super Manager> ss = pe;
        //ss.setFirst(cto);
        ss.setFirst(ceo);
        //Manager cc = ss.getSecond();
        System.out.println(ss.getSecond().toString());
    }
}

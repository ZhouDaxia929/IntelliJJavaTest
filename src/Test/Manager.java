package Test;

/**
 * Created by ggggg on 2016/9/25.
 */
public class Manager extends Employee{
    private double bonus;
    private Employee secretary;
    public Manager(String n, double s, int year, int month, int day){
        super(n, s, year, month, day);
        bonus = 0;
    }
    public double getSalary(){
        double baseSalary = super.getSalary();
        return baseSalary + bonus;
    }
    public void setBonus(double b){
        bonus = b;
    }
    public void setSecretary(Employee e){secretary = e;}
    public boolean equals(Object otherObject){
        if(!super.equals(otherObject))
            return false;
        Manager other = (Manager) otherObject;
        return bonus == other.bonus;
    }
    public int hashCode(){
        return super.hashCode() + 17 * new Double(bonus).hashCode();
    }
    public String toString(){
        return super.toString() + "[bonus=" + bonus + "]";
    }
}
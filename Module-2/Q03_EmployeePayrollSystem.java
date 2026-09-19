import java.util.*;
public class Q03_EmployeePayrollSystem {
 static class Employee{String name,id;double basicSalary;Employee(String n,String i,double b){name=n;id=i;basicSalary=b;}double calculateSalary(){return basicSalary;}public String toString(){return "Employee "+name+" ("+id+") Salary: "+calculateSalary();}}
 static class Manager extends Employee{double bonus;Manager(String n,String i,double b,double x){super(n,i,b);bonus=x;}double calculateSalary(){return basicSalary+bonus;}public String toString(){return "Manager "+name+" ("+id+") Salary: "+calculateSalary();}}
 public static void main(String[]a){Scanner s=new Scanner(System.in);while(s.hasNextLine()){String[]p=s.nextLine().split(",");if(p.length==4)System.out.println(new Employee(p[1],p[2],Double.parseDouble(p[3])));else if(p.length==5)System.out.println(new Manager(p[1],p[2],Double.parseDouble(p[3]),Double.parseDouble(p[4])));}}
}

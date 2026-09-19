import java.util.*;
public class Q01_UniversityDepartmentProfessor {
 static class Professor { String name,id,specialization; Professor(String n,String i,String s){name=n;id=i;specialization=s;} public String toString(){return "Name: "+name+", ID: "+id+", Specialization: "+specialization;} }
 static class Department { String deptName,hodName; List<Professor> professors=new ArrayList<>(); Department(String d,String h){deptName=d;hodName=h;} void addProfessor(Professor p){professors.add(p);} public String toString(){StringBuilder s=new StringBuilder("Department: "+deptName+"\nHOD: "+hodName+"\nProfessors:\n"); for(Professor p:professors)s.append(p).append('\n'); return s.toString();} }
 public static void main(String[] a){Scanner sc=new Scanner(System.in);String[] d=sc.nextLine().split(",",2);Department x=new Department(d[0],d[1]);int n=Integer.parseInt(sc.nextLine());while(n-->0){String[] p=sc.nextLine().split(",",3);x.addProfessor(new Professor(p[0],p[1],p[2]));}System.out.print(x);}
}

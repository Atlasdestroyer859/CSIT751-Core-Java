import java.util.*;
public class Q02_FlightBookingSystem {
 static abstract class Flight{String no,airline;double fare;Flight(String n,String a,double f){no=n;airline=a;fare=f;}abstract double calculateFare();public String toString(){return "Flight No: "+no+" Airline: "+airline+" Fare: "+calculateFare();}}
 static class DomesticFlight extends Flight{DomesticFlight(String n,String a,double f){super(n,a,f);}double calculateFare(){return fare*1.10;}}
 static class InternationalFlight extends Flight{InternationalFlight(String n,String a,double f){super(n,a,f);}double calculateFare(){return fare*1.25;}}
 public static void main(String[] a){Scanner s=new Scanner(System.in);while(s.hasNextLine()){String[]p=s.nextLine().split(",",4);if(p.length<4)continue;Flight f=p[0].equalsIgnoreCase("Domestic")?new DomesticFlight(p[1],p[2],Double.parseDouble(p[3])):new InternationalFlight(p[1],p[2],Double.parseDouble(p[3]));System.out.println(f);}}
}

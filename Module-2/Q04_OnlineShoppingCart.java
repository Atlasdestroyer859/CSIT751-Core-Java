import java.util.*;
public class Q04_OnlineShoppingCart {
 static class Product{String name;double price;int qty;Product(String n,double p,int q){name=n;price=p;qty=q;}public String toString(){return name+" x"+qty+" = "+price*qty;}}
 static class Order{String id;List<Product>products=new ArrayList<>();Order(String i){id=i;}double total(){double x=0;for(Product p:products)x+=p.price*p.qty;return x;}public String toString(){StringBuilder s=new StringBuilder("Order ID: "+id+"\nProducts:\n");for(Product p:products)s.append(p).append('\n');return s+"Total: "+total();}}
 public static void main(String[]a){Scanner s=new Scanner(System.in);Order o=new Order(s.nextLine());int n=Integer.parseInt(s.nextLine());while(n-->0){String[]p=s.nextLine().split(",");o.products.add(new Product(p[0],Double.parseDouble(p[1]),Integer.parseInt(p[2])));}System.out.println(o);}
}

import java.util.Scanner;

public class Q02_DataTypeConversion {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter temperature in Celsius: ");
        int celsius = sc.nextInt();

        double fahrenheit = (celsius * 9.0 / 5.0) + 32;

        System.out.println("Celsius: " + celsius);
        System.out.println("Fahrenheit: " + fahrenheit);

        sc.close();
    }
}

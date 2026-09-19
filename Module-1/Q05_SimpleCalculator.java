import java.util.Scanner;

public class Q05_SimpleCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first number: ");
        double firstNumber = sc.nextDouble();

        System.out.print("Enter second number: ");
        double secondNumber = sc.nextDouble();

        System.out.print("Enter operator (+, -, *, /): ");
        char operator = sc.next().charAt(0);

        double result;

        if (operator == '+') {
            result = firstNumber + secondNumber;
            System.out.println("Result: " + result);
        } else if (operator == '-') {
            result = firstNumber - secondNumber;
            System.out.println("Result: " + result);
        } else if (operator == '*') {
            result = firstNumber * secondNumber;
            System.out.println("Result: " + result);
        } else if (operator == '/') {
            if (secondNumber != 0) {
                result = firstNumber / secondNumber;
                System.out.println("Result: " + result);
            } else {
                System.out.println("Cannot divide by zero.");
            }
        } else {
            System.out.println("Invalid operator.");
        }

        sc.close();
    }
}

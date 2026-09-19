import java.util.Scanner;

public class Q03_ArithmeticOperations {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first integer: ");
        int firstNumber = sc.nextInt();

        System.out.print("Enter second integer: ");
        int secondNumber = sc.nextInt();

        System.out.println("Addition: " + (firstNumber + secondNumber));
        System.out.println("Subtraction: " + (firstNumber - secondNumber));
        System.out.println("Multiplication: " + (firstNumber * secondNumber));

        if (secondNumber != 0) {
            System.out.println("Division: " + (firstNumber / secondNumber));
            System.out.println("Modulus: " + (firstNumber % secondNumber));
        } else {
            System.out.println("Division: Cannot divide by zero");
            System.out.println("Modulus: Cannot divide by zero");
        }

        sc.close();
    }
}

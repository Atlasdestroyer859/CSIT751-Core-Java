import java.util.Scanner;

public class Q09_FindMaximum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] numbers = new int[10];

        for (int i = 0; i < numbers.length; i++) {
            System.out.print("Enter integer " + (i + 1) + ": ");
            numbers[i] = sc.nextInt();
        }

        int maximum = numbers[0];

        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > maximum) {
                maximum = numbers[i];
            }
        }

        System.out.println("Maximum value: " + maximum);

        sc.close();
    }
}

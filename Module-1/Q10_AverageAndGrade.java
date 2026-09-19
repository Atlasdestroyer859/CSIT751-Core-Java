import java.util.Scanner;

public class Q10_AverageAndGrade {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] marks = new int[5];
        int total = 0;

        for (int i = 0; i < marks.length; i++) {
            System.out.print("Enter marks for subject " + (i + 1) + ": ");
            marks[i] = sc.nextInt();
            total += marks[i];
        }

        double average = total / 5.0;
        char grade;

        if (average >= 90) {
            grade = 'A';
        } else if (average >= 75) {
            grade = 'B';
        } else if (average >= 50) {
            grade = 'C';
        } else {
            grade = 'F';
        }

        System.out.println("\nAverage Marks: " + average);
        System.out.println("Grade: " + grade);

        sc.close();
    }
}

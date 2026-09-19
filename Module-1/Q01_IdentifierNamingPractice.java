import java.util.Scanner;

public class Q01_IdentifierNamingPractice {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter student name: ");
        String studentName = sc.nextLine();

        System.out.print("Enter roll number: ");
        int rollNumber = sc.nextInt();

        System.out.print("Enter marks in Subject 1: ");
        double subjectOneMarks = sc.nextDouble();

        System.out.print("Enter marks in Subject 2: ");
        double subjectTwoMarks = sc.nextDouble();

        System.out.print("Enter marks in Subject 3: ");
        double subjectThreeMarks = sc.nextDouble();

        System.out.println("\nStudent Details");
        System.out.println("Name: " + studentName);
        System.out.println("Roll Number: " + rollNumber);
        System.out.println("Subject 1 Marks: " + subjectOneMarks);
        System.out.println("Subject 2 Marks: " + subjectTwoMarks);
        System.out.println("Subject 3 Marks: " + subjectThreeMarks);

        sc.close();
    }
}

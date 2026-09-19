import java.util.Scanner;

public class Q04_VotingEligibility {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter age: ");
        int age = sc.nextInt();

        System.out.print("Are you a citizen? (true/false): ");
        boolean citizen = sc.nextBoolean();

        boolean eligible = (age >= 18) && citizen;

        if (eligible) {
            System.out.println("The person is eligible to vote.");
        } else {
            System.out.println("The person is not eligible to vote.");
        }

        // Logical OR example
        boolean needsVerification = (age < 18) || !citizen;
        System.out.println("Needs eligibility verification: " + needsVerification);

        sc.close();
    }
}

// Importing the scanner class for user input.
import java.util.Scanner;

// Main application class for the Orbit Mission Planner.
public class App {
    
    // Entry point of the application.
    public static void main(String[] args)
    {
        // Create a variable to hold the mission name.
        String missionName = "\nOrbit Mission Planner";

        // This is the entry point of your application.
        System.out.println(missionName);
        System.out.println();

        // Create a Scanner object to read user input.
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter a number.
        System.out.print("Enter a number: ");

        // Read the number from user input.
        int userNumber = scanner.nextInt();

        // Prompt the user to enter another number.
        System.out.print("Enter another number: ");

        // Read the second number from user input.
        int anotherNumber = scanner.nextInt();

        // Calculate the sum of the two numbers.
        int sum = userNumber + anotherNumber;

        // Display the result to the user.
        System.out.println("The sum of " + userNumber + " and " + anotherNumber + " is " + sum + ".");

        // Close the scanner to prevent resource leaks.
        scanner.close();

    } // End of main method.

} // End of App class.
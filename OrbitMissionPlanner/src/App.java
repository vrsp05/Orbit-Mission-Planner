// OrbitMissionPlanner/src/App.java
import java.util.Scanner;

// Main application class for the Orbit Mission Planner
public class App {
    
    // Main method to run the Orbit Mission Planner application
    public static void main(String[] args) {
        
        // Create a Scanner object for user input
        Scanner scanner = new Scanner(System.in);

        // Mission setup, ask the user for rocket details
        System.out.println("=== Orbit Mission Planner ===");
        System.out.print("Enter Rocket Name (e.g., Saturn V): ");
        String rName = scanner.nextLine();

        System.out.print("Enter Max Capacity (kg): ");
        double rMax = scanner.nextDouble();
        
        // Create the Rocket Object
        Rocket myRocket = new Rocket(rName, rMax);
        System.out.println("Rocket '" + myRocket.getName() + "' created.");

        // Payload loading loop
        while (true) {
            System.out.println("\nCurrent Weight: " + myRocket.getCurrentWeight() + " / " + myRocket.getMaxWeight());
            System.out.println("1. Add Payload");
            System.out.println("2. Launch Check & Exit");
            System.out.print("Select: ");
            
            // Get user choice  
            int choice = scanner.nextInt();
            
            if (choice == 1) {
                // Fix the "Scanner Skip" bug (standard Java quirk when switching numbers to strings)
                scanner.nextLine(); 

                System.out.print("Item Name: ");
                String pName = scanner.nextLine();
                System.out.print("Item Weight: ");
                double pWeight = scanner.nextDouble();

                PayloadItem item = new PayloadItem(pName, pWeight);
                
                // Try to add it
                boolean success = myRocket.addPayload(item);
                if (success) {
                    System.out.println("[OK] Loaded " + pName);
                } else {
                    System.out.println("[ERROR] Too heavy! Cannot load " + pName);
                }
            
            } else if (choice == 2) {
                break; // Exit the loop
            }
        }

        // --- 3. Final Status ---
        System.out.println("\n--- Mission Status ---");
        System.out.println("Total Mass: " + myRocket.getCurrentWeight());
        System.out.println("Launch Decision: " + myRocket.launchCheck());
        
        // Close the scanner
        scanner.close();
    
    } // End of main method

} // End of App class
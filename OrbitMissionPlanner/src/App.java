import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // --- 1. Mission Setup ---
        System.out.println("=== Orbit Mission Planner (MVP) ===");
        System.out.print("Enter Rocket Name (e.g., Saturn V): ");
        String rName = scanner.nextLine();

        System.out.print("Enter Max Capacity (kg): ");
        double rMax = scanner.nextDouble();
        
        // Create the Rocket Object
        Rocket myRocket = new Rocket(rName, rMax);
        System.out.println("Rocket '" + myRocket.getName() + "' created.");

        // --- 2. Payload Loading Loop ---
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
        
        scanner.close();
    }
}
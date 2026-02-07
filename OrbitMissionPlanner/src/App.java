// OrbitMissionPlanner/src/App.java
import java.util.ArrayList;
import java.util.Scanner;

// Main application class for the Orbit Mission Planner
public class App {
    
    // Main method to run the Orbit Mission Planner application
    public static void main(String[] args) {
        
        // Bring in the Scanner for user input and initialize the FileManager to handle mission logs
        Scanner scanner = new Scanner(System.in);
        FileManager fileManager = new FileManager("OrbitMissionDatabase.csv");
        
        // Main menu loop to allow users to start new missions, view logs, or exit the application.
        while (true)
        {   
            // Display the main menu options to the user.
            System.out.println("\n=== ORBIT MISSION PLANNER ===");
            System.out.println("1. Start New Mission");
            System.out.println("2. View Mission Logs");
            System.out.println("3. Exit");
            System.out.print("Select: ");
            
            // Get the user's choice for the main menu.
            int mainChoice = scanner.nextInt();
            scanner.nextLine(); // Fix scanner bug

            if (mainChoice == 1) {
                
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
                    
                    // Display current payload status and options to the user.
                    System.out.println("\nCurrent Weight: " + myRocket.getCurrentWeight() + " / " + myRocket.getMaxWeight());
                    System.out.println("1. Add Payload");
                    System.out.println("2. Launch Check & Exit");
                    System.out.print("Select: ");
            
                    // Get user choice  
                    int choice = scanner.nextInt();
            
                    if (choice == 1)
                    {
                        // Fix the "Scanner Skip" bug (standard Java quirk when switching numbers to strings)
                        scanner.nextLine(); 

                        System.out.print("Item Name: ");
                        String pName = scanner.nextLine();
                        System.out.print("Item Weight: ");
                        double pWeight = scanner.nextDouble();

                        PayloadItem item = new PayloadItem(pName, pWeight);
                
                        // Try to add it
                        boolean success = myRocket.addPayload(item);
                
                        // Provide feedback to the user based on whether the item was successfully added or if it exceeded the rocket's capacity.
                        if (success)
                        {
                            System.out.println("[OK] Loaded " + pName);

                        } // End of if
                        
                        // If the item cannot be added because it exceeds the rocket's capacity, inform the user with an error message.
                        else
                        {
                            // Print an error message if the item cannot be added due to weight constraints.
                            System.out.println("[ERROR] Too heavy! Cannot load " + pName);
                        
                        } // End of else
            
                    } // End of if
        
                    // If the user wants to launch and exit. 
                    else if (choice == 2)
                    {
                        break; // Exit the loop

                    } // End of else-if

                } // End of while loop for payload loading
                
                // Display the launch check decision.
                System.out.println("Launch Decision: " + myRocket.launchCheck());

                // Only offer to save if it is a "Go" (optional business rule)
                if (myRocket.launchCheck().equals("Go"))
                {   
                    scanner.nextLine();
                    
                    // Prompt user to save mission since it is viable.
                    System.out.print("Mission is viable! Save to logs? (y/n): ");
                    String saveChoice = scanner.nextLine();
                    
                    // Save if yes.
                    if (saveChoice.equalsIgnoreCase("y"))
                    {   

                        // 1. Add current rocket to the manager's list
                        fileManager.getAllLoggedMissions().add(myRocket);
                        
                        // 2. Save to disk
                        boolean saved = fileManager.saveMissionLogs();
                        
                        // Proide feedback based on saved status.
                        if (saved) {
                            System.out.println("[SUCCESS] Mission saved to database.");
                        } else {
                            System.out.println("[ERROR] Failed to save mission.");
                        } // End of if for saving mission

                    } // End of if for saving choice
                
                } // End of if for launch check
            
            } // End of if for starting a new mission

            // If the user wants to view all mission logs.
            else if (mainChoice == 2)
            {
                // Display header for mission archives
                System.out.println("--- MISSION ARCHIVES ---");

                // 1. Load from disk
                boolean loaded = fileManager.loadMissionDatabase();

                // Provide feedback based on loaded status.
                if (loaded) {

                    // 2. Iterate and Display
                    ArrayList<Rocket> history = fileManager.getAllLoggedMissions();
                    
                    // Check if no missions.
                    if (history.isEmpty())
                    {   
                        // Print a message if there are no missions found in the logs.
                        System.out.println("No missions found in logs.");
                    }
                    
                    // If there are missions, display them in a readable format.
                    else
                    {   
                        // For loop that displays each rocket's name and the number of payloads it has.
                        for (Rocket r : history)
                        {   
                            // Print the name of the rocket and the count of its payloads to the console.
                            System.out.println("Rocket: " + r.getName() + " | Payloads: " + r.getPayloadCount());

                            // You could add a nested loop here to print payload names if you want!
                            for (PayloadItem p : r.getCurrentPayloads())
                            {   
                                // Print the data of the payload of that rocket in a readable format.
                                System.out.println("  - " + p.getName() + " (" + p.getWeight() + " kg)");

                            } // End of nested for loop for payloads

                        } // End of for loop
                    
                    } // End of else for displaying missions

                } // End of if for loading mission logs
            
            } // End of else-if for viewing mission logs

            // If the user wants to exit the application.
            else if (mainChoice == 3)
            {   
                // Close the scanner
                scanner.close();
                // Print a goodbye message and exit the application.
                System.out.println("Exiting. Fly safe!");

                // Exit the application with a status code of 0 (indicating normal termination).
                System.exit(0);

            } // End of else-if for exiting the application
            
        } // End of main menu loop
    
    } // End of main method

} // End of App class
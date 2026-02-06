// This class is responsible for managing file operations for the Orbit Mission Planner application.

import java.util.ArrayList; // Import the ArrayList class
import java.io.File;  // Import the File class
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.Scanner;    

public class FileManager {
    
    // Declaring private fields.
    private String fileName;
    private ArrayList<Rocket> allLoggedMissions;

    // Constructor to initialize the FileManager with a file name.
    public FileManager(String fileName) {
        this.fileName = fileName;
        this.allLoggedMissions = new ArrayList<>();
    } // End of constructor

    // GETTERS & SETTERS

    // Getter method to retrieve all logged missions.
    public ArrayList<Rocket> getAllLoggedMissions() {
        return allLoggedMissions;
    } // End of getAllLoggedMissions method

    // MAIN METHODS

    // Method to help writing all missions to a file.
    public boolean saveMissionLogs() {

        // Try-catch block to handle potential IOExceptions.
        try {

            // Create a FileWriter object to write to a file.
            FileWriter myWriter = new FileWriter(fileName);

            // For loop to iterate through all logged missions.
            for (Rocket mission : allLoggedMissions) {

                // Write each mission's details to the file.
                myWriter.write("ROCKET," + mission.getName() + "," + mission.getMaxWeight() + "," + mission.getCurrentWeight() + "\n");
                
                // For loop to iterate through each payload item in the mission.
                for (PayloadItem item : mission.getCurrentPayloads()) {

                    // Write each payload item's details to the file.
                    myWriter.write("PAYLOAD," + item.getName() + "," + item.getWeight() + "\n");
                
                } // End of inner for loop

                // Add a separator line between missions.
                myWriter.write("END_OF_MISSION\n");

            } // End of for loop

            // Close the FileWriter to save changes.
            myWriter.close();

            // Return true to indicate successful save operation.
            return true;

        } // End of try block
        
        // Catch block to handle IOExceptions.
        catch (IOException e)
        {   
            // Print an error message if an exception occurs.
            System.out.println("An error occurred while writing to the file.");

            // Print the stack trace for debugging purposes.
            e.printStackTrace();

            // Return false to indicate failure in save operation.
            return false;

        } // End of try-catch block

    } // End of writeMissionsToFile method


    // Method to help loading all missions from a file.
    public boolean loadMissionDatabase()
    {   
        // Placeholder for loading missions from a file.
        Rocket currentRocket = null;

        // Read the file line by line. 
        try {

            // Create a File object to read from the file.
            File myObj = new File(this.fileName);

            // Create a Scanner object to read the file.
            Scanner myReader = new Scanner(myObj);

            // While loop to read each line of the file.
            while (myReader.hasNextLine())
            {   
                // Read the next line from the file.
                String data = myReader.nextLine();

                // Check if the line starts with "ROCKET" or "PAYLOAD" to determine the type of data
                if (data.startsWith("ROCKET"))
                {
                    // Create new Rocket object
                    String[] parts = data.split(",");
                    String rName = parts[1];
                    double rMax = Double.parseDouble(parts[2]);
                    currentRocket = new Rocket(rName, rMax);

                } // End of if

                else if (data.startsWith("PAYLOAD"))
                {
                    // Create new PayloadItem and add to current rocket
                    String[] parts = data.split(",");
                    String pName = parts[1];
                    double pWeight = Double.parseDouble(parts[2]);
                    PayloadItem item = new PayloadItem(pName, pWeight);
                    currentRocket.addPayload(item);

                } // End of else-if

                else if (data.equals("END_OF_MISSION") && currentRocket != null)
                {
                    // Add the completed rocket mission to the list
                    allLoggedMissions.add(currentRocket);
                    currentRocket = null; // Reset for next mission

                } // End of else-if
                
                // Print the read line to the console.
                System.out.println(data);

            } // End of while loop

            // Close the Scanner after reading the file.
            myReader.close();

            // Return true to indicate successful load operation.
            return true;

        } // End of try block

        // Catch block to handle FileNotFoundException.
        catch (FileNotFoundException e)
        {   
            // Print an error message if the file is not found.
            System.out.println("An error occurred while reading the file.");
            
            // Print the stack trace for debugging purposes.
            e.printStackTrace();

            // Return false to indicate failure in load operation.
            return false;

        } // End of try-catch block
    
    } // End of loadMissionLogs method

} // End of FileManager class

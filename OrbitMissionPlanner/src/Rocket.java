// This class represents a rocket for an orbit mission planner.

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Represents a launch vehicle with specific lift capabilities.
 * Manages the loading of payloads and validates launch viability
 * based on weight limits.
 * * @author Victor Santana
 * @version 1.0 (MVP)
 */
public class Rocket {
    
    // Initializing private fields for the rocket's name, maximum weight capacity, and current weight.
    private String rocketName;
    private double rocketMaxWeight;
    private ArrayList<PayloadItem> rocketCurrentPayloads;
    
    /**
     * Creates a new Rocket instance.
     * * @param name The mission name.
     * @param maxWeight The absolute maximum weight capacity in kilograms.
     */
    // Constructor to initialize the rocket with a name and maximum weight capacity.
    public Rocket(String rocketName, double rocketMaxWeight) {
        this.rocketName = rocketName;
        this.rocketMaxWeight = rocketMaxWeight;
        this.rocketCurrentPayloads = new ArrayList<>(); // New rockets start with an empty payload list
    } // End of constructor

    // GETTERS & SETTERS

    // Getter method to retrieve the rocket's name.
    public String getName() {
        return rocketName;
    } // End of getName method

    // Getter method to retrieve the rocket's maximum weight capacity.
    public double getMaxWeight() {
        return rocketMaxWeight;
    } // End of getMaxWeight method

    // Getter to get the payload count. 
    public int getPayloadCount() {
        return rocketCurrentPayloads.size();
    } // End of getPayloadCount method

    // MAIN METHODS

    // Method to calculate and retrieve the rocket's current weight based on its payloads.
    public double getCurrentWeight() {

        // Sum up the weights of all payload items
        double total = 0;

        // Loop through all items and sum their weights
        for (PayloadItem item : rocketCurrentPayloads) {
            total += item.getWeight();
        } // End of for loop

        return total;
    } // End of getCurrentWeight method

    public boolean addPayload(PayloadItem item) {
        // 1. Calculate what the weight WOULD be if we added this
        double potentialWeight = getCurrentWeight() + item.getWeight();

        // 2. Check if it fits
        if (potentialWeight <= rocketMaxWeight) {

            // 3. If it fits, add it
            rocketCurrentPayloads.add(item);
            return true; // Success
        }
        else {
            return false; // Failure (Too heavy)
        } // End of if-else

    } // End of addPayload method

    // Method to check if the rocket is ready for launch based on its current weight.
    public String launchCheck() {

        // Simple check for the MVP
        if (getCurrentWeight() <= rocketMaxWeight) {
            return "Go";
        } else {
            return "No-Go";
        }

    } // End of launchCheck method

    // Getter method to retrieve the list of current payloads.
    public ArrayList<PayloadItem> getCurrentPayloads() {
        return rocketCurrentPayloads;
    } // End of getCurrentPayloads method

} // End of Rocket class

// This class represents a payload item for an orbit mission planner.
/**
 * Attempts to add a payload item to the rocket.
 * Performs a pre-check to ensure capacity is not exceeded.
 * * @param item The PayloadItem object to be added.
 * @return true if the item was added successfully; false if it would exceed max capacity.
*/
public class PayloadItem {
    
    // Declaring private fields for name and weight of the payloa.
    private String payloadName;
    private double payloadWeight;

    // Constructor to initialize the payload item with a name and weight.
    public PayloadItem(String payloadName, double payloadWeight) {
        
        // Assigning the provided name and weight to the object's fields.
        this.payloadName = payloadName;
        this.payloadWeight = payloadWeight;
    } // End of constructor

    // Getter method to retrieve the name of the payload item.
    public String getName() {
        return payloadName;
    } // End of getName method

    // Getter method to retrieve the weight of the payload item.
    public double getWeight() {
        return payloadWeight;
    } // End of getWeight method

} // End of PayloadItem class

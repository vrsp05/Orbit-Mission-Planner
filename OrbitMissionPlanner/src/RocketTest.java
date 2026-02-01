import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class RocketTest {

    private Rocket rocket;

    @BeforeEach
    void setUp() {
        // Initialize a rocket with: Name "Saturn V", Max Weight 1000.0
        rocket = new Rocket("Saturn V", 1000.0);
    }

    @Test
    void testRocketInitialization() {
        assertEquals("Saturn V", rocket.getName(), "Rocket name should be set correctly");
        assertEquals(1000.0, rocket.getMaxWeight(), "Max weight should be set correctly");
        assertEquals(0.0, rocket.getCurrentWeight(), "New rocket should have 0 weight");
    }

    @Test
    void testAddPayloadSuccess() {
        // Create a payload of 500kg
        PayloadItem satellite = new PayloadItem("Sputnik", 500.0);
        
        boolean added = rocket.addPayload(satellite);
        
        assertTrue(added, "Payload should be added successfully");
        assertEquals(500.0, rocket.getCurrentWeight(), "Current weight should update");
    }

    @Test
    void testAddPayloadFailure() {
        // Create a payload that is too heavy (1100kg > 1000kg capacity)
        PayloadItem heavyLoad = new PayloadItem("Lead Block", 1100.0);
        
        boolean added = rocket.addPayload(heavyLoad);
        
        assertFalse(added, "Payload should NOT be added if it exceeds capacity");
        assertEquals(0.0, rocket.getCurrentWeight(), "Weight should not change on failure");
    }

    @Test
    void testLaunchCheck() {
        // 1. Empty rocket -> GO
        assertEquals("Go", rocket.launchCheck());

        // 2. Add safe payload -> GO
        rocket.addPayload(new PayloadItem("Safe Load", 900.0));
        assertEquals("Go", rocket.launchCheck());

        // 3. Overload (Force add logically or test boundary if we allowed it, 
        // but here we just check logic based on current weight vs max)
        // (For this MVP, we will rely on addPayload to prevent overload, 
        // so launchCheck is mostly a sanity check).
    }
}
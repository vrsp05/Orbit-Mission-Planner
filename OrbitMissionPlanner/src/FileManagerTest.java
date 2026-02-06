import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import java.io.File;
import java.util.ArrayList;

public class FileManagerTest {

    private FileManager fileManager;
    private final String TEST_FILE = "test_missions.csv";

    @BeforeEach
    void setUp() {
        // Initialize with a safe test file name so we don't overwrite real data
        fileManager = new FileManager(TEST_FILE);
        
        // Ensure we start with a clean slate
        File file = new File(TEST_FILE);
        if (file.exists()) {
            file.delete();
        }
    }

    @AfterEach
    void tearDown() {
        // Cleanup: Delete the test file after we are done
        File file = new File(TEST_FILE);
        if (file.exists()) {
             file.delete();
        }
    }

    @Test
    void testSaveAndLoadComplexManifest() {
        // --- 1. SETUP DATA ---
        Rocket r1 = new Rocket("Falcon Heavy", 2000.0);
        r1.addPayload(new PayloadItem("Tesla Roadster", 1200.0));
        
        Rocket r2 = new Rocket("Saturn V", 3000.0);
        r2.addPayload(new PayloadItem("Eagle Lander", 1500.0));
        r2.addPayload(new PayloadItem("Command Module", 500.0));

        // Add to the manager's list (assuming you have a getter/setter or public list)
        // Adjust this line if your list is private!
        fileManager.getAllLoggedMissions().add(r1);
        fileManager.getAllLoggedMissions().add(r2);

        // --- 2. EXECUTE SAVE ---
        boolean saveResult = fileManager.saveMissionLogs();
        assertTrue(saveResult, "Save operation should return true on success");

        // --- 3. RESET & LOAD ---
        // Clear the memory to prove we are reading from disk
        fileManager.getAllLoggedMissions().clear();
        
        boolean loadResult = fileManager.loadMissionDatabase();
        assertTrue(loadResult, "Load operation should return true on success");

        // --- 4. VERIFY DATA ---
        ArrayList<Rocket> loadedList = fileManager.getAllLoggedMissions();
        
        // Assert: We should have 2 rockets
        assertEquals(2, loadedList.size(), "Should load exactly 2 rockets");

        // Assert: First Rocket (Falcon Heavy)
        Rocket loadedR1 = loadedList.get(0);
        assertEquals("Falcon Heavy", loadedR1.getName());
        assertEquals(1, loadedR1.getPayloadCount(), "Falcon should have 1 item");
        // (You might need to add a getPayloadCount() helper to Rocket, or allow access to the list)

        // Assert: Second Rocket (Saturn V)
        Rocket loadedR2 = loadedList.get(1);
        assertEquals("Saturn V", loadedR2.getName());
        // This is the critical test: Did it keep BOTH items attached to Saturn?
        assertEquals(2, loadedR2.getPayloadCount(), "Saturn should have 2 items");
    }
}
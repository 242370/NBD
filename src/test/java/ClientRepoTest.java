import org.junit.jupiter.api.Test;
import org.nbd.model.Client;
import org.nbd.repos.CassandraManager;
import org.nbd.repos.ClientRepo;

import static org.junit.jupiter.api.Assertions.*;

public class ClientRepoTest {
    CassandraManager manager = new CassandraManager();
    ClientRepo repo = new ClientRepo(CassandraManager.getSession());
    int testID = 1;
    String testLastname = "Cyberbully";
    String testFirstname = "Rafal";
    String newFirstname = "Adam";
    int testWeight = 79;
    boolean testHaspet = true;
    int testTripid = 1;
    Client testCLient = new Client(testID, testLastname, testFirstname, testWeight, testHaspet, testTripid);

    @Test
    void createAndDeleteTest() {
        repo.add(testCLient);

        Client newClient = null;
        try {
            newClient = repo.getByID(testID);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertNotNull(newClient);
        assertEquals(testLastname, newClient.getLastname());
        assertEquals(testFirstname, newClient.getFirstname());

        try {
            repo.remove(testID);
            newClient = repo.getByID(testID);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertNull(newClient);
    }

    @Test
    void updateTest() {
        repo.add(testCLient);

        Client newClient = null;
        try {
            newClient = repo.getByID(testID);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertNotNull(newClient);
        assertEquals(testLastname, newClient.getLastname());
        assertEquals(testFirstname, newClient.getFirstname());

        try {
            testCLient.setFirstname(newFirstname);
            repo.update(testCLient);
            newClient = repo.getByID(testID);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertNotNull(newClient);
        assertEquals(newFirstname, newClient.getFirstname());
    }
}

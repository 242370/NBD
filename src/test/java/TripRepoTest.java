import org.junit.jupiter.api.Test;
import org.nbd.model.Trip;
import org.nbd.repos.CassandraManager;
import org.nbd.repos.TripRepo;

import static org.junit.jupiter.api.Assertions.*;

public class TripRepoTest {
    CassandraManager manager = new CassandraManager();
    TripRepo repo = new TripRepo(CassandraManager.getSession());
    int testID = 1;
    int testLength = 7;
    String testName = "Akacje pod gruszą";
    int initialClients = 0;
    int testTransportmean = 1;
    int testAccommodation = 1;
    Trip testTrip = new Trip(testID, testLength, testName, initialClients, testTransportmean, testAccommodation);

    @Test
    void createAndDeleteTest() {
        repo.add(this.testTrip);

        Trip newTrip = null;
        try {
            newTrip = repo.getByID(testID);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertNotNull(newTrip);
        assertEquals(testLength, newTrip.getLength());
        assertEquals(testName, newTrip.getName());

        try {
            repo.remove(testID);
            newTrip = repo.getByID(testID);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertNull(newTrip);
    }

    @Test
    void updateTest() {
        repo.add(this.testTrip);

        Trip newTrip = null;
        try {
            newTrip = repo.getByID(testID);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertNotNull(newTrip);
        assertEquals(testLength, newTrip.getLength());
        assertEquals(testName, newTrip.getName());

        try {
            repo.addClientToTrip(newTrip);
            newTrip = repo.getByID(testID);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertNotNull(newTrip);
        assertEquals(initialClients + 1, newTrip.getClients());
    }

    @Test
    void createAndDeleteByTest()
    {
        repo.addBy(this.testTrip);

        Trip newTrip = repo.selectBy(this.testAccommodation).getFirst();
        assertNotNull(newTrip);
        assertEquals(this.testAccommodation, newTrip.getAccommodation());
        assertEquals(this.testID, newTrip.getId());

        repo.deleteBy(this.testAccommodation);
        try {
            newTrip = repo.selectBy(this.testAccommodation).getFirst();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            newTrip = null;
        }
        assertNull(newTrip);
    }
}
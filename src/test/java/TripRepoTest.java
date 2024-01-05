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
    void createAndDeleteTest()
    {
        repo.add(this.testTrip);

        Trip newTrip = null;
        try {
            newTrip = repo.getByID(testID);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        assertNotNull(newTrip);
        assertEquals(testLength, newTrip.getLength());
    }
}

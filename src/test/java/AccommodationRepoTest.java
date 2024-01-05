import org.junit.jupiter.api.Test;
import org.nbd.model.Accommodation;
import org.nbd.repos.AccommodationRepo;
import org.nbd.repos.CassandraManager;

import static org.junit.jupiter.api.Assertions.*;

public class AccommodationRepoTest {
    CassandraManager manager = new CassandraManager();
    AccommodationRepo repo = new AccommodationRepo(CassandraManager.getSession());
    int testID = 1;
    double testCapacity = 5.1;
    double testPricePerPerson = 10.0;
    int testRating = 4;
    String testDestination = "Zgierz";
    Accommodation testAccommodation = new Accommodation(testID, testDestination, testCapacity, testPricePerPerson, testRating);

    @Test
    void createAndDeleteTest() {
        repo.add(testAccommodation);

        Accommodation newAccommodation = null;
        try {
            newAccommodation = repo.getByID(testID);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertNotNull(newAccommodation);
        assertEquals(testDestination, newAccommodation.getDestination());
        assertEquals(testCapacity, newAccommodation.getCapacity());

        try {
            repo.remove(testID);
            newAccommodation = repo.getByID(testID);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertNull(newAccommodation);
    }
}

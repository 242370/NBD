import org.junit.jupiter.api.Test;
import org.nbd.model.CashedAccommodation;
import org.nbd.repos.AccommodationRepoRedis;

import static org.junit.jupiter.api.Assertions.*;

public class AccommodationRepoRedisTest {
    int expirationTime = 5;
    AccommodationRepoRedis repo = new AccommodationRepoRedis(this.expirationTime);
    int testID = 0;
    double testCapacity = 5.0;
    double testPricePerPerson = 10.0;
    int testRating = 4;
    String testDestination = "Zgierz";
    CashedAccommodation testAccommodation = new CashedAccommodation(testID, testCapacity, testPricePerPerson, testRating, testDestination);

    @Test
    public void savingToCacheTest() {
        try {
            assertNull(repo.getFromCache(this.testID));
            assertDoesNotThrow(() -> repo.putInCache(this.testAccommodation));

            CashedAccommodation newAccommodation = repo.getFromCache(this.testID);
            assertNotNull(newAccommodation);

            assertEquals(this.testCapacity, newAccommodation.getCapacity());
            assertEquals(this.testPricePerPerson, newAccommodation.getPricePerPerson());
            assertEquals(this.testDestination, newAccommodation.getDestination());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            repo.deleteFromCache(this.testID);
        }
    }

    @Test
    public void deletingFromCacheTest() {
        try {
            assertDoesNotThrow(() -> repo.putInCache(this.testAccommodation));

            CashedAccommodation newAccommodation = repo.getFromCache(this.testID);
            assertNotNull(newAccommodation);

            assertDoesNotThrow(() -> repo.deleteFromCache(this.testID));
            assertNull(repo.getFromCache(this.testID));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void keyExpiringAfterTimeTest() {
        try {
            assertDoesNotThrow(() -> repo.putInCache(this.testAccommodation));
            assertNotNull(repo.getFromCache(this.testID));

            wait(this.expirationTime * 1000L);

            assertNull(repo.getFromCache(this.testID));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

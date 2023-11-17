import org.junit.jupiter.api.Test;

import org.nbd.model.Accommodation;
import org.nbd.repos.AccommodationRepo;

import static org.junit.jupiter.api.Assertions.*;

public class AccommodationRepoTest {
    private final AccommodationRepo repo = new AccommodationRepo();
    private final int initialID = 1;
    private final double testCapacity = 2.1;
    private final double testPricePerPerson = 5.0;
    private final int testRating = 4;
    private final String testDestination = "Zgierz";
    private final double newPricePerPerson = 10.0;
    private final int newTestRating = 1;

    @Test
    void addingAccommodationsTest() {
        assertDoesNotThrow(() -> repo.add(new Accommodation(initialID, testCapacity, testPricePerPerson, testRating, testDestination)));

        Accommodation accommodation = repo.getByID(1);

        assertNotNull(accommodation);
        assertEquals(accommodation.getCapacity(), testCapacity);
        assertEquals(accommodation.getPricePerPerson(), testPricePerPerson);
        assertEquals(accommodation.getRating(), testRating);
        assertEquals(accommodation.getDestination(), testDestination);
    }

    @Test
    void changePricePerPersonTest() {
        assertDoesNotThrow(() -> repo.add(new Accommodation(initialID, testCapacity, testPricePerPerson, testRating, testDestination)));

        assertEquals(repo.getByID(1).getPricePerPerson(), testPricePerPerson);
        repo.changePricePerPerson(1, newPricePerPerson);
        assertEquals(repo.getByID(1).getPricePerPerson(), newPricePerPerson);
    }

    @Test
    void changeRating() {
        assertDoesNotThrow(() -> repo.add(new Accommodation(initialID, testCapacity, testPricePerPerson, testRating, testDestination)));


        assertEquals(repo.getByID(1).getRating(), testRating);
        repo.changeRating(1, newTestRating);
        assertEquals(repo.getByID(1).getRating(), newTestRating);
    }

    @Test
    void removeAccommodationTest() {
        assertDoesNotThrow(() -> repo.add(new Accommodation(initialID, testCapacity, testPricePerPerson, testRating, testDestination)));

        Accommodation accommodation = repo.getByID(1);

        assertNotNull(accommodation);
        repo.remove(1);
        Accommodation removedAccommodation;

        try {
            removedAccommodation = repo.getByID(1);
        } catch (Exception e) {
            removedAccommodation = null;
        }

        assertNull(removedAccommodation);
    }
}

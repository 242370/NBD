package repos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nbd.model.Accommodation;
import org.nbd.model.Client;
import org.nbd.repos.AccommodationRepo;
import org.nbd.repos.ClientRepo;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccommodationRepoTest {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("test");
    EntityManager entityManager = factory.createEntityManager();
    AccommodationRepo repo = new AccommodationRepo(entityManager);

    double testCapacity = 150.5;
    double testPricePerPerson = 10.0;
    double newPricePerPerson = 15.0;
    int testRating = 4;
    int newTestRating = 5;
    String testDestination = "Zgierz";


    @Test
    void addingAccommodationsTest() {
        assertDoesNotThrow(() -> repo.add(new Accommodation(testCapacity, testPricePerPerson, testRating, testDestination)));
        Accommodation accommodation = repo.getByID(1);
        assertNotNull(accommodation);
        assertEquals(accommodation.getCapacity(), testCapacity);
        assertEquals(accommodation.getPricePerPerson(), testPricePerPerson);
        assertEquals(accommodation.getRating(), testRating);
        assertEquals(accommodation.getDestination(), testDestination);
    }

    @Test
    void changePricePerPersonTest() {
        assertDoesNotThrow(() -> repo.add(new Accommodation(testCapacity, testPricePerPerson, testRating, testDestination)));
        Accommodation accommodation = repo.getByID(1);
        assertEquals(accommodation.getPricePerPerson(), testPricePerPerson);
        repo.changePricePerPerson(1, newPricePerPerson);
        assertEquals(accommodation.getPricePerPerson(), newPricePerPerson);
    }

    @Test
    void changeRating()
    {
        assertDoesNotThrow(() -> repo.add(new Accommodation(testCapacity, testPricePerPerson, testRating, testDestination)));
        Accommodation accommodation = repo.getByID(1);
        assertEquals(accommodation.getRating(), testRating);
        repo.changeRating(1, newTestRating);
        assertEquals(accommodation.getRating(), newTestRating);
    }

    @Test
    void removeAccommodationTest() {
        assertDoesNotThrow(() -> repo.add(new Accommodation(testCapacity, testPricePerPerson, testRating, testDestination)));
        Accommodation accommodation = repo.getByID(1);
        assertNotNull(accommodation);
        repo.remove(1);
        Accommodation removedAccommodation = repo.getByID(1);
        assertNull(removedAccommodation);
    }
}

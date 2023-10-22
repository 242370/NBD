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
    int testRating = 4;
    String testDestination = "Zgierz";

    @BeforeEach
    void init() {
    }

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
}

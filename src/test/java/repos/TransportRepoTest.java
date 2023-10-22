package repos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nbd.model.Jet;
import org.nbd.model.Lift;
import org.nbd.model.Scooter;
import org.nbd.repos.TransportRepo;
import static org.junit.jupiter.api.Assertions.*;

public class TransportRepoTest {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("test");
    EntityManager entityManager = factory.createEntityManager();
    TransportRepo repo = new TransportRepo(entityManager);
    int testLiftMaxWeight = 500;
    int testJetMaxWeight = 1000;
    int testScooterMaxWeight = 100;

    @BeforeEach
    void init()
    {

    }

    @Test
    void addingJetTest()
    {
        assertDoesNotThrow(()-> repo.add(new Jet(testJetMaxWeight)));
        Jet jet = (Jet) repo.getByID(1);
        assertNotNull(jet);
        assertEquals(jet.getMaxWeight(), testJetMaxWeight);
        assertTrue(jet.isAvailable());
        assertEquals(jet.getType(), "Jet");
    }

    @Test
    void addingLiftTest()
    {
        assertDoesNotThrow(() -> repo.add(new Lift(testLiftMaxWeight)));
        Lift lift = (Lift) repo.getByID(1);
        assertNotNull(lift);
        assertEquals(lift.getMaxWeight(), testLiftMaxWeight);
        assertTrue(lift.isAvailable());
        assertEquals(lift.getType(), "Lift");
    }

    @Test
    void addingScooterTest()
    {
        assertDoesNotThrow(() -> repo.add(new Scooter(testScooterMaxWeight)));
        Scooter scooter = (Scooter) repo.getByID(1);
        assertNotNull(scooter);
        assertEquals(scooter.getMaxWeight(), testScooterMaxWeight);
        assertTrue(scooter.isAvailable());
        assertEquals(scooter.getType(), "Scooter");
    }
}

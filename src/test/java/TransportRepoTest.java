import org.junit.jupiter.api.Test;
import org.nbd.model.TransportMapper;
import org.nbd.model.*;
import org.nbd.repos.TransportRepo;

import static org.junit.jupiter.api.Assertions.*;

public class TransportRepoTest {
    TransportRepo repo = new TransportRepo();
    int testLiftMaxWeight = 500;
    int testJetMaxWeight = 1000;
    int testScooterMaxWeight = 100;

    int initialID = 1;

    @Test
    void addingJetTest() {
        assertDoesNotThrow(() -> repo.add(new Jet(initialID, testJetMaxWeight)));

        Jet jet = (Jet) repo.getByID(1);

        assertNotNull(jet);
        assertEquals(jet.getMaxWeight(), testJetMaxWeight);
        assertTrue(jet.isAvailable());
        assertEquals(jet.getType(), "Jet");
    }

    @Test
    void addingLiftTest() {
        assertDoesNotThrow(() -> repo.add(new Lift(initialID, testLiftMaxWeight)));

        Lift lift = (Lift) repo.getByID(1);

        assertNotNull(lift);
        assertEquals(lift.getMaxWeight(), testLiftMaxWeight);
        assertTrue(lift.isAvailable());
        assertEquals(lift.getType(), "Lift");
    }

    @Test
    void addingScooterTest() {
        assertDoesNotThrow(() -> repo.add(new Scooter(initialID, testScooterMaxWeight)));

        Scooter scooter = (Scooter) repo.getByID(1);

        assertNotNull(scooter);
        assertEquals(scooter.getMaxWeight(), testScooterMaxWeight);
        assertTrue(scooter.isAvailable());
        assertEquals(scooter.getType(), "Scooter");
    }

    @Test
    void removeTransportMeanTest() {
        assertDoesNotThrow(() -> repo.add(new Scooter(initialID, testScooterMaxWeight)));
        TransportMean transport = repo.getByID(1);

        assertNotNull(transport);
        repo.remove(1);

        TransportMean transportMgd;
        try {
            transportMgd = repo.getByID(1);
        }
        catch (Exception e)
        {
            transportMgd = null;
        }

        assertNull(transportMgd);
    }
}
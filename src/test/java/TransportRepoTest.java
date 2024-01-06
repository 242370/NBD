import org.junit.jupiter.api.Test;
import org.nbd.model.Jet;
import org.nbd.model.Lift;
import org.nbd.model.Scooter;
import org.nbd.repos.CassandraManager;
import org.nbd.repos.TransportRepo;

import static org.junit.jupiter.api.Assertions.*;

public class TransportRepoTest {
    CassandraManager manager = new CassandraManager();
    TransportRepo repo = new TransportRepo(CassandraManager.getSession());
    String jetType = "Jet";
    String liftType = "Lift";
    String scooterType = "Scooter";
    int testID = 1;
    boolean testIsavailable = true;
    int testMaxWeight = 100;
    int testVelocity = 100;
    int testCapacity = 10;
    Jet testJet = new Jet(jetType, testID, testIsavailable, testMaxWeight, testVelocity);
    Lift testLift = new Lift(liftType, testID, testIsavailable, testMaxWeight, testCapacity);
    Scooter testScooter = new Scooter(scooterType, testID, testIsavailable, testMaxWeight, testVelocity);

    @Test
    void createAndDeleteTest() {
        repo.add(testJet);

        Jet newJet = null;
        try {
            newJet = (Jet) repo.getBy(testID, jetType);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertNotNull(newJet);
        assertEquals(jetType, newJet.getType());
        assertEquals(testMaxWeight, newJet.getMaxweight());

        try {
            repo.remove(testID, jetType);
            newJet = (Jet) repo.getBy(testID, jetType);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertNull(newJet);
    }

    @Test
    void changeAvailabilityTest() {
        repo.add(testLift);

        Lift newLift = null;
        try {
            newLift = (Lift) repo.getBy(testID, liftType);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertNotNull(newLift);
        assertEquals(testIsavailable, newLift.isIsavailable());

        repo.changeAvailability(newLift);
        assertEquals(!testIsavailable, newLift.isIsavailable());
    }
}

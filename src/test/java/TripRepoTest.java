import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.conversions.Bson;
import org.junit.jupiter.api.Test;
import org.nbd.model.*;
import org.nbd.repos.TripRepo;

import java.util.logging.Filter;

import static org.junit.jupiter.api.Assertions.*;

public class TripRepoTest {
    TripRepo repo = new TripRepo();
    int testlength = 7;
    String testName = "Akacje pod gruszą";
    int initialID = 1;
    TransportMean testJet = new Jet(initialID + 8, 55);

    TransportMean testScooter = new Scooter(initialID + 18, 100);
    Accommodation testAccommodation = new Accommodation(initialID, 1.5, 100.0, 4, "Zgierz");
    Client testClient1 = new Client(initialID, "Rafal", "Cyberbully", 40);
    Client testClient2 = new Client(initialID + 1, "Adam", "Kruszynski", 56);

    @Test
    void addingTripsTest() {
        assertDoesNotThrow(() -> repo.add(new Trip(initialID, testlength, testName, testJet, testAccommodation)));

        Trip trip = repo.getByID(initialID);

        assertNotNull(trip);
        assertEquals(trip.getActualWeight(), 0);
        assertEquals(trip.getLength(), testlength);
        assertEquals(trip.getName(), testName);
        assertEquals(trip.getNumberOfClients(), 0);

        repo.remove(initialID);
    }

    @Test
    void addingClientsToTripTest() {
        assertDoesNotThrow(() -> repo.add(new Trip(initialID, testlength, testName, testJet, testAccommodation)));
        assertDoesNotThrow(() -> repo.addClientToTrip(repo.getByID(1), testClient1));

        assertEquals(repo.getByID(initialID).getActualWeight(), 40);
        assertEquals(repo.getByID(initialID).getNumberOfClients(), 1);
        assertEquals(testClient1.getTrip_id(), initialID);

        repo.remove(initialID);
    }

    @Test
    void addingClientWithPetToTrip() {
        assertDoesNotThrow(() -> repo.add(new Trip(initialID, testlength, testName, testJet, testAccommodation)));
        testClient1.addPet("Manat", "Manatee", 4);
        assertDoesNotThrow(() -> repo.addClientToTrip(repo.getByID(1), testClient1));
        assertEquals(repo.getByID(initialID).getActualWeight(), 44);
        assertEquals(repo.getByID(initialID).getNumberOfClients(), 1);

        repo.remove(initialID);
    }

    @Test
    void addingClientsToTripMaxWeightFail() {
        assertDoesNotThrow(() -> repo.add(new Trip(initialID, testlength, testName, testJet, testAccommodation)));
        Trip trip = repo.getByID(initialID);
        repo.addClientToTrip(trip, testClient2);
        assertEquals(trip.getNumberOfClients(), 0);

        repo.remove(initialID);
    }

    @Test
    void addingClientsToTripCapacityFail()
    {
        assertDoesNotThrow(() -> repo.add(new Trip(initialID, testlength, testName, testScooter, testAccommodation)));
        Trip trip = repo.getByID(initialID);
        repo.addClientToTrip(trip, testClient1);
        repo.addClientToTrip(trip, testClient2);
        assertEquals(trip.getNumberOfClients(), 1);

        repo.remove(initialID);
    }

    @Test
    void addingClientWithPetFail()
    {
        assertDoesNotThrow(() -> repo.add(new Trip(initialID, testlength, testName, testScooter, testAccommodation)));
        testClient1.addPet("Manat", "Manatee", 4);
        Trip trip = repo.getByID(initialID);
        repo.addClientToTrip(trip, testClient1);
        assertEquals(trip.getNumberOfClients(), 0);

        repo.remove(initialID);
    }

    @Test
    void addingTripsIsTransportMeanAvailableFail()
    {
        assertDoesNotThrow(() -> repo.add(new Trip(initialID, testlength, testName, testScooter, testAccommodation)));
        repo.add(new Trip(initialID + 1, testlength, testName, testScooter, testAccommodation));
        assertEquals(repo.getSize(), 1);

        repo.remove(initialID);
    }
    @Test
    void removeTripTest() {
        assertDoesNotThrow(() -> repo.add(new Trip(initialID, testlength, testName, testScooter, testAccommodation)));
        Trip trip = repo.getByID(initialID);
        assertNotNull(trip);

        repo.remove(initialID);
        Trip removedTrip;
        try
        {
            removedTrip = repo.getByID(1);
        }
        catch (Exception e)
        {
            removedTrip = null;
        }
        assertNull(removedTrip);
    }
}
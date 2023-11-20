import org.junit.jupiter.api.Test;
import org.nbd.model.*;
import org.nbd.repos.TripRepo;

import static org.junit.jupiter.api.Assertions.*;

public class TripRepoTest {
    TripRepo repo = new TripRepo();
    int testlength = 7;
    String testName = "Akacje pod gruszą";
    int initialID = 1;
    TransportMean testJet = new Jet(initialID, 55);

    TransportMean testScooter = new Scooter(initialID + 1, 100);
    Accommodation testAccommodation = new Accommodation(initialID, 1.5, 100.0, 4, "Zgierz");
    Client testClient1 = new Client(initialID, "Rafal", "Cyberbully", 40);
    Client testClient2 = new Client(initialID + 1, "Adam", "Kruszynski", 56);

    @Test
    void addingTripsTest() {
        assertDoesNotThrow(() -> repo.add(new Trip(initialID, testlength, testName, testJet, testAccommodation)));

        Trip trip = repo.getByID(1);

        assertNotNull(trip);
        assertEquals(trip.getActualWeight(), 0);
        assertEquals(trip.getLength(), testlength);
        assertEquals(trip.getName(), testName);
        assertEquals(trip.getNumberOfClients(), 0);

        assertFalse(trip.getTransportMean().isAvailable());
    }

    @Test
    void addingClientsToTripTest() {
        assertDoesNotThrow(() -> repo.add(new Trip(initialID, testlength, testName, testJet, testAccommodation)));
        assertDoesNotThrow(() -> repo.addClientToTrip(repo.getByID(1), testClient1));

        assertEquals(repo.getByID(1).getActualWeight(), 40);
        assertEquals(repo.getByID(1).getNumberOfClients(), 1);
    }

//    @Test
//    void addingClientWithPetToTrip() {
//        assertDoesNotThrow(() -> repo.add(new Trip(initialID, testlength, testName, testJet, testAccommodation)));
//        testClient1.addPet("Manat", "Manatee", 4);
//        assertDoesNotThrow(() -> repo.addClientToTrip(repo.getByID(1), testClient1));
//        assertEquals(repo.getByID(1).getActualWeight(), 44);
//        assertEquals(repo.getByID(1).getNumberOfClients(), 1);
//        assertTrue(repo.getByID(1).getClient(0).hasPet());
//    }
//
//    @Test
//    void addingClientsToTripMaxWeightFail() {
//        assertDoesNotThrow(() -> repo.add(new Trip(initialID, testlength, testName, testJet, testAccommodation)));
//        Trip trip = repo.getByID(1);
//        repo.addClientToTrip(trip, testClient2);
//        assertEquals(trip.getNumberOfClients(), 0);
//    }
//
//    @Test
//    void addingClientsToTripCapacityFail()
//    {
//        assertDoesNotThrow(() -> repo.add(new Trip(initialID, testlength, testName, testScooter, testAccommodation)));
//        Trip trip = repo.getByID(1);
//        repo.addClientToTrip(trip, testClient1);
//        repo.addClientToTrip(trip, testClient2);
//        assertEquals(trip.getNumberOfClients(), 1);
//    }
//
//    @Test
//    void addingClientWithPetFail()
//    {
//        assertDoesNotThrow(() -> repo.add(new Trip(initialID, testlength, testName, testScooter, testAccommodation)));
//        testClient1.addPet("Manat", "Manatee", 4);
//        Trip trip = repo.getByID(1);
//        repo.addClientToTrip(trip, testClient1);
//        assertEquals(trip.getNumberOfClients(), 0);
//    }
//
//    @Test
//    void addingTripsIsTransportMeanAvailableFail()
//    {
//        assertDoesNotThrow(() -> repo.add(new Trip(initialID, testlength, testName, testScooter, testAccommodation)));
//        repo.add(new Trip(initialID + 1, testlength, testName, testScooter, testAccommodation));
//        assertEquals(repo.getSize(), 1);
//    }
//    @Test
//    void removeTripTest() {
//        assertDoesNotThrow(() -> repo.add(new Trip(initialID, testlength, testName, testScooter, testAccommodation)));
//        Trip trip = repo.getByID(1);
//        assertNotNull(trip);
//        repo.remove(1);
//        Trip removedTrip = repo.getByID(1);
//        assertNull(removedTrip);
//    }
}
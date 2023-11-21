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

    TransportMean testScooter = new Scooter(initialID + 9, 100);
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

        Bson filter = Filters.eq("id", repo.getByID(1).getTransportMean().getId());
        Bson update = Updates.inc("uses" , -1);
        this.repo.getDatabase().getCollection("transportMeans").updateOne(filter, update);
    }

    @Test
    void addingClientsToTripTest() {
        assertDoesNotThrow(() -> repo.add(new Trip(initialID, testlength, testName, testJet, testAccommodation)));
        assertDoesNotThrow(() -> repo.addClientToTrip(repo.getByID(1), testClient1));

        assertEquals(repo.getByID(1).getActualWeight(), 40);
        assertEquals(repo.getByID(1).getNumberOfClients(), 1);

        Bson filter = Filters.eq("id", repo.getByID(1).getTransportMean().getId());
        Bson update = Updates.inc("uses" , -1);
        this.repo.getDatabase().getCollection("transportMeans").updateOne(filter, update);
    }

    @Test
    void addingClientWithPetToTrip() {
        assertDoesNotThrow(() -> repo.add(new Trip(initialID, testlength, testName, testJet, testAccommodation)));
        testClient1.addPet("Manat", "Manatee", 4);
        assertDoesNotThrow(() -> repo.addClientToTrip(repo.getByID(1), testClient1));
        assertEquals(repo.getByID(1).getActualWeight(), 44);
        assertEquals(repo.getByID(1).getNumberOfClients(), 1);

        Bson filter = Filters.eq("id", repo.getByID(1).getTransportMean().getId());
        Bson update = Updates.inc("uses" , -1);
        this.repo.getDatabase().getCollection("transportMeans").updateOne(filter, update);
    }

    @Test
    void addingClientsToTripMaxWeightFail() {
        assertDoesNotThrow(() -> repo.add(new Trip(initialID, testlength, testName, testJet, testAccommodation)));
        Trip trip = repo.getByID(1);
        repo.addClientToTrip(trip, testClient2);
        assertEquals(trip.getNumberOfClients(), 0);

        Bson filter = Filters.eq("id", repo.getByID(1).getTransportMean().getId());
        Bson update = Updates.inc("uses" , -1);
        this.repo.getDatabase().getCollection("transportMeans").updateOne(filter, update);
    }

    @Test
    void addingClientsToTripCapacityFail()
    {
        assertDoesNotThrow(() -> repo.add(new Trip(initialID, testlength, testName, testScooter, testAccommodation)));
        Trip trip = repo.getByID(1);
        repo.addClientToTrip(trip, testClient1);
        repo.addClientToTrip(trip, testClient2);
        assertEquals(trip.getNumberOfClients(), 1);

        Bson filter = Filters.eq("id", repo.getByID(1).getTransportMean().getId());
        Bson update = Updates.inc("uses" , -1);
        this.repo.getDatabase().getCollection("transportMeans").updateOne(filter, update);
    }

    @Test
    void addingClientWithPetFail()
    {
        assertDoesNotThrow(() -> repo.add(new Trip(initialID, testlength, testName, testScooter, testAccommodation)));
        testClient1.addPet("Manat", "Manatee", 4);
        Trip trip = repo.getByID(1);
        repo.addClientToTrip(trip, testClient1);
        assertEquals(trip.getNumberOfClients(), 0);

        Bson filter = Filters.eq("id", repo.getByID(1).getTransportMean().getId());
        Bson update = Updates.inc("uses" , -1);
        this.repo.getDatabase().getCollection("transportMeans").updateOne(filter, update);
    }

    @Test
    void addingTripsIsTransportMeanAvailableFail()
    {
        assertDoesNotThrow(() -> repo.add(new Trip(initialID, testlength, testName, testScooter, testAccommodation)));
        repo.add(new Trip(initialID + 1, testlength, testName, testScooter, testAccommodation));
        assertEquals(repo.getSize(), 1);

        Bson filter = Filters.eq("id", repo.getByID(1).getTransportMean().getId());
        Bson update = Updates.inc("uses" , -1);
        this.repo.getDatabase().getCollection("transportMeans").updateOne(filter, update);
    }
    @Test
    void removeTripTest() {
        assertDoesNotThrow(() -> repo.add(new Trip(initialID, testlength, testName, testScooter, testAccommodation)));
        Trip trip = repo.getByID(1);
        assertNotNull(trip);

        Bson filter = Filters.eq("id", repo.getByID(1).getTransportMean().getId());
        Bson update = Updates.inc("uses" , -1);
        this.repo.getDatabase().getCollection("transportMeans").updateOne(filter, update);

        repo.remove(1);
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
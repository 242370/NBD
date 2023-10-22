package repos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nbd.model.Accommodation;
import org.nbd.model.Client;
import org.nbd.repos.ClientRepo;

import static org.junit.jupiter.api.Assertions.*;

public class ClientRepoTest {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("test");
    EntityManager entityManager = factory.createEntityManager();
    ClientRepo repo = new ClientRepo(entityManager);

    String testClientName = "Rafal";
    String testClientLastName = "Cyberbully";
    int testClientWeight = 50;


    @Test
    void addingClientsTest() {
        assertDoesNotThrow(() -> repo.add(new Client(testClientName, testClientLastName, testClientWeight)));
        Client client = repo.getByID(1);
        assertNotNull(client);
        assertEquals(client.getFirstName(), testClientName);
        assertEquals(client.getLastName(), testClientLastName);
        assertEquals(client.getWeight(), testClientWeight);
        assertFalse(client.hasPet());
    }

    @Test
    void addingClientWithPetTest() {
        Client client = new Client(testClientName, testClientLastName, testClientWeight);
        client.addPet("Manat", "Manatee", 38);
        assertDoesNotThrow(() -> repo.add(client));
        Client clientFromDatabase = repo.getByID(1);
        assertNotNull(clientFromDatabase.getPet());
        assertEquals(clientFromDatabase.getPet().getPetWeight(), 38);
        assertEquals(clientFromDatabase.getPet().getSpecies(), "Manatee");
    }

    @Test
    void removeClientTest() {
        assertDoesNotThrow(() -> repo.add(new Client(testClientName, testClientLastName, testClientWeight)));
        Client client = repo.getByID(1);
        assertNotNull(client);
        repo.remove(1);
        Client removedClient = repo.getByID(1);
        assertNull(removedClient);
    }

}

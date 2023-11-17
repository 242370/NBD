import org.junit.jupiter.api.Test;

import org.nbd.model.Client;
import org.nbd.repos.ClientRepo;

import static org.junit.jupiter.api.Assertions.*;

public class ClientRepoTest {
    ClientRepo repo = new ClientRepo();
    String testClientName = "Rafal";
    String testClientLastName = "Cyberbully";
    int testClientWeight = 50;
    int initialID = 1;


    @Test
    void addingClientsTest() {
        assertDoesNotThrow(() -> repo.add(new Client(initialID, testClientName, testClientLastName, testClientWeight)));

        Client client = repo.getByID(1);

        assertNotNull(client);

        assertEquals(client.getFirstName(), testClientName);
        assertEquals(client.getLastName(), testClientLastName);
        assertEquals(client.getWeight(), testClientWeight);

        assertFalse(client.hasPet());
    }

    @Test
    void addingClientWithPetTest() {
        Client client = new Client(initialID, testClientName, testClientLastName, testClientWeight);
        client.addPet("Manat", "Manatee", 38);

        assertDoesNotThrow(() -> repo.add(client));

        Client clientFromDatabase = repo.getByID(1);

        assertNotNull(clientFromDatabase.getPet());
        assertEquals(clientFromDatabase.getPet().getPetWeight(), 38);
        assertEquals(clientFromDatabase.getPet().getSpecies(), "Manatee");
    }

    @Test
    void removeClientTest() {
        assertDoesNotThrow(() -> repo.add(new Client(initialID, testClientName, testClientLastName, testClientWeight)));
        Client client = repo.getByID(1);

        assertNotNull(client);
        repo.remove(1);

        Client removedClient;
        try {
            removedClient = repo.getByID(1);
        } catch (Exception e) {
            removedClient = null;
        }
        assertNull(removedClient);
    }
}
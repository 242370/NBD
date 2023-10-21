package repos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nbd.model.Client;
import org.nbd.repos.ClientRepo;

import static org.junit.jupiter.api.Assertions.*;

public class ClientRepoTest {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("test");
    EntityManager entityManager = factory.createEntityManager();
    ClientRepo repo = new ClientRepo(entityManager);

    @BeforeEach
    void init() {
    }

    @Test
    void addingClientsTest() {
        assertDoesNotThrow(() -> repo.addClient(new Client("Rafal", "Cyberbully", 50)));
    }
}

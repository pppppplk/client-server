package project.spring.repo;

import project.spring.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepo extends JpaRepository<Client, Long> {

    List<Client> findByLastname(String lastName);

    List<Client> findClientsById(Long id);

    Client findClientById(Long id);

    Client findClientByLastname(String lastName);

    List<Client> findAllById(Long id);
}












//}

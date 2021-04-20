package project.spring.repo;

import project.spring.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий ClientRepo - ребенок JPA репозитория
 */

public interface ClientRepo extends JpaRepository<Client, Long> {

    Client findClientById(Long id);


}












//}

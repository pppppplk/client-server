package project.spring.repo;

import project.spring.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepo extends JpaRepository<Client, Long> {



    Client findClientById(Long id);


}












//}

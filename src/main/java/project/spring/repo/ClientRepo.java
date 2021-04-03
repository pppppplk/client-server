package project.spring.repo;

import project.spring.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepo extends JpaRepository<Client, Long> {



    Client findClientById(Long id);


}












//}

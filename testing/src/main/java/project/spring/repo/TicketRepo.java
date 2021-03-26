package project.spring.repo;

import project.spring.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepo extends JpaRepository<Ticket, Long> {

    List<Ticket> findTicketByPrice(Integer price);

    Ticket findTicketById(Long id);

}

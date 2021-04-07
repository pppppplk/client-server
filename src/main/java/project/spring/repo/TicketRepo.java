package project.spring.repo;

import project.spring.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepo extends JpaRepository<Ticket, Long> {
    Ticket findTicketById(Long id);

    List<Ticket> findAllByPerformanceId(Long id);


}

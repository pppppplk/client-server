package project.spring.repo;

import project.spring.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Репозиторий TicketRepo - ребенок JPA репозитория
 */

public interface TicketRepo extends JpaRepository<Ticket, Long> {
    Ticket findTicketById(Long id);

    List<Ticket> findAllByPerformanceId(Long id);

    List<Ticket> findTicketByPerformance_Name(String name);

    List<Ticket> findTicketBySeat_Type(String type);


}

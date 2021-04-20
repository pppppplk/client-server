package project.spring.repo;


import project.spring.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
/**
 * Репозиторий SeatRepo - ребенок JPA репозитория
 */

public interface SeatRepo extends JpaRepository<Seat, Long> {


    List<Seat> findAllByHall_Id(Long id);
    Seat findSeatById(Long id);
}

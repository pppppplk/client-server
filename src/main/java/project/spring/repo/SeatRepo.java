package project.spring.repo;

import project.spring.models.Performance;
import project.spring.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepo extends JpaRepository<Seat, Long> {

    List<Seat> findAllByType(String type);

    List<Seat> findAllByHall_Id(Long id);
}

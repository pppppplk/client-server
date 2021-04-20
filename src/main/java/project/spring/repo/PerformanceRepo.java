package project.spring.repo;

import project.spring.models.Performance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Репозиторий PerformanceRepo - ребенок JPA репозитория
 */

public interface PerformanceRepo extends JpaRepository<Performance, Long> {
    List<Performance> findAllByName(String name);

    List<Performance> findAllByTime(String time);
    Performance findPerformanceById(Long id);

    Performance findPerformanceByNameAndTime(String name, String time);


}

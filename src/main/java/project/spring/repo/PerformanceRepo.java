package project.spring.repo;

import project.spring.models.Performance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PerformanceRepo extends JpaRepository<Performance, Long> {
    List<Performance> findAllByName(String name);
}

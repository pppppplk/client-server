
package project.spring.repo;


import project.spring.models.Hall;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий HallRepo - ребенок JPA репозитория
 */
public interface HallRepo extends JpaRepository<Hall, Long> {

    Hall findHallByName(String name);

    Hall findHallById(Long id);


}





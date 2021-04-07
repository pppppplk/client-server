
package project.spring.repo;


import project.spring.models.Hall;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HallRepo extends JpaRepository<Hall, Long> {

//    Hall findHallByTickets(List<Ticket> tickets);
    Hall findHallByName(String name);
}





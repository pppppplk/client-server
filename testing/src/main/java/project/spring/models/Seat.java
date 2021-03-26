package project.spring.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name = "seats")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonManagedReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "hall_id")
    private Hall hall;

    @JsonBackReference
    @OneToOne(mappedBy = "seat")
    private Ticket ticket;

    private boolean employment;
    private  String type;
    private Integer  location;

    public Seat(){
    }



    public Seat(Integer location, String type) {
        this.location = location;
        this.type = type;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public Integer getLocation() {
        return location;
    }

    public Long getId() {
        return id;
    }

    public boolean getEmployment() {
        return employment;
    }

    public void setEmployment(boolean employment) {
        this.employment = employment;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "id=" + id +
                ", hall=" + hall +
                ", ticket=" + ticket +
                ", employment=" + employment +
                ", type='" + type + '\'' +
                ", location=" + location +
                '}';
    }
}

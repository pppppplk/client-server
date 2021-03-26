package project.spring.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "halls")
public class Hall {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonBackReference
    @OneToMany(mappedBy = "hall")
    private List<Seat> seats = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "hall")
    private List<Performance> performances = new ArrayList<>();



    private String name;
    private String time;

    public Hall(){}

    public Hall(String name, String time) {
        this.name =  name;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public List<Performance> getPerformances() {
        return performances;
    }

    public void setPerformances(List<Performance> performances) {
        this.performances = performances;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Hall{" +
                "id=" + id +
                ", seats=" + seats +
                ", performances=" + performances +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}

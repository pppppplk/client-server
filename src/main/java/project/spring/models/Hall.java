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

    private String name;
    private String time;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "hall")
    private List<Performance> performances =  new ArrayList<>();





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

    public void setPerformances(List<Performance> performances) {
        this.performances = performances;
    }



    public List<Performance> getPerformances() {
        return performances;
    }

    @Override
    public String toString() {
        return "Hall{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", performances=" + performances +
                '}';
    }
}

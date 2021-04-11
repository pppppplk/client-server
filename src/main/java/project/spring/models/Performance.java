package project.spring.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "performances")
public class Performance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String timeofpremier, timeofend, time ;
    private Integer agelimit;

    /*
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "hall_id", nullable = false)

     */
    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hall_id")
    private Hall hall;

    public Hall getHall() {
        return hall;
    }



    public Performance(){

    }

    public Performance(String name, String timeofpremier,
                       String timeofend, String time,  Integer agelimit ){
        this.name = name;
        this.timeofpremier = timeofpremier;
        this.timeofend = timeofend;
        this.time = time;
        this.agelimit = agelimit;
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

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getTimeofpremier() {
        return timeofpremier;
    }

    public void setTimeofpremier(String timeofpremier) {
        this.timeofpremier = timeofpremier;
    }

    public String getTimeofend() {
        return timeofend;
    }

    public void setTimeofend(String timeofend) {
        this.timeofend = timeofend;
    }

    public Integer getAgelimit() {
        return agelimit;
    }

    public void setAgelimit(Integer agelimit) {
        this.agelimit = agelimit;
    }


    @Override
    public String toString() {
        return "Performance{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", timeofpremier='" + timeofpremier + '\'' +
                ", timeofend='" + timeofend + '\'' +
                ", time='" + time + '\'' +
                ", agelimit=" + agelimit +
                '}';
    }
}

package project.spring.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;


@Entity
@Table(name = "performances")
public class Performance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "hall_id")
    private Hall hall;

    private String name;
    private String timeofpremier,timeofend ;
    private Integer agelimit;

    public Performance(){

    }

    public Performance(String name, String timeofpremier,
                       String timeofend, Integer agelimit ){
        this.name = name;
        this.timeofpremier = timeofpremier;
        this.timeofend = timeofend;
        this.agelimit = agelimit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public String getName() {
        return name;
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
                ", hall=" + hall +
                ", name='" + name + '\'' +
                ", time_of_premier='" + timeofpremier + '\'' +
                ", time_of_end='" + timeofend + '\'' +
                ", age_limit=" + agelimit +
                '}';
    }
}

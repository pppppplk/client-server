
package project.spring.models;


import javax.persistence.*;

@Entity
@Table(name = "times")
public class Time {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "hall_id", nullable = false)
    private Hall hall;

    private String timeinhall;

    public Time(){}

    public  Time(String timeinhall){
        this.timeinhall = timeinhall;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTimeinhall() {
        return timeinhall;
    }

    public void setTimeinhall(String timeinhall) {
        this.timeinhall = timeinhall;
    }

    @Override
    public String toString() {
        return "Time{" +
                "id=" + id +
                ", timeinhall='" + timeinhall + '\'' +
                '}';
    }
}





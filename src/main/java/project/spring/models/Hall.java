package project.spring.models;


import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "halls")
public class Hall {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;





    //@OneToMany(fetch = FetchType.LAZY, mappedBy = "hall")
    //private List<Performance> performances =  new ArrayList<>();


    //@OneToMany(fetch = FetchType.LAZY, mappedBy = "hall")
    //private List<Time> times =  new ArrayList<>();



    public Hall(){}

    public Hall(String name) {
        this.name =  name;

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

    /*

    public void setPerformances(List<Performance> performances) {
        this.performances = performances;
    }
    public List<Performance> getPerformances() {
        return performances;
    }

     */



    @Override
    public String toString() {
        return "Hall{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

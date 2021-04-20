package project.spring.models;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;

/**
 * Класс Seat
 * Сущность Seat
 */
@Entity
@Table(name = "seats")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Связь многие к одному с сущностью Hall
     */

    @JsonManagedReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "hall_id")
    private Hall hall;

    private  String type;
    private Integer  location;

    public Seat(){
    }

    /**
     * Конструктор, в котором определяются переменные ( атрибуты сущности)
     * @param location - номер места
     * @param type - тип места
     */

    public Seat(Integer location, String type) {
        this.location = location;
        this.type = type;
    }

    /**
     * Getters и Setters
     * Getters - выводять значение
     * Setters - задают значение
     */

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


    public void setId(Long id) {
        this.id = id;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     *  Вывод сущности Seat
     * @return сущность Seat со всеми значениями
     */

    @Override
    public String toString() {
        return "Seat{" +
                "id=" + id +
                ", hall=" + hall +
                ", type='" + type + '\'' +
                ", location=" + location +
                '}';
    }
}

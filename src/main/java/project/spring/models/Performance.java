package project.spring.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;


/**
 * Класс Performance
 * Сущность Performance
 */

@Entity
@Table(name = "performances")
public class Performance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String timeofpremier, timeofend, time ;
    private Integer agelimit;

    /**
     * Связь многие к одному с сущность Hall
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

    /**
     * Конструктор, в котором определяются переменные ( атрибуты сущности)
     * @param name - назвнаие спектакля
     * @param timeofpremier - дата премьеры
     * @param timeofend - дата послежнего показа
     * @param time - время показала спектакля
     * @param agelimit - возрастное ограничение
     */

    public Performance(String name, String timeofpremier,
                       String timeofend, String time,  Integer agelimit ){
        this.name = name;
        this.timeofpremier = timeofpremier;
        this.timeofend = timeofend;
        this.time = time;
        this.agelimit = agelimit;
    }


    /**
     * Getters и Setters
     * Getters - выводять значение
     * Setters - задают значение
     */


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


    /**
     * Вывод сущности Performance
     * @return сущность Performance со всеми значениями
     */


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

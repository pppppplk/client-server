package project.spring.models;


import javax.persistence.*;


/**
 * Класс Hall
 * Сущность Hall
 */

@Entity
@Table(name = "halls")
public class Hall {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;


    public Hall(){}

    /**
     * Конструктор, в котором определяются переменные ( атрибуты сущности)
     * @param name - название зала
     */

    public Hall(String name) {
        this.name =  name;

    }

    /**
     * Getters и Setters
     * Getters - выводять значение
     * Setters - задают значение
     * @return
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

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Вывод сущности Hall
     * @return сущность Hall со всеми значениями
     */

    @Override
    public String toString() {
        return "Hall{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

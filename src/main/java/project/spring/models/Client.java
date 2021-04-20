package project.spring.models;


import javafx.beans.property.SimpleStringProperty;
import javax.persistence.*;

/**
 * Класс Client
 * Сущность Client
 */


@Entity
@Table(name = "clients")
public class Client{



    // создала уникальный идентификатор
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstname, lastname, contact;
    private int age;

    public SimpleStringProperty getFirstNameProp(){return new SimpleStringProperty(firstname); }
    public SimpleStringProperty getLastNameProp(){return new SimpleStringProperty(lastname); }


    /**
     * Конструктор, в котором определяются переменные ( атрибуты сущности)
     * @param firstname - имя
     * @param lastname - фамилия
     * @param contact - номер телефона
     * @param age - возраст
     */

    public Client(String firstname, String lastname, String contact, Integer age) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.contact = contact;
        this.age = age;
    }



    public Client(){}


    /**
     * Getters и Setters
     * Getters - выводять значение
     * Setters - задают значение
     * @return
     */




    public Long getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }


    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * Вывод сущности Client
     * @return сущность Client со всеми значениями
     */


    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", contact='" + contact + '\'' +
                ", age=" + age +
                '}';
    }
}

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

    /**
     * Конструктор Client
     */

    public Client(){}


    /**
     * метод класса Client
     * @return возвращает id клиента
     */
    public Long getId() {
        return id;
    }

    /**
     * метод класса Client
     * @return возвращает возраст клиента
     */

    public int getAge() {
        return age;
    }

    /**
     * метод класса Client, который задает возраст клиента
     * @return возвращает возраст клиента
     */

    public void setAge(int age) {
        this.age = age;
    }

    /**
     * метод класса Client, который задает id клиента
     * @return возвращает id клиента
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * метод класса Client,
     * @return возвращает возраст имя клиента
     */

    public String getFirstname() {
        return firstname;
    }


    /**
     * метод класса Client, который задает имя клиента
     * @return возвращает имя  клиента
     */

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * метод класса Client
     * @return возвращает фамилию клиента
     */

    public String getLastname() {
        return lastname;
    }

    /**
     * метод класса Client, который задает фамилию клиента
     * @return возвращает фамилию клиента
     */

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    /**
     * метод класса Client
     * @return возвращает номер телефона клиента
     */
    public String getContact() {
        return contact;
    }

    /**
     * метод класса Client, который задает номер телефона клиента
     * @return возвращает номер телефона клиента
     */

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

package project.spring.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleSetProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;


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

    public Client(String firstname, String lastname, String contact, Integer age) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.contact = contact;
        this.age = age;
    }



    public Client(){}




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

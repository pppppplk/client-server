package project.spring.models;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;

/**
 * Класс Ticket
 * Сущность Ticket
 */

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    /*
    Соединяю ticket и client
     */

    /**
     * Связь многие к одному со сущностью Client, Seat, Performance
     */

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seat_id")
    private Seat seat;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "perf_id")
    private Performance performance;



    private Integer price;
    private String date;

    /**
     * Конструктор, в котором определяются переменные ( атрибуты сущности)
     * @param price - цена билета
     * @param date - дата проведения
     */

    public Ticket( Integer price, String date) {
        this.price = price;
        this.date = date;
    }

    public Ticket(){}

    /**
     * Getters и Setters
     * Getters - выводять значение
     * Setters - задают значение
     */

    public Performance getPerformance() {
        return performance;
    }

    public void setPerformance(Performance performance) {
        this.performance = performance;
    }

    public Long getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }


    /**
     * Вывод сущности Ticket
     * @return сущность Ticket со всеми значениями
     */

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", client=" + client +
                ", seat=" + seat +
                ", performance=" + performance +
                ", price=" + price +
                ", date='" + date + '\'' +
                '}';
    }
}

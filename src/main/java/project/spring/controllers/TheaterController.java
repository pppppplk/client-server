package project.spring.controllers;


import project.JavaFX;
import project.spring.repo.*;
import project.spring.models.*;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("api/theater")
public class TheaterController {

    private final ClientRepo clientRepo;
    private final TicketRepo ticketRepo;
    private final HallRepo hallRepo;
    private final SeatRepo seatRepo;
    private final PerformanceRepo performanceRepo;
    private final TimeRepo timeRepo;




    public TheaterController(ClientRepo clientRepo, TicketRepo ticketRepo, HallRepo hallRepo,
                             SeatRepo seatRepo, PerformanceRepo performanceRepo, TimeRepo timeRepo) {
        this.clientRepo = clientRepo;
        this.ticketRepo = ticketRepo;
        this.hallRepo = hallRepo;
        this.seatRepo = seatRepo;
        this.performanceRepo = performanceRepo;
        this.timeRepo = timeRepo;
    }



    @PostMapping("/tickets")
    Ticket createTicket(@RequestParam Integer price,@RequestParam Integer location, @RequestParam String type,
                        @RequestParam String firstname, @RequestParam String lastname, @RequestParam String contact,
                        @RequestParam Integer age) {
//        curl -X POST http://127.0.0.1:8080/api/theater/tickets?price=4238&location=9&type=A
        Ticket ticket = new Ticket(price);
        Client client = new Client(firstname, lastname, contact, age);
        Seat seat = new Seat(location, type);

        Date d = new Date();
        SimpleDateFormat date;
        SimpleDateFormat dateprem;
        SimpleDateFormat dateend;
        date = new SimpleDateFormat("12:20");
        dateprem = new SimpleDateFormat("01.01.2021 10:00");
        dateend = new SimpleDateFormat("01.07.2021 20:00");

        date.format(d);
        dateprem.format(d);
        dateend.format(d);
        String date1 = date.format(d);
        String dateprem1 = dateprem.format(d);
        String dateend1 = dateend.format(d);

        Performance performance = new Performance("Мастер и Маргарита",
                dateprem1, dateend1,date1, 16);
        Hall hall = new Hall("малый зал");

        Time time = new Time(date1);

        this.clientRepo.save(client);
        this.hallRepo.save(hall);
        this.timeRepo.save(time);

        seat.setHall(hall);
        this.seatRepo.save(seat);

        ticket.setClient(client);
        ticket.setSeat(seat);
        return this.ticketRepo.save(ticket);
    }





    @GetMapping("/tickets/{id}")
    Ticket getTicket(@PathVariable Long id) {
        System.out.println(id);
//        curl -X GET http://127.0.0.1:8080/api/theater/tickets/2
        return this.ticketRepo.findTicketById(id);
    }

    @GetMapping("/clients/{id}")
    Client getClient(@PathVariable Long id) {
        return this.clientRepo.findClientById(id);
    }

    public void setMain(JavaFX javaFX) {
    }

    @GetMapping("/clients/all")
    List<Client> getClients(){
        return this.clientRepo.findAll();
    }

    @GetMapping("/tickets/all")
    List<Ticket> getTickets(){
        return this.ticketRepo.findAll();
    }

    @GetMapping("/halls/all")
    List<Hall> getHalls(){
        return this.hallRepo.findAll();
    }

    @GetMapping("/times/{id}")
    List<Time> getTimes(@PathVariable Long id){
        return this.timeRepo.findAllByHall_Id(id);
    }


    @GetMapping("/times/all")
    List<Time> getTimes(){
        return this.timeRepo.findAll();
    }

    @GetMapping("/seats/all")
    List<Seat> getSeats(){
        return this.seatRepo.findAll();
    }


    @GetMapping("/perfs/all")
    List<Performance> getPerfs(){
        return this.performanceRepo.findAll();
    }



    @GetMapping("/perfs/data={data}")
    List<Performance> getPerformances(@PathVariable String data){
        List<Performance> allper = this.performanceRepo.findAll();
        List<Performance> list = new ArrayList<>();
        List<String> names = new ArrayList<>();
        for ( Performance datainper  :  allper){
            LocalDate start = LocalDate.parse(datainper.getTimeofpremier());
            LocalDate end = LocalDate.parse(datainper.getTimeofend());
            LocalDate calendar = LocalDate.parse(data);
            if (start != null  &&  end != null){
                if((start.isBefore(calendar) && end.isAfter(calendar)) || (start.isEqual(calendar) || end.isEqual(calendar))){
                    if(!names.contains(datainper.getName())){
                        list.add(datainper);
                        names.add(datainper.getName());
                        System.out.println(names);
                    }
                }

            }else{
                System.out.println("пусто");
            }
        }
        return  list;
    }



    @DeleteMapping("/tickets")

    // http://127.0.0.1:8080/api/theater/tickets?id=..
    Ticket deleteTicket(@RequestParam Long id) {

        Ticket foundTicket = this.ticketRepo.findTicketById(id);
        this.ticketRepo.delete(foundTicket);
        return foundTicket;



    }




    @DeleteMapping("/clients")

        // http://127.0.0.1:8080/api/theater/clients?id=..
    Client deleteClient(@RequestParam Long id) {
        Client foundClient = this.clientRepo.findClientById(id);
        this.clientRepo.delete(foundClient);
        return foundClient;
    }

    @GetMapping("/perfs/name={name}")
    List<Performance> getTimesOnPerf(@PathVariable String name){
        return this.performanceRepo.findAllByName(URLDecoder.decode(name));
    }




    @GetMapping("/seats/type={type}")
    List<Seat> getTypeofSeats(@PathVariable String type){
        System.out.println("srtsdtudiyfddsedfgyuyrtsdgxfchjyitrtudfgvjhu");
        List<Seat> allseat = this.seatRepo.findAll();
        List<Seat> list = new ArrayList<>();
        List<String> names = new ArrayList<>();
        for ( Seat typeinseat  :  allseat){
            if(!names.contains(typeinseat.getType())){
                list.add(typeinseat);
                names.add(typeinseat.getType());
                System.out.println(names);
            }else{
                System.out.println("ошибка");
            }

        }


        return list;
    }


    @PutMapping("/updateclient")
    public Client updateClient(@RequestBody Client newClient){
        Client client = this.clientRepo.findClientById(newClient.getId());
        client.setId(newClient.getId());
        client.setFirstname(newClient.getFirstname());
        client.setLastname(newClient.getLastname());
        client.setAge(newClient.getAge());
        client.setContact(newClient.getContact());
        System.out.println(client);
        return this.clientRepo.save(client);
    }











}

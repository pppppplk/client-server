package project.spring.controllers;


import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.transaction.annotation.Transactional;
import project.JavaFX;
import project.spring.repo.*;
import project.spring.models.*;
import org.springframework.web.bind.annotation.*;
import java.net.URLDecoder;
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





    public TheaterController(ClientRepo clientRepo, TicketRepo ticketRepo, HallRepo hallRepo,
                             SeatRepo seatRepo, PerformanceRepo performanceRepo) {
        this.clientRepo = clientRepo;
        this.ticketRepo = ticketRepo;
        this.hallRepo = hallRepo;
        this.seatRepo = seatRepo;
        this.performanceRepo = performanceRepo;

    }



    @PostMapping("/clients/postclient")
    Client postClient(@RequestBody String client) throws JSONException {
        JSONObject rawClient = new JSONObject(client);
        Client finalClient = new Client(rawClient.getString("firstname"), rawClient.getString("lastname"), rawClient.getString("contact"), rawClient.getInt("age"));
        System.out.println(finalClient);
        return this.clientRepo.save(finalClient);
    }


    @PostMapping("halls/posthalls")
    Hall postHall(@RequestBody Hall hall){
        return this.hallRepo.save(hall);
    }

    @PostMapping("/perf/postperf")
    Performance postPerformance(@RequestBody String performance) throws JSONException {
        JSONObject performanceObject = new JSONObject(performance);
        Performance per = new Performance();
        per.setName(performanceObject.getString("name"));
        per.setTimeofend(performanceObject.getString("timeofpremier"));
        per.setHall(this.hallRepo.findHallById(performanceObject.getJSONObject("hall").getLong("id")));

        return this.performanceRepo.save(per);
    }

    @PostMapping("tickets/posttickets")
    Ticket postTicket(@RequestBody String ticket) throws JSONException {
        JSONObject ticketObject = new JSONObject(ticket);
        Ticket tick  = new Ticket(ticketObject.getInt("price"), ticketObject.getString("date"));
        System.out.println("zskdhaesfhehfjshefg"+ticketObject);
        System.out.println("123456789"+ticket);

        tick.setSeat(this.seatRepo.findSeatById(ticketObject.getJSONObject("seat").getLong("id")));
        tick.setPerformance(this.performanceRepo.findPerformanceById(ticketObject.getJSONObject("performance").getLong("id")));
        tick.setClient(this.clientRepo.findClientById(ticketObject.getJSONObject("client").getLong("id")));
        System.out.println(tick);
        return this.ticketRepo.save(tick);

    }






    @PostMapping("/tickets")
    Ticket createTicket(@RequestParam Integer price,@RequestParam Integer location, @RequestParam String type,
                        @RequestParam String firstname, @RequestParam String lastname, @RequestParam String contact,
                        @RequestParam Integer age) {
//        curl -X POST http://127.0.0.1:8080/api/theater/tickets?price=4238&location=9&type=A

        Date d = new Date();
        SimpleDateFormat date;
        SimpleDateFormat dateprem;
        SimpleDateFormat dateend;
        SimpleDateFormat dateper;
        date = new SimpleDateFormat("12:20");
        dateper = new SimpleDateFormat("2021-04-23");
        dateprem = new SimpleDateFormat("01.01.2021 10:00");
        dateend = new SimpleDateFormat("01.07.2021 20:00");

        String date1 = date.format(d);
        String dateprem1 = dateprem.format(d);
        String dateend1 = dateend.format(d);
        String dateofper = dateper.format(d);

        Ticket ticket = new Ticket(price, dateofper);
        Client client = new Client(firstname, lastname, contact, age);
        Seat seat = new Seat(location, type);


        Performance performance = new Performance("Мастер и Маргарита",
                dateprem1, dateend1,date1, 16);
        Hall hall = new Hall("малый зал");


        this.clientRepo.save(client);
        this.hallRepo.save(hall);


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

    @Transactional
    @PutMapping("/updateclient")
    public Client updateClient(@RequestBody Client newClient){
        Client client = this.clientRepo.findClientById(newClient.getId());
        client.setId(newClient.getId());
        client.setFirstname(newClient.getFirstname());
        client.setLastname(newClient.getLastname());
        client.setContact(newClient.getContact());
        client.setAge(newClient.getAge());
        System.out.println(client);
        return this.clientRepo.save(client);
    }

    @GetMapping("/seats/hall={name}")
    List<Seat> getseatsByHall(@PathVariable String name){
        return this.seatRepo.findAllByHall_Id(this.hallRepo.findHallByName(URLDecoder.decode(name, StandardCharsets.UTF_8)).getId());
    }


    @GetMapping("/seats/name={name}/time={time}")
    List<Seat> getperfByNameandTime(@PathVariable String name, @PathVariable String time){
        Performance chosen = this.performanceRepo.findPerformanceByNameAndTime(URLDecoder.decode(name,
                StandardCharsets.UTF_8), time);
        List<Seat> seatsForPerf = this.seatRepo.findAllByHall_Id(chosen.getHall().getId());
        List<Seat> freeForPerf = new ArrayList<>();
        List<Ticket> ticketsForPerf = this.ticketRepo.findAllByPerformanceId(chosen.getId());
        List<Long> ids = new ArrayList<>();
        for (Ticket tick: ticketsForPerf){
            ids.add(tick.getId());
        }
        for (int i=0; i<seatsForPerf.size(); i++){
            Long seatId = seatsForPerf.get(i).getId();
            if(!ids.contains(seatId)){
                freeForPerf.add(seatsForPerf.get(i));
            }
        }
        System.out.println(freeForPerf);
        return freeForPerf;
    }

    @GetMapping("/perfs/time={time}")
    List<Performance> getPerfByTime(@PathVariable String time){
        return this.performanceRepo.findAllByTime(time);
    }

    @GetMapping("/perfs/name={name}/time={time}")
    Performance getPerByNameandTime(@PathVariable String name, @PathVariable String time) {
        return this.performanceRepo.findPerformanceByNameAndTime(URLDecoder.decode(name), time);
    }



    @GetMapping("/perfs/name={name}")
    List<Performance> getTimesOnPerf(@PathVariable String name){
        return this.performanceRepo.findAllByName(URLDecoder.decode(name));
    }

    @GetMapping("/halls/perfName={name}")
    Hall getHallOnPerf(@PathVariable String name){
        return this.hallRepo.findHallById(this.performanceRepo.findAllByName(URLDecoder.decode(name)).get(0).getHall().getId());
    }







}

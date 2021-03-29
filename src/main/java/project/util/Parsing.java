package project.util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import project.spring.models.*;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class Parsing {

    private ObservableList<Client> client = FXCollections.observableArrayList();
    private ObservableList<Ticket> tickets = FXCollections.observableArrayList();
    private Client clientclass;
    private Ticket ticketclass;
    private Hall hallclass;
    private Performance perfotmanceclass;
    private Seat seatclass;



    public Parsing() throws IOException{


        URL url = new URL("http://localhost:8080/api/theater/tickets/all");


        URLConnection urlConnection = url.openConnection(); // предоставляем доступ к атрибутам url

        InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream()); //получение данных из  источника
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream)); //считывает текст
        String str = " ";

        // считываеи из url  построчно информацию и добавляем ее в строку,
        // затем возвращаем строку уже со всей информацией о погоде, которая находится в url
        while ((str = bufferedReader.readLine()) != null) {

            stringBuilder.append(str);

        }
        String response = stringBuilder.toString();
        bufferedReader.close();
        System.out.println("1111" + response);

        // создание json объекта


        try {
            JSONArray json = new JSONArray(response);

            for (int i =0; i<json.length(); i++) {
                Ticket tempTicket = new Ticket();
                tempTicket.setId(json.getJSONObject(i).getLong("id"));
                tempTicket.setPrice(json.getJSONObject(i).getInt("price"));

                String clientString = json.getJSONObject(i).getString("client");
                JSONObject client = new JSONObject(clientString);
                Client tempClient = new Client();
                tempClient.setId(client.getLong("id"));
                tempClient.setFirstname(client.getString("firstname"));
                tempClient.setLastname(client.getString("lastname"));
                tempClient.setContact(client.getString("contact"));
                tempClient.setAge(client.getInt("age"));
                tempTicket.setClient(tempClient);

                String seatString = json.getJSONObject(i).getString("seat");
                JSONObject seat = new JSONObject(seatString);

                Seat tempSeat = new Seat();
                tempSeat.setId(seat.getLong("id"));
                tempSeat.setEmployment(seat.getBoolean("employment"));
                tempSeat.setType(seat.getString("type"));
                tempSeat.setLocation(seat.getInt("location"));

                String hallString = seat.getString("hall");


                JSONObject hall = new JSONObject(hallString);

                Hall tempHall = new Hall();
                tempHall.setId(hall.getLong("id"));
                tempHall.setName(hall.getString("name"));
                tempHall.setTime(hall.getString("time"));

                String perfomancesString = hall.getString("performances");
                JSONArray perfomances = new JSONArray(perfomancesString);
                List<Performance> performanceList = new ArrayList<>();


                for (int j = 0; j<perfomances.length(); j++){
                    JSONObject perfomanceJSON = perfomances.getJSONObject(j);
                    Performance tempPerfomance = new Performance();
                    tempPerfomance.setId(perfomanceJSON.getLong("id"));
                    tempPerfomance.setName(perfomanceJSON.getString("name"));
                    tempPerfomance.setTimeofpremier(perfomanceJSON.getString("timeofpremier"));
                    tempPerfomance.setTimeofend(perfomanceJSON.getString("timeofend"));
                    tempPerfomance.setAgelimit(perfomanceJSON.getInt("agelimit"));

                    performanceList.add(tempPerfomance);
                    System.out.println(tempPerfomance);

                }
                tempHall.setPerformances(performanceList);
                System.out.println("dtrfyguh" + tempHall);
                tempSeat.setHall(tempHall);
                tempTicket.setSeat(tempSeat);
                System.out.println(tempTicket);
                tickets.add(tempTicket);
                System.out.println("-------------------------");
                System.out.println(tickets);




                 /*
            вывожу имя и фамилию с контактами
             */
               /*
                String firstname = json.getJSONObject(i).getString("firstname");
                String lastname = json.getJSONObject(i).getString("lastname");
                String contact = json.getJSONObject(i).getString("contact");
                String ageperson = json.getJSONObject(i).getString("age");
                String idclient = json.getJSONObject(i).getString("id");*/
            /*
            вывожу id - номер билета
             *//*
                String ticket = json.getJSONObject(i).getString("ticket");
                JSONObject numeric = new JSONObject(ticket);
                String number_of_ticket = numeric.getString("id");
                String price = numeric.getString("price");
                System.out.println("тикет" + ticket);*/

             /*
            вывожу место
             *//*
                String seat = numeric.getString("seat");
                JSONObject seat_on_hall = new JSONObject(seat);
                String location = seat_on_hall.getString("location");
                String type = seat_on_hall.getString("type");*/

            /*
            вывожу название зала и время спектакля
             *//*
                String hall = seat_on_hall.getString("hall");
                JSONObject type_hall = new JSONObject(hall);
                String name = type_hall.getString("name");
                String time = type_hall.getString("time");*/


            /*
            выввожу название спектакля и возрастное ограничение
             *//*
                clientclass = new Client(firstname, lastname, contact, Integer.parseInt(ageperson));
                clientclass.setId(Long.valueOf(idclient));

                ticketclass = new Ticket(Integer.parseInt(price));
                ticketclass.setId(Long.valueOf(number_of_ticket));
                System.out.println(clientclass);

                String performance = type_hall.getString("performances");
                JSONArray name_per = new JSONArray(performance);
                for (int j =0; j<name_per.length(); j++){
                    String name_of_per = name_per.getJSONObject(j).getString("name");
                    String age = name_per.getJSONObject(j).getString("agelimit");
                    String timeofpremier = name_per.getJSONObject(j).getString("timeofpremier");
                    String  timeofend= name_per.getJSONObject(j).getString("timeofend");*/



                    /*

                    clientclass = new Client(firstname, lastname, contact, Integer.parseInt(ageperson));
                    clientclass.setId(Long.valueOf(idclient));
                    client.add(clientclass);

                     */

                    //perfotmanceclass = new Performance(name_of_per, timeofpremier, timeofend, Integer.parseInt(age));
                    //hallclass = new Hall(name, time);

                    //System.out.println("SDXFCGVHBJK" + clientclass.getTicket().getId());

                    //seatclass = new Seat( Integer.parseInt(location), type);

                    /*clientclass.setTicket(ticketclass);
                    client.add(clientclass);

                    System.out.println("id" + idclient + "\nИмя: " + firstname +"\n" + "Фамилия: " + lastname +
                            "\n" + "Номер билета: " + number_of_ticket + "\n" + "Место: " + type + " " + location +
                            "\n" + "Цена: " + price + "\n" + "Зал: " + name + "\n" +
                            "Время: " + time +"\n" + "Контакты " + contact + "\n" + "Название спектакля: " + name_of_per +
                            "\n" + "Возрастное ограничение " + age + "\n***************************"  + timeofpremier + timeofend);*/

            }
            System.out.println(client.size());
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public ObservableList<Client> getClient() {
        return client;
    }



    public ObservableList<Ticket> getTickets() {
        return  tickets;
    }

    public String getContacts(){
        return clientclass.getContact();
    }

    public Integer getPrices(){
        return ticketclass.getPrice();
    }

    public Long getIds(){
        return ticketclass.getId();
    }

    public String getHallName(){
        return hallclass.getName();
    }

    public String getPerfTime(){
        return hallclass.getTime();
    }

    public String getNameofPer(){
        return perfotmanceclass.getName();
    }

    public Integer getSeatLocation(){
        return seatclass.getLocation();
    }

    public String getSeatType(){
        return seatclass.getType();
    }






}

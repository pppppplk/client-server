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

    private ObservableList<Client> clients = FXCollections.observableArrayList();
    private ObservableList<Ticket> tickets = FXCollections.observableArrayList();

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
                System.out.println(perfomancesString + "-----------------------++++++++");
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
                    System.out.println("dssd" + performanceList);
                    tempHall.setPerformances(performanceList);

                }


                tempSeat.setHall(tempHall);
                tempTicket.setSeat(tempSeat);
                System.out.println(tempTicket);
                tickets.add(tempTicket);
                System.out.println("-------------------------");
                System.out.println(tickets);
                System.out.println("cli " + tempTicket.getClient());
                clients.add(tempTicket.getClient());
                System.out.println(clients);



            }
            System.out.println(clients.size());




        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public ObservableList<Client> getClients(){
        return clients;
    }


}

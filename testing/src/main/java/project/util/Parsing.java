package project.util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import project.spring.models.Client;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class Parsing {

    private ObservableList<Client> client = FXCollections.observableArrayList();





    public Parsing() throws IOException{


        URL url = new URL("http://localhost:8080/api/theater/clients/all");


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
        System.out.println(response);

        // создание json объекта


        try {

             /*
            вывожу имя и фамилию с контактами
             */
            JSONArray json = new JSONArray(response);




            for (int i =0; i<json.length(); i++) {



                String firstname = json.getJSONObject(i).getString("firstname");
                String lastname = json.getJSONObject(i).getString("lastname");
                String contact = json.getJSONObject(i).getString("contact");
                String ageperson = json.getJSONObject(i).getString("age");


            /*
            вывожу id - номер билета
             */
                String ticket = json.getJSONObject(i).getString("ticket");
                JSONObject numeric = new JSONObject(ticket);
                String number_of_ticket = numeric.getString("id");
                String price = numeric.getString("price");

             /*
            вывожу место
             */
                String seat = numeric.getString("seat");
                JSONObject seat_on_hall = new JSONObject(seat);
                String location = seat_on_hall.getString("location");
                String type = seat_on_hall.getString("type");

            /*
            вывожу название зала и время спектакля
             */
                String hall = seat_on_hall.getString("hall");
                JSONObject type_hall = new JSONObject(hall);
                String name = type_hall.getString("name");
                String time = type_hall.getString("time");

            /*
            выввожу название спектакля и возрастное ограничение
             */
                String performance = type_hall.getString("performances");
                JSONArray name_per = new JSONArray(performance);
                for (int j =0; j<name_per.length(); j++){
                    String name_of_per = name_per.getJSONObject(j).getString("name");
                    String age = name_per.getJSONObject(j).getString("agelimit");
                    System.out.println("Имя: " + firstname +"\n" + "Фамилия: " + lastname +
                            "\n" + "Номер билета: " + number_of_ticket + "\n" + "Место: " + type + " " + location +
                            "\n" + "Цена: " + price + "\n" + "Зал: " + name + "\n" +
                            "Время: " + time +"\n" + "Контакты " + contact + "\n" + "Название спектакля: " + name_of_per +
                            "\n" + "Возрастное ограничение " + age + "\n***************************");
                }


                client.add(new Client(firstname,lastname, contact, Integer.parseInt(ageperson)));


            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    public ObservableList<Client> getClient() {
        return client;
    }

}

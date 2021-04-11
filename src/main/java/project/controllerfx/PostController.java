
package project.controllerfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import project.JavaFX;
import project.util.Rest;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;


public class PostController {

    private JavaFX main;
    private Stage stage;

    @FXML
    private ChoiceBox<String> hallChoiceBox;

    @FXML
    private ChoiceBox<String> zoneChoiceBox;

    @FXML
    private TextField firstname;

    @FXML
    private TextField lastname;

    @FXML
    private TextField contact;

    @FXML
    private TextField age;


    @FXML
    private Label pricelabel;

    @FXML
    private ChoiceBox<String> timeChoiceBox;

    @FXML
    private ChoiceBox<Integer> placeChoiceBox;

    @FXML
    private ChoiceBox<String> perfomChoiceBox;

    @FXML
    private DatePicker calendar;

    @FXML
    private Button ok;

    @FXML
    private Button cancel;



    private Rest connect = new Rest();
    private LocalDate datestart;
    private LocalDate dateend;
    private Rest rest = new Rest();




    @FXML
    private void initialize() throws IOException, JSONException {
        PostClient();
    }

    /**
     * метод для проверки валидации на ввод данных имя и фамилия
     * @param string
     * @return
     */
    public static boolean TestString(String string){
        return string.matches("^[а-яА-Я]+$");

    }

    /**
     * метод для проверки валидации на ввод данных о возрасте
     * @param string
     * @return
     */

    public static boolean TestInt(String string){
        return string.matches("^(\\d){1,2}$");

    }

    /**
     * метод для проверки валидвции ввода номера телефона
     * @param string
     * @return
     */


    public static boolean TestContact(String string){
        return string.matches("^\\+\\d?-?\\d?\\d?\\d{11}$");

    }




    public PostController() throws IOException{ }

    private void PostClient(){
        ok.setOnAction(actionEvent -> {
            if(firstname.getText() == "" || lastname.getText() == "" || contact.getText() == "" || age.getText() == "" ||
                zoneChoiceBox.getValue() == null || perfomChoiceBox.getValue() == null || hallChoiceBox.getValue() == null ||
                placeChoiceBox.getValue() == null || timeChoiceBox.getValue() == null || calendar.getValue() == null ){
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setTitle("Ошибка");
                alert2.setHeaderText("Не все данные введены");
                alert2.showAndWait();
            }else{
                if((TestString(firstname.getText())) && (TestString(lastname.getText())) && (TestContact(contact.getText()))  && (TestInt(age.getText())) ){
                    if(calendar.getValue().isAfter(LocalDate.now())) {

                        System.out.println("проверка пройдена");
                        JSONObject jsonObjectclient = new JSONObject();
                        JSONObject jsonObjectticket = new JSONObject();
                        JSONObject jsonObjectperfomance = new JSONObject();
                        JSONObject jsonObjecthall = new JSONObject();
                        JSONObject jsonObjectseat = new JSONObject();

                        try {
                            jsonObjectclient.put("firstname", firstname.getText());
                            jsonObjectclient.put("lastname", lastname.getText());
                            jsonObjectclient.put("contact", contact.getText());
                            jsonObjectclient.put("age", age.getText());
                            rest.PostRest("http://127.0.0.1:8080/api/theater/clients/postclient", jsonObjectclient);
                            System.out.println("добавила");
                            if (jsonObjectclient != null) {
                                jsonObjectticket.put("client", jsonObjectclient);
                                jsonObjectperfomance.put("name", perfomChoiceBox.getValue());
                                jsonObjectperfomance.put("time", timeChoiceBox.getValue());
                                jsonObjectperfomance.put("hall", jsonObjecthall);
                                jsonObjecthall.put("name", hallChoiceBox.getValue());
                                jsonObjectseat.put("type", zoneChoiceBox.getValue());
                                jsonObjectseat.put("location", placeChoiceBox.getValue());
                                jsonObjectseat.put("hall", jsonObjecthall);
                                jsonObjectticket.put("performance", jsonObjectperfomance);
                                jsonObjectticket.put("seat", jsonObjectseat);
                                jsonObjectticket.put("price", pricelabel.getText());
                                jsonObjectticket.put("date", calendar.getValue());
                                rest.PostRest("http://127.0.0.1:8080/api/theater/tickets/posttickets", jsonObjectticket);
                                System.out.println("билет добавлен");
                            } else {
                                System.out.println("не  могу добавить билет");
                            }


                        } catch (JSONException | IOException e) {
                            e.printStackTrace();
                        }
                    }else{
                        System.out.println("такая дата не может быть");
                        Alert alert2 = new Alert(Alert.AlertType.ERROR);
                        alert2.setTitle("Ошибка");
                        alert2.setHeaderText("Неверная дата. Дата не может быть раньше сегодняшнего числа");
                        alert2.showAndWait();
                    }
                    System.out.println("добавили нового пользователя ");

                }else{
                    Alert alert2 = new Alert(Alert.AlertType.ERROR);
                    alert2.setTitle("Ошибка");
                    alert2.setHeaderText("Неверный тип данных. Введите имя/фамилию русскими символами. Номер телефона вводится через +7");
                    alert2.showAndWait();
                }
            }
        });




    }

    /**
     * сопоставление даты спектаклей с календарем
     * @throws IOException
     * @throws JSONException
     */

    @FXML
    private void Choice() throws IOException, JSONException {
        JSONArray getper =  new JSONArray(connect.GetRest("http://localhost:8080/api/theater/perfs/data=" + calendar.getValue()));
        System.out.println("не обработанные даты" + getper);
        ObservableList<String> nameofper = FXCollections.observableArrayList();
        for (int i=0; i<getper.length(); i++) {
            nameofper.add(getper.getJSONObject(i).getString("name"));
            System.out.println("----------------"+nameofper);

        }
        perfomChoiceBox.setItems(nameofper);
    }

    /**
     * функция заполнения времени по спектаклю
     * @throws IOException
     * @throws JSONException
     */
    @FXML
    private void TimePerf() throws IOException, JSONException {
        System.out.println(perfomChoiceBox.getValue());
        JSONArray getTimes =  new JSONArray(connect.GetRest("http://localhost:8080/api/theater/perfs/name="
                + URLEncoder.encode(perfomChoiceBox.getValue(), StandardCharsets.UTF_8)));
        System.out.println("обработанные времена" + getTimes);
        ObservableList<String> timeofper = FXCollections.observableArrayList();
        for (int i=0; i<getTimes.length(); i++) {
            timeofper.add(getTimes.getJSONObject(i).getString("time"));



        }
        System.out.println("вывод времени"+ timeofper);
        timeChoiceBox.setItems(timeofper);
        JSONObject hall = new JSONObject(connect.GetRest("http://localhost:8080/api/theater/halls/perfName="+ URLEncoder.encode(perfomChoiceBox.getValue(), StandardCharsets.UTF_8)));
        ObservableList<String> hallNames = FXCollections.observableArrayList();
        hallNames.add(hall.getString("name"));
        hallChoiceBox.setItems(hallNames);
    }

    /**
     * функция заполнения зон по залу
     * @throws IOException
     * @throws JSONException
     */

    @FXML
    private void fillingZones() throws IOException, JSONException {
        System.out.println(hallChoiceBox.getValue());
        try {
            JSONArray getseats = new JSONArray(connect.GetRest("http://localhost:8080/api/theater/seats/hall=" + URLEncoder.encode(hallChoiceBox.getValue(), StandardCharsets.UTF_8)));
        ObservableList<String> zones = FXCollections.observableArrayList();
        for (int i=0; i<getseats.length(); i++) {
            if(!zones.contains(getseats.getJSONObject(i).getString("type"))){
                zones.add(getseats.getJSONObject(i).getString("type"));
                zoneChoiceBox.setItems(zones);
            }
        }}catch (RuntimeException e){
            ObservableList<String> zones = FXCollections.observableArrayList();
            zoneChoiceBox.setItems(zones);
        }

    }


    /**
     * вывожу толкьо свободные места и задаю цену по зоне
     * @throws IOException
     * @throws JSONException
     */


    @FXML
    private void fillingSeats() throws IOException, JSONException {
        try {
            JSONArray getseats = new JSONArray(connect.GetRest("http://localhost:8080/api/theater/seats/name=" +
                    URLEncoder.encode(perfomChoiceBox.getValue(), StandardCharsets.UTF_8) +
                    "/time=" + URLEncoder.encode(timeChoiceBox.getValue(), StandardCharsets.UTF_8)));

            ObservableList<Integer> seats = FXCollections.observableArrayList();
            System.out.println("вывод мест " + getseats);
            for (int i = 0; i < getseats.length(); i++) {
                if (getseats.getJSONObject(i).getString("type").equals(zoneChoiceBox.getValue()) && getseats.getJSONObject(i).getJSONObject("hall").getString("name").equals(hallChoiceBox.getValue())) {
                    seats.add(getseats.getJSONObject(i).getInt("location"));
                }
            }
            placeChoiceBox.setItems(seats);
            switch (zoneChoiceBox.getValue()) {
                case "Партер":
                    pricelabel.setText("3500");
                    break;
                case "Амфитеатр":
                    pricelabel.setText("3000");
                    break;
                case "Бельэтаж":
                    pricelabel.setText("2000");
                    break;
                case "Балкон":
                    pricelabel.setText("1300");
                    break;
            }
        }catch (RuntimeException e){
            e.printStackTrace();
            ObservableList<Integer> seats = FXCollections.observableArrayList();
            placeChoiceBox.setItems(seats);
            pricelabel.setText(" ");
        }
    }




















}



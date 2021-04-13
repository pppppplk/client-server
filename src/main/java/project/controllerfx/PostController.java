
package project.controllerfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import project.DTO.HallDTO;
import project.DTO.SeatDTO;
import project.DTO.SeatLocationDTO;
import project.JavaFX;
import project.DTO.PerformanceDTO;
import project.spring.models.Seat;
import project.util.Rest;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class PostController {

    private JavaFX main;
    private Stage stage;

    private PerformanceDTO performanceDTO;

    @FXML
    private ChoiceBox<HallDTO> hallChoiceBox;

    @FXML
    private ChoiceBox<SeatDTO> zoneChoiceBox;

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
    private ChoiceBox<SeatLocationDTO> placeChoiceBox;

    @FXML
    private ChoiceBox<PerformanceDTO> perfomChoiceBox;

    /**
     * @FXML private ChoiceBox<String> perfomChoiceBox;
     */

    @FXML
    private DatePicker calendar;

    @FXML
    private Button ok;




    private Rest connect = new Rest();
    private LocalDate datestart;
    private LocalDate dateend;
    private Rest rest = new Rest();


    @FXML
    private void initialize() throws IOException, JSONException {
        PostClient();
    }


    public JavaFX getMain() {
        return main;
    }

    public void setMain(JavaFX main) {
        this.main = main;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * метод для проверки валидации на ввод данных имя и фамилия
     *
     * @param string
     * @return
     */
    public static boolean TestString(String string) {
        return string.matches("^[а-яА-Я]+$");

    }

    /**
     * метод для проверки валидации на ввод данных о возрасте
     *
     * @param string
     * @return
     */

    public static boolean TestInt(String string) {
        return string.matches("^(\\d){1,2}$");

    }

    /**
     * метод для проверки валидвции ввода номера телефона
     *
     * @param string
     * @return
     */


    public static boolean TestContact(String string) {
        return string.matches("^\\+\\d?-?\\d?\\d?\\d{11}$");

    }


    public PostController() throws IOException {
    }

    /**
     * добавление нового клиента + новый билет
     */

    private void PostClient() {
        ok.setOnAction(actionEvent -> {
            if (firstname.getText() == "" || lastname.getText() == "" || contact.getText() == "" || age.getText() == "" ||
                    zoneChoiceBox.getValue().getType() == null || perfomChoiceBox.getValue().getName() == null || hallChoiceBox.getValue().getName() == null ||
                    placeChoiceBox.getValue().getLocation() == null || timeChoiceBox.getValue() == null || calendar.getValue() == null) {
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setTitle("Ошибка");
                alert2.setHeaderText("Не все данные введены");
                alert2.showAndWait();
            } else {
                if ((TestString(firstname.getText())) && (TestString(lastname.getText())) && (TestContact(contact.getText())) && (TestInt(age.getText()))) {
                    if (calendar.getValue().isAfter(LocalDate.now()) || calendar.getValue().isEqual(LocalDate.now())) {
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
                            JSONObject jsonObjectcl = new JSONObject(rest.PostRest("http://127.0.0.1:8080/api/theater/clients/postclient", jsonObjectclient));
                            System.out.println("добавила");
                            if (jsonObjectclient.length() != 0) {
                                jsonObjectticket.put("client", jsonObjectcl);
                                jsonObjectperfomance.put("id", perfomChoiceBox.getValue().getId());
                                jsonObjectperfomance.put("name", perfomChoiceBox.getValue().getName());
                                jsonObjectperfomance.put("time", timeChoiceBox.getValue());
                                jsonObjectperfomance.put("hall", jsonObjecthall);

                                jsonObjecthall.put("id", hallChoiceBox.getValue().getId());
                                jsonObjecthall.put("name", hallChoiceBox.getValue().getName());

                                jsonObjectseat.put("type", zoneChoiceBox.getValue().getType());
                                jsonObjectseat.put("location", placeChoiceBox.getValue().getLocation());
                                jsonObjectseat.put("id", placeChoiceBox.getValue().getId());
                                jsonObjectseat.put("hall", jsonObjecthall);

                                jsonObjectticket.put("performance", jsonObjectperfomance);
                                jsonObjectticket.put("seat", jsonObjectseat);
                                jsonObjectticket.put("price", pricelabel.getText());
                                jsonObjectticket.put("date", calendar.getValue());
                                System.out.println(jsonObjectticket);
                                rest.PostRest("http://127.0.0.1:8080/api/theater/tickets/posttickets", jsonObjectticket);

                                System.out.println("билет добавлен");
                                //this.main.initRootLayout();

                            } else {
                                System.out.println("не  могу добавить билет");
                            }


                        } catch (JSONException | IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("такая дата не может быть");
                        Alert alert2 = new Alert(Alert.AlertType.ERROR);
                        alert2.setTitle("Ошибка");
                        alert2.setHeaderText("Неверная дата. Дата не может быть раньше сегодняшнего числа");
                        alert2.showAndWait();
                    }
                    System.out.println("добавили нового пользователя ");

                } else {
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
     *
     * @throws IOException
     * @throws JSONException
     */

    @FXML
    private void Choice() throws IOException, JSONException {

        try{
            JSONArray getper = new JSONArray(connect.GetRest("http://localhost:8080/api/theater/perfs/data=" + calendar.getValue()));
            System.out.println("не обработанные даты" + getper);
            ObservableList<PerformanceDTO> representations = FXCollections.observableArrayList();
            for (int i = 0; i < getper.length(); i++) {
                representations.add(PerformanceDTO.of(getper.getJSONObject(i)));
            }

            perfomChoiceBox.setItems(representations);
        } catch (NullPointerException e){
            JSONArray getper = new JSONArray(connect.GetRest("http://localhost:8080/api/theater/perfs/data=" + calendar.getValue()));
            System.out.println("не обработанные даты" + getper);
            ObservableList<PerformanceDTO> representations = FXCollections.observableArrayList();
            for (int i = 0; i < getper.length(); i++) {
                representations.add(PerformanceDTO.of(getper.getJSONObject(i)));
            }

            perfomChoiceBox.setItems(representations);
        }
        catch(Exception e ){
            System.out.println("ошибка");
            e.printStackTrace();
        }

    }

    /**
     * функция заполнения времени по спектаклю
     *
     * @throws IOException
     * @throws JSONException
     */
    @FXML
    private void TimePerf() throws IOException, JSONException {
        try{
            System.out.println(perfomChoiceBox.getValue());
            JSONArray getTimes = new JSONArray(connect.GetRest("http://localhost:8080/api/theater/perfs/name="
                    + URLEncoder.encode(perfomChoiceBox.getValue().getName(), StandardCharsets.UTF_8)));
            System.out.println("обработанные времена" + getTimes);
            ObservableList<String> timeofper = FXCollections.observableArrayList();
            for (int i = 0; i < getTimes.length(); i++) {
                timeofper.add(getTimes.getJSONObject(i).getString("time"));


            }
            System.out.println("вывод времени" + timeofper);
            timeChoiceBox.setItems(timeofper);
            JSONObject hall = new JSONObject(connect.GetRest("http://localhost:8080/api/theater/halls/perfName=" + URLEncoder.encode(perfomChoiceBox.getValue().getName(), StandardCharsets.UTF_8)));
            ObservableList<HallDTO> hallNames = FXCollections.observableArrayList();
//        hallNames.add(hall.getString("name"));
            for (PerformanceDTO perfomance : perfomChoiceBox.getItems()) {
                if (perfomChoiceBox.getValue().getHall().getName().equals(perfomance.getHall().getName())) {
                    hallNames.add(perfomance.getHall());
                }
            }
            hallChoiceBox.setItems(hallNames);
        }catch(NullPointerException e){
            System.out.println("ошибка 2");
            e.printStackTrace();
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Справка");
            alert2.setHeaderText("Изменение даты");
            alert2.showAndWait();
            


        }

    }

    /**
     * функция заполнения зон по залу
     *
     * @throws IOException
     * @throws JSONException
     */

    @FXML
    private void fillingZones() throws IOException, JSONException {
        System.out.println(hallChoiceBox.getValue());
        JSONArray getseats = new JSONArray(connect.GetRest("http://localhost:8080/api/theater/seats/hall=" + URLEncoder.encode(hallChoiceBox.getValue().getName(), StandardCharsets.UTF_8)));
        ObservableList<SeatDTO> zones = FXCollections.observableArrayList();
        zones.add(SeatDTO.of(getseats.getJSONObject(0)));
        for (int i = 0; i < getseats.length(); i++) {
            SeatDTO seatDTO = SeatDTO.of(getseats.getJSONObject(i));
            zones.add(seatDTO);
        }

        boolean flag = false;
        ObservableList<SeatDTO> uniqZones = FXCollections.observableArrayList();
        for (int i = 0; i < zones.size(); i++) {
            for (int j = 0; j < uniqZones.size(); j++) {
                if (zones.get(i).getType().equals(uniqZones.get(j).getType())) {
                    flag = true;

                }
            }
            if (!flag){
                uniqZones.add(zones.get(i));
            }
            flag = false;
        }


        zoneChoiceBox.setItems(uniqZones);

    }


    /**
     * вывожу толкьо свободные места и задаю цену по зоне
     *
     * @throws IOException
     * @throws JSONException
     */


    @FXML
    private void fillingSeats() throws IOException, JSONException {
        try {
            JSONArray getseats = new JSONArray(connect.GetRest("http://localhost:8080/api/theater/seats/name=" +
                    URLEncoder.encode(perfomChoiceBox.getValue().getName(), StandardCharsets.UTF_8) +
                    "/time=" + URLEncoder.encode(timeChoiceBox.getValue(), StandardCharsets.UTF_8)));

            ObservableList<SeatLocationDTO> seats = FXCollections.observableArrayList();
            System.out.println("вывод мест " + getseats);
            for (int i = 0; i < getseats.length(); i++) {
                SeatLocationDTO seatLocationDTO = SeatLocationDTO.of(getseats.getJSONObject(i));
                if (getseats.getJSONObject(i).getString("type").equals(zoneChoiceBox.getValue().getType()) && getseats.getJSONObject(i).getJSONObject("hall").getString("name").equals(hallChoiceBox.getValue().getName())) {
                    seats.add(seatLocationDTO);
                }
            }
            placeChoiceBox.setItems(seats);
            switch (zoneChoiceBox.getValue().getType()) {
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
        } catch (RuntimeException e) {
            e.printStackTrace();
            ObservableList<SeatLocationDTO> seats = FXCollections.observableArrayList();
            placeChoiceBox.setItems(seats);
            pricelabel.setText(" ");
        }
    }


}



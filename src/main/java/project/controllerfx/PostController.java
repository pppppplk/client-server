
package project.controllerfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONException;
import project.JavaFX;
import project.util.Rest;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


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







    @FXML
    private void initialize() throws IOException, JSONException {


        System.out.println("полученная инфа из пост");



        JSONArray gethall =  new JSONArray(connect.GetRest("http://localhost:8080/api/theater/halls/all"));

        ObservableList<String> names = FXCollections.observableArrayList();
        for (int i=0; i<gethall.length(); i++){
            names.add(gethall.getJSONObject(i).getString("name"));
            String id_hall = gethall.getJSONObject(i).getString("id");

        }
        hallChoiceBox.setItems(names);


        






        ObservableList<Integer>  place = FXCollections.observableArrayList(1,2 ,3, 4 ,5, 6, 7, 8, 9, 10);
        placeChoiceBox.setItems(place);

        /**
        JSONArray getseat =  new JSONArray(connect.GetRest("http://localhost:8080/api/theater/seats/all"));
        System.out.println("место" + getseat);
        ObservableList<String> nameofseat = FXCollections.observableArrayList();

        for (int i=0; i<getseat.length(); i++) {
            nameofseat.add(getseat.getJSONObject(i).getString("employment"));
            nameofseat.add(getseat.getJSONObject(i).getString("location"));
            nameofseat.add(getseat.getJSONObject(i).getString("type"));


        }

        System.out.println(nameofseat);
         */











        /**
         * соотвкствие цены с типом сиденьев в зале
         */


        ObservableList<String>  zone = FXCollections.observableArrayList("Партер", "Амфитеатр", "Бельэтаж", "Балкон");
        zoneChoiceBox.setItems(zone);
        zoneChoiceBox.setOnAction(actionEvent -> {
            String value = "Партер";
            String value1 = "Амфитеатр";
            String value2 = "Бельэтаж";
            String value3 = "Балкон";
            if(value == zoneChoiceBox.getValue()){
                pricelabel.setText("3500");
            }else if (value1 == zoneChoiceBox.getValue()){
                pricelabel.setText("3000");
            }else if (value2 == zoneChoiceBox.getValue()){
                pricelabel.setText("2000");
            }else if (value3 == zoneChoiceBox.getValue()){
                pricelabel.setText("1300");
            }

        });



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
                placeChoiceBox.getValue() == null || timeChoiceBox.getValue() == null || calendar.getValue() == null){
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setTitle("Ошибка");
                alert2.setHeaderText("Не все данные введены");
                alert2.showAndWait();
            }else{
                if((TestString(firstname.getText())) && (TestString(lastname.getText())) && (TestContact(contact.getText()))  && (TestInt(age.getText())) ){
                    System.out.println("проверка пройдена");

                }else{
                    Alert alert2 = new Alert(Alert.AlertType.ERROR);
                    alert2.setTitle("Ошибка");
                    alert2.setHeaderText("Неверный тип данных. Введите имя/фамилию русскими символами. Номер телефона вводится через +7");
                    alert2.showAndWait();
                }
                System.out.println("добавили нового пользователя ");
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
        JSONArray getTimes =  new JSONArray(connect.GetRest("http://localhost:8080/api/theater/perfs/name="+ URLEncoder.encode(perfomChoiceBox.getValue(), StandardCharsets.UTF_8)));
        System.out.println("обработанные времена" + getTimes);
        ObservableList<String> timeofper = FXCollections.observableArrayList();
        for (int i=0; i<getTimes.length(); i++) {
            timeofper.add(getTimes.getJSONObject(i).getString("time"));
        }
        timeChoiceBox.setItems(timeofper);
    }




    /*

    @FXML
    private void handleLocalType() throws IOException, JSONException {
        System.out.println("тип" + zoneChoiceBox.getValue());
        JSONArray getLocal = new JSONArray(connect.GetRest("http://localhost:8080/api/theater/seats/type=" +URLEncoder.encode(zoneChoiceBox.getValue(), StandardCharsets.UTF_8)));
        System.out.println("обработанные места" + getLocal);
        ObservableList<Integer> localofseat = FXCollections.observableArrayList();
        for (int i=0; i<getLocal.length(); i++) {
            localofseat.add(getLocal.getJSONObject(i).getInt("location"));
        }
        placeChoiceBox.setItems(localofseat);
    }

     */



}



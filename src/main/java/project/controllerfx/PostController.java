
package project.controllerfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONException;
import project.JavaFX;
import project.spring.models.Hall;
import project.spring.models.Ticket;
import project.util.Rest;
import project.util.Parsing;

import java.io.IOException;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.regex.Pattern;

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

    private Rest connect = new Rest();
    private LocalDate datestart;
    private LocalDate dateend;



    @FXML
    private void initialize() throws IOException, JSONException {


        System.out.println("полученная инфа из пост");

        /**
         * проверка валидвции ввода данных
         */

        firstname.setOnAction(actionEvent -> {
            System.out.println(firstname.getText());

            if(TestString(firstname.getText())){
                System.out.println("проверка пройдена");
            }else{
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setTitle("Ошибка");
                alert2.setHeaderText("Неверный тип данных. Введите буквы");
                alert2.showAndWait();
            }
        });

        lastname.setOnAction(actionEvent -> {
            System.out.println(lastname.getText());

            if(TestString(lastname.getText())){
                System.out.println("проверка пройдена");
            }else{
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setTitle("Ошибка");
                alert2.setHeaderText("Неверный тип данных. Введите буквы");
                alert2.showAndWait();
            }
        });


        age.setOnAction(actionEvent -> {
            System.out.println(age.getText());

            if((TestInt(age.getText()))){
                System.out.println("проверка пройдена");
            }else{
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setTitle("Ошибка");
                alert2.setHeaderText("Неверный тип данных. Введите число");
                alert2.showAndWait();
            }
        });

        contact.setOnAction(actionEvent -> {
            System.out.println(contact.getText());

            if((TestContact(contact.getText()))){
                System.out.println("проверка пройдена");
            }else{
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setTitle("Ошибка");
                alert2.setHeaderText("Неверный тип данных. Введите сотовый номер телефона");
                alert2.showAndWait();
            }
        });




        /**
         * не доделан календаль и выборка по нему
         */
        JSONArray getper =  new JSONArray(connect.GetRest("http://localhost:8080/api/theater/perfs/all"));

        ObservableList<String> nameofper = FXCollections.observableArrayList();

        try{
            for (int i=0; i<getper.length(); i++) {
                nameofper.add(getper.getJSONObject(i).getString("name"));
                datestart = LocalDate.parse(getper.getJSONObject(i).getString("timeofpremier"));
                dateend = LocalDate.parse(getper.getJSONObject(i).getString("timeofend"));
                if (calendar.getValue() != null){
                    System.out.println("календарь"+ calendar.getValue());
                }else{
                    calendar.setOnAction(actionEvent -> {
                        System.out.println("календарь 2 "+ calendar.getValue());
                        System.out.println("начало "+ datestart);
                        try{
                            if(datestart.isBefore(calendar.getValue()) && dateend.isAfter(calendar.getValue()) ){
                                perfomChoiceBox.setItems(nameofper);

                            }else{
                                perfomChoiceBox = null;

                            }
                        }catch (Exception e ){
                            e.printStackTrace();
                            Alert alert2 = new Alert(Alert.AlertType.WARNING);
                            alert2.setTitle("Ошибка");
                            alert2.setHeaderText("На данную дату нет спектаклей");
                            alert2.showAndWait();

                        }

                    });
                    System.out.println("пустой календаль");
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }


        //perfomChoiceBox.setItems(nameofper);

        /**
         * соотвествие зала со временем
         */


        JSONArray gethall =  new JSONArray(connect.GetRest("http://localhost:8080/api/theater/halls/all"));

        ObservableList<String> names = FXCollections.observableArrayList();
        for (int i=0; i<gethall.length(); i++){
            names.add(gethall.getJSONObject(i).getString("name"));


        }
        hallChoiceBox.setItems(names);

        JSONArray gettime =  new JSONArray(connect.GetRest("http://localhost:8080/api/theater/times/"+1));
        JSONArray gettime2 =  new JSONArray(connect.GetRest("http://localhost:8080/api/theater/times/"+2));
        ObservableList<String> nameoftime = FXCollections.observableArrayList();
        ObservableList<String> nameoftime2 = FXCollections.observableArrayList();
        for (int i=0; i<gettime.length() || i<gettime2.length(); i++) {
            nameoftime.add(gettime.getJSONObject(i).getString("timeinhall"));
            nameoftime2.add(gettime2.getJSONObject(i).getString("timeinhall"));
        }
        System.out.println(nameoftime);

        hallChoiceBox.setOnAction(actionEvent -> {
            String hallname = names.get(0);
            String hallname1 = names.get(1);

            if(hallname == hallChoiceBox.getValue()){
                timeChoiceBox.setItems(nameoftime);

            }else if(hallname1 == hallChoiceBox.getValue()){
                System.out.println("vudsagsbac 22222" );
                timeChoiceBox.setItems(nameoftime2);
            }else{
                System.out.println("не заходит");
            }

        });



        ObservableList<Integer>  place = FXCollections.observableArrayList(1,2 ,3, 4 ,5, 6, 7, 8, 9, 10);
        placeChoiceBox.setItems(place);


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

    }



}



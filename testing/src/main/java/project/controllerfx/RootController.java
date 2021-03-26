package project.controllerfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import project.JavaFX;
import project.util.Parsing;
import project.spring.models.Client;

import java.io.IOException;

public class RootController {

    private ObservableList<Client> clients = FXCollections.observableArrayList();
    private Parsing parsing = new Parsing();

    private Client client = new Client();



    @FXML
    private TableView<Client> clientInfo;

    @FXML
    private TableColumn<Client, String> surnamecolumn;

    @FXML
    private TableColumn<Client, String> namecolumn;

    @FXML
    private ChoiceBox<String> hallChoiceBox;

    @FXML
    private ChoiceBox<String> zoneChoiceBox;

    @FXML
    private ChoiceBox<String> priceChoiceBox;

    @FXML
    private Label lablecontact;


    private JavaFX main;
    private Stage stage;

    public RootController() throws IOException {
    }

    @FXML
    private void  initialize(){

        initTable();
        namecolumn.setCellValueFactory(new PropertyValueFactory<Client, String>("firstname"));
        surnamecolumn.setCellValueFactory(new PropertyValueFactory<Client, String>("lastname"));
        ShowInfo();





        /*
        заполняю выпадающий список зала

        ObservableList<String> hall = FXCollections.observableArrayList("Большой зал", "Малый зал", "Средний зал");
        hallChoiceBox.setItems(hall);

        ObservableList<String>  zone = FXCollections.observableArrayList("Партер", "Амфитеатр", "Бельэтаж", "Балкон");
        zoneChoiceBox.setItems(zone);

        ObservableList<String>  price = FXCollections.observableArrayList("3500", "3000", "2000", "1300");
        priceChoiceBox.setItems(price);

         */

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


    private void initTable(){
        this.clientInfo.setItems(parsing.getClient());

    }

    private void ShowInfo(){
        if(clients != null){
            lablecontact.setText("fjdkelw");
        }else{
            lablecontact.setText("не информации");
        }

    }

}

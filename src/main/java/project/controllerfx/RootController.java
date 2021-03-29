package project.controllerfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import project.JavaFX;
import project.spring.models.Ticket;
import project.util.Parsing;
import project.spring.models.Client;

import java.io.IOException;

public class RootController {

    private ObservableList<Client> clients = FXCollections.observableArrayList();
    private Parsing parsing = new Parsing();





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

    @FXML
    private Label lableprice;

    @FXML
    private Label lableid;

    @FXML
    private Label lablehallname;

    @FXML
    private Label lablepertime;

    @FXML
    private Label lablenameofper;

    @FXML
    private Label lableseatlocation;

    @FXML
    private Label lableseattype;


    private JavaFX main;
    private Stage stage;

    public RootController() throws IOException {
    }

    @FXML
    private void  initialize(){

        initTable();
        namecolumn.setCellValueFactory(new PropertyValueFactory<Client, String>("firstname"));
        surnamecolumn.setCellValueFactory(new PropertyValueFactory<Client, String>("lastname"));

        ShowInfo(null); //очищаем справа
        clientInfo.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)
                -> ShowInfo(newValue));






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
        System.out.println("66666" + this.clientInfo.getItems());

    }

    private void ShowInfo(Client client){
        if( client!= null){


            lablecontact.setText(String.valueOf(client.getContact()));
            //lableid.setText(String.valueOf(client.getTicket().getId()));


            //lableprice.setText(String.valueOf(client.getTicket().getId()));

            /*
            lableprice.setText(String.valueOf(parsing.getPrices()));
            lableid.setText(String.valueOf(parsing.getIds()));
            lablehallname.setText(String.valueOf(parsing.getHallName()));
            lablepertime.setText(String.valueOf(parsing.getPerfTime()));
            lablenameofper.setText(String.valueOf(parsing.getNameofPer()));
            lableseatlocation.setText(String.valueOf(parsing.getSeatLocation()));
            lableseattype.setText(String.valueOf(parsing.getSeatType()));

             */
        }else{
            lablecontact.setText("не информации");
        }

    }

}

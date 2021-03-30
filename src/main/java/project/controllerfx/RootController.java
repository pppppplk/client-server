package project.controllerfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import project.JavaFX;
import project.spring.models.Performance;
import project.spring.models.Ticket;
import project.util.Parsing;
import project.spring.models.Client;

import java.io.IOException;

public class RootController {

    private ObservableList<Client> clients = FXCollections.observableArrayList();
    private Parsing parsing = new Parsing();






    @FXML
    private TableView<Ticket> clientInfo;

    @FXML
    private TableColumn<Ticket, String> surnamecolumn;

    @FXML
    private TableColumn<Ticket, String> namecolumn;

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
        namecolumn.setCellValueFactory(cellData -> cellData.getValue().getClient().getFirstNameProp());
        surnamecolumn.setCellValueFactory(cellData -> cellData.getValue().getClient().getLastNameProp());

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
        this.clientInfo.setItems(parsing.getTickets());



    }

    private void ShowInfo(Ticket ticket){
        if( ticket!= null){
            lablecontact.setText(String.valueOf(ticket.getClient().getContact()));
            lableid.setText(String.valueOf(ticket.getId()));
            lableprice.setText(String.valueOf(ticket.getPrice()));
            lablehallname.setText(String.valueOf(ticket.getSeat().getHall().getName()));
            lablepertime.setText(String.valueOf(ticket.getSeat().getHall().getTime()));
            for (int i = 0; i<ticket.getSeat().getHall().getPerformances().size(); i++){
                lablenameofper.setText(String.valueOf(ticket.getSeat().getHall().getPerformances().get(i).getName()));
            }

            lableseatlocation.setText(String.valueOf(ticket.getSeat().getLocation()));
            lableseattype.setText(String.valueOf(ticket.getSeat().getType()));


        }else{
            lablecontact.setText("нет информации");
        }

    }

}

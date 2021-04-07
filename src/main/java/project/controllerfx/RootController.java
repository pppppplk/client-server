package project.controllerfx;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import project.JavaFX;
import project.spring.models.Ticket;
import project.util.Rest;
import project.util.Parsing;

import java.io.IOException;
import java.util.Optional;

public class RootController {


    private Parsing parsing = new Parsing();
    private Rest rest = new Rest();


    @FXML
    private TableView<Ticket> clientInfo;

    @FXML
    private TableColumn<Ticket, String> surnamecolumn;

    @FXML
    private TableColumn<Ticket, String> namecolumn;

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

    @FXML
    private TextField searchtext;

    @FXML
    private TextField firstnametext;

    @FXML
    private TextField lastnametext;

    @FXML
    private TextField contacttext;

    @FXML
    private TextField agetext;




    private JavaFX main;
    private Stage stage;

    public RootController() throws IOException {
    }

    @FXML
    private void initialize() {

        initTable();
        SearchTable();
        //Info();

        namecolumn.setCellValueFactory(cellData -> cellData.getValue().getClient().getFirstNameProp());
        surnamecolumn.setCellValueFactory(cellData -> cellData.getValue().getClient().getLastNameProp());

        ShowInfo(null); //очищаем справа
        clientInfo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)
                -> ShowInfo(newValue));



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


    private void initTable() {
        this.clientInfo.setItems(parsing.getTickets());


    }

    /**
     * открытие окна для изменения клиента
     */


    /*
    @FXML
    public void PutClient(){
        System.out.println("изменение");
        int clientrow = clientInfo.getSelectionModel().getSelectedIndex();

        if (clientrow >= 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Предупреждение");
            alert.setHeaderText("Нажмите ОК, если хотите изменить пользователя");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == ButtonType.OK) {
                System.out.println("перед изменением");

                if(clientInfo.getSelectionModel().getSelectedItem()  == null){
                    try{
                        Alert alert2 = new Alert(Alert.AlertType.ERROR);
                        alert2.setTitle("Ошибка");
                        alert2.setHeaderText("Не выбран пользователь для изменения");
                        alert2.showAndWait();
                    }catch (Exception e){

                    }
                }else{
                    System.out.println("открыла окно для изменения");

                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(main.getClass().getResource("/fxml/update.fxml"));
                        AnchorPane root1 = fxmlLoader.load();
                        stage = new Stage();
                        stage.setTitle("Изменение");
                        stage.setScene(new Scene(root1));
                        stage.show();

                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("окно не открывается ");


                    }
                    //initTable();
                    //clientInfo.getItems().removeAll(clientInfo.getSelectionModel().getSelectedItem());
                }


            }else if (option.get() == ButtonType.CANCEL){
                System.out.println("окно закрыто ");
            }
            alert.showAndWait();


        } else {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setTitle("Ошибка");
            alert2.setHeaderText("Не выбран пользователь для изменения");

            alert2.showAndWait();
        }


    }

     */



    /**
     * @param ticket полная информауия о клиенте и его билете
     *
     */

    private void ShowInfo(Ticket ticket) {
        if (ticket != null) {
            lablecontact.setText(String.valueOf(ticket.getClient().getContact()));
            lableid.setText(String.valueOf(ticket.getId()));
            lableprice.setText(String.valueOf(ticket.getPrice()));
            lablehallname.setText(String.valueOf(ticket.getSeat().getHall().getName()));
            firstnametext.setText(String.valueOf(ticket.getClient().getFirstname()));
            lastnametext.setText(String.valueOf(ticket.getClient().getLastname()));
            contacttext.setText(String.valueOf(ticket.getClient().getContact()));
            agetext.setText(String.valueOf(ticket.getClient().getAge()));
            lablenameofper.setText(String.valueOf(ticket.getPerformance().getName()));
            lablepertime.setText(String.valueOf(ticket.getPerformance().getTime()));
            lableseatlocation.setText(String.valueOf(ticket.getSeat().getLocation()));
            lableseattype.setText(String.valueOf(ticket.getSeat().getType()));


        } else {
            lablecontact.setText("нет информации");
        }

    }




    /**
     * @param actionEvent функция, окрывающая окно инструкции
     */

    @FXML

    public void searchButton(javafx.event.ActionEvent actionEvent) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/info.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            stage = new Stage();
            stage.setTitle("Инструкция");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("окно не открывается ");


        }

    }


    @FXML

    /**
     * функция, которая ищет клиентов по бд
     */

    public void SearchTable() {

        System.out.println("зашел");

        FilteredList<Ticket> filteredList = new FilteredList<>(parsing.getTickets(), b -> true);

        searchtext.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(ticket -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String filter = newValue.toLowerCase();

                if (ticket.getClient().getFirstname().toLowerCase().indexOf(filter) != -1) {
                    return true;
                } else if (ticket.getClient().getLastname().toLowerCase().indexOf(filter) != -1) {
                    return true;
                } else {
                    return false;

                }

            });

        });


        SortedList<Ticket> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(clientInfo.comparatorProperty());
        clientInfo.setItems(sortedList);


    }


    /**
     * @param actionEvent
     * @throws IOException функция удаления клиента из бд + окно с предупрежденим и ошибкой
     */


    @FXML
    public void DeleteClient(javafx.event.ActionEvent actionEvent) throws IOException {

        System.out.println("удаление");
        int clientrow = clientInfo.getSelectionModel().getSelectedIndex();

        if (clientrow >= 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Предупреждение");
            alert.setHeaderText("Нажмите ОК, если хотите удалить пользователя");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == ButtonType.OK) {
                System.out.println("перед удалением");

                if(clientInfo.getSelectionModel().getSelectedItem()  == null){
                    try{
                        Alert alert2 = new Alert(Alert.AlertType.ERROR);
                        alert2.setTitle("Ошибка");
                        alert2.setHeaderText("Не выбран пользователь для удаления");
                        alert2.showAndWait();
                    }catch (Exception e){

                    }
                }else{
                    Long idticket = clientInfo.getSelectionModel().getSelectedItem().getId();
                    Long idclient = clientInfo.getSelectionModel().getSelectedItem().getClient().getId();

                    rest.DeleteRest("http://localhost:8080/api/theater/tickets?id="+idticket);
                    rest.DeleteRest("http://localhost:8080/api/theater/clients?id="+idclient);
                    initTable();
                    clientInfo.getItems().removeAll(clientInfo.getSelectionModel().getSelectedItem());
                }


            }else if (option.get() == ButtonType.CANCEL){
                System.out.println("окно закрыто ");
            }
            alert.showAndWait();


        } else {
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setTitle("Ошибка");
                alert2.setHeaderText("Не выбран пользователь для удаления");

                alert2.showAndWait();
            }


    }


    @FXML
    public void addButton() {

        System.out.println("зашел в добавление");

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(main.getClass().getResource("/fxml/post.fxml"));
            AnchorPane root1 = fxmlLoader.load();
            stage = new Stage();
            stage.setTitle("Инструкция");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("окно не открывается ");


        }


    }






}







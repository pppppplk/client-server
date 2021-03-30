package project;

import com.sun.webkit.ContextMenu;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Window;
import project.controllerfx.RootController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import project.controllerfx.SearchController;
import project.spring.models.Ticket;
import project.util.Parsing;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;



public class JavaFX extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;



    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Информационно-справочная система театра");

        initRootLayout();

        //showPersonOverview();
    }



    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(JavaFX.class.getResource("/fxml/root.fxml"));
            rootLayout = loader.load();
            RootController rootController = loader.getController();
            rootController.setStage(primaryStage);
            rootController.setMain(this);
            //rootController.fillClients(this.clients);
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    /*

    public void showPersonOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(JavaFX.class.getResource("/fxml/root.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            rootLayout.setCenter(personOverview);
            TheaterController controller = loader.getController();
            controller.setMain(this);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

     */






    /*

    public boolean showPersonEditDialog(Client client) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("views/PersonEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            PersonEditingController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(person);
            dialogStage.showAndWait();
            return controller.isOkClicked();


        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


     */

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

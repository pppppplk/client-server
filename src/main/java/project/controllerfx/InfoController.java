
package project.controllerfx;


import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import project.JavaFX;
import project.spring.models.Ticket;
import project.util.Parsing;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InfoController {


    private JavaFX main;
    private Stage stage;



    @FXML
    private TextField textField;



    @FXML
    private Label label1;

    private Parsing parsing;
    private Ticket ticket;
    private RootController rootController;
    private List<String> textlist = new ArrayList<String>();




    @FXML
    private void initialize() throws IOException {


        System.out.println("полученная инфа");

    }


    public InfoController() throws IOException{ }


    /*

    public void Search(javafx.event.ActionEvent actionEvent) {


        textField = new TextField();
        label1 = new Label();

        textField.setText("t");
        textField.getText();
        label1.setText(textField.getText());
        System.out.println(textField.getText());
        /*

        Long znach = Long.valueOf(textField.getText());
        label1.setText(String.valueOf(znach));

        System.out.println(textField.getText());
        textlist.add(textField.getText());
        System.out.println("список" + textlist );

         */
    /*


        if(!textField.getText().isEmpty()){
            System.out.println("yhtfgtdrsgz");
        }else{
            System.out.println("123456");
        }
    }

     */

}




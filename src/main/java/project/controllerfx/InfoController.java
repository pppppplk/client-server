
package project.controllerfx;


import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import project.JavaFX;
import project.spring.models.Ticket;
import project.util.Parsing;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InfoController {


    private JavaFX main;
    private Stage stage;



    @FXML
    private void initialize() throws IOException {


        System.out.println("полученная инфа");

    }


    public InfoController() throws IOException{ }

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
}




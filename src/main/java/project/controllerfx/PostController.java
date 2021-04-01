
package project.controllerfx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import project.JavaFX;

import java.io.IOException;

public class PostController {

    private JavaFX main;
    private Stage stage;


    @FXML
    private void initialize() throws IOException {


        System.out.println("полученная инфа из пост");

    }


    public PostController() throws IOException{ }



}



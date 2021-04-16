package project.controllerfx;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import project.JavaFX;
import javafx.geometry.Insets;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class AuthorController {


    private JavaFX main;
    private Stage stage;
    private Pane pane = new Pane();


    @FXML
    private void initialize(){


        System.out.println("полученная инфа из автора");




    }





    public AuthorController() throws IOException{ }

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

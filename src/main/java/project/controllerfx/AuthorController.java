package project.controllerfx;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import project.JavaFX;
import java.io.IOException;

/**
 * Контроллер окна author.fxml
 */

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


package project.controllerfx;


import javafx.fxml.FXML;
import javafx.stage.Stage;
import project.JavaFX;
import java.io.IOException;

/**
 * Контроллер окна info.fxml
 */

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




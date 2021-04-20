package project.controllerfx;


import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONException;
import project.JavaFX;
import project.util.Rest;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Контроллер окна graphic.fxml
 */

public class GraphicController {

    private JavaFX main;
    private Stage stage;
    private Rest rest = new Rest();

    @FXML
    private BarChart<String, Integer> barChart;

    @FXML
    private BarChart<String, Integer> barChart2;



    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;

    @FXML
    private CategoryAxis x2;

    @FXML
    private NumberAxis y2;


    /**
     * метод инициализации, вызовы методов PerfInfo() и ZoneInfo()
     * @throws IOException
     * @throws JSONException
     */


    @FXML
    private void initialize() throws IOException, JSONException {

        System.out.println("полученная инфа из графика");

        PerfInfo();
        ZoneInfo();

    }


    public GraphicController() throws IOException{ }


    /**
     * Getters и Setters
     * Getters - выводять значение
     * Setters - задают значение
     * @return
     */


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


    /**
     * вывод спектаклей по оси Х
     * вывод кол-ва билетов по оси Y
     */


    public void  PerfInfo() throws IOException, JSONException {


        JSONArray perfname = new JSONArray(rest.GetRest("http://127.0.0.1:8080/api/theater/perfs/all"));


        Set<String> name = new HashSet<>();
        for(int i = 0; i<perfname.length(); i++){
            name.add(perfname.getJSONObject(i).getString("name"));

        }

        XYChart.Series series = new XYChart.Series<>();

        for( String s : name){
            JSONArray ticket = new JSONArray(rest.GetRest("http://localhost:8080/api/theater/tickets/perName=" + URLEncoder.encode(s, StandardCharsets.UTF_8)));
            List<Long> num = new ArrayList<>();
            for(int i =0; i<ticket.length(); i++){
                num.add(ticket.getJSONObject(i).getLong("id"));
            }
            series.getData().add(new XYChart.Data(s, num.size()));


        }
        barChart.getData().add(series);


    }

    /**
     * вывод типов мест по оси Х
     * вывод кол-ва билетов по оси У
     * @throws IOException
     * @throws JSONException
     */


    public void  ZoneInfo() throws IOException, JSONException {

        JSONArray zonename = new JSONArray(rest.GetRest("http://127.0.0.1:8080/api/theater/seats/all"));


        Set<String> nameofz = new HashSet<>();
        for(int i = 0; i<zonename.length(); i++){
            nameofz.add(zonename.getJSONObject(i).getString("type"));

        }



        XYChart.Series series2 = new XYChart.Series<>();

        for( String s : nameofz){
            JSONArray ticket = new JSONArray(rest.GetRest("http://localhost:8080/api/theater/tickets/typeName=" + URLEncoder.encode(s, StandardCharsets.UTF_8)));
            List<Long> numzone = new ArrayList<>();
            for(int i =0; i<ticket.length(); i++){
                numzone.add(ticket.getJSONObject(i).getLong("id"));
            }
            series2.getData().add(new XYChart.Data(s, numzone.size()));


        }
        barChart2.getData().add(series2);


    }






}

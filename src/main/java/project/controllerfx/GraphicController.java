package project.controllerfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import project.DTO.PerformanceDTO;
import project.DTO.SeatDTO;
import project.JavaFX;
import project.spring.models.Performance;
import project.util.Rest;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GraphicController {

    private JavaFX main;
    private Stage stage;
    private PerformanceDTO performanceDTO;
    private Rest rest = new Rest();

    @FXML
    private BarChart<String, Integer> barChart;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;


    private ObservableList<String> PerNames = FXCollections.observableArrayList();



    @FXML
    private void initialize() throws IOException, JSONException {
        //XYChart.Series series = new XYChart.Series<>();

        //series.getData().add(new XYChart.Data("ууу", 1));
        //series.getData().add(new XYChart.Data("12 стульев", 12));

        //barChart.getData().addAll(series);

        System.out.println("полученная инфа из графика");

        PerfInfo();

    }


    public GraphicController() throws IOException{ }

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


        System.out.println("вывожу названия спектаклей из графика" + name );

    }



}

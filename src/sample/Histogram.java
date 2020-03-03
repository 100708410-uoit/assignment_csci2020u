package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class Histogram extends Application {
    private final char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    private GridPane pane = new GridPane();
    private BarChart<String , Number> chart;
    private TextField input = new TextField();
    private Button view = new Button("View");

    @Override
    public void start(Stage stage) throws Exception {
        pane.setVgap(5);
        pane.setHgap(5);
        pane.setAlignment(Pos.CENTER);

        barGraph();
        pane.add(new Label("Input:"), 0, 1);
        pane.add(input, 1, 1);
        pane.add(view, 2, 1);

        //when user presses enter key
        input.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                pane.getChildren().remove(chart);
                barGraph();
            }
        });

        //when user presses button
        view.setOnAction(actionEvent -> {
            pane.getChildren().remove(chart);
            barGraph();
        });

        Scene scene = new Scene(pane, 850, 450);
        stage.setTitle("QUESTION 4 -- Histogram");
        stage.setScene(scene);
        stage.show();
    }

    //create histogram
    private void barGraph() {
        CategoryAxis letters = new CategoryAxis();
        NumberAxis frequency = new NumberAxis();
        chart = new BarChart<>(letters, frequency);
        chart.setLegendVisible(false);
        chart.getYAxis().setOpacity(0);
        chart.setMinWidth(750);
        chart.setMinHeight(250);

        //populate graph with data
        XYChart.Series stats = new XYChart.Series();
        HashMap<Character, Long> data = getCount(input.getText());
        for (Map.Entry<Character, Long> entry: data.entrySet())
            stats.getData().add(new XYChart.Data<>(entry.getKey().toString(), entry.getValue()));

        chart.getData().addAll(stats);
        pane.add(chart, 1, 0);
    }

    private HashMap<Character, Long> getCount(String text) {
        HashMap<Character, Long> occurrences = new HashMap<>();

        //count occurrences of each letter
        for (char c : alphabet) {
            long count = text.toUpperCase().chars().filter(ch -> ch == c).count();
            occurrences.put(c, count);
        }
        return occurrences;
    }


    public static void main(String[] args) {launch(args);}
}

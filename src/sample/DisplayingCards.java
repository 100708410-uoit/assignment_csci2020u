package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.concurrent.ThreadLocalRandom;

public class DisplayingCards extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(10,10,10,10));
        pane.setVgap(5);
        pane.setHgap(5);

        //display random cards
        int[] cardsNum = ThreadLocalRandom.current().ints(1, 55).distinct().limit(3).toArray();
        for (int i=0; i<3; i++)
            pane.add(new ImageView("/img/"+cardsNum[i]+".png"), i, 0);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("QUESTION 1 -- Displaying 3 Cards");
        stage.show();
    }

    public static void main(String[] args) {launch(args);}
}

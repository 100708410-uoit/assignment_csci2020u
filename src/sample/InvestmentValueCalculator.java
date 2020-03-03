package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class InvestmentValueCalculator extends Application {
    private TextField amount = new TextField();
    private TextField time = new TextField();
    private TextField interestRate = new TextField();
    private TextField fValue = new TextField();
    private Button calculate = new Button("Calculate");

    @Override
    public void start(Stage stage) throws Exception {
        GridPane pane = new GridPane();
        pane.setVgap(5);
        pane.setHgap(5);

        //set textfields to enter numbers only
        amount.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,7}([\\.]\\d{0,2})?"))
                amount.setText(oldValue);
        });

        time.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,2}([\\.]\\d{0,1})?"))
                time.setText(oldValue);
        });

        interestRate.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,2}([\\.]\\d{0,2})?"))
                interestRate.setText(oldValue);
        });

        pane.add(new Label("Investment Amount:"), 0, 0);
        pane.add(amount, 1, 0);
        pane.add(new Label("Number Of Years:"), 0, 1);
        pane.add(time, 1, 1);
        pane.add(new Label("Annual Interest Rate:"), 0, 2);
        pane.add(interestRate, 1, 2);
        pane.add(new Label("Future Value:"), 0, 3);
        pane.add(fValue, 1, 3);
        pane.add(calculate, 1, 4);

        pane.setAlignment(Pos.CENTER);
        fValue.setEditable(false);
        pane.setPadding(new Insets(10, 10, 10, 10));

        //calculate future value
        calculate.setOnAction(actionEvent -> futureValue());

        Scene scene = new Scene(pane);
        stage.setTitle("QUESTION 2 -- Investment Value Calculator");
        stage.setScene(scene);
        stage.show();
    }

    private void futureValue() {
        double investmentAmount = Double.parseDouble(amount.getText());
        double years = Double.parseDouble(time.getText());
        double monthlyInterestRate = Double.parseDouble(interestRate.getText())/1200;

        fValue.setText(String.format("$%.2f", investmentAmount * Math.pow(1 + monthlyInterestRate, years * 12)));
    }


    public static void main(String[] args) {launch(args);}
}

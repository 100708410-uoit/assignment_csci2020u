package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DraggingPointsCircle extends Application {
    private Pane pane = new Pane();

    @Override
    public void start(Stage stage) throws Exception {
        double height = 400;
        double width = 400;
        Circle c = new Circle(width / 2, height / 2, 100);
        pane.getChildren().add(c);

        c.setFill(Color.TRANSPARENT);
        c.setStroke(Color.BLACK);
        Circle[] points = new Circle[3];
        Line[] lines = new Line[3];
        Text[] angles = new Text[3];

        //initialize points on the circle
        for (int i=0; i<points.length; i++) {
            angles[i] = new Text();
            points[i] = new Circle(0, 0, 5);
            setRandomLocation(points[i], c);
            points[i].setFill(Color.RED);

            //set drag actions on points
            final int j = i;
            points[i].setOnMouseDragged(actionEvent -> {
                double radian = Math.atan2(actionEvent.getY() - c.getCenterY(), actionEvent.getX() - c.getCenterX());
                double x = c.getCenterX() + c.getRadius() * Math.cos(radian);
                double y = c.getCenterY() + c.getRadius() * Math.sin(radian);
                points[j].setCenterX(x);
                points[j].setCenterY(y);
                updateLines(lines, points, angles);
            });

        }

        //create lines between points
        for (int i=0; i<lines.length; i++) {
            int j = (i+1 >= points.length)? 0 : i+1;
            lines[i] = new Line(points[i].getCenterX(), points[i].getCenterY(), points[j].getCenterX(), points[j].getCenterY());
        }

        updateLines(lines, points, angles);
        pane.getChildren().addAll(lines);
        pane.getChildren().addAll(angles);
        pane.getChildren().addAll(points);

        stage.setScene(new Scene(pane, width, height));
        stage.setTitle("QUESTION 3 -- Dragging Points on a Circle");
        stage.show();
    }

    private void updateLines(Line[] lines, Circle[] p, Text[] angles) {
        //set length of lines
        for (int i=0; i<lines.length; i++) {
            int j = (i+1 >= p.length)? 0 : i+1;

            lines[i].setStartX(p[i].getCenterX());
            lines[i].setEndX(p[j].getCenterX());
            lines[i].setStartY(p[i].getCenterY());
            lines[i].setEndY(p[j].getCenterY());

            angles[i].setX(p[i].getCenterX() + 5);
            angles[i].setY(p[i].getCenterY() - 5);
        }

        //calculate distance of the lines
        double a = distance(lines[0]);
        double b = distance(lines[1]);
        double c = distance(lines[2]);

        //calculate angles
        double A = Math.toDegrees(Math.acos((Math.pow(a, 2)-Math.pow(b, 2)-Math.pow(c, 2)) / (-2*b*c)));
        angles[2].setText(String.format("%.2f°", A));

        double B = Math.toDegrees(Math.acos((Math.pow(b, 2)-Math.pow(a, 2)-Math.pow(c, 2)) / (-2*a*c)));
        angles[0].setText(String.format("%.2f°", B));

        double C = Math.toDegrees(Math.acos((Math.pow(c, 2)-Math.pow(b, 2)-Math.pow(a, 2)) / (-2*a*b)));
        angles[1].setText(String.format("%.2f°", C));
    }

    private void setRandomLocation(Circle point, Circle c) {
        double angle = Math.random() * 360;
        double x = c.getCenterX() + c.getRadius() * Math.cos(Math.toRadians(angle));
        double y = c.getCenterY() + c.getRadius() * Math.sin(Math.toRadians(angle));

        point.setCenterX(x);
        point.setCenterY(y);
    }

    private double distance(Line l) {
        double x1 = l.getStartX();
        double x2 = l.getEndX();
        double y1 = l.getStartY();
        double y2 = l.getEndY();

        return Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
    }


    public static void main(String[] args) {launch(args);}
}

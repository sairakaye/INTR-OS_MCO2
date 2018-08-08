import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class CalTrainController implements Initializable{

    @FXML ComboBox<String> stationTo;

    @FXML ComboBox<String> stationFrom;

    @FXML AnchorPane background;

    @FXML
    private void spawnPassenger() {
        String stationF = stationFrom.getSelectionModel().getSelectedItem();
        Image roboImage = new Image("images/robot.png");
        ImageView roboImageView = new ImageView(roboImage);
        roboImageView.setFitHeight(85);
        roboImageView.setFitWidth(85);
        Random r = new Random();

        switch(stationF) {
            case "Station 1":
                System.out.println("Spawning Passenger at Station 1");
                background.getChildren().add(roboImageView);

                roboImageView.setX(r.nextInt(711-411) + 411);
                roboImageView.setY(100);
                break;
            case "Station 2":
                System.out.println("Spawning Passenger at Station 2");
                background.getChildren().add(roboImageView);

                roboImageView.setX(r.nextInt(711-411) + 411);
                roboImageView.setY(300);
                break;
            case "Station 3":
                System.out.println("Spawning Passenger at Station 3");
                background.getChildren().add(roboImageView);

                roboImageView.setX(r.nextInt(711-411) + 411);
                roboImageView.setY(500);
                break;
            case "Station 4":
                System.out.println("Spawning Passenger at Station 4");
                background.getChildren().add(roboImageView);

                roboImageView.setX(r.nextInt(711-411) + 411);
                roboImageView.setY(700);
                break;
            case "Station 5":
                System.out.println("Spawning Passenger at Station 5");
                background.getChildren().add(roboImageView);

                roboImageView.setX(r.nextInt(1311-1011) + 1011);
                roboImageView.setY(100);
                break;
            case "Station 6":
                System.out.println("Spawning Passenger at Station 6");
                background.getChildren().add(roboImageView);

                roboImageView.setX(r.nextInt(1311-1011) + 1011);
                roboImageView.setY(300);
                break;
            case "Station 7":
                System.out.println("Spawning Passenger at Station 7");
                background.getChildren().add(roboImageView);

                roboImageView.setX(r.nextInt(1311-1011) + 1011);
                roboImageView.setY(500);
                break;
            case "Station 8":
                System.out.println("Spawning Passenger at Station 8");
                background.getChildren().add(roboImageView);

                roboImageView.setX(r.nextInt(1311-1011) + 1011);
                roboImageView.setY(700);
                break;
        }
    }

    @FXML
    private void deployTrain() {
        System.out.println("Deploying Train");

        Image trainImage = new Image("images/train.png");
        ImageView leftTrainImageView = new ImageView(trainImage);
        ImageView rightTrainImageView = new ImageView(trainImage);

        background.getChildren().add(leftTrainImageView);
        leftTrainImageView.setFitHeight(100);
        leftTrainImageView.setFitWidth(100);

        background.getChildren().add(rightTrainImageView);
        rightTrainImageView.setFitWidth(100);
        rightTrainImageView.setFitHeight(100);

        leftTrainImageView.setX(261);
        leftTrainImageView.setY(80);
        rightTrainImageView.setX(861);
        rightTrainImageView.setY(80);

        rightTrainImageView.setVisible(false);

        TranslateTransition toCenter = new TranslateTransition();
        TranslateTransition toSide = new TranslateTransition();

        toCenter.setDuration(Duration.millis(1000));
        toCenter.setNode(leftTrainImageView);
        toCenter.setByX(200);

        toSide.setDuration(Duration.millis(1000));
        toSide.setNode(leftTrainImageView);
        toSide.setByX(300);

        TranslateTransition rightToCenter = new TranslateTransition();
        TranslateTransition rightToSide = new TranslateTransition();

        rightToCenter.setDuration(Duration.millis(1000));
        rightToCenter.setNode(rightTrainImageView);
        rightToCenter.setByX(200);

        rightToSide.setDuration(Duration.millis(1000));
        rightToSide.setNode(rightTrainImageView);
        rightToSide.setByX(300);

        toCenter.play();
        toCenter.setOnFinished(event -> {

            // insert checking when a passenger is in the station or if the train is full
            // else we move to the next station
            toSide.play();
        });

        toSide.setOnFinished(event -> {

            if(leftTrainImageView.getY() >= 680) {
                leftTrainImageView.setX(leftTrainImageView.getX() - 500);
                leftTrainImageView.setY(80);
                leftTrainImageView.setVisible(false);
                rightTrainImageView.setVisible(true);
                rightToCenter.play();
            } else {
                leftTrainImageView.setX(leftTrainImageView.getX() - 500);
                leftTrainImageView.setY(leftTrainImageView.getY() + 200);
                toCenter.play();
            }
        });

        rightToCenter.setOnFinished( event -> {
            rightToSide.play();
        });

        rightToSide.setOnFinished( event -> {
            if(rightTrainImageView.getY() >= 680) {
                rightTrainImageView.setX(rightTrainImageView.getX() - 500);
                rightTrainImageView.setY(80);
                rightTrainImageView.setVisible(false);
                leftTrainImageView.setVisible(true);
                toCenter.play();
            } else {
                rightTrainImageView.setX(rightTrainImageView.getX() - 500);
                rightTrainImageView.setY(rightTrainImageView.getY() + 200);
                rightToCenter.play();
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for(int i = 1; i <= 8; i++) {
            stationFrom.getItems().add("Station " + i);
            stationTo.getItems().add("Station " + i);
        }
    }
}

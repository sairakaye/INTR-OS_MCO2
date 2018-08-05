import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
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
import java.util.concurrent.atomic.AtomicBoolean;

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
        System.out.println("Deploying Train...");

        Image trainImage = new Image("images/train.png");
        ImageView trainImageView = new ImageView(trainImage);
        trainImageView.setFitHeight(100);
        trainImageView.setFitWidth(100);

        ImageView secondTrainImageView = new ImageView(trainImage);
        secondTrainImageView.setFitHeight(100);
        secondTrainImageView.setFitWidth(100);

        background.getChildren().add(trainImageView);
        background.getChildren().add(secondTrainImageView);

        secondTrainImageView.setVisible(false);

        trainImageView.setX(261);
        trainImageView.setY(80);
        secondTrainImageView.setX(861);
        secondTrainImageView.setY(80);

        PauseTransition pt = new PauseTransition(Duration.millis(200));
        TranslateTransition toCenter = new TranslateTransition();
        TranslateTransition toSide = new TranslateTransition();

        toCenter.setDuration(Duration.millis(1000));
        toCenter.setNode(trainImageView);
        toCenter.setByX(150);

        toSide.setDuration(Duration.millis(1000));
        toSide.setNode(trainImageView);
        toSide.setByX(400);

        SequentialTransition move = new SequentialTransition(toCenter, pt, toSide);

        TranslateTransition toRightCenter = new TranslateTransition();
        TranslateTransition toRightSide = new TranslateTransition();

        toRightCenter.setDuration(Duration.millis(1000));
        toRightCenter.setNode(secondTrainImageView);
        toRightCenter.setByX(300);

        toRightSide.setDuration(Duration.millis(1000));
        toRightSide.setNode(secondTrainImageView);
        toRightSide.setByX(300);

        SequentialTransition secondMove = new SequentialTransition(toRightCenter, pt, toRightSide);

        move.play();
        move.setOnFinished((ActionEvent event) -> {

            if(trainImageView.getY() <= 680) {
                trainImageView.setX(trainImageView.getX() - 550);
                trainImageView.setY(trainImageView.getY() + 200);
                move.play();
            }

            if(trainImageView.getY() == 880) {
                trainImageView.setX(-1940);
                trainImageView.setY(80);
                move.stop();
                trainImageView.setVisible(false);
                secondTrainImageView.setVisible(true);
                secondMove.play();
            }
        });

        secondMove.setOnFinished((ActionEvent event) -> {

            if(secondTrainImageView.getY() <= 680) {
                secondTrainImageView.setX(secondTrainImageView.getX()  - 600);
                secondTrainImageView.setY(secondTrainImageView.getY() + 200);

                secondMove.play();
            }

            if(secondTrainImageView.getY() == 880) {
                System.out.println("Finish Right");
                trainImageView.setVisible(true);
                secondTrainImageView.setVisible(false);

                secondTrainImageView.setX(-1537);
                secondTrainImageView.setY(80);

                secondMove.stop();

                move.play();
            }
        });

//        secondMove.setOnFinished((ActionEvent e) -> {
//
//            if(secondTrainImageView.getY() > 480) {
//                flag[0] = false;
////                        secondTrainImageView.setVisible(false);
//                System.out.println("Going back to the left");
//                trainImageView.setVisible(true);
//                secondTrainImageView.setX(-1340);
//                secondTrainImageView.setY(80);
//
//
//            } else {
//                secondTrainImageView.setX(secondTrainImageView.getX() - 550);
//                secondTrainImageView.setY(secondTrainImageView.getY() + 200);
//                secondMove.play();
//            }
//
//
//        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for(int i = 1; i <= 8; i++) {
            stationFrom.getItems().add("Station " + i);
            stationTo.getItems().add("Station " + i);
        }
    }
}

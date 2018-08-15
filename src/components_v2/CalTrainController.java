package components_v2;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class CalTrainController implements Initializable{

    @FXML ComboBox<String> stationTo;

    @FXML ComboBox<String> stationFrom;

    @FXML AnchorPane background;

    @FXML TextArea capacity;

    @FXML TextArea logArea;

    Station[] stations = new Station[8];
    Train trains[] = new Train[16];
    int roboCounter = 1;
    int trainCounter = 0;

    ArrayList<ImageView> robo1 = new ArrayList<>();
    ArrayList<ImageView> robo2 = new ArrayList<>();
    ArrayList<ImageView> robo3 = new ArrayList<>();
    ArrayList<ImageView> robo4 = new ArrayList<>();
    ArrayList<ImageView> robo5 = new ArrayList<>();
    ArrayList<ImageView> robo6 = new ArrayList<>();
    ArrayList<ImageView> robo7 = new ArrayList<>();
    ArrayList<ImageView> robo8 = new ArrayList<>();

    @FXML
    private void spawnPassenger() {
        String stationF = stationFrom.getSelectionModel().getSelectedItem();
        String stationT = stationTo.getSelectionModel().getSelectedItem();

        String[] temp1 = stationF.split(" ");
        temp1[1] = temp1[1].replaceAll("\\s","");
        String[] temp2 = stationT.split(" ");
        temp2[1] = temp2[1].replaceAll("\\s","");

        Image roboImage = new Image("images/robot.png");
        ImageView roboImageView = new ImageView(roboImage);
        roboImageView.setFitHeight(85);
        roboImageView.setFitWidth(85);
        Random r = new Random();
        int x = r.nextInt(200-100) + 100;
        background.getChildren().add(roboImageView);
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.millis(2000));
        translateTransition.setNode(roboImageView);
        translateTransition.setByX(-x);

        switch(stationF) {
            case "Station 1":
                roboImageView.setX(791);
                roboImageView.setY(100);
                robo1.add(roboImageView);
                break;
            case "Station 2":
                roboImageView.setX(791);
                roboImageView.setY(300);
                robo2.add(roboImageView);
                break;
            case "Station 3":
                roboImageView.setX(791);
                roboImageView.setY(500);
                robo3.add(roboImageView);
                break;
            case "Station 4":
                roboImageView.setX(791);
                roboImageView.setY(700);
                robo4.add(roboImageView);
                break;
            case "Station 5":
                roboImageView.setX(1500);
                roboImageView.setY(100);
                robo5.add(roboImageView);
                break;
            case "Station 6":
                roboImageView.setX(1500);
                roboImageView.setY(300);
                robo6.add(roboImageView);
                break;
            case "Station 7":
                roboImageView.setX(1500);
                roboImageView.setY(500);
                robo7.add(roboImageView);
                break;
            case "Station 8":
                roboImageView.setX(1500);
                roboImageView.setY(700);
                robo8.add(roboImageView);
                break;
        }

        translateTransition.play();
        translateTransition.setOnFinished(event -> {
            System.out.println("Robot " + roboCounter + " is now waiting at Station " + (stations[Integer.parseInt(temp1[1]) - 1].getStationID()+1));
            logArea.appendText("Robot " + roboCounter + " is now waiting at Station " + (stations[Integer.parseInt(temp1[1]) - 1].getStationID()+1) + "\n");
            RobotModel robot = new RobotModel(roboCounter, Integer.parseInt(temp1[1]) - 1, Integer.parseInt(temp2[1]) - 1);
            RobotController controller = new RobotController(robot, roboImageView);

            roboCounter++;
            stations[Integer.parseInt(temp1[1]) - 1].getRobots().add(robot);
        });
    }

    @FXML
    private void deployTrain() {
        System.out.println("Deploying Train " + trainCounter);
        logArea.appendText("Deploying Train " + trainCounter + "\n");
        String cap = capacity.getText();
        cap = cap.replaceAll("\\s","");

        Train train = new Train(trainCounter, stations, Integer.parseInt(cap));
        Node imageView = train.getTrainController().getView();

        background.getChildren().add(imageView);
        trains[trainCounter] = train;
        trains[trainCounter].startThread();
        trainCounter++;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for(int i = 1; i <= 8; i++) {
            stationFrom.getItems().add("Station " + i);
            stationTo.getItems().add("Station " + i);
        }

        for(int nCtr = 0; nCtr < 8; nCtr++)
            stations[nCtr] = new Station(nCtr, this);
    }

    public void boardPassenger(int station){
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.millis(1000));
        switch(station)
        {
            case 0: translateTransition.setNode(robo1.get(0));
                    translateTransition.setByX(-200);
                    translateTransition.play();
                    translateTransition.setOnFinished(event -> {
                        robo1.get(0).setVisible(false);
                        robo1.remove(0);
                    });
                    break;

            case 1: translateTransition.setNode(robo2.get(0));
                    translateTransition.setByX(-200);
                    translateTransition.play();
                    translateTransition.setOnFinished(event -> {
                        robo2.get(0).setVisible(false);
                        robo2.remove(0);
                    });
                    break;

            case 2: translateTransition.setNode(robo3.get(0));
                    translateTransition.setByX(-200);
                    translateTransition.play();
                    translateTransition.setOnFinished(event -> {
                        robo3.get(0).setVisible(false);
                        robo3.remove(0);
                    });
                    break;

            case 3: translateTransition.setNode(robo4.get(0));
                    translateTransition.setByX(-200);
                    translateTransition.play();
                    translateTransition.setOnFinished(event -> {
                        robo4.get(0).setVisible(false);
                        robo4.remove(0);
                    });
                    break;

            case 4: translateTransition.setNode(robo5.get(0));
                    translateTransition.setByX(-200);
                    translateTransition.play();
                    translateTransition.setOnFinished(event -> {
                        robo5.get(0).setVisible(false);
                        robo5.remove(0);
                    });
                    break;

            case 5: translateTransition.setNode(robo6.get(0));
                    translateTransition.setByX(-200);
                    translateTransition.play();
                    translateTransition.setOnFinished(event -> {
                        robo6.get(0).setVisible(false);
                        robo6.remove(0);
                    });
                    break;

            case 6: translateTransition.setNode(robo7.get(0));
                    translateTransition.setByX(-200);
                    translateTransition.play();
                    translateTransition.setOnFinished(event -> {
                        robo7.get(0).setVisible(false);
                        robo7.remove(0);
                    });
                    break;

            case 7: translateTransition.setNode(robo8.get(0));
                    translateTransition.setByX(-200);
                    translateTransition.play();
                    translateTransition.setOnFinished(event -> {
                        robo8.get(0).setVisible(false);
                        robo8.remove(0);
                    });
                    break;
        }
    }

    public void disembarkPassenger(int station) {
        TranslateTransition translateTransition = new TranslateTransition();
        Image roboImage = new Image("images/robot.png");
        ImageView roboImageView = new ImageView(roboImage);
        roboImageView.setFitHeight(85);
        roboImageView.setFitWidth(85);
        background.getChildren().add(roboImageView);
        switch(station) {
            case 0: roboImageView.setX(400);
                    roboImageView.setY(100);
                    break;

            case 1: roboImageView.setX(400);
                    roboImageView.setY(300);
                    break;

            case 2: roboImageView.setX(400);
                    roboImageView.setY(500);
                    break;

            case 3: roboImageView.setX(400);
                    roboImageView.setY(700);
                    break;

            case 4: roboImageView.setX(1000);
                    roboImageView.setY(100);
                    break;

            case 5: roboImageView.setX(1000);
                    roboImageView.setY(300);
                    break;

            case 6: roboImageView.setX(1000);
                    roboImageView.setY(500);
                    break;

            case 7: roboImageView.setX(1000);
                    roboImageView.setY(700);
                    break;
        }

        translateTransition.setDuration(Duration.millis(1000));
        translateTransition.setNode(roboImageView);
        translateTransition.setByX(-160);
        translateTransition.play();
        translateTransition.setOnFinished(event -> {
            roboImageView.setVisible(false);
        });
    }

    public void disembarkMiddleman(int station){
        Platform.runLater(() -> disembarkPassenger(station));
    }
}

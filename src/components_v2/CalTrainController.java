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
                System.out.println("Spawning Passenger at Station 1");
                roboImageView.setX(791);
                roboImageView.setY(100);
                robo1.add(roboImageView);
                break;
            case "Station 2":
                System.out.println("Spawning Passenger at Station 2");
                roboImageView.setX(791);
                roboImageView.setY(300);
                robo2.add(roboImageView);
                break;
            case "Station 3":
                System.out.println("Spawning Passenger at Station 3");
                roboImageView.setX(791);
                roboImageView.setY(500);
                robo3.add(roboImageView);
                break;
            case "Station 4":
                System.out.println("Spawning Passenger at Station 4");
                roboImageView.setX(791);
                roboImageView.setY(700);
                robo4.add(roboImageView);
                break;
            case "Station 5":
                System.out.println("Spawning Passenger at Station 5");
                roboImageView.setX(1500);
                roboImageView.setY(100);
                robo5.add(roboImageView);
                break;
            case "Station 6":
                System.out.println("Spawning Passenger at Station 6");
                roboImageView.setX(1500);
                roboImageView.setY(300);
                robo6.add(roboImageView);
                break;
            case "Station 7":
                System.out.println("Spawning Passenger at Station 7");
                roboImageView.setX(1500);
                roboImageView.setY(500);
                robo7.add(roboImageView);
                break;
            case "Station 8":
                System.out.println("Spawning Passenger at Station 8");
                roboImageView.setX(1500);
                roboImageView.setY(700);
                robo8.add(roboImageView);
                break;
        }

        translateTransition.play();
        translateTransition.setOnFinished(event -> {
            RobotModel robot = new RobotModel(roboCounter, Integer.parseInt(temp1[1]) - 1, Integer.parseInt(temp2[1]) - 1);
            System.out.println("Robot has been added to station " + stations[Integer.parseInt(temp1[1]) - 1].getStationID());
            System.out.println("Robot " + roboCounter + " is now waiting at Station " + stations[Integer.parseInt(temp1[1]) - 1].getStationID());
            roboCounter++;
            stations[Integer.parseInt(temp1[1]) - 1].getRobots().add(robot);
        });
    }

    @FXML
    private void deployTrain() {
        System.out.println("Deploying Train");
        String cap = capacity.getText();
        cap = cap.replaceAll("\\s","");

//        Image trainImage = new Image("images/train.png");
//        ImageView leftTrainImageView = new ImageView(trainImage);
//        ImageView rightTrainImageView = new ImageView(trainImage);
//        leftTrainImageView.toFront();
//        rightTrainImageView.toFront();
//
//        background.getChildren().add(leftTrainImageView);
//        leftTrainImageView.setFitHeight(100);
//        leftTrainImageView.setFitWidth(250);
//
//        background.getChildren().add(rightTrainImageView);
//        rightTrainImageView.setFitHeight(100);
//        rightTrainImageView.setFitWidth(300);
//
//        leftTrainImageView.setX(261);
//        leftTrainImageView.setY(87);
//        rightTrainImageView.setX(861);
//        rightTrainImageView.setY(87);
//
//        rightTrainImageView.setVisible(false);
//
//        TranslateTransition toCenter = new TranslateTransition();
//        TranslateTransition toSide = new TranslateTransition();
//
//        toCenter.setDuration(Duration.millis(1000));
//        toCenter.setNode(leftTrainImageView);
//        toCenter.setByX(100);
//
//        toSide.setDuration(Duration.millis(1000));
//        toSide.setNode(leftTrainImageView);
//        toSide.setByX(250);
//
//        TranslateTransition rightToCenter = new TranslateTransition();
//        TranslateTransition rightToSide = new TranslateTransition();
//
//        rightToCenter.setDuration(Duration.millis(1000));
//        rightToCenter.setNode(rightTrainImageView);
//        rightToCenter.setByX(100);
//
//        rightToSide.setDuration(Duration.millis(1000));
//        rightToSide.setNode(rightTrainImageView);
//        rightToSide.setByX(250);

        Train train = new Train(trainCounter, stations, Integer.parseInt(cap));
        Node imageView = train.getTrainController().getView();

        background.getChildren().add(imageView);
        trains[trainCounter] = train;
        trains[trainCounter].startThread();
        trainCounter++;

//        toCenter.play();
//        toCenter.setOnFinished(event -> {
////            for (int i = 0; i < trainCounter; i++){
////                if (trains[i] != null){
////                    if (trains[i].station_load_train(1) == 1){
////                        try{
////                            System.out.println("Thread sleep");
////                            trains[i].getThread().sleep(2000);
////                        } catch(InterruptedException e){
////                            e.printStackTrace();
////                        }
////                        toSide.play();
////                    } else{
////                        toSide.play();
////                    }
////                }
////            }
//            for(Train train: trains) {
//                if(train != null) {
//                    if(train.station_load_train(1) == 1) {
//                            Platform.runLater(() -> {
//                                try {
//                                    Thread.sleep(2000);
//                                } catch (InterruptedException e) {
//                                    e.printStackTrace();
//                                }
//                            });
//                        Platform.runLater(() ->  toSide.play());
//                    } else {
//                        Platform.runLater(() -> toSide.play());
//                    }
//                }
//            }
////            for(Train train : trains) {
////                if(train != null) {
////                    if(train.station_load_train(1) == 1) {
////                        if(stations[train.getStationID()].getRobots().size() > 0) {
////                            for(Robot robot : stations[train.getStationID()].getRobots()) {
////                                if(robot.station_wait_for_train() == 0) {
////                                    System.out.println("nakasakay na si gago");
////                                    int stationNumber = robot.getArrivalStation().getStationID();
////                                    switch(stationNumber) {
////                                        case 0:
////
////                                            break;
////                                        case 1:
////                                            break;
////                                        case 2:
////                                            break;
////                                        case 3:
////                                            break;
////                                        case 4:
////                                            break;
////                                        case 5:
////                                            break;
////                                        case 6:
////                                            break;
////                                        case 7:
////                                            break;
////                                        case 8:
////                                            break;
////                                    }
////                                }
////                            }
////                        }
////
////                        try {
////                            Thread.sleep(1000);
////                            toSide.play();
////                        } catch (InterruptedException e) {
////                            e.printStackTrace();
////                        }
////                    }
////                }
////            }
//        });
//
//        toSide.setOnFinished(event -> {
//            if(leftTrainImageView.getY() >= 680) {
//                leftTrainImageView.setX(leftTrainImageView.getX() - 350);
//                leftTrainImageView.setY(80);
//                leftTrainImageView.setVisible(false);
//                rightTrainImageView.setVisible(true);
//                rightToCenter.play();
//            } else {
//                leftTrainImageView.setX(leftTrainImageView.getX() - 350);
//                leftTrainImageView.setY(leftTrainImageView.getY() + 200);
//                toCenter.play();
//            }
//        });
//
//        // animation for stations 5 - 8
//        rightToCenter.setOnFinished( event -> {
//            rightToSide.play();
//        });
//
//        rightToSide.setOnFinished( event -> {
//            if(rightTrainImageView.getY() >= 680) {
//                rightTrainImageView.setX(rightTrainImageView.getX() - 350);
//                rightTrainImageView.setY(80);
//                rightTrainImageView.setVisible(false);
//                leftTrainImageView.setVisible(true);
//                toCenter.play();
//            } else {
//                rightTrainImageView.setX(rightTrainImageView.getX() - 350);
//                rightTrainImageView.setY(rightTrainImageView.getY() + 200);
//                rightToCenter.play();
//            }
//        });
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

    public synchronized void boardPassenger(int station){
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.millis(1000));
        switch(station)
        {
            case 0: System.out.println(robo1.get(0));
                    translateTransition.setNode(robo1.get(0));
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

            case 5: robo6.get(0).setVisible(false);
                    translateTransition.setByX(-200);
                    translateTransition.play();
                    translateTransition.setOnFinished(event -> {
                        robo6.get(0).setVisible(false);
                        robo6.remove(0);
                    });
                    break;

            case 6: robo7.get(0).setVisible(false);
                    translateTransition.setByX(-200);
                    translateTransition.play();
                    translateTransition.setOnFinished(event -> {
                        robo7.get(0).setVisible(false);
                        robo7.remove(0);
                    });
                    break;

            case 7: robo8.get(0).setVisible(false);
                    translateTransition.setByX(-200);
                    translateTransition.play();
                    translateTransition.setOnFinished(event -> {
                        robo8.get(0).setVisible(false);
                        robo8.remove(0);
                    });
                    break;
        }
    }

    public synchronized void disembarkPassenger(int station) {
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

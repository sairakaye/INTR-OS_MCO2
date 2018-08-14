package components_v2;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

public class TrainController {
    private ImageView view;
    private Train model;
    private TranslateTransition movement;

    private static int START_X_LEFT = 261;
    private static int START_Y = 87;

    private static int START_X_RIGHT = 855;

    private static int MOVE_X = 350;
    private static int MOVE_Y = 200;

    public TrainController (Train model) {
        this.view = new ImageView(new Image("images/train.png"));
        this.model = model;

        view.toFront();
        view.setX(START_X_LEFT);
        view.setY(START_Y);
        view.setFitHeight(100);
        view.setFitWidth(250);
    }

    public Node getView () {
        return view;
    }

    public void moveRight () {

        TranslateTransition movement = new TranslateTransition();
        movement.setNode(view);
        movement.setToX(MOVE_X/2);
        movement.setDuration(Duration.seconds(1));

        TranslateTransition movement2 = new TranslateTransition();
        movement2.setDelay(Duration.seconds(1));
        movement2.setNode(view);
        movement2.setToX(MOVE_X);
        movement2.setDuration(Duration.seconds(1));

        movement2.setOnFinished(event -> {
            view.setTranslateX(0);
            view.setVisible(false);
        });

        SequentialTransition sequentialTransition = new SequentialTransition(movement, movement2);
        sequentialTransition.play();
    }

    public void changeStation (int station) {
        view.setVisible(false);

        view.setX(START_X_RIGHT);

        if (station < 4) {
            view.setX(START_X_LEFT);
        }

        int st = station % 4;

        view.setY((st)*MOVE_Y + 87);
        view.setVisible(true);
    }

}

package components_v2;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RobotController {
    private RobotModel model;
    private ImageView view;

    public RobotController (RobotModel model, ImageView view) {
        model.setController(this);
    }
}

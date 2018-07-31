import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class CalTrainController implements Initializable{

    @FXML ComboBox<String> stationTo;

    @FXML ComboBox<String> stationFrom;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for(int i = 1; i <= 8; i++) {
            stationFrom.getItems().add("Station " + i);
            stationTo.getItems().add("Station " + i);
        }
    }
}

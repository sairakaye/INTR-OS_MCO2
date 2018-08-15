import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class CalTrain2 extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("index.fxml"));
        primaryStage.setTitle("INTR-OS MCO2");
        primaryStage.setScene(new Scene(root, 1461, 800));
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> System.exit(0));
    }

    public static void main(String[] args) {
        launch(args);
    }
}

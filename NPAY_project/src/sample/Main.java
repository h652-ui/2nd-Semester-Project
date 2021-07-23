package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root=FXMLLoader.load(getClass().getResource("welcome.fxml"));
        stage.initStyle(StageStyle.UNDECORATED);
        Scene scene =new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

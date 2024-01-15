package sample;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Welcome implements Initializable{

    @FXML
    Button cont;

    @FXML
    AnchorPane scene1,container;

    @Override
    public void initialize(URL url,ResourceBundle resourceBundle){

    }

    public void presskey(ActionEvent actionEvent) throws IOException {
        Parent root=FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene=cont.getScene();
        root.translateYProperty().set(scene.getHeight());
        container.getChildren().add(root);
        Timeline timeline=new Timeline();
        KeyValue keyValue=new KeyValue(root.translateYProperty(),0, Interpolator.EASE_IN);
        KeyFrame keyFrame=new KeyFrame(Duration.seconds(1),keyValue);
        timeline.getKeyFrames().add(keyFrame);
        timeline.setOnFinished(actionEvent1 ->
                container.getChildren().remove(scene1));
        timeline.play();
    }
}
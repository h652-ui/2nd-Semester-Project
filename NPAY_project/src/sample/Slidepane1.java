package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Slidepane1 implements Initializable {
    @FXML
    Button edit,c_order,trans_btn, reviews;
    @FXML
    ImageView logo,logo1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File logoimage=new File("src/Images/logo.jpg");
        Image logo_icon=new Image(logoimage.toURI().toString());
        logo.setImage(logo_icon);

        File logoimage1=new File("src/Images/logo1.jpg");
        Image logo_icon1=new Image(logoimage1.toURI().toString());
        logo1.setImage(logo_icon1);
    }


    public void edit_profile(ActionEvent actionEvent) throws IOException {
        Stage stage=(Stage) edit.getScene().getWindow();
        stage.close();
        Parent edit_profile= FXMLLoader.load(getClass().getResource("edit_profile_screen.fxml"));
        Stage estage = new Stage();
        estage.initStyle(StageStyle.UNDECORATED);
        estage.setScene(new Scene(edit_profile,540,487));
        estage.show();
    }

    public void current_order(ActionEvent actionEvent) throws IOException {
        Stage stage=(Stage) c_order.getScene().getWindow();
        stage.close();
        Parent order_table= FXMLLoader.load(getClass().getResource("order_window.fxml"));
        Stage ostage = new Stage();
        ostage.initStyle(StageStyle.UNDECORATED);
        ostage.setScene(new Scene(order_table,638,566));
        ostage.show();
    }

    public void transaction(ActionEvent actionEvent) throws IOException {
        Stage stage=(Stage) trans_btn.getScene().getWindow();
        stage.close();
        Parent trans_table= FXMLLoader.load(getClass().getResource("trans_window.fxml"));
        Stage tstage = new Stage();
        tstage.initStyle(StageStyle.UNDECORATED);
        tstage.setScene(new Scene(trans_table,703,566));
        tstage.show();
    }

    public void review_action(ActionEvent actionEvent) throws IOException {
        Stage stage=(Stage) reviews.getScene().getWindow();
        stage.close();
        Parent trans_table= FXMLLoader.load(getClass().getResource("Review_Window.fxml"));
        Stage tstage = new Stage();
        tstage.initStyle(StageStyle.UNDECORATED);
        tstage.setScene(new Scene(trans_table,584,476));
        tstage.show();
    }
}

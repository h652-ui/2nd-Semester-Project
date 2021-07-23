package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserTable implements Initializable {
    @FXML
    private ImageView logo;

    @FXML
    private ImageView logo1;

    @FXML
    private TableView<user_list> user_table;

    @FXML
    private TableColumn<user_list, String> user;

    @FXML
    private TableColumn<user_list, String> pass;

    @FXML
    private TableColumn<user_list, Integer> balance;

    @FXML
    private TableColumn<user_list, String> status;

    @FXML
    Button back_btn;

    ObservableList<user_list> list;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File logoimage=new File("src/Images/logo.jpg");
        Image logo_icon=new Image(logoimage.toURI().toString());
        logo.setImage(logo_icon);

        File logoimage1=new File("src/Images/logo1.jpg");
        Image logo_icon1=new Image(logoimage1.toURI().toString());
        logo1.setImage(logo_icon1);

        user.setCellValueFactory(new PropertyValueFactory<>("username"));
        pass.setCellValueFactory(new PropertyValueFactory<>("password"));
        balance.setCellValueFactory(new PropertyValueFactory<>("wallet"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        list=DatabaseConnection.get_users();
        user_table.setItems(list);
    }

    public void back_action(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) back_btn.getScene().getWindow();
        stage.close();
        Parent root= FXMLLoader.load(getClass().getResource("admin_window.fxml"));
        Stage lstage = new Stage();
        lstage.initStyle(StageStyle.UNDECORATED);
        lstage.setScene(new Scene(root,513,393));
        lstage.show();
    }
}

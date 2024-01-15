package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ResturantTransWindow implements Initializable {
    @FXML
    private Label resturantname;

    @FXML
    private TableView<resturant_trans_list> trans_list;

    @FXML
    private TableColumn<resturant_trans_list, String> order_from;

    @FXML
    private TableColumn<resturant_trans_list, String> hostel;

    @FXML
    private TableColumn<resturant_trans_list, String> product;

    @FXML
    private TableColumn<resturant_trans_list, Integer> quantity;

    @FXML
    private TableColumn<resturant_trans_list, Integer> price;

    @FXML
    private TableColumn<resturant_trans_list, String> phone;

    @FXML
    private TableColumn<resturant_trans_list, String> ID;

    @FXML
    private Label balance;

    @FXML
    Button back_btn;

    ObservableList<resturant_trans_list> list;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        resturantname.setText(userinformation.getU_name());
        balance.setText(Integer.toString(userinformation.getWallet()));
        ID.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getOrder_ID()));
        order_from.setCellValueFactory(new PropertyValueFactory<>("u_name"));
        hostel.setCellValueFactory(new PropertyValueFactory<>("hostel"));
        product.setCellValueFactory(new PropertyValueFactory<>("product"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        list=DatabaseConnection.get_resturant_trans();
        trans_list.setItems(list);
    }

    public void back_action(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) back_btn.getScene().getWindow();
        stage.close();
        Parent root= FXMLLoader.load(getClass().getResource("resturant_window.fxml"));
        Stage bstage = new Stage();
        bstage.initStyle(StageStyle.UNDECORATED);
        bstage.setScene(new Scene(root,674,450));
        bstage.show();
    }
}

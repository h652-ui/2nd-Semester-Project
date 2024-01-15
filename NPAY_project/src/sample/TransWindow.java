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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TransWindow implements Initializable {
    @FXML
    Button back_to;

    @FXML
    private TableView<trans_list> trans_table;

    @FXML
    private TableColumn<trans_list, String> ID;

    @FXML
    private TableColumn<trans_list, String> col_product;

    @FXML
    private TableColumn<trans_list, Integer> col_quantity;

    @FXML
    private TableColumn<trans_list, Integer> col_price;

    @FXML
    private TableColumn<trans_list, String> col_cafe;

    @FXML
    private  TableColumn<trans_list,String> col_status;

    ObservableList<trans_list> list;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        col_product.setCellValueFactory(new PropertyValueFactory<>("product"));
        col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        col_cafe.setCellValueFactory(new PropertyValueFactory<>("cafe"));
        col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        ID.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getOrder_ID()));
        list=DatabaseConnection.gettransaction();
        trans_table.setItems(list);
    }

    public void back_to_main(ActionEvent actionEvent) throws IOException {
        Stage stage=(Stage)back_to.getScene().getWindow();
        stage.close();
        Parent root= FXMLLoader.load(getClass().getResource("studentwindow.fxml"));
        Stage s_stage = new Stage();
        s_stage.initStyle(StageStyle.UNDECORATED);
        s_stage.setScene(new Scene(root,800,620));
        s_stage.show();
    }
}

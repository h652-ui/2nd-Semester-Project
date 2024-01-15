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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class OrderWindow implements Initializable {
    @FXML
    Button back_to,cancel_btn;

    @FXML
    private TableView<order_list > order_table;

    @FXML
    private TableColumn<order_list , String> col_product;

    @FXML
    private TableColumn<order_list , Integer> col_quantity;

    @FXML
    private TableColumn<order_list , Integer> col_price;

    @FXML
    private TableColumn<order_list , String> col_cafe;

    @FXML
    private TableColumn<order_list , String> col_ot;

    @FXML
    private TableColumn<order_list , String> ID;

    @FXML
    Label cancelation,selection;

    ObservableList<order_list> list;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        col_product.setCellValueFactory(new PropertyValueFactory<>("product"));
        col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        col_cafe.setCellValueFactory(new PropertyValueFactory<>("cafe"));
        col_ot.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getOrder_Time()));
        ID.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getOrder_ID()));
        list=DatabaseConnection.getlist();
        order_table.setItems(list);
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

    public void cancel_order(ActionEvent actionEvent) throws IOException {
        order_list obj=order_table.getSelectionModel().getSelectedItem();
        if(obj==null){
            cancelation.setText("");
            selection.setText("Please select something!");
        }
        else {
            String Updater="";
            int Order_ID = Integer.parseInt(obj.Order_ID);
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection connection = databaseConnection.getconnection();
            Updater = "update orders set order_status = 'Cancelled' where Order_ID = "+Order_ID+"";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(Updater);
                preparedStatement.executeUpdate();
            } catch (Exception e) {
                e.getCause();
                e.printStackTrace();
            }
            order_table.getItems().removeAll(order_table.getSelectionModel().getSelectedItem());
            selection.setText("");
            cancelation.setText("Cancelled Successfully");
        }
    }
}

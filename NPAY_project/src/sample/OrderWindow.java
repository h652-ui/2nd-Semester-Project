package sample;

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
    Label cancelation,selection;

    ObservableList<order_list> list;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        col_product.setCellValueFactory(new PropertyValueFactory<>("product"));
        col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        col_cafe.setCellValueFactory(new PropertyValueFactory<>("cafe"));
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
            String query1;
            String query2 = "";
            String product = obj.product;
            String rcafe = obj.cafe;
            int price = obj.price;
            int quantity = obj.quantity;
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection connection = databaseConnection.getconnection();
            query1 = "delete from " + userinformation.getO_table() + " where product=? and quantity=? and price=? and cafe=?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query1);
                preparedStatement.setString(1, product);
                preparedStatement.setString(4, rcafe);
                preparedStatement.setInt(2, quantity);
                preparedStatement.setInt(3, price);
                preparedStatement.executeUpdate();
            } catch (Exception e) {
                e.getCause();
                e.printStackTrace();
            }
            if (rcafe.equals("Concordia 1")) {
                query2 = "DELETE FROM concordia1_order where product='" + product + "' and price=" + price + " and quantity=" + quantity + " and order_from='" + userinformation.getname() + "'";
            }
            if (rcafe.equals("Concordia 2")) {
                query2 = "DELETE FROM concordia2_order where product='" + product + "' and price=" + price + " and quantity=" + quantity + " and order_from='" + userinformation.getname() + "'";
            }
            if (rcafe.equals("Concordia 3")) {
                query2 = "DELETE FROM concordia3_order where product='" + product + "' and price=" + price + " and quantity=" + quantity + " and order_from='" + userinformation.getname() + "'";
            }
            if (rcafe.equals("kybo")) {
                query2 = "DELETE FROM kybo_order where product='" + product + "' and price=" + price + " and quantity=" + quantity + " and order_from='" + userinformation.getname() + "'";
            }
            if (rcafe.equals("retro")) {
                query2 = "DELETE FROM retro_order where product='" + product + "' and price=" + price + " and quantity=" + quantity + " and order_from='" + userinformation.getname() + "'";
            }
            try {
                Statement statement = connection.createStatement();
                statement.executeUpdate(query2);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            order_table.getItems().removeAll(order_table.getSelectionModel().getSelectedItem());
            selection.setText("");
            cancelation.setText("Cancelled Successfully");
            String query3="INSERT INTO "+userinformation.getT_table()+"(product,cafe,price,quantity,o_status) Values('"+product+"','"+rcafe+"',"+price+","+quantity+",'Cancelled')";
            try {
                Statement statement = connection.createStatement();
                statement.executeUpdate(query3);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}

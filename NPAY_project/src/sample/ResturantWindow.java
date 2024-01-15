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
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ResturantWindow implements Initializable {

    @FXML
    Button log_out,trans_btn;

    @FXML
     Label r_name,completion,selection;

    @FXML
    private TableView<resturant_order_list> resturant_order_table;

    @FXML
    private TableColumn<resturant_order_list, String> ID;

    @FXML
    private TableColumn<resturant_order_list, String> username;

    @FXML
    private TableColumn<resturant_order_list, String> name;

    @FXML
    private TableColumn<resturant_order_list, String> hostel;

    @FXML
    private TableColumn<resturant_order_list, String> product;

    @FXML
    private TableColumn<resturant_order_list, Integer> quantity;

    @FXML
    private TableColumn<resturant_order_list, Integer> price;

    @FXML
    private TableColumn<resturant_order_list, String> phone;

    ObservableList<resturant_order_list> list;

    public void out_action(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) log_out.getScene().getWindow();
        stage.close();
        Parent root= FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage lstage = new Stage();
        lstage.initStyle(StageStyle.UNDECORATED);
        lstage.setScene(new Scene(root,676,400));
        lstage.show();

    }

    public void trans_window(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) trans_btn.getScene().getWindow();
        stage.close();
        Parent root= FXMLLoader.load(getClass().getResource("resturant_trans_window.fxml"));
        Stage tstage = new Stage();
        tstage.initStyle(StageStyle.UNDECORATED);
        tstage.setScene(new Scene(root,600,401));
        tstage.show();
    }

    public void complete_order(ActionEvent actionEvent) {
        resturant_order_list obj=resturant_order_table.getSelectionModel().getSelectedItem();
        if(obj==null){
            selection.setText("Please Select Something!");
        }else {
            String query="SELECT wallet from login, student where username='"+obj.u_name+"' and login.User_ID = student.Student_ID";
            int balance=0;
            DatabaseConnection u_databaseConnection=new DatabaseConnection();
            Connection u_connection=u_databaseConnection.getconnection();
            try{
                Statement statement = u_connection.createStatement();
                ResultSet resultSet=statement.executeQuery(query);
                while(resultSet.next()){
                    balance=Integer.parseInt(resultSet.getString("wallet"));
                }
            }catch(Exception e){
                e.printStackTrace();
                e.getCause();
            }
            balance=balance-obj.u_price;
            int c_balance=(userinformation.getWallet()+obj.u_price);
            String query1="update student, login set wallet = "+balance+" where student.Student_ID=login.User_ID and login.Username='"+obj.u_name+"'";
            String query2="update orders set Order_Status = 'Completed' where Order_ID = "+obj.getOrder_ID()+"";
            String query3="update cafe, login set wallet = "+c_balance+" where cafe.Cafe_ID=login.User_ID and login.Username='"+userinformation.getU_name()+"'";
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection connection=databaseConnection.getconnection();
            try {
                Statement statement=connection.createStatement();
                statement.executeUpdate(query1);
                statement.executeUpdate(query2);
                statement.executeUpdate(query3);
                userinformation.setWallet(userinformation.getWallet()+obj.u_price);
            }catch(Exception e){
                e.printStackTrace();
                e.getCause();
            }
            resturant_order_table.getItems().removeAll(resturant_order_table.getSelectionModel().getSelectedItem());
            completion.setText("Completed Successfully!");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DatabaseConnection dbconnect = new DatabaseConnection();
        Connection myconnect = dbconnect.getconnection();
        String verifylogIn = "select CafeName, Wallet, Phone_Number from login, cafe where login.Username='"+userinformation.getU_name()+"' and login.User_ID=cafe.Cafe_ID";
        try{
            Statement statement = myconnect.createStatement();
            ResultSet resultSet = statement.executeQuery(verifylogIn);
            while (resultSet.next()){
                userinformation.setWallet(Integer.parseInt(resultSet.getString("wallet")));
                userinformation.setName(resultSet.getString("CafeName"));
                userinformation.setPhone(resultSet.getString("phone_number"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        r_name.setText(userinformation.getU_name());
        ID.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getOrder_ID()));
        username.setCellValueFactory(new PropertyValueFactory<>("u_name"));
        name.setCellValueFactory(new PropertyValueFactory<>("p_name"));
        hostel.setCellValueFactory(new PropertyValueFactory<>("u_hostel"));
        product.setCellValueFactory(new PropertyValueFactory<>("u_product"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("u_quantity"));
        price.setCellValueFactory(new PropertyValueFactory<>("u_price"));
        phone.setCellValueFactory(new PropertyValueFactory<>("u_phone"));
        list=DatabaseConnection.get_resturant_order();
        resturant_order_table.setItems(list);
    }
}

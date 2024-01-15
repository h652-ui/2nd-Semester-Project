package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.ResourceBundle;

public class Review_Adder implements Initializable {

    @FXML
    Button back_btn;

    @FXML
    ChoiceBox<String> product_name, stars;

    @FXML
    ComboBox<String> cafe_name;

    @FXML
    Label success;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        success.setText("");
        String Cafe_Fetcher="Select * from cafe";
        int ID=0;
        DatabaseConnection databaseConnection=new DatabaseConnection();
        Connection connection=databaseConnection.getconnection();
        try{
            Statement statement=connection.createStatement();
            ResultSet resultSet = statement.executeQuery(Cafe_Fetcher);
            while(resultSet.next()){
                cafe_name.getItems().add(resultSet.getString("CafeName"));
                cafe_name.setValue(resultSet.getString("CafeName"));
                ID = Integer.parseInt(resultSet.getString("Cafe_ID"));
            }
            String Product_Fetcher="Select * from product where Cafe_ID = "+ID+"";
            resultSet = statement.executeQuery(Product_Fetcher);
            while(resultSet.next()){
                product_name.getItems().add(resultSet.getString("P_Name"));
                product_name.setValue(resultSet.getString("P_Name"));
            }
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        stars.getItems().addAll("1","2","3","4","5");
        stars.setValue("1");
    }

    public void back_action(ActionEvent actionEvent) throws IOException {
        Stage stage=(Stage)back_btn.getScene().getWindow();
        stage.close();
        Parent root= FXMLLoader.load(getClass().getResource("Review_Window.fxml"));
        Stage s_stage = new Stage();
        s_stage.initStyle(StageStyle.UNDECORATED);
        s_stage.setScene(new Scene(root,584,476));
        s_stage.show();
    }

    public void add_action(ActionEvent actionEvent) {
        success.setText("");
        int P_ID = 0, C_ID = 0, U_ID = 0;
        String Product_ID="Select product_id from product where P_name = '"+product_name.getValue()+"'";
        String Cafe_ID="Select cafe_id from cafe where CafeName = '"+cafe_name.getValue()+"'";
        String User_ID = "Select User_ID from login where username = '"+userinformation.getU_name()+"'";
        DatabaseConnection databaseConnection=new DatabaseConnection();
        Connection connection=databaseConnection.getconnection();
        try{
            Statement statement=connection.createStatement();
            ResultSet resultSet = statement.executeQuery(Product_ID);
            while(resultSet.next()){
                P_ID = Integer.parseInt(resultSet.getString("product_id"));
            }
            resultSet = statement.executeQuery(Cafe_ID);
            while(resultSet.next()){
                C_ID = Integer.parseInt(resultSet.getString("cafe_id"));
            }
            resultSet = statement.executeQuery(User_ID);
            while(resultSet.next()){
                U_ID = Integer.parseInt(resultSet.getString("user_id"));
            }
            String inserter = "insert into review(consumer_id, cafe_id, product_id, stars) values("+U_ID+","+C_ID+","+P_ID+","+Integer.parseInt(stars.getValue())+")";
            statement.executeUpdate(inserter);
            success.setText("Added Successfully");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void c_products(ActionEvent actionEvent) {
        product_name.getItems().clear();
        String ID_getter="select cafe_id from cafe where CafeName = '"+cafe_name.getValue()+"'";
        int ID=0;
        DatabaseConnection databaseConnection=new DatabaseConnection();
        Connection connection=databaseConnection.getconnection();
        try{
            Statement statement=connection.createStatement();
            ResultSet resultSet = statement.executeQuery(ID_getter);
            while(resultSet.next()){
                ID = Integer.parseInt(resultSet.getString("Cafe_ID"));
            }
            String Product_Fetcher="Select * from product where Cafe_ID = "+ID+"";
            resultSet = statement.executeQuery(Product_Fetcher);
            while(resultSet.next()){
                product_name.getItems().add(resultSet.getString("P_Name"));
                product_name.setValue(resultSet.getString("P_Name"));
            }
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
}

package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class KyboMenu implements Initializable {
    @FXML
    ImageView kybo_image;
    @FXML
    Button back;
    @FXML
    Label p_count,j_count,t_count,s_count,t_label,not_label,order_success, r_price, j_price, t_price, s_price;
    @FXML
    CheckBox c_roll,c_jumbo,c_tikka,c_shake;
    int p=0,j=0,t=0,s=0,total;
    int[] prices = new int[4];
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        Label[] p_labels = {r_price, j_price, t_price, s_price};
        CheckBox[] checkBoxes = {c_roll,c_jumbo,c_tikka,c_shake};
        File kyboImage=new File("src/Images/Kybo.jpg");
        Image kybo = new Image(kyboImage.toURI().toString());
        kybo_image.setImage(kybo);
        DatabaseConnection databaseConnection=new DatabaseConnection();
        Connection connection=databaseConnection.getconnection();
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select P_Name, Price from product where Cafe_ID in (Select Cafe_ID from cafe where CafeName = 'Kybo')");
            int count = 0;
            while(resultSet.next() && count < p_labels.length) {
                checkBoxes[count].setText(resultSet.getString("p_name"));
                p_labels[count].setText(resultSet.getString("price"));
                count++;
            }
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        for (int i=0;i<4;i++){
            prices[i] = Integer.parseInt(p_labels[i].getText());
        }
    }
    public void s_decrement(ActionEvent actionEvent) {
        if(c_shake.isSelected()&&s>0){
            --s;
            s_count.setText(Integer.toString(s));
            total-=prices[3];
            t_label.setText(Integer.toString(total)+" Rs.");
        }
    }

    public void t_decrement(ActionEvent actionEvent) {
        if(c_tikka.isSelected()&&t>0){
            --t;
            t_count.setText(Integer.toString(t));
            total-=prices[2];
            t_label.setText(Integer.toString(total)+" Rs.");
        }
    }

    public void j_decrement(ActionEvent actionEvent) {
        if(c_jumbo.isSelected()&&j>0){
            --j;
            j_count.setText(Integer.toString(j));
            total-=prices[1];
            t_label.setText(Integer.toString(total)+" Rs.");
        }
    }

    public void p_decrement(ActionEvent actionEvent) {
        if(c_roll.isSelected()&&p>0){
            --p;
            p_count.setText(Integer.toString(p));
            total-=prices[0];
            t_label.setText(Integer.toString(total)+" Rs.");
        }
    }

    public void s_increment(ActionEvent actionEvent) {
        if(c_shake.isSelected()&&t<99){
            ++s;
            s_count.setText(Integer.toString(s));
            total+=prices[3];
            t_label.setText(Integer.toString(total)+" Rs.");
        }
    }

    public void t_increment(ActionEvent actionEvent) {
        if(c_tikka.isSelected()&&t<99){
            ++t;
            t_count.setText(Integer.toString(t));
            total+=prices[2];
            t_label.setText(Integer.toString(total)+" Rs.");
        }
    }

    public void j_increment(ActionEvent actionEvent) {
        if(c_jumbo.isSelected()&&j<99){
            ++j;
            j_count.setText(Integer.toString(j));
            total+=prices[1];
            t_label.setText(Integer.toString(total)+" Rs.");
        }
    }

    public void p_increment(ActionEvent actionEvent) {
        if(c_roll.isSelected()&&p<99){
            ++p;
            p_count.setText(Integer.toString(p));
            total+=prices[0];
            t_label.setText(Integer.toString(total)+" Rs.");
        }
    }

    public void o_action(ActionEvent actionEvent) {
        if(total>userinformation.getWallet()){
            not_label.setText("Not Enough Money");
        }
        else{
            not_label.setText("");
            String P_ID, C_ID, S_ID, inserter;
            int product_id = 0, cafe_id = 0, student_id = 0;
            DatabaseConnection databaseConnection=new DatabaseConnection();
            Connection connection=databaseConnection.getconnection();
            try {
                Statement statement=connection.createStatement();
                S_ID = "SELECT User_ID from login where username = '"+userinformation.getU_name()+"'";
                ResultSet resultSet = statement.executeQuery(S_ID);
                while(resultSet.next())
                    student_id = Integer.parseInt(resultSet.getString("User_ID"));
                C_ID = "SELECT Cafe_ID FROM cafe where CafeName = 'Kybo'";
                resultSet = statement.executeQuery(C_ID);
                while(resultSet.next())
                    cafe_id = Integer.parseInt(resultSet.getString("Cafe_ID"));
                if(p>0&&c_roll.isSelected()){
                    P_ID = "SELECT Product_ID FROM product where P_Name = '"+c_roll.getText()+"'";
                    resultSet = statement.executeQuery(P_ID);
                    while(resultSet.next())
                        product_id = Integer.parseInt(resultSet.getString("Product_ID"));
                    inserter = "Insert into orders(Student_Id, Cafe_ID, Product_ID, Product, Price, Quantity, Order_Status) values("+student_id+", "+cafe_id+", "+product_id+", '"+c_roll.getText()+"', "+prices[0]*p+", "+p+", 'Pending')";
                    statement.executeUpdate(inserter);
                    t_label.setText("");
                    order_success.setText("Ordered Successfully");
                }
                if(j>0&&c_jumbo.isSelected()){
                    P_ID = "SELECT Product_ID FROM product where P_Name = '"+c_jumbo.getText()+"'";
                    resultSet = statement.executeQuery(P_ID);
                    while(resultSet.next())
                        product_id = Integer.parseInt(resultSet.getString("Product_ID"));
                    inserter = "Insert into orders(Student_Id, Cafe_ID, Product_ID, Product, Price, Quantity, Order_Status) values("+student_id+", "+cafe_id+", "+product_id+", '"+c_jumbo.getText()+"', "+prices[1]*j+", "+j+", 'Pending')";
                    statement.executeUpdate(inserter);
                    t_label.setText("");
                    order_success.setText("Ordered Successfully");
                }
                if(t>0&&c_tikka.isSelected()){
                    P_ID = "SELECT Product_ID FROM product where P_Name = '"+c_tikka.getText()+"'";
                    resultSet = statement.executeQuery(P_ID);
                    while(resultSet.next())
                        product_id = Integer.parseInt(resultSet.getString("Product_ID"));
                    inserter = "Insert into orders(Student_Id, Cafe_ID, Product_ID, Product, Price, Quantity, Order_Status) values("+student_id+", "+cafe_id+", "+product_id+", '"+c_tikka.getText()+"', "+prices[2]*t+", "+t+", 'Pending')";
                    statement.executeUpdate(inserter);
                    t_label.setText("");
                    order_success.setText("Ordered Successfully");
                }
                if(s>0&&c_shake.isSelected()){
                    P_ID = "SELECT Product_ID FROM product where P_Name = '"+c_shake.getText()+"'";
                    resultSet = statement.executeQuery(P_ID);
                    while(resultSet.next())
                        product_id = Integer.parseInt(resultSet.getString("Product_ID"));
                    inserter = "Insert into orders(Student_Id, Cafe_ID, Product_ID, Product, Price, Quantity, Order_Status) values("+student_id+", "+cafe_id+", "+product_id+", '"+c_shake.getText()+"', "+prices[3]*s+", "+s+", 'Pending')";
                    statement.executeUpdate(inserter);
                    t_label.setText("");
                    order_success.setText("Ordered Successfully");
                }
            }catch (Exception e){
                e.printStackTrace();
                e.getCause();
            }
        }
    }

    public void b_action(ActionEvent actionEvent) throws IOException {
        Stage stage=(Stage)back.getScene().getWindow();
        stage.close();
        Parent root= FXMLLoader.load(getClass().getResource("studentwindow.fxml"));
        Stage s_stage = new Stage();
        s_stage.initStyle(StageStyle.UNDECORATED);
        s_stage.setScene(new Scene(root,800,620));
        s_stage.show();
    }
}

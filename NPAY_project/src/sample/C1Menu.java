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

public class C1Menu implements Initializable {

    @FXML
    ImageView c1_image;
    @FXML
    Button back;
    @FXML
    CheckBox c_icecream,c_donut,c_Nestea,c_Coffee;
    @FXML
    Label i_label,d_label,n_label,c_label,t_label,not_label,order_success, i_price, d_price, c_price, n_price;
    int i=0,d=0,n=0,c=0,total=0;
    int[] prices = new int[4];
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        Label[] p_labels = {i_price, c_price, n_price, d_price};
        CheckBox[] checkBoxes = {c_icecream, c_Coffee, c_Nestea, c_donut};
        File c1Image=new File("src/Images/C1.png");
        Image c1 = new Image(c1Image.toURI().toString());
        c1_image.setImage(c1);
        DatabaseConnection databaseConnection=new DatabaseConnection();
        Connection connection=databaseConnection.getconnection();
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select P_Name, Price from product where Cafe_ID in (Select Cafe_ID from cafe where CafeName = 'Concordia 1')");
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

    public void i_increment(ActionEvent actionEvent) {
        if(c_icecream.isSelected() && i<99){
            ++i;
            i_label.setText(Integer.toString(i));
            total+=prices[0];
            t_label.setText(Integer.toString(total)+" Rs.");
        }
    }

    public void d_increment(ActionEvent actionEvent) {
        if(c_donut.isSelected() && d<99){
            ++d;
            d_label.setText(Integer.toString(d));
            total+=prices[1];
            t_label.setText(Integer.toString(total)+" Rs.");
        }
    }

    public void n_increment(ActionEvent actionEvent) {
        if(c_Nestea.isSelected() && n<99){
            ++n;
            n_label.setText(Integer.toString(n));
            total+=prices[2];
            t_label.setText(Integer.toString(total)+" Rs.");
        }
    }

    public void c_increment(ActionEvent actionEvent) {
        if(c_Coffee.isSelected() && c<99){
            ++c;
            c_label.setText(Integer.toString(c));
            total+=prices[3];
            t_label.setText(Integer.toString(total)+" Rs.");
        }
    }

    public void i_decrement(ActionEvent actionEvent) {
        if(c_icecream.isSelected() && i>0){
            --i;
            i_label.setText(Integer.toString(i));
            if(total>0) {
                total -= prices[0];
                t_label.setText(Integer.toString(total) + " Rs.");
            }
        }
    }

    public void c_decrement(ActionEvent actionEvent) {
        if(c_Coffee.isSelected() && c>0){
            --c;
            c_label.setText(Integer.toString(c));
            if(total>0) {
                total-=prices[3];
                t_label.setText(Integer.toString(total)+" Rs.");
            }
        }
    }

    public void n_decrement(ActionEvent actionEvent) {
        if(c_Nestea.isSelected() && n>0){
            --n;
            n_label.setText(Integer.toString(n));
            if(total>0){
                total-=prices[2];
                t_label.setText(Integer.toString(total)+" Rs.");
            }
        }
    }

    public void d_decrement(ActionEvent actionEvent) {
        if(c_donut.isSelected() && d>0){
            --d;
            d_label.setText(Integer.toString(d));
            if(total>0) {
                total-=prices[1];
                t_label.setText(Integer.toString(total)+" Rs.");
            }
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
                C_ID = "SELECT Cafe_ID FROM cafe where CafeName = 'Concordia 1'";
                resultSet = statement.executeQuery(C_ID);
                while(resultSet.next())
                    cafe_id = Integer.parseInt(resultSet.getString("Cafe_ID"));
                if(i>0&&c_icecream.isSelected()){
                    P_ID = "SELECT Product_ID FROM product where P_Name = '"+c_icecream.getText()+"'";
                    resultSet = statement.executeQuery(P_ID);
                    while(resultSet.next())
                        product_id = Integer.parseInt(resultSet.getString("Product_ID"));
                    inserter = "Insert into orders(Student_Id, Cafe_ID, Product_ID, Product, Price, Quantity, Order_Status) values("+student_id+", "+cafe_id+", "+product_id+", '"+c_icecream.getText()+"', "+prices[0]*i+", "+i+", 'Pending')";
                    statement.executeUpdate(inserter);
                    t_label.setText("");
                    order_success.setText("Ordered Successfully");
                }
                if(d>0&&c_donut.isSelected()){
                    P_ID = "SELECT Product_ID FROM product where P_Name = '"+c_donut.getText()+"'";
                    resultSet = statement.executeQuery(P_ID);
                    while(resultSet.next())
                        product_id = Integer.parseInt(resultSet.getString("Product_ID"));
                    inserter = "Insert into orders(Student_Id, Cafe_ID, Product_ID, Product, Price, Quantity, Order_Status) values("+student_id+", "+cafe_id+", "+product_id+", '"+c_donut.getText()+"', "+prices[1]*d+", "+d+", 'Pending')";
                    statement.executeUpdate(inserter);
                    t_label.setText("");
                    order_success.setText("Ordered Successfully");
                }
                if(n>0&&c_Nestea.isSelected()){
                    P_ID = "SELECT Product_ID FROM product where P_Name = '"+c_Nestea.getText()+"'";
                    resultSet = statement.executeQuery(P_ID);
                    while(resultSet.next())
                        product_id = Integer.parseInt(resultSet.getString("Product_ID"));
                    inserter = "Insert into orders(Student_Id, Cafe_ID, Product_ID, Product, Price, Quantity, Order_Status) values("+student_id+", "+cafe_id+", "+product_id+", '"+c_Nestea.getText()+"', "+prices[2]*n+", "+n+", 'Pending')";
                    statement.executeUpdate(inserter);
                    t_label.setText("");
                    order_success.setText("Ordered Successfully");
                }
                if(c>0&&c_Coffee.isSelected()){
                    P_ID = "SELECT Product_ID FROM product where P_Name = '"+c_Coffee.getText()+"'";
                    resultSet = statement.executeQuery(P_ID);
                    while(resultSet.next())
                        product_id = Integer.parseInt(resultSet.getString("Product_ID"));
                    inserter = "Insert into orders(Student_Id, Cafe_ID, Product_ID, Product, Price, Quantity, Order_Status) values("+student_id+", "+cafe_id+", "+product_id+", '"+c_Coffee.getText()+"', "+prices[3]*c+", "+c+", 'Pending')";
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

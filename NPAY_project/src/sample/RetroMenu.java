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

public class RetroMenu implements Initializable {
    @FXML
    ImageView retro_image;
    @FXML
    Button back;
    @FXML
    Label z_count,f_count,t_count,t_label,not_label,order_success, z_price, f_price, t_price;
    @FXML
    CheckBox c_zinger,c_fries,c_tea;
    int z=0,f=0,t=0,total=0;
    int[] prices = new int[3];
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        Label[] p_labels = {z_price, f_price, t_price};
        CheckBox[] checkBoxes = {c_zinger, c_fries, c_tea};
        File retroImage=new File("src/Images/Retro.jpg");
        Image retro = new Image(retroImage.toURI().toString());
        retro_image.setImage(retro);
        DatabaseConnection databaseConnection=new DatabaseConnection();
        Connection connection=databaseConnection.getconnection();
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select P_Name, Price from product where Cafe_ID in (Select Cafe_ID from cafe where CafeName = 'Retro')");
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
        for (int i=0;i<3;i++){
            prices[i] = Integer.parseInt(p_labels[i].getText());
        }
    }

    public void z_decrement(ActionEvent actionEvent) {
        if(c_zinger.isSelected()&&z>0){
            --z;
            z_count.setText(Integer.toString(z));
            total-=prices[0];
            t_label.setText(Integer.toString(total)+" Rs.");
        }
    }

    public void f_decrement(ActionEvent actionEvent) {
        if(c_fries.isSelected()&&f>0){
            --f;
            f_count.setText(Integer.toString(f));
            total-=prices[1];
            t_label.setText(Integer.toString(total)+" Rs.");
        }
    }

    public void t_decrement(ActionEvent actionEvent) {
        if(c_tea.isSelected()&&t>0){
            --t;
            t_count.setText(Integer.toString(t));
            total-=prices[2];
            t_label.setText(Integer.toString(total)+" Rs.");
        }
    }

    public void z_increment(ActionEvent actionEvent) {
        if(c_zinger.isSelected()&&z<99){
            ++z;
            z_count.setText(Integer.toString(z));
            total+=prices[0];
            t_label.setText(Integer.toString(total)+" Rs.");
        }
    }

    public void f_increment(ActionEvent actionEvent) {
        if(c_fries.isSelected()&&f<99){
            ++f;
            f_count.setText(Integer.toString(f));
            total+=prices[1];
            t_label.setText(Integer.toString(total)+" Rs.");
        }
    }

    public void t_increment(ActionEvent actionEvent) {
        if(c_tea.isSelected()&&t<99){
            ++t;
            t_count.setText(Integer.toString(t));
            total+=prices[2];
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
                C_ID = "SELECT Cafe_ID FROM cafe where CafeName = 'Retro'";
                resultSet = statement.executeQuery(C_ID);
                while(resultSet.next())
                    cafe_id = Integer.parseInt(resultSet.getString("Cafe_ID"));
                if(z>0&&c_zinger.isSelected()){
                    P_ID = "SELECT Product_ID FROM product where P_Name = '"+c_zinger.getText()+"'";
                    resultSet = statement.executeQuery(P_ID);
                    while(resultSet.next())
                        product_id = Integer.parseInt(resultSet.getString("Product_ID"));
                    inserter = "Insert into orders(Student_Id, Cafe_ID, Product_ID, Product, Price, Quantity, Order_Status) values("+student_id+", "+cafe_id+", "+product_id+", '"+c_zinger.getText()+"', "+prices[0]*z+", "+z+", 'Pending')";
                    statement.executeUpdate(inserter);
                    t_label.setText("");
                    order_success.setText("Ordered Successfully");
                }
                if(f>0&&c_fries.isSelected()){
                    P_ID = "SELECT Product_ID FROM product where P_Name = '"+c_fries.getText()+"'";
                    resultSet = statement.executeQuery(P_ID);
                    while(resultSet.next())
                        product_id = Integer.parseInt(resultSet.getString("Product_ID"));
                    inserter = "Insert into orders(Student_Id, Cafe_ID, Product_ID, Product, Price, Quantity, Order_Status) values("+student_id+", "+cafe_id+", "+product_id+", '"+c_fries.getText()+"', "+prices[1]*f+", "+f+", 'Pending')";
                    statement.executeUpdate(inserter);
                    t_label.setText("");
                    order_success.setText("Ordered Successfully");
                }
                if(t>0&&c_tea.isSelected()){
                    P_ID = "SELECT Product_ID FROM product where P_Name = '"+c_tea.getText()+"'";
                    resultSet = statement.executeQuery(P_ID);
                    while(resultSet.next())
                        product_id = Integer.parseInt(resultSet.getString("Product_ID"));
                    inserter = "Insert into orders(Student_Id, Cafe_ID, Product_ID, Product, Price, Quantity, Order_Status) values("+student_id+", "+cafe_id+", "+product_id+", '"+c_tea.getText()+"', "+prices[2]*t+", "+t+", 'Pending')";
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

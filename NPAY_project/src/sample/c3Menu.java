package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
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

public class c3Menu implements Initializable {
    @FXML
    ImageView margalla_image;
    @FXML
    Button back;
    @FXML
    Label b_count,k_count,bu_count,c_count,t_label,not_label,order_success, b_price, k_price, c_price, bu_price;
    @FXML
    CheckBox c_bbq,c_karahi,c_chips,c_burger;
    int bq=0,k=0,c=0,b=0,total=0;
    int[] prices = new int[4];
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        Label[] p_labels = {b_price, k_price, c_price, bu_price};
        CheckBox[] checkBoxes = {c_bbq,c_karahi,c_chips,c_burger};
        File margallaImage=new File("src/Images/C3.png");
        Image margalla = new Image(margallaImage.toURI().toString());
        margalla_image.setImage(margalla);
        DatabaseConnection databaseConnection=new DatabaseConnection();
        Connection connection=databaseConnection.getconnection();
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select P_Name, Price from product where Cafe_ID in (Select Cafe_ID from cafe where CafeName = 'Concordia 3')");
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

    public void bq_increment(ActionEvent actionEvent) {
        if(c_bbq.isSelected()&&bq<99){
            ++bq;
            b_count.setText(Integer.toString(bq));
            total+=prices[0];
            t_label.setText(Integer.toString(total)+" Rs.");
        }
    }

    public void k_increment(ActionEvent actionEvent) {
        if(c_karahi.isSelected()&&c<99){
            ++k;
            k_count.setText(Integer.toString(k));
            total+=prices[1];
            t_label.setText(Integer.toString(total)+" Rs.");
        }
    }

    public void c_increment(ActionEvent actionEvent) {
        if(c_chips.isSelected()&&c<99){
            ++c;
            c_count.setText(Integer.toString(c));
            total+=prices[2];
            t_label.setText(Integer.toString(total)+" Rs.");
        }
    }

    public void b_increment(ActionEvent actionEvent) {
        if(c_burger.isSelected()&&b<99){
            ++b;
            bu_count.setText(Integer.toString(b));
            total+=prices[3];
            t_label.setText(Integer.toString(total)+" Rs.");
        }
    }

    public void bq_decrement(ActionEvent actionEvent) {
        if(c_bbq.isSelected()&&bq>0){
            --bq;
            b_count.setText(Integer.toString(bq));
            total-=prices[0];
            t_label.setText(Integer.toString(total)+" Rs.");
        }
    }

    public void k_decrement(ActionEvent actionEvent) {
        if(c_karahi.isSelected()&&k>0){
            --k;
            k_count.setText(Integer.toString(k));
            total-=prices[1];
            t_label.setText(Integer.toString(total)+" Rs.");
        }
    }

    public void c_decrement(ActionEvent actionEvent) {
        if(c_chips.isSelected()&&c>0){
            --c;
            c_count.setText(Integer.toString(c));
            total-=prices[2];
            t_label.setText(Integer.toString(total)+" Rs.");
        }
    }

    public void b_decrement(ActionEvent actionEvent) {
        if(c_burger.isSelected()&&b>0){
            --b;
            bu_count.setText(Integer.toString(b));
            total-=prices[3];
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
                C_ID = "SELECT Cafe_ID FROM cafe where CafeName = 'Concordia 3'";
                resultSet = statement.executeQuery(C_ID);
                while(resultSet.next())
                    cafe_id = Integer.parseInt(resultSet.getString("Cafe_ID"));
                if(bq>0&&c_bbq.isSelected()){
                    P_ID = "SELECT Product_ID FROM product where P_Name = '"+c_bbq.getText()+"'";
                    resultSet = statement.executeQuery(P_ID);
                    while(resultSet.next())
                        product_id = Integer.parseInt(resultSet.getString("Product_ID"));
                    inserter = "Insert into orders(Student_Id, Cafe_ID, Product_ID, Product, Price, Quantity, Order_Status) values("+student_id+", "+cafe_id+", "+product_id+", '"+c_bbq.getText()+"', "+prices[0]*bq+", "+bq+", 'Pending')";
                    statement.executeUpdate(inserter);
                    t_label.setText("");
                    order_success.setText("Ordered Successfully");
                }
                if(b>0&&c_burger.isSelected()){
                    P_ID = "SELECT Product_ID FROM product where P_Name = '"+c_burger.getText()+"'";
                    resultSet = statement.executeQuery(P_ID);
                    while(resultSet.next())
                        product_id = Integer.parseInt(resultSet.getString("Product_ID"));
                    inserter = "Insert into orders(Student_Id, Cafe_ID, Product_ID, Product, Price, Quantity, Order_Status) values("+student_id+", "+cafe_id+", "+product_id+", '"+c_burger.getText()+"', "+prices[3]*b+", "+b+", 'Pending')";
                    statement.executeUpdate(inserter);
                    t_label.setText("");
                    order_success.setText("Ordered Successfully");
                }
                if(k>0&&c_karahi.isSelected()){
                    P_ID = "SELECT Product_ID FROM product where P_Name = '"+c_karahi.getText()+"'";
                    resultSet = statement.executeQuery(P_ID);
                    while(resultSet.next())
                        product_id = Integer.parseInt(resultSet.getString("Product_ID"));
                    inserter = "Insert into orders(Student_Id, Cafe_ID, Product_ID, Product, Price, Quantity, Order_Status) values("+student_id+", "+cafe_id+", "+product_id+", '"+c_karahi.getText()+"', "+prices[1]*k+", "+k+", 'Pending')";
                    statement.executeUpdate(inserter);
                    t_label.setText("");
                    order_success.setText("Ordered Successfully");
                }
                if(c>0&&c_chips.isSelected()){
                    P_ID = "SELECT Product_ID FROM product where P_Name = '"+c_chips.getText()+"'";
                    resultSet = statement.executeQuery(P_ID);
                    while(resultSet.next())
                        product_id = Integer.parseInt(resultSet.getString("Product_ID"));
                    inserter = "Insert into orders(Student_Id, Cafe_ID, Product_ID, Product, Price, Quantity, Order_Status) values("+student_id+", "+cafe_id+", "+product_id+", '"+c_chips.getText()+"', "+prices[2]*c+", "+c+", 'Pending')";
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

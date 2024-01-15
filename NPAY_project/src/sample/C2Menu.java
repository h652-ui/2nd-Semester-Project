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
import java.util.List;
import java.util.ResourceBundle;

public class C2Menu implements Initializable {
    @FXML
    ImageView c2_image;
    @FXML
    Button back;
    @FXML
    Label b_count,s_count,p_count,w_count,t_label,order_success,not_label, b_price, s_price, p_price, w_price;
    @FXML
    CheckBox c_biryani, c_Sharma,c_pizza,c_water;
    int b=0,s=0,p=0,w=0,total;
    int[] prices = new int[4];
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        Label[] p_labels = {b_price, s_price, p_price, w_price};
        CheckBox[] checkBoxes = {c_biryani, c_Sharma,c_pizza,c_water};
        File c2Image=new File("src/Images/C2(1).jpg");
        Image c2 = new Image(c2Image.toURI().toString());
        c2_image.setImage(c2);
        DatabaseConnection databaseConnection=new DatabaseConnection();
        Connection connection=databaseConnection.getconnection();
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select P_Name, Price from product where Cafe_ID in (Select Cafe_ID from cafe where CafeName = 'Concordia 2')");
            int count=0;
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

    public void b_decrement(ActionEvent actionEvent) {
        if(c_biryani.isSelected()&&b>0){
            --b;
            b_count.setText(Integer.toString(b));
            total -= prices[0];
            t_label.setText(Integer.toString(total) + " Rs.");
        }
    }

    public void b_increment(ActionEvent actionEvent) {
        if(c_biryani.isSelected()&&b<99){
            ++b;
            b_count.setText(Integer.toString(b));
            total += prices[0];
            t_label.setText(Integer.toString(total) + " Rs.");
        }
    }

    public void s_decrement(ActionEvent actionEvent) {
        if(c_Sharma.isSelected()&&s>0){
            --s;
            s_count.setText(Integer.toString(s));
            total -= prices[1];
            t_label.setText(Integer.toString(total) + " Rs.");
        }
    }

    public void s_increment(ActionEvent actionEvent) {
        if(c_Sharma.isSelected()&&s<99){
            ++s;
            s_count.setText(Integer.toString(s));
            total += prices[1];
            t_label.setText(Integer.toString(total) + " Rs.");
        }
    }

    public void p_decrement(ActionEvent actionEvent) {
        if(c_pizza.isSelected()&&p>0){
            --p;
            p_count.setText(Integer.toString(p));
            total -= prices[2];
            t_label.setText(Integer.toString(total) + " Rs.");
        }
    }

    public void p_increment(ActionEvent actionEvent) {
        if(c_pizza.isSelected()&&p<99){
            ++p;
            p_count.setText(Integer.toString(p));
            total += prices[2];
            t_label.setText(Integer.toString(total) + " Rs.");
        }
    }

    public void w_decrement(ActionEvent actionEvent) {
        if(c_water.isSelected()&&w>0){
            --w;
            w_count.setText(Integer.toString(w));
            total -= prices[3];
            t_label.setText(Integer.toString(total) + " Rs.");
        }
    }

    public void w_increment(ActionEvent actionEvent) {
        if(c_water.isSelected()&&w<99){
            ++w;
            w_count.setText(Integer.toString(w));
            total += prices[3];
            t_label.setText(Integer.toString(total) + " Rs.");
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

    public void o_action(ActionEvent actionEvent){
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
                C_ID = "SELECT Cafe_ID FROM cafe where CafeName = 'Concordia 2'";
                resultSet = statement.executeQuery(C_ID);
                while(resultSet.next())
                    cafe_id = Integer.parseInt(resultSet.getString("Cafe_ID"));
                if(b>0&&c_biryani.isSelected()){
                    P_ID = "SELECT Product_ID FROM product where P_Name = '"+c_biryani.getText()+"'";
                    resultSet = statement.executeQuery(P_ID);
                    while(resultSet.next())
                        product_id = Integer.parseInt(resultSet.getString("Product_ID"));
                    inserter = "Insert into orders(Student_Id, Cafe_ID, Product_ID, Product, Price, Quantity, Order_Status) values("+student_id+", "+cafe_id+", "+product_id+", '"+c_biryani.getText()+"', "+prices[0]*b+", "+b+", 'Pending')";
                    statement.executeUpdate(inserter);
                    t_label.setText("");
                    order_success.setText("Ordered Successfully");
                }
                if(s>0&& c_Sharma.isSelected()){
                    P_ID = "SELECT Product_ID FROM product where P_Name = '"+c_Sharma.getText()+"'";
                    resultSet = statement.executeQuery(P_ID);
                    while(resultSet.next())
                        product_id = Integer.parseInt(resultSet.getString("Product_ID"));
                    inserter = "Insert into orders(Student_Id, Cafe_ID, Product_ID, Product, Price, Quantity, Order_Status) values("+student_id+", "+cafe_id+", "+product_id+", '"+c_Sharma.getText()+"', "+prices[1]*s+", "+s+", 'Pending')";
                    statement.executeUpdate(inserter);
                    t_label.setText("");
                    order_success.setText("Ordered Successfully");
                }
                if(p>0&&c_pizza.isSelected()){
                    P_ID = "SELECT Product_ID FROM product where P_Name = '"+c_Sharma.getText()+"'";
                    resultSet = statement.executeQuery(P_ID);
                    while(resultSet.next())
                        product_id = Integer.parseInt(resultSet.getString("Product_ID"));
                    inserter = "Insert into orders(Student_Id, Cafe_ID, Product_ID, Product, Price, Quantity, Order_Status) values("+student_id+", "+cafe_id+", "+product_id+", '"+c_pizza.getText()+"', "+prices[2]*p+", "+p+", 'Pending')";
                    statement.executeUpdate(inserter);
                    t_label.setText("");
                    order_success.setText("Ordered Successfully");
                }
                if(w>0&&c_water.isSelected()){
                    P_ID = "SELECT Product_ID FROM product where P_Name = '"+c_Sharma.getText()+"'";
                    resultSet = statement.executeQuery(P_ID);
                    while(resultSet.next())
                        product_id = Integer.parseInt(resultSet.getString("Product_ID"));
                    inserter = "Insert into orders(Student_Id, Cafe_ID, Product_ID, Product, Price, Quantity, Order_Status) values("+student_id+", "+cafe_id+", "+product_id+", '"+c_water.getText()+"', "+prices[3]*w+", "+w+", 'Pending')";
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
}

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
    Label i_label,d_label,n_label,c_label,t_label,not_label,order_success;
    int i=0,d=0,n=0,c=0,total=0;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File c1Image=new File("src/Images/C1.png");
        Image c1 = new Image(c1Image.toURI().toString());
        c1_image.setImage(c1);
    }

    public void i_increment(ActionEvent actionEvent) {
        if(c_icecream.isSelected() && i<99){
            ++i;
            i_label.setText(Integer.toString(i));
            total+=50;
            t_label.setText(Integer.toString(total)+" Rs.");
        }
    }

    public void d_increment(ActionEvent actionEvent) {
        if(c_donut.isSelected() && d<99){
            ++d;
            d_label.setText(Integer.toString(d));
            total+=100;
            t_label.setText(Integer.toString(total)+" Rs.");
        }
    }

    public void n_increment(ActionEvent actionEvent) {
        if(c_Nestea.isSelected() && n<99){
            ++n;
            n_label.setText(Integer.toString(n));
            total+=70;
            t_label.setText(Integer.toString(total)+" Rs.");
        }
    }

    public void c_increment(ActionEvent actionEvent) {
        if(c_Coffee.isSelected() && c<99){
            ++c;
            c_label.setText(Integer.toString(c));
            total+=110;
            t_label.setText(Integer.toString(total)+" Rs.");
        }
    }

    public void i_decrement(ActionEvent actionEvent) {
        if(c_icecream.isSelected() && i>0){
            --i;
            i_label.setText(Integer.toString(i));
            if(total>0) {
                total -= 50;
                t_label.setText(Integer.toString(total) + " Rs.");
            }
        }
    }

    public void c_decrement(ActionEvent actionEvent) {
        if(c_Coffee.isSelected() && c>0){
            --c;
            c_label.setText(Integer.toString(c));
            if(total>0) {
                total-=110;
                t_label.setText(Integer.toString(total)+" Rs.");
            }
        }
    }

    public void n_decrement(ActionEvent actionEvent) {
        if(c_Nestea.isSelected() && n>0){
            --n;
            n_label.setText(Integer.toString(n));
            if(total>0){
                total-=70;
                t_label.setText(Integer.toString(total)+" Rs.");
            }
        }
    }

    public void d_decrement(ActionEvent actionEvent) {
        if(c_donut.isSelected() && d>0){
            --d;
            d_label.setText(Integer.toString(d));
            if(total>0) {
                total-=100;
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
            String query;
            String query1;
            String o_table=userinformation.getname();
            o_table=o_table.replaceAll("[^a-zA-Z0-9]","")+"_order";
            DatabaseConnection databaseConnection=new DatabaseConnection();
            Connection connection=databaseConnection.getconnection();
            try {
                Statement statement=connection.createStatement();
                if(i>0&&c_icecream.isSelected()){
                    query="insert into "+o_table+"(product,quantity,price,cafe)\n" +
                            "values('Ice Cream',"+i+","+i*50+",'Concordia 1');";
                    query1="insert into concordia1_order(u_name,order_from,product,quantity,price,phone,hostel) values('"+userinformation.getU_name()+"','"+userinformation.getname()+"','Ice Cream',"+i+","+i*50+",'"+userinformation.getPhone()+"','"+userinformation.getHostel()+"');";
                    statement.executeUpdate(query);
                    statement.executeUpdate(query1);
                    t_label.setText("");
                    order_success.setText("Ordered Successfully");
                }
                if(d>0&&c_donut.isSelected()){
                    query="insert into "+o_table+"(product,quantity,price,cafe)\n" +
                            "values('Donut',"+d+","+d*50+",'Concordia 1');";
                    query1="insert into concordia1_order(u_name,order_from,product,quantity,price,phone,hostel) values('"+userinformation.getU_name()+"','"+userinformation.getname()+"','Donut',"+d+","+d*50+",'"+userinformation.getPhone()+"','"+userinformation.getHostel()+"');";
                    statement.executeUpdate(query);
                    statement.executeUpdate(query1);
                    t_label.setText("");
                    order_success.setText("Ordered Successfully");
                }
                if(n>0&&c_Nestea.isSelected()){
                    query="insert into "+o_table+"(product,quantity,price,cafe)\n" +
                            "values('Nestea',"+n+","+n*50+",'Concordia 1');";
                    query1="insert into concordia1_order(u_name,order_from,product,quantity,price,phone,hostel) values('"+userinformation.getU_name()+"','"+userinformation.getname()+"','Nestea',"+n+","+n*50+",'"+userinformation.getPhone()+"','"+userinformation.getHostel()+"');";
                    statement.executeUpdate(query);
                    statement.executeUpdate(query1);
                    t_label.setText("");
                    order_success.setText("Ordered Successfully");
                }
                if(c>0&&c_Coffee.isSelected()){
                    query="insert into "+o_table+"(product,quantity,price,cafe)\n" +
                            "values('Coffee',"+c+","+c*50+",'Concordia 1');";
                    query1="insert into concordia1_order(u_name,order_from,product,quantity,price,phone,hostel) values('"+userinformation.getU_name()+"','"+userinformation.getname()+"','Coffee',"+c+","+c*50+",'"+userinformation.getPhone()+"','"+userinformation.getHostel()+"');";
                    statement.executeUpdate(query);
                    statement.executeUpdate(query1);
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

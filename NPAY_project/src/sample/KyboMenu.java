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

public class KyboMenu implements Initializable {
    @FXML
    ImageView kybo_image;
    @FXML
    Button back;
    @FXML
    Label p_count,j_count,t_count,s_count,t_label,not_label,order_success;
    @FXML
    CheckBox c_roll,c_jumbo,c_tikka,c_shake;
    int p=0,j=0,t=0,s=0,total;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File kyboImage=new File("src/Images/Kybo.jpg");
        Image kybo = new Image(kyboImage.toURI().toString());
        kybo_image.setImage(kybo);
    }
    public void s_decrement(ActionEvent actionEvent) {
        if(c_shake.isSelected()&&s>0){
            --s;
            s_count.setText(Integer.toString(s));
            total-=80;
            t_label.setText(Integer.toString(total)+" Rs.");
        }
    }

    public void t_decrement(ActionEvent actionEvent) {
        if(c_tikka.isSelected()&&t>0){
            --t;
            t_count.setText(Integer.toString(t));
            total-=150;
            t_label.setText(Integer.toString(total)+" Rs.");
        }
    }

    public void j_decrement(ActionEvent actionEvent) {
        if(c_jumbo.isSelected()&&j>0){
            --j;
            j_count.setText(Integer.toString(j));
            total-=280;
            t_label.setText(Integer.toString(total)+" Rs.");
        }
    }

    public void p_decrement(ActionEvent actionEvent) {
        if(c_roll.isSelected()&&p>0){
            --p;
            p_count.setText(Integer.toString(p));
            total-=100;
            t_label.setText(Integer.toString(total)+" Rs.");
        }
    }

    public void s_increment(ActionEvent actionEvent) {
        if(c_shake.isSelected()&&t<99){
            ++s;
            s_count.setText(Integer.toString(s));
            total+=80;
            t_label.setText(Integer.toString(total)+" Rs.");
        }
    }

    public void t_increment(ActionEvent actionEvent) {
        if(c_tikka.isSelected()&&t<99){
            ++t;
            t_count.setText(Integer.toString(t));
            total+=150;
            t_label.setText(Integer.toString(total)+" Rs.");
        }
    }

    public void j_increment(ActionEvent actionEvent) {
        if(c_jumbo.isSelected()&&j<99){
            ++j;
            j_count.setText(Integer.toString(j));
            total+=280;
            t_label.setText(Integer.toString(total)+" Rs.");
        }
    }

    public void p_increment(ActionEvent actionEvent) {
        if(c_roll.isSelected()&&p<99){
            ++p;
            p_count.setText(Integer.toString(p));
            total+=100;
            t_label.setText(Integer.toString(total)+" Rs.");
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
                if(p>0&&c_roll.isSelected()){
                    query="insert into "+o_table+"(product,quantity,price,cafe)\n" +
                            "values('Paratha Roll',"+p+","+p*100+",'kybo');";
                    query1="insert into kybo_order(u_name,order_from,product,quantity,price,phone,hostel) values('"+userinformation.getU_name()+"','"+userinformation.getname()+"','Paratha Roll',"+p+","+p*100+",'"+userinformation.getPhone()+"','"+userinformation.getHostel()+"');";
                    statement.executeUpdate(query);
                    statement.executeUpdate(query1);
                    t_label.setText("");
                    order_success.setText("Ordered Successfully");
                }
                if(j>0&&c_jumbo.isSelected()){
                    query="insert into "+o_table+"(product,quantity,price,cafe)\n" +
                            "values('Jumbo',"+j+","+j*280+",'kybo');";
                    query1="insert into kybo_order(u_name,order_from,product,quantity,price,phone,hostel) values('"+userinformation.getU_name()+"','"+userinformation.getname()+"','Jumbo',"+j+","+j*280+",'"+userinformation.getPhone()+"','"+userinformation.getHostel()+"');";
                    statement.executeUpdate(query);
                    statement.executeUpdate(query1);
                    t_label.setText("");
                    order_success.setText("Ordered Successfully");
                }
                if(t>0&&c_tikka.isSelected()){
                    query="insert into "+o_table+"(product,quantity,price,cafe)\n" +
                            "values('Tikka',"+t+","+t*150+",'kybo');";
                    query1="insert into kybo_order(u_name,order_from,product,quantity,price,phone,hostel) values('"+userinformation.getU_name()+"','"+userinformation.getname()+"','Tikka',"+t+","+t*150+",'"+userinformation.getPhone()+"','"+userinformation.getHostel()+"');";
                    statement.executeUpdate(query);
                    statement.executeUpdate(query1);
                    t_label.setText("");
                    order_success.setText("Ordered Successfully");
                }
                if(s>0&&c_shake.isSelected()){
                    query="insert into "+o_table+"(product,quantity,price,cafe)\n" +
                            "values('Shake',"+s+","+s*80+",'kybo');";
                    query1="insert into kybo_order(u_name,order_from,product,quantity,price,phone,hostel) values('"+userinformation.getU_name()+"','"+userinformation.getname()+"','Shake',"+s+","+s*80+",'"+userinformation.getPhone()+"','"+userinformation.getHostel()+"');";
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

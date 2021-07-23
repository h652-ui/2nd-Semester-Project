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

public class RetroMenu implements Initializable {
    @FXML
    ImageView retro_image;
    @FXML
    Button back;
    @FXML
    Label z_count,f_count,t_count,t_label,not_label,order_success;
    @FXML
    CheckBox c_zinger,c_fries,c_tea;
    int z=0,f=0,t=0,total=0;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File retroImage=new File("src/Images/Retro.jpg");
        Image retro = new Image(retroImage.toURI().toString());
        retro_image.setImage(retro);
    }

    public void z_decrement(ActionEvent actionEvent) {
        if(c_zinger.isSelected()&&z>0){
            --z;
            z_count.setText(Integer.toString(z));
            total-=200;
            t_label.setText(Integer.toString(total)+" Rs.");
        }
    }

    public void f_decrement(ActionEvent actionEvent) {
        if(c_fries.isSelected()&&f>0){
            --f;
            f_count.setText(Integer.toString(f));
            total-=100;
            t_label.setText(Integer.toString(total)+" Rs.");
        }
    }

    public void t_decrement(ActionEvent actionEvent) {
        if(c_tea.isSelected()&&t>0){
            --t;
            t_count.setText(Integer.toString(t));
            total-=40;
            t_label.setText(Integer.toString(total)+" Rs.");
        }
    }

    public void z_increment(ActionEvent actionEvent) {
        if(c_zinger.isSelected()&&z<99){
            ++z;
            z_count.setText(Integer.toString(z));
            total+=200;
            t_label.setText(Integer.toString(total)+" Rs.");
        }
    }

    public void f_increment(ActionEvent actionEvent) {
        if(c_fries.isSelected()&&f<99){
            ++f;
            f_count.setText(Integer.toString(f));
            total+=100;
            t_label.setText(Integer.toString(total)+" Rs.");
        }
    }

    public void t_increment(ActionEvent actionEvent) {
        if(c_tea.isSelected()&&t<99){
            ++t;
            t_count.setText(Integer.toString(t));
            total+=40;
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
                if(z>0&&c_zinger.isSelected()){
                    query="insert into "+o_table+"(product,quantity,price,cafe)\n" +
                            "values('Zinger',"+z+","+z*200+",'retro');";
                    query1="insert into retro_order(u_name,order_from,product,quantity,price,phone,hostel) values('"+userinformation.getU_name()+"','"+userinformation.getname()+"','Zinger',"+z+","+z*200+",'"+userinformation.getPhone()+"','"+userinformation.getHostel()+"');";
                    statement.executeUpdate(query);
                    statement.executeUpdate(query1);
                    t_label.setText("");
                    order_success.setText("Ordered Successfully");
                }
                if(f>0&&c_fries.isSelected()){
                    query="insert into "+o_table+"(product,quantity,price,cafe)\n" +
                            "values('Fries',"+f+","+f*100+",'retro');";
                    query1="insert into retro_order(u_name,order_from,product,quantity,price,phone,hostel) values('"+userinformation.getU_name()+"','"+userinformation.getname()+"','Fries',"+f+","+f*100+",'"+userinformation.getPhone()+"','"+userinformation.getHostel()+"');";
                    statement.executeUpdate(query);
                    statement.executeUpdate(query1);
                    t_label.setText("");
                    order_success.setText("Ordered Successfully");
                }
                if(t>0&&c_tea.isSelected()){
                    query="insert into "+o_table+"(product,quantity,price,cafe)\n" +
                            "values('Tea',"+t+","+t*40+",'retro');";
                    query1="insert into retro_order(u_name,order_from,product,quantity,price,phone,hostel) values('"+userinformation.getU_name()+"','"+userinformation.getname()+"','Tea',"+t+","+t*40+",'"+userinformation.getPhone()+"','"+userinformation.getHostel()+"');";
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

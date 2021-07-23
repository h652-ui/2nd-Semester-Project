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
import java.sql.Statement;
import java.util.ResourceBundle;

public class c3Menu implements Initializable {
    @FXML
    ImageView margalla_image;
    @FXML
    Button back;
    @FXML
    Label b_count,k_count,bu_count,c_count,t_label,not_label,order_success;
    @FXML
    CheckBox c_bbq,c_karahi,c_chips,c_burger;
    int bq=0,k=0,c=0,b=0,total=0;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File margallaImage=new File("src/Images/C3.png");
        Image margalla = new Image(margallaImage.toURI().toString());
        margalla_image.setImage(margalla);
    }

    public void bq_increment(ActionEvent actionEvent) {
        if(c_bbq.isSelected()&&bq<99){
            ++bq;
            b_count.setText(Integer.toString(bq));
            total+=100;
            t_label.setText(Integer.toString(total)+" Rs.");
        }
    }

    public void k_increment(ActionEvent actionEvent) {
        if(c_karahi.isSelected()&&c<99){
            ++k;
            k_count.setText(Integer.toString(k));
            total+=300;
            t_label.setText(Integer.toString(total)+" Rs.");
        }
    }

    public void c_increment(ActionEvent actionEvent) {
        if(c_chips.isSelected()&&c<99){
            ++c;
            c_count.setText(Integer.toString(c));
            total+=50;
            t_label.setText(Integer.toString(total)+" Rs.");
        }
    }

    public void b_increment(ActionEvent actionEvent) {
        if(c_burger.isSelected()&&b<99){
            ++b;
            bu_count.setText(Integer.toString(b));
            total+=150;
            t_label.setText(Integer.toString(total)+" Rs.");
        }
    }

    public void bq_decrement(ActionEvent actionEvent) {
        if(c_bbq.isSelected()&&bq>0){
            --bq;
            b_count.setText(Integer.toString(bq));
            total-=100;
            t_label.setText(Integer.toString(total)+" Rs.");
        }
    }

    public void k_decrement(ActionEvent actionEvent) {
        if(c_karahi.isSelected()&&k>0){
            --k;
            k_count.setText(Integer.toString(k));
            total-=300;
            t_label.setText(Integer.toString(total)+" Rs.");
        }
    }

    public void c_decrement(ActionEvent actionEvent) {
        if(c_chips.isSelected()&&c>0){
            --c;
            c_count.setText(Integer.toString(c));
            total-=50;
            t_label.setText(Integer.toString(total)+" Rs.");
        }
    }

    public void b_decrement(ActionEvent actionEvent) {
        if(c_burger.isSelected()&&b>0){
            --b;
            bu_count.setText(Integer.toString(b));
            total-=150;
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
                if(bq>0&&c_bbq.isSelected()){
                    query="insert into "+o_table+"(product,quantity,price,cafe)\n" +
                            "values('BBQ',"+bq+","+bq*100+",'Concordia 3');";
                    query1="insert into concordia3_order(u_name,order_from,product,quantity,price,phone,hostel) values('"+userinformation.getU_name()+"','"+userinformation.getname()+"','Barbeque',"+bq+","+bq*100+",'"+userinformation.getPhone()+"','"+userinformation.getHostel()+"');";
                    statement.executeUpdate(query);
                    statement.executeUpdate(query1);
                    t_label.setText("");
                    order_success.setText("Ordered Successfully");
                }
                if(b>0&&c_burger.isSelected()){
                    query="insert into "+o_table+"(product,quantity,price,cafe)\n" +
                            "values('Burger',"+b+","+b*150+",'Concordia 3');";
                    query1="insert into concordia3_order(u_name,order_from,product,quantity,price,phone,hostel) values('"+userinformation.getU_name()+"','"+userinformation.getname()+"','Burger',"+b+","+b*150+",'"+userinformation.getPhone()+"','"+userinformation.getHostel()+"');";
                    statement.executeUpdate(query);
                    statement.executeUpdate(query1);
                    t_label.setText("");
                    order_success.setText("Ordered Successfully");
                }
                if(k>0&&c_karahi.isSelected()){
                    query="insert into "+o_table+"(product,quantity,price,cafe)\n" +
                            "values('Karahi',"+k+","+k*300+",'Concordia 3');";
                    query1="insert into concordia3_order(u_name,order_from,product,quantity,price,phone,hostel) values('"+userinformation.getU_name()+"','"+userinformation.getname()+"','Karahi',"+k+","+k*300+",'"+userinformation.getPhone()+"','"+userinformation.getHostel()+"');";
                    statement.executeUpdate(query);
                    statement.executeUpdate(query1);
                    t_label.setText("");
                    order_success.setText("Ordered Successfully");
                }
                if(c>0&&c_chips.isSelected()){
                    query="insert into "+o_table+"(product,quantity,price,cafe)\n" +
                            "values('Chips',"+c+","+c*50+",'Concordia 3');";
                    query1="insert into concordia3_order(u_name,order_from,product,quantity,price,phone,hostel) values('"+userinformation.getU_name()+"','"+userinformation.getname()+"','Chips',"+c+","+c*50+",'"+userinformation.getPhone()+"','"+userinformation.getHostel()+"');";
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

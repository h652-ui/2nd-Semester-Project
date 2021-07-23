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

public class C2Menu implements Initializable {
    @FXML
    ImageView c2_image;
    @FXML
    Button back;
    @FXML
    Label b_count,s_count,p_count,w_count,t_label,order_success,not_label;
    @FXML
    CheckBox c_biryani,c_shawarma,c_pizza,c_water;
    int b=0,s=0,p=0,w=0,total;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File c2Image=new File("src/Images/C2(1).jpg");
        Image c2 = new Image(c2Image.toURI().toString());
        c2_image.setImage(c2);
    }

    public void b_decrement(ActionEvent actionEvent) {
        if(c_biryani.isSelected()&&b>0){
            --b;
            b_count.setText(Integer.toString(b));
            total -= 100;
            t_label.setText(Integer.toString(total) + " Rs.");
        }
    }

    public void b_increment(ActionEvent actionEvent) {
        if(c_biryani.isSelected()&&b<99){
            ++b;
            b_count.setText(Integer.toString(b));
            total += 100;
            t_label.setText(Integer.toString(total) + " Rs.");
        }
    }

    public void s_decrement(ActionEvent actionEvent) {
        if(c_shawarma.isSelected()&&s>0){
            --s;
            s_count.setText(Integer.toString(s));
            total -= 80;
            t_label.setText(Integer.toString(total) + " Rs.");
        }
    }

    public void s_increment(ActionEvent actionEvent) {
        if(c_shawarma.isSelected()&&s<99){
            ++s;
            s_count.setText(Integer.toString(s));
            total += 80;
            t_label.setText(Integer.toString(total) + " Rs.");
        }
    }

    public void p_decrement(ActionEvent actionEvent) {
        if(c_pizza.isSelected()&&p>0){
            --p;
            p_count.setText(Integer.toString(p));
            total -= 999;
            t_label.setText(Integer.toString(total) + " Rs.");
        }
    }

    public void p_increment(ActionEvent actionEvent) {
        if(c_pizza.isSelected()&&p<99){
            ++p;
            p_count.setText(Integer.toString(p));
            total += 999;
            t_label.setText(Integer.toString(total) + " Rs.");
        }
    }

    public void w_decrement(ActionEvent actionEvent) {
        if(c_water.isSelected()&&w>0){
            --w;
            w_count.setText(Integer.toString(w));
            total -= 20;
            t_label.setText(Integer.toString(total) + " Rs.");
        }
    }

    public void w_increment(ActionEvent actionEvent) {
        if(c_water.isSelected()&&w<99){
            ++w;
            w_count.setText(Integer.toString(w));
            total += 20;
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
            String query;
            String query1;
            String o_table=userinformation.getname();
            o_table=o_table.replaceAll("[^a-zA-Z0-9]","")+"_order";
            DatabaseConnection databaseConnection=new DatabaseConnection();
            Connection connection=databaseConnection.getconnection();
            try {
                Statement statement=connection.createStatement();
                if(b>0&&c_biryani.isSelected()){
                    query="insert into "+o_table+"(product,quantity,price,cafe)\n" +
                            "values('Biryani',"+b+","+b*100+",'Concordia 2');";
                    query1="insert into concordia2_order(u_name,order_from,product,quantity,price,phone,hostel) values('"+userinformation.getU_name()+"','"+userinformation.getname()+"','Biryani',"+b+","+b*100+",'"+userinformation.getPhone()+"','"+userinformation.getHostel()+"');";
                    statement.executeUpdate(query);
                    statement.executeUpdate(query1);
                    t_label.setText("");
                    order_success.setText("Ordered Successfully");
                }
                if(s>0&&c_shawarma.isSelected()){
                    query="insert into "+o_table+"(product,quantity,price,cafe)\n" +
                            "values('Shawarma',"+s+","+s*80+",'Concordia 2');";
                    query1="insert into concordia2_order(u_name,order_from,product,quantity,price,phone,hostel) values('"+userinformation.getU_name()+"','"+userinformation.getname()+"','Shawarma',"+s+","+s*80+",'"+userinformation.getPhone()+"','"+userinformation.getHostel()+"');";
                    statement.executeUpdate(query);
                    statement.executeUpdate(query1);
                    t_label.setText("");
                    order_success.setText("Ordered Successfully");
                }
                if(p>0&&c_pizza.isSelected()){
                    query="insert into "+o_table+"(product,quantity,price,cafe)\n" +
                            "values('Pizza',"+p+","+p*999+",'Concordia 2');";
                    query1="insert into concordia2_order(u_name,order_from,product,quantity,price,phone,hostel) values('"+userinformation.getU_name()+"','"+userinformation.getname()+"','Pizza',"+p+","+p*999+",'"+userinformation.getPhone()+"','"+userinformation.getHostel()+"');";
                    statement.executeUpdate(query);
                    statement.executeUpdate(query1);
                    t_label.setText("");
                    order_success.setText("Ordered Successfully");
                }
                if(w>0&&c_water.isSelected()){
                    query="insert into "+o_table+"(product,quantity,price,cafe)\n" +
                            "values('Water Bottle',"+w+","+w*20+",'Concordia 2');";
                    query1="insert into concordia2_order(u_name,order_from,product,quantity,price,phone,hostel) values('"+userinformation.getU_name()+"','"+userinformation.getname()+"','Water Bottle',"+w+","+w*20+",'"+userinformation.getPhone()+"','"+userinformation.getHostel()+"');";
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
}

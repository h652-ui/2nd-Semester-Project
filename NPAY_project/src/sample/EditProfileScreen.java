package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class EditProfileScreen implements Initializable {
    @FXML
    Button mainmenu;
    @FXML
    Label f_missing,l_missing,u_missing,p_missing,c_missing,username_success,password_success,name_success,hostel_success,name_already_use,password_already_use;
    @FXML
    TextField firstname,lastname,username,password,confirm;
    @FXML
    ChoiceBox<String> hostel;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        hostel.getItems().addAll("Ghazali 1","Ghazali 2","Attar 1","Attar 2","Razi 1","Razi 2","Zakriya");
        hostel.setValue("Ghazali 2");
    }
    public void back_to(ActionEvent actionEvent) throws IOException {
        Stage stage=(Stage)mainmenu.getScene().getWindow();
        stage.close();
        Parent root= FXMLLoader.load(getClass().getResource("studentwindow.fxml"));
        Stage s_stage = new Stage();
        s_stage.initStyle(StageStyle.UNDECORATED);
        s_stage.setScene(new Scene(root,800,620));
        s_stage.show();
    }

    public void n_edit(ActionEvent actionEvent) {
        f_missing.setText("");
        l_missing.setText("");
        u_missing.setText("");
        p_missing.setText("");
        c_missing.setText("");
        username_success.setText("");
        password_success.setText("");
        hostel_success.setText("");
        name_already_use.setText("");
        password_already_use.setText("");
        if(firstname.getText().equals("")){
            f_missing.setText("Missing");
        }
        else if(lastname.getText().equals("")){
            l_missing.setText("Missing");
        }
        else{
            String inserter = "update student set firstname='"+firstname.getText()+"', lastname='"+lastname.getText()+"' where Student_ID in (SELECT User_ID from login where username = '"+userinformation.getU_name()+"')";
            DatabaseConnection databaseConnection=new DatabaseConnection();
            Connection connection=databaseConnection.getconnection();
            try{
                Statement statement1=connection.createStatement();
                statement1.executeUpdate(inserter);
                name_success.setText("Edit Successfully");
                firstname.setText("");
                lastname.setText("");
            }catch (Exception e){
                e.printStackTrace();
                e.getCause();
            }
        }
    }

    public void u_edit(ActionEvent actionEvent) {
        f_missing.setText("");
        l_missing.setText("");
        u_missing.setText("");
        p_missing.setText("");
        c_missing.setText("");
        username_success.setText("");
        password_success.setText("");
        hostel_success.setText("");
        name_already_use.setText("");
        password_already_use.setText("");
        name_success.setText("");
        if(username.getText().equals("")){
            u_missing.setText("Missing");
        }
        else{
            boolean error=false;
            String searcher="select * from login";
            DatabaseConnection s_databaseConnection=new DatabaseConnection();
            Connection s_connection = s_databaseConnection.getconnection();
            try{
                Statement search_statement=s_connection.createStatement();
                ResultSet resultSet=search_statement.executeQuery(searcher);
                while(resultSet.next()){
                    if(username.getText().equals(resultSet.getString("username"))){
                        error=true;
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
                e.getCause();
            }
            if(error){
                name_already_use.setText("Already in use...");
            }
            else {
                String inserter = "update login set username='" + username.getText() + "' where User_ID = "+Integer.parseInt(userinformation.getU_id())+"";
                System.out.println(inserter);
                DatabaseConnection databaseConnection = new DatabaseConnection();
                Connection connection = databaseConnection.getconnection();
                try {
                    Statement statement1 = connection.createStatement();
                    statement1.executeUpdate(inserter);
                    username_success.setText("Edit Successfully");
                    userinformation.setU_name(username.getText());
                    username.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                    e.getCause();
                }
            }
        }
    }

    public void p_edit(ActionEvent actionEvent) {
        f_missing.setText("");
        l_missing.setText("");
        u_missing.setText("");
        p_missing.setText("");
        c_missing.setText("");
        username_success.setText("");
        password_success.setText("");
        hostel_success.setText("");
        name_success.setText("");
        name_already_use.setText("");
        password_already_use.setText("");
        if(password.getText().equals("")){
            p_missing.setText("Missing");
        }
        else if(confirm.getText().equals("")){
            c_missing.setText("Missing");
        }
        else if(!password.getText().equals("")&&!confirm.getText().equals("")&&!password.getText().equals(confirm.getText())){
            password_already_use.setText("Not Matching...");
        }
        else{
            boolean error=false;
            String searcher="select * from login";
            DatabaseConnection s_databaseConnection=new DatabaseConnection();
            Connection s_connection = s_databaseConnection.getconnection();
            try{
                Statement search_statement=s_connection.createStatement();
                ResultSet resultSet=search_statement.executeQuery(searcher);
                while(resultSet.next()){
                    if(password.getText().equals(resultSet.getString("pasword"))){
                        error=true;
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
                e.getCause();
            }
            if(error){
                password_already_use.setText("Already in use...");
            }
            else {
                String inserter = "update login set pasword=md5('" + password.getText() + "') where username='" + userinformation.getU_name() + "'";
                DatabaseConnection databaseConnection = new DatabaseConnection();
                Connection connection = databaseConnection.getconnection();
                try {
                    Statement statement1 = connection.createStatement();
                    statement1.executeUpdate(inserter);
                    password_success.setText("Edit Successfully");
                    userinformation.setPassword(password.getText());
                    password.setText("");
                    confirm.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                    e.getCause();
                }
            }
        }
    }

    public void h_edit(ActionEvent actionEvent) {
        f_missing.setText("");
        l_missing.setText("");
        u_missing.setText("");
        p_missing.setText("");
        c_missing.setText("");
        name_success.setText("");
        username_success.setText("");
        password_success.setText("");
        hostel_success.setText("");
        name_already_use.setText("");
        password_already_use.setText("");
        String inserter = "update Student set hostel='" + hostel.getValue() + "' where Student_ID in (SELECT User_ID from login where username = '"+userinformation.getU_name()+"')";
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getconnection();
        try {
            Statement statement1 = connection.createStatement();
            statement1.executeUpdate(inserter);
            hostel_success.setText("Edit Successfully");
            hostel.setValue("Ghazali 2");
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}

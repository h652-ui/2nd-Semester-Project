package sample;

import com.mysql.cj.protocol.Resultset;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class registrationcontroller implements Initializable{
    @FXML
    TextField firstname;
    @FXML
    TextField lastname;
    @FXML
    TextField username;
    @FXML
    TextField password,phone_number;
    @FXML
    TextField cpassword;
    @FXML
    Label repeatusername;
    @FXML
    Label repeatpassword;
    @FXML
    Label notmatch,invalid_phone;
    @FXML
    Label registrationlabel;
    @FXML
    Label sregisterlabel;
    @FXML
    Label fmissing;
    @FXML
    Label lmissing;
    @FXML
    Label umissing;
    @FXML
    Label pmissing;
    @FXML
    Label cmissing;
    @FXML
    ChoiceBox<String> hostelname;
    @FXML
    ImageView shieldid;
    @FXML
    Button backto;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File sfile=new File("src/Images/shield.png");
        Image shieldimage=new Image(sfile.toURI().toString());
        shieldid.setImage(shieldimage);
        hostelname.getItems().addAll("Ghazali 1","Ghazali 2","Attar 1","Attar 2","Razi 1","Razi 2","Zakriya");
        hostelname.setValue("Ghazali 2");
    }
    public void backtobuttonaction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) backto.getScene().getWindow();
        stage.close();
        Parent root= FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage lstage = new Stage();
        lstage.initStyle(StageStyle.UNDECORATED);
        lstage.setScene(new Scene(root,676,400));
        lstage.show();
    }

    public void registerbuttonaction(ActionEvent actionEvent) {
        if(checkanomalies()){
            sregisterlabel.setText("");
            registrationlabel.setText("User is not registered, Try Again");
        }
        else{
            String firstName=firstname.getText();
            String lastName=lastname.getText();
            String u_Name=username.getText();
            String pass = password.getText();
            String h_Name=hostelname.getValue();
            String p_number=phone_number.getText();
            String User_ID = "";
            String s_inserter = "";
            String inserter="INSERT INTO login(username,pasword, User_Status) values('"+u_Name+"',md5('"+pass+"'),'student')";
            String searcher = "SELECT USER_ID from login WHERE Username = '"+u_Name+"'";
            DatabaseConnection databaseConnection=new DatabaseConnection();
            Connection connection=databaseConnection.getconnection();
            try{
                Statement statement=connection.createStatement();
                statement.executeUpdate(inserter);
                ResultSet resultSet = statement.executeQuery(searcher);
                while(resultSet.next()){
                    User_ID = resultSet.getString("User_ID");
                }
                s_inserter = "INSERT INTO student VALUES("+Integer.parseInt(User_ID)+",'"+firstName+"','"+lastName+"', '"+h_Name+"','"+p_number+"')";
                statement.executeUpdate(s_inserter);
            }catch (Exception e){
                e.printStackTrace();
                e.getCause();
            }
            registrationlabel.setText("");
            sregisterlabel.setText("User is registered successfully!");
        }
    }

    public boolean checkanomalies(){
        boolean error=false;
        if(firstname.getText().equals("")){
            fmissing.setText("Missing");
            error = true;
        }else{fmissing.setText("");}
        if(lastname.getText().equals("")){
            lmissing.setText("Missing");
            error = true;
        } else{lmissing.setText("");}
        if(username.getText().equals("")){
            repeatusername.setText("");
            umissing.setText("Missing");
            error = true;
        }else{umissing.setText("");}
        if(password.getText().equals("")){
            repeatpassword.setText("");
            pmissing.setText("Missing");
            error = true;
        }else{pmissing.setText("");}
        if(cpassword.getText().equals("")){
            notmatch.setText("");
            cmissing.setText("Missing");
            error = true;
        }else{cmissing.setText("");}
        if(!password.getText().equals(cpassword.getText())&&!cpassword.getText().equals("")) {
            notmatch.setText("Password not matched");
            error = true;
        }else{notmatch.setText("");}
        if(phone_number.getText().equals("")){
            invalid_phone.setText("Missing");
            error=true;
        }else{invalid_phone.setText("");}
        if(!phone_number.getText().equals("")&&phone_number.getText().length()!=11){
            String new_number=phone_number.getText().replaceAll("^0-9","");
            if(new_number.length()!=11)
                invalid_phone.setText("Invalid Phone Number");
            error=true;
        }else{invalid_phone.setText("");}
        String query="SELECT * FROM login";
        DatabaseConnection dbconnect= new DatabaseConnection();
        Connection gconnection = dbconnect.getconnection();
        try{
            Statement statement = gconnection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                if(username.getText().equals(resultSet.getString("username"))){
                    repeatusername.setText("Already in Used");
                    error = true;
                }else{repeatusername.setText("");}
                if(password.getText().equals(resultSet.getString("pasword"))) {
                    repeatpassword.setText("Already in Used");
                    error = true;
                }else{repeatpassword.setText("");}

            }
        }catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return error;
    }
}

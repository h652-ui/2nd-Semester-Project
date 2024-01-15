package sample;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class logincontroller implements Initializable{
    @FXML
    AnchorPane scene2;
    @FXML
    ImageView profile,logo,logo1;
    @FXML
    ImageView lock;
    @FXML
    Button loginbutton;
    @FXML
    private Button cancelbutton;
    @FXML
    private Button registerbutton;
    @FXML
    public Label logInMessage;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @Override
    public void initialize(URL url,ResourceBundle resourceBundle){

        File profileimage=new File("src/Images/Profile.jpg");
        Image icon = new Image(profileimage.toURI().toString());
        profile.setImage(icon);

        File lockimage=new File("src/Images/Lock.jpg");
        Image l_icon = new Image(lockimage.toURI().toString());
        lock.setImage(l_icon);

        File logoimage=new File("src/Images/logo.jpg");
        Image logo_icon=new Image(logoimage.toURI().toString());
        logo.setImage(logo_icon);

        File logoimage1=new File("src/Images/logo1.jpg");
        Image logo_icon1=new Image(logoimage1.toURI().toString());
        logo1.setImage(logo_icon1);
    }
    public void loginbuttonaction(ActionEvent actionEvent) throws IOException {
        if(!username.getText().isBlank() && !password.getText().isBlank()){
            if(username.getText().equals("admin")&&password.getText().equals("admin")){
                Stage stage = (Stage) loginbutton.getScene().getWindow();
                stage.close();
                Parent register = FXMLLoader.load(getClass().getResource("admin_window.fxml"));
                Stage rstage = new Stage();
                rstage.initStyle(StageStyle.UNDECORATED);
                rstage.setScene(new Scene(register, 513, 393));
                rstage.show();
            }else{
                validateInput();
            }
        }
        else{
            logInMessage.setText("Please Enter Username and Password");
        }
    }

    public void cancelbuttonaction(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelbutton.getScene().getWindow();
        stage.close();
    }

    public void validateInput(){
        DatabaseConnection dbconnect = new DatabaseConnection();
        Connection myconnect = dbconnect.getconnection();
        String verifylogIn = "SELECT count(*) FROM login WHERE username='"+username.getText()+"'AND pasword=md5('"+password.getText()+"')";
        String verifylogIn1 = "SELECT * FROM login WHERE username='"+username.getText()+"'AND pasword=md5('"+password.getText()+"')";
        userinformation.setU_name(username.getText());
        userinformation.setPassword(password.getText());
        try {
            Statement statement= myconnect.createStatement();
            ResultSet resultSet = statement.executeQuery(verifylogIn);
            while(resultSet.next()){
                if(resultSet.getInt(1)==0) {
                    logInMessage.setText("Invalid Login! Try Again...");
                }else{
                    try{
                        logInMessage.setText("");
                        Statement statement1=myconnect.createStatement();
                        ResultSet resultquery=statement1.executeQuery(verifylogIn1);
                        while(resultquery.next()){
                            Stage stage = (Stage) loginbutton.getScene().getWindow();
                            stage.close();
                            if (resultquery.getString("User_Status").equals("Resturant")) {
                                Parent register = FXMLLoader.load(getClass().getResource("resturant_window.fxml"));
                                Stage rstage = new Stage();
                                rstage.initStyle(StageStyle.UNDECORATED);
                                rstage.setScene(new Scene(register, 667, 450));
                                rstage.show();
                            } else if (resultquery.getString("User_Status").equals("student")) {
                                userinformation.setU_id(resultquery.getString("User_ID"));
                                Parent register = FXMLLoader.load(getClass().getResource("studentwindow.fxml"));
                                Stage rstage = new Stage();
                                rstage.initStyle(StageStyle.UNDECORATED);
                                rstage.setScene(new Scene(register, 800, 620));
                                rstage.show();
                            }
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                        e.getCause();
                    }
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    public void registerbuttonaction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)registerbutton.getScene().getWindow();
        stage.close();
        Parent register= FXMLLoader.load(getClass().getResource("registration.fxml"));
        Stage rstage = new Stage();
        rstage.initStyle(StageStyle.UNDECORATED);
        rstage.setScene(new Scene(register,580,689));
        rstage.show();
    }
}

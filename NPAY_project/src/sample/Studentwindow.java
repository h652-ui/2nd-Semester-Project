package sample;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Studentwindow implements Initializable {
    @FXML
    Button logoutbutton,c1,c2,kybo,c3,retro;
    @FXML
    Label namelabel,usernameid,hostelnameid,balanceid;
    @FXML
    JFXHamburger hamburger;
    @FXML
    JFXDrawer drawer;
    @FXML
    ImageView profile_image,c1_image,c2_image,retro_image,kybo_image,margalla_image;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        try {
            VBox vBox= FXMLLoader.load(getClass().getResource("slidepane1.fxml"));
            drawer.setSidePane(vBox);
        }catch(IOException ex){
            Logger.getLogger(Studentwindow.class.getName()).log(Level.SEVERE,null,ex);
        }
        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
        drawer.open();
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED,e->{
            transition.setRate(transition.getRate()*-1);
            transition.play();

            if(drawer.isOpened()){
                drawer.close();
            }
            else
                drawer.open();
        });
        File profileImage=new File("src/Images/profile.png");
        Image profile = new Image(profileImage.toURI().toString());
        profile_image.setImage(profile);
        File c1Image=new File("src/Images/C1.png");
        Image c1 = new Image(c1Image.toURI().toString());
        c1_image.setImage(c1);
        File c2Image=new File("src/Images/C2.png");
        Image c2 = new Image(c2Image.toURI().toString());
        c2_image.setImage(c2);
        File retroImage=new File("src/Images/Retro.jpg");
        Image retro = new Image(retroImage.toURI().toString());
        retro_image.setImage(retro);
        File kyboImage=new File("src/Images/Kybo.jpg");
        Image kybo = new Image(kyboImage.toURI().toString());
        kybo_image.setImage(kybo);
        File margallaImage=new File("src/Images/C3.png");
        Image margalla = new Image(margallaImage.toURI().toString());
        margalla_image.setImage(margalla);
        DatabaseConnection dbconnect = new DatabaseConnection();
        Connection myconnect = dbconnect.getconnection();
        String verifylogIn = "select Username, FirstName, LastName, Wallet, Hostel, Phone_Number from student s, (select * from login where username='"+userinformation.getU_name()+"' and User_ID = "+Integer.parseInt(userinformation.getU_id())+") a where s.student_ID=a.User_ID";
        try{
            Statement statement = myconnect.createStatement();
            ResultSet resultSet = statement.executeQuery(verifylogIn);
            while (resultSet.next()){
                namelabel.setText(resultSet.getString("firstname")+" "+resultSet.getString("lastname"));
                usernameid.setText(resultSet.getString("username"));
                hostelnameid.setText(resultSet.getString("hostel"));
                balanceid.setText(resultSet.getString("wallet"));
                userinformation.setWallet(Integer.parseInt(resultSet.getString("wallet")));
                userinformation.setName(resultSet.getString("firstname"));
                userinformation.setHostel(resultSet.getString("hostel"));
                userinformation.setPhone(resultSet.getString("phone_number"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void logoutaction(ActionEvent actionEvent) throws IOException {
        Stage stage=(Stage)logoutbutton.getScene().getWindow();
        stage.close();
        Parent root= FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage lstage = new Stage();
        lstage.initStyle(StageStyle.UNDECORATED);
        lstage.setScene(new Scene(root,676,400));
        lstage.show();
    }

    public void c1_action(ActionEvent actionEvent) throws IOException {
        Stage stage=(Stage)c1.getScene().getWindow();
        stage.close();
        Parent root= FXMLLoader.load(getClass().getResource("c1Menu.fxml"));
        Stage c1stage = new Stage();
        c1stage.initStyle(StageStyle.UNDECORATED);
        c1stage.setScene(new Scene(root,600,381));
        c1stage.show();
    }

    public void c2_action(ActionEvent actionEvent) throws IOException {
        Stage stage=(Stage)c2.getScene().getWindow();
        stage.close();
        Parent root= FXMLLoader.load(getClass().getResource("c2Menu.fxml"));
        Stage c2stage = new Stage();
        c2stage.initStyle(StageStyle.UNDECORATED);
        c2stage.setScene(new Scene(root,526,408));
        c2stage.show();
    }

    public void r_action(ActionEvent actionEvent) throws IOException {
        Stage stage=(Stage)retro.getScene().getWindow();
        stage.close();
        Parent root= FXMLLoader.load(getClass().getResource("RetroMenu.fxml"));
        Stage retrostage = new Stage();
        retrostage.initStyle(StageStyle.UNDECORATED);
        retrostage.setScene(new Scene(root,601,350));
        retrostage.show();
    }

    public void k_action(ActionEvent actionEvent) throws IOException {
        Stage stage=(Stage)kybo.getScene().getWindow();
        stage.close();
        Parent root= FXMLLoader.load(getClass().getResource("kyboMenu.fxml"));
        Stage kybostage = new Stage();
        kybostage.initStyle(StageStyle.UNDECORATED);
        kybostage.setScene(new Scene(root,600,405));
        kybostage.show();
    }

    public void c3_action(ActionEvent actionEvent) throws IOException {
        Stage stage=(Stage)c3.getScene().getWindow();
        stage.close();
        Parent root= FXMLLoader.load(getClass().getResource("c3Menu.fxml"));
        Stage c3stage = new Stage();
        c3stage.initStyle(StageStyle.UNDECORATED);
        c3stage.setScene(new Scene(root,600,405));
        c3stage.show();
    }
}

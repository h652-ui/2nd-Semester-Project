package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AdminWindow {

    @FXML
    Button logout,update,users,withdraw;
    @FXML
    TextField username,balance;
    @FXML
    Label update_success,no_info;

    public void logout_action(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) logout.getScene().getWindow();
        stage.close();
        Parent root= FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage lstage = new Stage();
        lstage.initStyle(StageStyle.UNDECORATED);
        lstage.setScene(new Scene(root,676,400));
        lstage.show();
    }

    public void users_action(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) update.getScene().getWindow();
        stage.close();
        Parent root= FXMLLoader.load(getClass().getResource("user_table.fxml"));
        Stage ustage = new Stage();
        ustage.initStyle(StageStyle.UNDECORATED);
        ustage.setScene(new Scene(root,600,400));
        ustage.show();
    }

    public void update_action(ActionEvent actionEvent) {
        if(username.getText().equals("")||balance.getText().equals("")){
            update_success.setText("");
            no_info.setText("Insufficient Details!");
        }else{
            int actual_balance=0;
            String get_username=username.getText();
            int get_amount=Integer.parseInt(balance.getText());
            DatabaseConnection databaseConnection=new DatabaseConnection();
            Connection connection=databaseConnection.getconnection();
            String query="SELECT * FROM login where username='"+get_username+"'";
            String search="SELECT count(*) FROM login where username='"+get_username+"'";
            try{
                Statement search_statement=connection.createStatement();
                ResultSet resultSet=search_statement.executeQuery(search);
                while(resultSet.next()){
                    if(resultSet.getInt(1)==1){
                        try{
                            Statement statement = connection.createStatement();
                            ResultSet resultSet1=statement.executeQuery(query);
                            while(resultSet1.next()){
                                actual_balance=Integer.parseInt(resultSet1.getString("wallet"));
                                actual_balance+=get_amount;
                            }
                        }catch(Exception e){
                            e.getCause();
                            e.printStackTrace();
                        }
                        String query2="Update login set wallet="+actual_balance+" where username='"+get_username+"'";
                        try{
                            Statement statement=connection.createStatement();
                            statement.executeUpdate(query2);
                            no_info.setText("");
                            update_success.setText("Balance Updated!");
                        }catch (Exception e){
                            e.printStackTrace();
                            e.getCause();
                        }
                    }else{
                        no_info.setText("");
                        no_info.setText("Invalid Username");
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
                e.getCause();
            }
        }
    }

    public void withdraw_action(ActionEvent actionEvent) {
        if(username.getText().equals("")||balance.getText().equals("")){
            update_success.setText("");
            no_info.setText("Insufficient Details!");
        }else{
            int actual_balance=0;
            String get_username=username.getText();
            int get_amount=Integer.parseInt(balance.getText());
            DatabaseConnection databaseConnection=new DatabaseConnection();
            Connection connection=databaseConnection.getconnection();
            String query="SELECT * FROM login where username='"+get_username+"'";
            String search="SELECT count(*) FROM login where username='"+get_username+"'";
            try{
                Statement search_statement=connection.createStatement();
                ResultSet resultSet=search_statement.executeQuery(search);
                while(resultSet.next()){
                    if(resultSet.getInt(1)==1){
                        try{
                            Statement statement = connection.createStatement();
                            ResultSet resultSet1=statement.executeQuery(query);
                            while(resultSet1.next()){
                                actual_balance=Integer.parseInt(resultSet1.getString("wallet"));
                                actual_balance-=get_amount;
                            }
                        }catch(Exception e){
                            e.getCause();
                            e.printStackTrace();
                        }
                        if(actual_balance>0){
                            String query2="Update login set wallet="+actual_balance+" where username='"+get_username+"'";
                            try{
                                Statement statement=connection.createStatement();
                                statement.executeUpdate(query2);
                                no_info.setText("");
                                update_success.setText("Withdraw Successfully!");
                            }catch (Exception e){
                                e.printStackTrace();
                                e.getCause();
                            }
                        }else{
                            update_success.setText("");
                            no_info.setText("");
                            no_info.setText("Not Enough Money!");
                        }
                    }else{
                        no_info.setText("");
                        no_info.setText("Invalid Username");
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
                e.getCause();
            }
        }
    }

    public void reset_action(ActionEvent actionEvent) {
        if(username.getText().equals("")){
            update_success.setText("");
            no_info.setText("Username Missing!");
        }else{
            String get_username=username.getText();
            DatabaseConnection databaseConnection=new DatabaseConnection();
            Connection connection=databaseConnection.getconnection();
            String query="Update login set pasword = md5('NUSTUNI') where username='"+get_username+"'";
            try {
                Statement statement = connection.createStatement();
                statement.executeUpdate(query);
                no_info.setText("");
                update_success.setText("Reset Successfully");
            }catch (Exception e){
                e.printStackTrace();
                e.getCause();
            }
        }
    }
}

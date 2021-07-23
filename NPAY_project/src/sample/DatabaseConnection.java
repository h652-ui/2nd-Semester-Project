package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseConnection {
    public Connection databaselink;

    public Connection getconnection() {
        String databasename = "login";
        String databaseuser = "root";
        String databasepassword = "admin";
        String url = "jdbc:mysql://localhost:3306/" + databasename;
        try {
            databaselink = DriverManager.getConnection(url, databaseuser, databasepassword);
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return databaselink;
    }

    public static ObservableList<order_list> getlist() {
        ObservableList<order_list> list = FXCollections.observableArrayList();
        Connection databaselink1;
        String databasename = "login";
        String databaseuser = "root";
        String databasepassword = "admin";
        String url = "jdbc:mysql://localhost:3306/" + databasename;
        try {
            databaselink1 = DriverManager.getConnection(url, databaseuser, databasepassword);
            Statement statement = databaselink1.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + userinformation.getO_table());
            while (resultSet.next()) {
                list.addAll(new order_list(resultSet.getString("product"), resultSet.getString("cafe"), Integer.parseInt(resultSet.getString("quantity")), Integer.parseInt(resultSet.getString("price"))));
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return list;
    }

    public static ObservableList<trans_list> gettransaction() {
        ObservableList<trans_list> list = FXCollections.observableArrayList();
        Connection databaselink1;
        String databasename = "login";
        String databaseuser = "root";
        String databasepassword = "admin";
        String url = "jdbc:mysql://localhost:3306/" + databasename;
        try {
            databaselink1 = DriverManager.getConnection(url, databaseuser, databasepassword);
            Statement statement = databaselink1.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + userinformation.getT_table());
            while (resultSet.next()) {
                list.addAll(new trans_list(resultSet.getString("product"), resultSet.getString("cafe"), resultSet.getString("o_status"), Integer.parseInt(resultSet.getString("price")), Integer.parseInt(resultSet.getString("quantity"))));
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return list;
    }


    public static ObservableList<resturant_order_list> get_resturant_order() {
        ObservableList<resturant_order_list> list = FXCollections.observableArrayList();
        Connection databaselink1;
        String databasename = "login";
        String databaseuser = "root";
        String databasepassword = "admin";
        String url = "jdbc:mysql://localhost:3306/" + databasename;
        try {
            databaselink1 = DriverManager.getConnection(url, databaseuser, databasepassword);
            Statement statement = databaselink1.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + userinformation.getO_table());
            while (resultSet.next()) {
                list.addAll(new resturant_order_list(resultSet.getString("u_name"), resultSet.getString("order_from"), resultSet.getString("hostel"), resultSet.getString("product"), resultSet.getString("phone"), Integer.parseInt(resultSet.getString("price")), Integer.parseInt(resultSet.getString("quantity"))));
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return list;
    }

    public static ObservableList<resturant_trans_list> get_resturant_trans() {
        ObservableList<resturant_trans_list> list = FXCollections.observableArrayList();
        Connection databaselink1;
        String databasename = "login";
        String databaseuser = "root";
        String databasepassword = "admin";
        String url = "jdbc:mysql://localhost:3306/" + databasename;
        try {
            databaselink1 = DriverManager.getConnection(url, databaseuser, databasepassword);
            Statement statement = databaselink1.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + userinformation.getT_table());
            while (resultSet.next()) {
                list.addAll(new resturant_trans_list(resultSet.getString("u_name"), resultSet.getString("product"), resultSet.getString("hostel"), resultSet.getString("phone"), Integer.parseInt(resultSet.getString("price")), Integer.parseInt(resultSet.getString("quantity"))));
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return list;
    }

    public static ObservableList<user_list> get_users(){
        ObservableList<user_list> list = FXCollections.observableArrayList();
        Connection databaselink1;
        String databasename = "login";
        String databaseuser = "root";
        String databasepassword = "admin";
        String url = "jdbc:mysql://localhost:3306/" + databasename;
        try {
            databaselink1 = DriverManager.getConnection(url, databaseuser, databasepassword);
            Statement statement = databaselink1.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM login");
            while (resultSet.next()) {
                list.addAll(new user_list(resultSet.getString("username"), resultSet.getString("pasword"), resultSet.getString("u_status"), Integer.parseInt(resultSet.getString("wallet"))));
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return list;
    }
}
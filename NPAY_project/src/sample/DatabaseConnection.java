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
        String databasename = "npay";
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
        String databasename = "npay";
        String databaseuser = "root";
        String databasepassword = "admin";
        String url = "jdbc:mysql://localhost:3306/" + databasename;
        try {
            databaselink1 = DriverManager.getConnection(url, databaseuser, databasepassword);
            Statement statement = databaselink1.createStatement();
            ResultSet resultSet = statement.executeQuery("select Order_ID, CafeName, Product, Quantity, Price, Order_Time from orders, cafe, Student, login where orders.Cafe_ID = cafe.Cafe_ID and orders.Student_ID=Student.Student_ID and login.User_ID=Student.Student_ID and login.username = '"+userinformation.getU_name()+"' and Order_Status='Pending'");
            while (resultSet.next()) {
                list.addAll(new order_list(resultSet.getString("product"), resultSet.getString("CafeName"),  resultSet.getString("Order_Time"), Integer.parseInt(resultSet.getString("quantity")), Integer.parseInt(resultSet.getString("price")), resultSet.getString("Order_ID")));
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
        String databasename = "npay";
        String databaseuser = "root";
        String databasepassword = "admin";
        String url = "jdbc:mysql://localhost:3306/" + databasename;
        try {
            databaselink1 = DriverManager.getConnection(url, databaseuser, databasepassword);
            Statement statement = databaselink1.createStatement();
            ResultSet resultSet = statement.executeQuery("select Order_ID, CafeName, Product, Quantity, Price, Order_Time, Order_Status from orders, cafe, Student, login where orders.Cafe_ID = cafe.Cafe_ID and orders.Student_ID=Student.Student_ID and login.User_ID=Student.Student_ID and login.username = '"+userinformation.getU_name()+"' and Order_Status != 'Pending'");
            while (resultSet.next()) {
                list.addAll(new trans_list(resultSet.getString("Order_ID"), resultSet.getString("product"), resultSet.getString("CafeName"), resultSet.getString("Order_Status"), Integer.parseInt(resultSet.getString("price")), Integer.parseInt(resultSet.getString("quantity"))));
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
        String databasename = "npay";
        String databaseuser = "root";
        String databasepassword = "admin";
        String url = "jdbc:mysql://localhost:3306/" + databasename;
        try {
            databaselink1 = DriverManager.getConnection(url, databaseuser, databasepassword);
            Statement statement = databaselink1.createStatement();
            ResultSet resultSet = statement.executeQuery("select Order_ID, username, firstname, lastname, Hostel, Product, Quantity, Price, Student.Phone_Number from orders, Student, login, Cafe where orders.Student_ID=Student.Student_ID and login.User_ID=Student.Student_ID and Order_Status = 'Pending' and CafeName = '"+userinformation.getname()+"' and cafe.Cafe_ID = orders.Cafe_ID");
            while (resultSet.next()) {
                list.addAll(new resturant_order_list(resultSet.getString("Order_ID"), resultSet.getString("username"), resultSet.getString("firstname")+" "+resultSet.getString("lastname"), resultSet.getString("hostel"), resultSet.getString("product"), resultSet.getString("phone_number"), Integer.parseInt(resultSet.getString("price")), Integer.parseInt(resultSet.getString("quantity"))));
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
        String databasename = "npay";
        String databaseuser = "root";
        String databasepassword = "admin";
        String url = "jdbc:mysql://localhost:3306/" + databasename;
        try {
            databaselink1 = DriverManager.getConnection(url, databaseuser, databasepassword);
            Statement statement = databaselink1.createStatement();
            ResultSet resultSet = statement.executeQuery("select Order_ID, username, Student.phone_number, hostel, Product, Quantity, Price, Order_Time from orders, Student, login, cafe where orders.Student_ID=Student.Student_ID and login.User_ID=Student.Student_ID and cafe.Cafe_ID=orders.Cafe_ID and cafe.CafeName = '"+userinformation.getname()+"' and Order_Status = 'Completed'");
            while (resultSet.next()) {
                list.addAll(new resturant_trans_list(resultSet.getString("Order_ID"), resultSet.getString("username"), resultSet.getString("product"), resultSet.getString("hostel"), resultSet.getString("phone_number"), Integer.parseInt(resultSet.getString("price")), Integer.parseInt(resultSet.getString("quantity"))));
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
        String databasename = "npay";
        String databaseuser = "root";
        String databasepassword = "admin";
        String url = "jdbc:mysql://localhost:3306/" + databasename;
        try {
            databaselink1 = DriverManager.getConnection(url, databaseuser, databasepassword);
            Statement statement = databaselink1.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM npay.student_user");
            while (resultSet.next()) {
                list.addAll(new user_list(resultSet.getString("username"), resultSet.getString("pasword"), resultSet.getString("User_Status"), Integer.parseInt(resultSet.getString("wallet"))));
            }
            resultSet = statement.executeQuery("SELECT * FROM npay.cafe_user");
            while (resultSet.next()) {
                list.addAll(new user_list(resultSet.getString("username"), resultSet.getString("pasword"), resultSet.getString("User_Status"), Integer.parseInt(resultSet.getString("wallet"))));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return list;
    }

    public static ObservableList<review_list> get_reviews(){
        ObservableList<review_list> list = FXCollections.observableArrayList();
        Connection databaselink1;
        String databasename = "npay";
        String databaseuser = "root";
        String databasepassword = "admin";
        String url = "jdbc:mysql://localhost:3306/" + databasename;
        try {
            databaselink1 = DriverManager.getConnection(url, databaseuser, databasepassword);
            Statement statement = databaselink1.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT firstname, lastname, p_name, CafeName, stars, review_date from login, cafe, product, student, review where cafe.Cafe_ID = review.Cafe_ID and student.Student_ID = review.Consumer_ID and product.Product_ID = review.Product_ID and login.User_ID = student.Student_ID and login.username != '"+userinformation.getU_name()+"'");
            while (resultSet.next()) {
                list.addAll(new review_list(resultSet.getString("firstname")+" "+resultSet.getString("lastname"),resultSet.getString("P_Name"),resultSet.getString("CafeName"),resultSet.getString("stars"), resultSet.getString("review_date")));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return list;
    }
}
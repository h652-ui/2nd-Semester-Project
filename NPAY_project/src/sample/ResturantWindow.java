package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ResturantWindow implements Initializable {

    @FXML
    Button log_out,trans_btn;

    @FXML
     Label r_name,completion,selection;

    @FXML
    private TableView<resturant_order_list> resturant_order_table;

    @FXML
    private TableColumn<resturant_order_list, String> username;

    @FXML
    private TableColumn<resturant_order_list, String> name;

    @FXML
    private TableColumn<resturant_order_list, String> hostel;

    @FXML
    private TableColumn<resturant_order_list, String> product;

    @FXML
    private TableColumn<resturant_order_list, Integer> quantity;

    @FXML
    private TableColumn<resturant_order_list, Integer> price;

    @FXML
    private TableColumn<resturant_order_list, String> phone;

    ObservableList<resturant_order_list> list;

    public void out_action(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) log_out.getScene().getWindow();
        stage.close();
        Parent root= FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage lstage = new Stage();
        lstage.initStyle(StageStyle.UNDECORATED);
        lstage.setScene(new Scene(root,676,400));
        lstage.show();

    }

    public void trans_window(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) trans_btn.getScene().getWindow();
        stage.close();
        Parent root= FXMLLoader.load(getClass().getResource("resturant_trans_window.fxml"));
        Stage tstage = new Stage();
        tstage.initStyle(StageStyle.UNDECORATED);
        tstage.setScene(new Scene(root,600,401));
        tstage.show();
    }

    public void complete_order(ActionEvent actionEvent) {
        resturant_order_list obj=resturant_order_table.getSelectionModel().getSelectedItem();
        if(obj==null){
            selection.setText("Please Select Something!");
        }else {
            String cafe=userinformation.getU_name().replaceAll("[^A-Za-z0-9 ]","");
            String order_frm=obj.getP_name();
            String name=obj.getU_name();
            String student_order_table=order_frm.replaceAll("[^a-zA-Z0-9]","")+"_order";
            String student_trans_table=order_frm.replaceAll("[^a-zA-Z0-9]","")+"_trans";
            String product=obj.getU_product();
            String hostel=obj.getU_hostel();
            int price=obj.getU_price();
            String phone=obj.getU_phone();
            int quantity=obj.getU_quantity();
            String query="SELECT * from login where username='"+order_frm+"'";
            int balance=0;
            DatabaseConnection u_databaseConnection=new DatabaseConnection();
            Connection u_connection=u_databaseConnection.getconnection();
            try{
                Statement statement = u_connection.createStatement();
                ResultSet resultSet=statement.executeQuery(query);
                while(resultSet.next()){
                    balance=Integer.parseInt(resultSet.getString("wallet"));
                }
            }catch(Exception e){
                e.printStackTrace();
                e.getCause();
            }
            String query1="DELETE FROM "+userinformation.getO_table()+" where order_from='"+order_frm+"' and product='"+product+"' and hostel='"+hostel+"' and u_name='"+name+"' and quantity="+quantity;
            String query2="Insert into "+userinformation.getT_table()+"(u_name,hostel,product,price,quantity,phone) Values('"+order_frm+"','"+hostel+"','"+product+"',"+price+","+quantity+",'"+phone+"')";
            String query3="DELETE FROM "+student_order_table+" where product='"+product+"' and quantity="+quantity+" and price="+price;
            String query4="Insert into "+student_trans_table+"(product,cafe,price,quantity,o_status) Values('"+product+"','"+cafe+"',"+price+","+quantity+",'Completed')";
            String query5="update login set wallet="+(userinformation.getWallet()+price)+" where username='"+userinformation.getname()+"'";
            balance=balance-price;
            String query6="update login set wallet="+balance+" where username='"+order_frm+"'";
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Connection connection=databaseConnection.getconnection();
            try {
                Statement statement=connection.createStatement();
                statement.executeUpdate(query1);
                statement.executeUpdate(query2);
                statement.executeUpdate(query3);
                statement.executeUpdate(query4);
                statement.executeUpdate(query5);
                userinformation.setWallet(userinformation.getWallet()+price);
                statement.executeUpdate(query6);
            }catch(Exception e){
                e.printStackTrace();
                e.getCause();
            }
            resturant_order_table.getItems().removeAll(resturant_order_table.getSelectionModel().getSelectedItem());
            completion.setText("Completed Successfully!");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        r_name.setText(userinformation.getU_name());
        username.setCellValueFactory(new PropertyValueFactory<>("u_name"));
        name.setCellValueFactory(new PropertyValueFactory<>("p_name"));
        hostel.setCellValueFactory(new PropertyValueFactory<>("u_hostel"));
        product.setCellValueFactory(new PropertyValueFactory<>("u_product"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("u_quantity"));
        price.setCellValueFactory(new PropertyValueFactory<>("u_price"));
        phone.setCellValueFactory(new PropertyValueFactory<>("u_phone"));
        list=DatabaseConnection.get_resturant_order();
        resturant_order_table.setItems(list);
    }
}

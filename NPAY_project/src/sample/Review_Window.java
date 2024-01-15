package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Review_Window implements Initializable {
    @FXML
    Button back_to, review_btn;

    @FXML
    private TableView<review_list > review_table;

    @FXML
    private TableColumn<review_list , String> col_product;

    @FXML
    private TableColumn<review_list , String> col_cafe;

    @FXML
    private TableColumn<review_list , String> col_consumer;

    @FXML
    private TableColumn<review_list , String> col_stars;

    @FXML
    private TableColumn<review_list , String> col_date;

    ObservableList<review_list> list;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        col_consumer.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getConsumer()));
        col_product.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getProduct()));
        col_stars.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getStars()));
        col_cafe.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCafe()));
        col_date.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDate()));
        list=DatabaseConnection.get_reviews();
        review_table.setItems(list);
    }

    public void review_reviews(ActionEvent actionEvent) throws IOException {
        Stage stage=(Stage)review_btn.getScene().getWindow();
        stage.close();
        Parent root= FXMLLoader.load(getClass().getResource("Review_Adder.fxml"));
        Stage s_stage = new Stage();
        s_stage.initStyle(StageStyle.UNDECORATED);
        s_stage.setScene(new Scene(root,513,393));
        s_stage.show();
    }

    public void back_to_main(ActionEvent actionEvent) throws IOException {
        Stage stage=(Stage)back_to.getScene().getWindow();
        stage.close();
        Parent root= FXMLLoader.load(getClass().getResource("studentwindow.fxml"));
        Stage s_stage = new Stage();
        s_stage.initStyle(StageStyle.UNDECORATED);
        s_stage.setScene(new Scene(root,800,620));
        s_stage.show();
    }
}

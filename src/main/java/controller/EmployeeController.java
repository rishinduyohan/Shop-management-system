package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class EmployeeController {
    Stage stage = new Stage();
    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colDob;

    @FXML
    private TableColumn<?, ?> colEmpId;

    @FXML
    private TableColumn<?, ?> colJoined;

    @FXML
    private TableColumn<?, ?> colNic;

    @FXML
    private TableColumn<?, ?> colPosition;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private ComboBox<String> comboStatus;

    @FXML
    private DatePicker dateDob;

    @FXML
    private DatePicker dateJoined;

    @FXML
    private TableView<?> tblEmployee;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmpId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNic;

    @FXML
    private TextField txtPosition;

    @FXML
    private TextField txtSalary;

    @FXML
    void btnAddOnAction(ActionEvent event) {

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnCustomersOnAction(ActionEvent event) {
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/CustomerMgt.fxml"))));
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Customer Management");
        stage.show();
    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) {
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Dashboard.fxml"))));
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Dashboard");
        stage.show();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnEmployeesOnAction(ActionEvent event) {

    }

    @FXML
    void btnItemsOnAction(ActionEvent event) {
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/ItemMgt.fxml"))));
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Item Management");
        stage.show();
    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) {
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Login_form.fxml"))));
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Login Form");
        stage.show();
    }

    @FXML
    void btnSuppliersOnAction(ActionEvent event) {
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/SupplierMgt.fxml"))));
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Supplier Management");
        stage.show();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }
}

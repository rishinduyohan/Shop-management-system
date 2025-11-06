package controller.mainController;

import controller.dbConnector.CustomerDb;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.dto.CustomerDTO;
import controller.service.CustomerService;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {
    Stage stage = new Stage();
    CustomerService customerService = new CustomerDb();
    @FXML
    private AnchorPane mainContent;

    @FXML
    private TableColumn<?, ?> tblAddress;

    @FXML
    private TableColumn<?, ?> tblCity;

    @FXML
    private TableColumn<?, ?> tblCusId;

    @FXML
    private TableView<CustomerDTO> tblCustomer;

    @FXML
    private TableColumn<?, ?> tblDob;

    @FXML
    private TableColumn<?, ?> tblName;

    @FXML
    private TableColumn<?, ?> tblProvince;

    @FXML
    private TableColumn<?, ?> tblPsCode;

    @FXML
    private TableColumn<?, ?> tblSalary;

    @FXML
    private TableColumn<?, ?> tblTitle;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtCity;

    @FXML
    private TextField txtCusId;

    @FXML
    private TextField txtDob;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPostalCode;

    @FXML
    private TextField txtProvince;

    @FXML
    private TextField txtSalary;

    @FXML
    private TextField txtTitle;

    private CustomerDTO getCurrentCustomer(){
        String cusId = txtCusId.getText();
        String title = txtTitle.getText();
        String name = txtName.getText();
        String dob = txtDob.getText();
        String salary = txtSalary.getText();
        String address = txtAddress.getText();
        String city = txtCity.getText();
        String province = txtProvince.getText();
        String postalCode = txtPostalCode.getText();
        return new CustomerDTO(cusId, title, name, dob, Double.parseDouble(salary), address, city, province, postalCode);
    }
    @FXML
    void btnAddOnAction(ActionEvent event) {
        try {
            if (customerService.addCustomer(getCurrentCustomer())){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Conformation");
                alert.setHeaderText("Customer added!");
                alert.setContentText("Customer successfully added to the system!");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Customer NOT added!");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        loadTable();
        clearText();

    }
    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearText();
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
        CustomerDTO selectedCustomer = tblCustomer.getSelectionModel().getSelectedItem();
        try {
            if (customerService.deleteCustomer(selectedCustomer.getCusID())){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Success!");
                alert.setHeaderText("Customer Deleted!");
                alert.setContentText("Customer deleted successfully in the system.");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Customer NOT deleted!");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        loadTable();
        clearText();
    }
    @FXML
    void btnEmployeesOnAction(ActionEvent event) {
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/EmployeeMgt.fxml"))));
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Employee Management");
        stage.show();
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
        CustomerDTO selectedCustomer = tblCustomer.getSelectionModel().getSelectedItem();
        try {
            if (customerService.updateCustomer(selectedCustomer.getCusID(),getCurrentCustomer())){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Success!");
                alert.setHeaderText("Customer Updated!");
                alert.setContentText("Customer details updated successfully!");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Customer NOT Updated!");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        loadTable();
        clearText();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tblCusId.setCellValueFactory(new PropertyValueFactory<>("cusID"));
        tblTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tblName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        tblSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        tblAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tblCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        tblProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        tblPsCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        loadTable();
        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (null != newValue) {
                txtCusId.setText(newValue.getCusID());
                txtTitle.setText(newValue.getTitle());
                txtName.setText(newValue.getName());
                txtDob.setText(newValue.getDob());
                txtSalary.setText(String.valueOf(newValue.getSalary()));
                txtAddress.setText(newValue.getAddress());
                txtCity.setText(newValue.getCity());
                txtProvince.setText(newValue.getProvince());
                txtPostalCode.setText(newValue.getPostalCode());
            }
        });
    }

    private void loadTable() {
        ObservableList<CustomerDTO> customerDTOS = FXCollections.observableArrayList();
        try {
            ResultSet resultSet=customerService.getAllCustomers();
            while (resultSet.next()) {
                customerDTOS.add(new CustomerDTO(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getDouble(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getString(9)
                ));
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Database error!");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        tblCustomer.setItems(customerDTOS);
    }

    public void clearText() {
        txtCusId.setText("");
        txtTitle.setText("");
        txtName.setText("");
        txtDob.setText("");
        txtSalary.setText("");
        txtAddress.setText("");
        txtCity.setText("");
        txtProvince.setText("");
        txtPostalCode.setText("");
    }
}
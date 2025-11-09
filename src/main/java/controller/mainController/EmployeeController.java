package controller.mainController;

import controller.dbConnector.EmployeeDb;
import controller.service.EmployeeService;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.dto.EmployeeDTO;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {
    Stage stage = new Stage();
    EmployeeService employeeService = new EmployeeDb();
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
    private TableColumn<?, ?> colName;

    @FXML
    private ComboBox<String> comboStatus;

    @FXML
    private DatePicker dateDob;

    @FXML
    private DatePicker dateJoined;

    @FXML
    private TableView<EmployeeDTO> tblEmployee;

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

    private EmployeeDTO getCurrentEmployee() {
        String employeeId = txtEmpId.getText();
        String name = txtName.getText();
        String nic = txtNic.getText();
        String dob = String.valueOf(dateDob.getValue());
        String position = txtPosition.getText();
        double salary = Double.parseDouble(txtSalary.getText());
        String contactNumber = txtContact.getText();
        String address = txtAddress.getText();
        String joinedDate = String.valueOf(dateJoined.getValue());
        String status = comboStatus.getValue();

        return new EmployeeDTO(employeeId, name, nic, dob, position, salary, contactNumber, address, joinedDate, status);
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        try {
            if (employeeService.addEmployee(getCurrentEmployee())) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Successs!");
                alert.setHeaderText("Employee Added!");
                alert.setContentText("Employee Successfully added to the system!");
                alert.showAndWait();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Employee NOT Added!");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        tblEmployee.refresh();
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
        EmployeeDTO selected = tblEmployee.getSelectionModel().getSelectedItem();
        try {
            if (employeeService.deleteEmployee(selected.getEmployeeId())) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Success!");
                alert.setHeaderText("Employee Deleted!");
                alert.setContentText("Employee successfully deleted from the system");
                alert.showAndWait();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Employee NOT Deleted!");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        loadTable();
        clearText();
    }
    @FXML
    void btnEmployeesOnAction(ActionEvent event) {}

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
        EmployeeDTO selected = tblEmployee.getSelectionModel().getSelectedItem();
        try {
            if (employeeService.updateCustomer(selected.getEmployeeId(),getCurrentEmployee())){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Success!");
                alert.setHeaderText("Employee Updated!");
                alert.setContentText("Employee updated successfully in the system");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Employee NOT Updated!");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        loadTable();
        clearText();
    }
    public void clearText() {
        txtEmpId.setText("");
        txtName.setText("");
        txtNic.setText("");
        dateDob.setValue(null);
        txtPosition.setText("");
        txtSalary.setText("");
        txtContact.setText("");
        txtAddress.setText("");
        dateJoined.setValue(null);
        comboStatus.setValue("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colEmpId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colPosition.setCellValueFactory(new PropertyValueFactory<>("position"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colJoined.setCellValueFactory(new PropertyValueFactory<>("joinedDate"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        loadTable();
        tblEmployee.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (null != newValue) {
                txtEmpId.setText(newValue.getEmployeeId());
                txtName.setText(newValue.getName());
                txtNic.setText(newValue.getNic());
                dateDob.setValue(LocalDate.parse(newValue.getDob()));
                txtPosition.setText(newValue.getPosition());
                txtSalary.setText(String.valueOf(newValue.getSalary()));
                txtContact.setText(newValue.getContactNumber());
                txtAddress.setText(newValue.getAddress());
                dateJoined.setValue(LocalDate.parse(newValue.getJoinedDate()));
                comboStatus.setValue(newValue.getStatus());
            }
        });
    }

    private void loadTable() {
        ObservableList<EmployeeDTO> employeeDTOS = FXCollections.observableArrayList();
        try {
            ResultSet rst = employeeService.getAllEmployees();
            while (rst.next()) {
                employeeDTOS.add(new EmployeeDTO(
                        rst.getString(1),
                        rst.getString(2),
                        rst.getString(3),
                        rst.getString(4),
                        rst.getString(5),
                        rst.getDouble(6),
                        rst.getString(7),
                        rst.getString(8),
                        rst.getString(9),
                        rst.getString(10)
                ));
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Database error!");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        tblEmployee.setItems(employeeDTOS);
    }
}

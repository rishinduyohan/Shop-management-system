package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.EmployeeDTO;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {
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

    ObservableList<EmployeeDTO> employeeDTOS = FXCollections.observableArrayList(
            new EmployeeDTO("E001", "Ravi Perera", "901234567V", "1990-05-12", "Manager", 85000.00, "0771234567", "Colombo", "2015-03-01", "Active"),
            new EmployeeDTO("E002", "Nimali Silva", "911234568V", "1991-08-22", "Software Engineer", 95000.00, "0772345678", "Kandy", "2018-07-15", "Active"),
            new EmployeeDTO("E003", "Tharindu Jayasena", "921234569V", "1992-11-05", "HR Executive", 65000.00, "0773456789", "Galle", "2016-01-20", "Inactive"),
            new EmployeeDTO("E004", "Ishara Fernando", "931234570V", "1993-02-18", "Accountant", 72000.00, "0774567890", "Matara", "2019-09-10", "Active"),
            new EmployeeDTO("E005", "Kasun Rathnayake", "941234571V", "1994-06-30", "Graphic Designer", 68000.00, "0775678901", "Kurunegala", "2020-05-25", "Active"),
            new EmployeeDTO("E006", "Dilani Wickramasinghe", "951234572V", "1995-12-12", "Marketing Lead", 80000.00, "0776789012", "Negombo", "2017-11-03", "Inactive"),
            new EmployeeDTO("E007", "Chamika Bandara", "961234573V", "1996-03-27", "QA Analyst", 70000.00, "0777890123", "Anuradhapura", "2021-02-14", "Active"),
            new EmployeeDTO("E008", "Sanduni Herath", "971234574V", "1997-09-09", "Intern", 40000.00, "0778901234", "Badulla", "2023-06-01", "Active")
    );
    @FXML
    void btnAddOnAction(ActionEvent event) {
         String employeeId = txtEmpId.getText();
         String name = txtName.getText();
         String nic = txtNic.getText();
         String dob = String.valueOf(dateDob.getValue());
         String position = txtPosition.getText();
         double salary = Double.parseDouble(txtSalary.getText());
         String contactNumber = txtContact.getText();
         String address = txtAddress.getText();
         String joinedDate = String.valueOf(dateJoined.getValue());
         String status = txtSalary.getText();
         EmployeeDTO newEmployee = new EmployeeDTO(employeeId,name,nic,dob,position,salary,contactNumber,address,joinedDate,status);
         employeeDTOS.add(newEmployee);
         tblEmployee.refresh();
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

    public void clearText(){
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
        tblEmployee.setItems(employeeDTOS);

        tblEmployee.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->{
            if(null!=newValue){
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
}

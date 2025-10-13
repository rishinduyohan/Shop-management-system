package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.SupplierDTO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SupplierController implements Initializable {
    Stage stage = new Stage();
    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colCity;

    @FXML
    private TableColumn<?, ?> colComName;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPhone;

    @FXML
    private TableColumn<?, ?> colProvince;

    @FXML
    private TableColumn<?, ?> colPsCode;

    @FXML
    private TableColumn<?, ?> colSupId;

    @FXML
    private AnchorPane mainContent;

    @FXML
    private TableView<SupplierDTO> tblSuppliers;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtCity;

    @FXML
    private TextField txtComName;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtProvince;

    @FXML
    private TextField txtPsCode;

    @FXML
    private TextField txtSupId;

    ObservableList<SupplierDTO> supplierDTOS = FXCollections.observableArrayList(
            new SupplierDTO("S001", "Ravi Perera", "Perera Traders", "123 Main St", "Colombo", "Western", "00100", "0771234567", "ravi@pereratraders.lk"),
            new SupplierDTO("S002", "Nimal Silva", "Silva Distributors", "45 Galle Rd", "Galle", "Southern", "80000", "0719876543", "nimal@silvadist.lk"),
            new SupplierDTO("S003", "Kamal Fernando", "Kamal Supplies", "67 Kandy Rd", "Kandy", "Central", "20000", "0752345678", "kamal@ksupplies.lk"),
            new SupplierDTO("S004", "Anura Jayasuriya", "Jay Imports", "22 Temple Rd", "Matara", "Southern", "81000", "0783456789", "anura@jayimports.lk"),
            new SupplierDTO("S005", "Sunil Wijesinghe", "Wijesinghe & Sons", "98 Beach Rd", "Negombo", "Western", "11500", "0764567890", "sunil@wijsons.lk"),
            new SupplierDTO("S006", "Roshan de Silva", "Roshan Hardware", "12 Hill St", "Nuwara Eliya", "Central", "22000", "0745678901", "roshan@rhardware.lk"),
            new SupplierDTO("S007", "Priya Abeykoon", "Priya Textiles", "77 Bazaar St", "Kurunegala", "North Western", "60000", "0726789012", "priya@ptextiles.lk"),
            new SupplierDTO("S008", "Manjula Rathnayake", "MR Electronics", "55 Lake View", "Anuradhapura", "North Central", "50000", "0797890123", "manjula@mrelec.lk")
    );
    public void clearText(){
        txtSupId.setText("");
        txtName.setText("");
        txtComName.setText("");
        txtAddress.setText("");
        txtCity.setText("");
        txtProvince.setText("");
        txtPsCode.setText("");
        txtPhone.setText("");
        txtEmail.setText("");
    }
    @FXML
    void btnAddOnAction(ActionEvent event) {
        String id = txtSupId.getText();
        String name = txtName.getText();
        String comName = txtComName.getText();
        String address = txtAddress.getText();
        String city = txtCity.getText();
        String province = txtProvince.getText();
        String psCode = txtPsCode.getText();
        String phone = txtPhone.getText();
        String email = txtEmail.getText();
        SupplierDTO newSupplier = new SupplierDTO(id,name,comName,address,city,province,psCode,phone,email);
        supplierDTOS.add(newSupplier);
        tblSuppliers.refresh();
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
        SupplierDTO selected = tblSuppliers.getSelectionModel().getSelectedItem();
        supplierDTOS.remove(selected);
        tblSuppliers.refresh();
        clearText();
    }

    @FXML
    void btnEmployeesOnAction(ActionEvent event) {

    }

    @FXML
    void btnItemsOnAction(ActionEvent event) {

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

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        SupplierDTO selected = tblSuppliers.getSelectionModel().getSelectedItem();
        selected.setSupplierId(txtSupId.getText());
        selected.setName(txtName.getText());
        selected.setCompanyName(txtComName.getText());
        selected.setAddress(txtAddress.getText());
        selected.setCity(txtCity.getText());
        selected.setProvince(txtProvince.getText());
        selected.setPostalCode(txtPsCode.getText());
        selected.setPhone(txtPhone.getText());
        selected.setEmail(txtEmail.getText());
        tblSuppliers.refresh();
        clearText();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colSupId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colComName.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        colPsCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tblSuppliers.setItems(supplierDTOS);

        tblSuppliers.getSelectionModel().selectedItemProperty().addListener((obeservable,oldValue,newValue)->{
            if (null != newValue){
                txtSupId.setText(newValue.getSupplierId());
                txtName.setText(newValue.getName());
                txtComName.setText(newValue.getCompanyName());
                txtAddress.setText(newValue.getAddress());
                txtCity.setText(newValue.getCity());
                txtProvince.setText(newValue.getProvince());
                txtPsCode.setText(newValue.getPostalCode());
                txtPhone.setText(newValue.getPhone());
                txtEmail.setText(newValue.getEmail());
            }
        });
    }
}
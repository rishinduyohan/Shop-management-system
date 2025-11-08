package controller.mainController;

import controller.dbConnector.SupplierDb;
import controller.service.SupplierService;
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
import model.dto.SupplierDTO;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class SupplierController implements Initializable {
    Stage stage = new Stage();
    SupplierService supplierService = new SupplierDb();
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

    public void clearText() {
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

    private SupplierDTO getCurrentSupplier(){
        String id = txtSupId.getText();
        String name = txtName.getText();
        String comName = txtComName.getText();
        String address = txtAddress.getText();
        String city = txtCity.getText();
        String province = txtProvince.getText();
        String psCode = txtPsCode.getText();
        String phone = txtPhone.getText();
        String email = txtEmail.getText();
        return new SupplierDTO(id, name, comName, address, city, province, psCode, phone, email);
    }
    @FXML
    void btnAddOnAction(ActionEvent event) {
        try {
            if(supplierService.addSupplier(getCurrentSupplier())){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Supplier Added!");
                alert.setContentText("Supplier successfully added to the system");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Supplier NOT Added!");
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
        SupplierDTO selected = tblSuppliers.getSelectionModel().getSelectedItem();
        if (isDeleted(selected.getSupplierId())){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success!");
            alert.setHeaderText("Supplier Deleted!");
            alert.setContentText("Supplier deleted form the system");
            alert.showAndWait();
        }
        tblSuppliers.refresh();
        loadTable();
        clearText();
    }
    private boolean isDeleted(String id){
        try {
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement("DELETE FROM SUPPLIER WHERE supplierId='"+id+"'");
            return statement.executeUpdate()>0;
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Supplier NOT Deleted!");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        return false;
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

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        SupplierDTO selected = tblSuppliers.getSelectionModel().getSelectedItem();
        if (isUpdated(selected)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Success!");
            alert.setHeaderText("Supplier Updated!");
            alert.setContentText("Supplier successfully updated in the system");
            alert.showAndWait();
        }
        tblSuppliers.refresh();
        loadTable();
        clearText();
    }
    private boolean isUpdated(SupplierDTO selected){
        SupplierDTO supplier = getCurrentSupplier();
        try {
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement("UPDATE SUPPLIER SET name=?,companyName=?,address=?,city=?,province=?,postalCode=?,phone=?,email=? WHERE supplierId=?");
            statement.setObject(9,selected.getSupplierId());
            statement.setObject(1,supplier.getName());
            statement.setObject(2,supplier.getCompanyName());
            statement.setObject(3,supplier.getAddress());
            statement.setObject(4,supplier.getCity());
            statement.setObject(5,supplier.getProvince());
            statement.setObject(6,supplier.getPostalCode());
            statement.setObject(7,supplier.getPhone());
            statement.setObject(8,supplier.getEmail());
            return statement.executeUpdate()>0;
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Supplier NOT Updated!");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        return false;
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
        loadTable();
        tblSuppliers.getSelectionModel().selectedItemProperty().addListener((obeservable, oldValue, newValue) -> {
            if (null != newValue) {
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

    private void loadTable() {
        ObservableList<SupplierDTO> supplierDTOS = FXCollections.observableArrayList();
        try {
            Statement statement = DBConnection.getInstance().getConnection().createStatement();
            ResultSet rst = statement.executeQuery("SELECT * FROM SUPPLIER");
            while (rst.next()) {
                supplierDTOS.add(new SupplierDTO(
                        rst.getString(1),
                        rst.getString(2),
                        rst.getString(3),
                        rst.getString(4),
                        rst.getString(5),
                        rst.getString(6),
                        rst.getString(7),
                        rst.getString(8),
                        rst.getString(9)
                ));
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Database error!");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        tblSuppliers.setItems(supplierDTOS);
    }
}
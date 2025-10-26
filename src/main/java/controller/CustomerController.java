package controller;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
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
import model.CustomerDTO;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {
    Stage stage = new Stage();
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

    ObservableList<CustomerDTO> customerDTOS = FXCollections.observableArrayList();

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String cusId = txtCusId.getText();
        String title = txtTitle.getText();
        String name = txtName.getText();
        String dob = txtDob.getText();
        String salary = txtSalary.getText();
        String address = txtAddress.getText();
        String city = txtCity.getText();
        String province = txtProvince.getText();
        String postalCode = txtPostalCode.getText();

        CustomerDTO newCustomer = new CustomerDTO(cusId, title, name, dob, Double.parseDouble(salary), address, city, province, postalCode);
        if (isAdded(newCustomer)){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Conformation");
            alert.setHeaderText("Customer added!");
            alert.setContentText("Customer successfully added to the system!");
            alert.showAndWait();
        }
        tblCustomer.refresh();
        loadTable();
        clearText();

    }
    public boolean isAdded(CustomerDTO newCustomer){
        try {
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO CUSTOMER VALUES(?,?,?,?,?,?,?,?,?)");
            statement.setObject(1,newCustomer.getCusID());
            statement.setObject(2,newCustomer.getTitle());
            statement.setObject(3,newCustomer.getName());
            statement.setObject(4,newCustomer.getDob());
            statement.setObject(5,newCustomer.getSalary());
            statement.setObject(6,newCustomer.getAddress());
            statement.setObject(7,newCustomer.getCity());
            statement.setObject(8,newCustomer.getProvince());
            statement.setObject(9,newCustomer.getPostalCode());
            return 0 < statement.executeUpdate();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("Customer NOT added!");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        return false;
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
        customerDTOS.remove(selectedCustomer);
        tblCustomer.refresh();
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
        selectedCustomer.setCusID(txtCusId.getText());
        selectedCustomer.setTitle(txtTitle.getText());
        selectedCustomer.setName(txtName.getText());
        selectedCustomer.setDob(txtDob.getText());
        selectedCustomer.setSalary(Double.parseDouble(txtSalary.getText()));
        selectedCustomer.setAddress(txtAddress.getText());
        selectedCustomer.setCity(txtCity.getText());
        selectedCustomer.setProvince(txtProvince.getText());
        selectedCustomer.setPostalCode(txtPostalCode.getText());
        tblCustomer.refresh();
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
        customerDTOS = FXCollections.observableArrayList();
        try {
            Statement statement = DBConnection.getInstance().getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT *FROM CUSTOMER");
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
            throw new RuntimeException(e);
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
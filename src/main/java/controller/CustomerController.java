package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
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
import model.CustomerDTO;

import java.io.IOException;
import java.net.URL;
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

    ObservableList<CustomerDTO> customerDTOS = FXCollections.observableArrayList(
            new CustomerDTO("C001", "Mr.", "John Smith", "1985-06-15", 55000.00, "123 Maple St", "Toronto", "Ontario", "M5A1A1"),
            new CustomerDTO("C002", "Ms.", "Emily Davis", "1990-03-22", 62000.00, "456 Oak Ave", "Vancouver", "British Columbia", "V6B2B2"),
            new CustomerDTO("C003", "Mrs.", "Ayesha Perera", "1988-11-05", 48000.00, "789 Palm Rd", "Colombo", "Western Province", "Y00500"),
            new CustomerDTO("C004", "Dr.", "Liam Chen", "1979-01-30", 98000.00, "321 Birch Blvd", "Calgary", "Alberta", "T2P3P3"),
            new CustomerDTO("C005", "Mr.", "Carlos Ruiz", "1992-07-18", 53000.00, "654 Cedar Ln", "Montreal", "Quebec", "H3Z2Y7"),
            new CustomerDTO("C006", "Ms.", "Nandini Rao", "1987-09-12", 61000.00, "987 Spruce Ct", "Bangalore", "Karnataka", "V560001"),
            new CustomerDTO("C007", "Mr.", "David Kim", "1995-12-03", 47000.00, "159 Elm St", "Seattle", "Washington", "L98101"),
            new CustomerDTO("C008", "Mrs.", "Fatima Ali", "1983-04-27", 75000.00, "753 Willow Way", "Dubai", "Dubai Emirate", "C56156")
    );

    @FXML
    void btnAddOnAction(ActionEvent event) {

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

        tblCustomer.setItems(customerDTOS);
        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->{
            if (null!=newValue){
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
    public void clearText(){
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
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.ItemDTO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ItemController implements Initializable {
    Stage stage = new Stage();
    @FXML
    private TableColumn<?, ?> colCategory;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private ComboBox<String> comboCategory;

    @FXML
    private AnchorPane mainContent;

    @FXML
    private TableView<ItemDTO> tblItems;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtItemCode;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtUnitPrice;

    ObservableList<ItemDTO> itemDTOS = FXCollections.observableArrayList(
            new ItemDTO("I001", "LED Bulb 12W", "Electronics", 150, 450.00),
            new ItemDTO("I002", "A4 Paper Pack", "Office Supplies", 80, 1200.00),
            new ItemDTO("I003", "Steel Hammer", "Hardware Tools", 40, 950.00),
            new ItemDTO("I004", "Basmathi Rice 5kg", "Groceries", 60, 2500.00),
            new ItemDTO("I005", "Men’s Cotton Shirt", "Clothing", 30, 3500.00),
            new ItemDTO("I006", "Extension Cord 5m", "Electronics", 100, 1100.00),
            new ItemDTO("I007", "Paint Brush Set", "Hardware Tools", 75, 650.00),
            new ItemDTO("I008", "Printer Ink Cartridge", "Office Supplies", 45, 3200.00)
    );

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String code = txtItemCode.getText();
        String desc = txtDescription.getText();
        String category = comboCategory.getValue();
        int qty = Integer.parseInt(txtQty.getText());
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        ItemDTO newItem = new ItemDTO(code,desc,category,qty,unitPrice);
        itemDTOS.add(newItem);
        tblItems.refresh();
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
    public void clearText(){
        txtItemCode.setText("");
        txtDescription.setText("");
        txtQty.setText("");
        txtUnitPrice.setText("");
        comboCategory.setValue("Category");
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
        ItemDTO selected = tblItems.getSelectionModel().getSelectedItem();
        itemDTOS.remove(selected);
        tblItems.refresh();
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
        ItemDTO selected = tblItems.getSelectionModel().getSelectedItem();
        selected.setItemCode(txtItemCode.getText());
        selected.setDescription(txtDescription.getText());
        selected.setCategory(comboCategory.getValue());
        selected.setQty(Integer.parseInt(txtQty.getText()));
        selected.setUnitPrice(Double.parseDouble(txtUnitPrice.getText()));
        tblItems.refresh();
        clearText();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblItems.setItems(itemDTOS);

        tblItems.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->{
            if (null!=newValue){
                txtItemCode.setText(newValue.getItemCode());
                txtDescription.setText(newValue.getDescription());
                comboCategory.setValue(newValue.getCategory());
                txtQty.setText(String.valueOf(newValue.getQty()));
                txtUnitPrice.setText(String.valueOf(newValue.getUnitPrice()));
            }
        });

    }
}

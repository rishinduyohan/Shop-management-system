package controller;

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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.ItemDTO;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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



    private ItemDTO getCurrentItem(){
        String code = txtItemCode.getText();
        String desc = txtDescription.getText();
        String category = comboCategory.getValue();
        int qty = Integer.parseInt(txtQty.getText());
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        return new ItemDTO(code, desc, category, qty, unitPrice);
    }
    @FXML
    void btnAddOnAction(ActionEvent event) {
        if (isAdded(getCurrentItem())){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success!");
            alert.setHeaderText("Item Added!");
            alert.setContentText("Item successfully added to the system");
            alert.showAndWait();
        }
        tblItems.refresh();
        loadTable();
        clearText();
    }

    private boolean isAdded(ItemDTO newItem){
        try {
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO ITEM VALUES(?,?,?,?,?)");
            statement.setObject(1,newItem.getItemCode());
            statement.setObject(2,newItem.getDescription());
            statement.setObject(3,newItem.getCategory());
            statement.setObject(4,newItem.getQty());
            statement.setObject(5,newItem.getUnitPrice());
            return statement.executeUpdate()>0;
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Item NOT Added!");
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

    public void clearText() {
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
        loadTable();
        tblItems.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (null != newValue) {
                txtItemCode.setText(newValue.getItemCode());
                txtDescription.setText(newValue.getDescription());
                comboCategory.setValue(newValue.getCategory());
                txtQty.setText(String.valueOf(newValue.getQty()));
                txtUnitPrice.setText(String.valueOf(newValue.getUnitPrice()));
            }
        });
    }

    private void loadTable() {
        ObservableList<ItemDTO> itemDTOS = FXCollections.observableArrayList();
        try {
            Statement statement = DBConnection.getInstance().getConnection().createStatement();
            ResultSet rst = statement.executeQuery("SELECT * FROM item");
            while (rst.next()) {
                itemDTOS.add(new ItemDTO(
                        rst.getString(1),
                        rst.getString(2),
                        rst.getString(3),
                        rst.getInt(4),
                        rst.getDouble(5)
                ));
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Database error!");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        tblItems.setItems(itemDTOS);
    }
}

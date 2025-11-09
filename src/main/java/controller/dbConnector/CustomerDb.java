package controller.dbConnector;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.dto.CustomerDTO;
import controller.service.CustomerService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerDb implements CustomerService {

    @Override
    public boolean addCustomer(CustomerDTO newCustomer) throws SQLException {
        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO CUSTOMER VALUES(?,?,?,?,?,?,?,?,?)");
        statement.setObject(1, newCustomer.getCusID());
        statement.setObject(2, newCustomer.getTitle());
        statement.setObject(3, newCustomer.getName());
        statement.setObject(4, newCustomer.getDob());
        statement.setObject(5, newCustomer.getSalary());
        statement.setObject(6, newCustomer.getAddress());
        statement.setObject(7, newCustomer.getCity());
        statement.setObject(8, newCustomer.getProvince());
        statement.setObject(9, newCustomer.getPostalCode());
        return 0 < statement.executeUpdate();
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException {
        Statement stm = DBConnection.getInstance().getConnection().createStatement();
        int res = stm.executeUpdate("DELETE FROM CUSTOMER WHERE cusID = '" + id + "' ");
        return res > 0;
    }

    @Override
    public boolean updateCustomer(String id, CustomerDTO updatedCustomer) throws SQLException {
        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement("UPDATE CUSTOMER SET title=?, name=?,dob=?,salary=?,address=?,city=?,province=?,postalCode=? WHERE cusID='" + id + "'");
        statement.setObject(1, updatedCustomer.getTitle());
        statement.setObject(2, updatedCustomer.getName());
        statement.setObject(3, updatedCustomer.getDob());
        statement.setObject(4, updatedCustomer.getSalary());
        statement.setObject(5, updatedCustomer.getAddress());
        statement.setObject(6, updatedCustomer.getCity());
        statement.setObject(7, updatedCustomer.getProvince());
        statement.setObject(8, updatedCustomer.getPostalCode());
        return statement.executeUpdate() > 0;
    }

    @Override
    public ResultSet getAllCustomers() throws SQLException {
        Statement statement = DBConnection.getInstance().getConnection().createStatement();
        return statement.executeQuery("SELECT *FROM CUSTOMER");
    }
}

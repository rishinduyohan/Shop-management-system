package controller.service;

import model.dto.CustomerDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface CustomerService {
    boolean addCustomer(CustomerDTO customer) throws SQLException;
    boolean deleteCustomer(String id) throws SQLException;
    boolean updateCustomer(String id,CustomerDTO updated) throws SQLException;
    ResultSet getAllCustomers() throws SQLException;
}

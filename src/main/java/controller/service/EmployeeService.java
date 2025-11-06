package controller.service;

import model.dto.EmployeeDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface EmployeeService {
    boolean addEmployee(EmployeeDTO employee) throws SQLException;
    boolean deleteEmployee(String id) throws SQLException;
    boolean updateCustomer(String id,EmployeeDTO newDetails) throws SQLException;
    ResultSet getAllEmployees() throws SQLException;
}

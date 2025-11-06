package controller.dbConnector;

import controller.service.EmployeeService;
import db.DBConnection;
import model.dto.EmployeeDTO;

import java.sql.*;

public class EmployeeDb implements EmployeeService {
    @Override
    public boolean addEmployee(EmployeeDTO emp) throws SQLException {
        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO EMPLOYEE VALUES(?,?,?,?,?,?,?,?,?,?)");
        statement.setObject(1, emp.getEmployeeId());
        statement.setObject(2, emp.getName());
        statement.setObject(3, emp.getNic());
        statement.setObject(4, Date.valueOf(emp.getDob()));
        statement.setObject(5, emp.getPosition());
        statement.setObject(6, emp.getSalary());
        statement.setObject(7, emp.getContactNumber());
        statement.setObject(8, emp.getAddress());
        statement.setObject(9, Date.valueOf(emp.getJoinedDate()));
        statement.setObject(10, emp.getStatus());
        return statement.executeUpdate() > 0;
    }

    @Override
    public boolean deleteEmployee(String id) throws SQLException {
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("DELETE FROM EMPLOYEE WHERE employeeId='" + id + "'");
        return stm.executeUpdate() > 0;
    }

    @Override
    public boolean updateCustomer(String id, EmployeeDTO curEmp) throws SQLException {
        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement("UPDATE EMPLOYEE SET name=?,nic=?,dob=?,position=?,salary=?,contactNumber=?,address=?,joinedDate=?,status=? WHERE employeeId='"+id+"'");
        statement.setObject(1, curEmp.getName());
        statement.setObject(2, curEmp.getNic());
        statement.setObject(3, Date.valueOf(curEmp.getDob()));
        statement.setObject(4, curEmp.getPosition());
        statement.setObject(5, curEmp.getSalary());
        statement.setObject(6, curEmp.getContactNumber());
        statement.setObject(7, curEmp.getAddress());
        statement.setObject(8, Date.valueOf(curEmp.getJoinedDate()));
        statement.setObject(9, curEmp.getStatus());
        return statement.executeUpdate() > 0;
    }

    @Override
    public ResultSet getAllEmployees() throws SQLException {
        Statement stm = DBConnection.getInstance().getConnection().createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM EMPLOYEE");
        return rst;
    }
}

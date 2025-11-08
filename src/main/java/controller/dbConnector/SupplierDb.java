package controller.dbConnector;

import controller.service.SupplierService;
import db.DBConnection;
import model.dto.SupplierDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierDb implements SupplierService {
    @Override
    public boolean addSupplier(SupplierDTO supplier) throws SQLException {
        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO SUPPLIER VALUES(?,?,?,?,?,?,?,?,?)");
        statement.setObject(1,supplier.getSupplierId());
        statement.setObject(2,supplier.getName());
        statement.setObject(3,supplier.getCompanyName());
        statement.setObject(4,supplier.getAddress());
        statement.setObject(5,supplier.getCity());
        statement.setObject(6,supplier.getProvince());
        statement.setObject(7,supplier.getPostalCode());
        statement.setObject(8,supplier.getPhone());
        statement.setObject(9,supplier.getEmail());
        return statement.executeUpdate()>0;
    }

    @Override
    public boolean deleteSupplier(String id) throws SQLException {
        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement("DELETE FROM SUPPLIER WHERE supplierId='"+id+"'");
        return statement.executeUpdate()>0;
    }

    @Override
    public boolean updateSupplier(String id, SupplierDTO supplier) throws SQLException {
        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement("UPDATE SUPPLIER SET name=?,companyName=?,address=?,city=?,province=?,postalCode=?,phone=?,email=? WHERE supplierId='"+id+"'");
        statement.setObject(1,supplier.getName());
        statement.setObject(2,supplier.getCompanyName());
        statement.setObject(3,supplier.getAddress());
        statement.setObject(4,supplier.getCity());
        statement.setObject(5,supplier.getProvince());
        statement.setObject(6,supplier.getPostalCode());
        statement.setObject(7,supplier.getPhone());
        statement.setObject(8,supplier.getEmail());
        return statement.executeUpdate()>0;
    }

    @Override
    public ResultSet getAllSupplier() {
        return null;
    }
}

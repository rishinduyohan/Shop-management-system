package controller.service;

import model.dto.SupplierDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface SupplierService {
    boolean addSupplier(SupplierDTO newSupplier) throws SQLException;
    boolean deleteSupplier(String id);
    boolean updateSupplier(String id,SupplierDTO newSupplier);
    ResultSet getAllSupplier();
}

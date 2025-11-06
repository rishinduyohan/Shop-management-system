package controller.service;

import model.dto.ItemDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ItemService {
    boolean addItem(ItemDTO item) throws SQLException;
    boolean deleteItem(String id) throws SQLException;
    boolean updateItem(String id,ItemDTO newDetails) throws SQLException;
    ResultSet getAllItems() throws SQLException;
}

package controller.dbConnector;

import controller.service.ItemService;
import db.DBConnection;
import model.dto.ItemDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ItemDb implements ItemService {
    @Override
    public boolean addItem(ItemDTO newItem) throws SQLException {
        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO ITEM VALUES(?,?,?,?,?)");
        statement.setObject(1, newItem.getItemCode());
        statement.setObject(2, newItem.getDescription());
        statement.setObject(3, newItem.getCategory());
        statement.setObject(4, newItem.getQty());
        statement.setObject(5, newItem.getUnitPrice());
        return statement.executeUpdate() > 0;
    }

    @Override
    public boolean deleteItem(String id) throws SQLException {
        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement("DELETE FROM item WHERE itemCode='" + id + "'");
        return statement.executeUpdate() > 0;
    }

    @Override
    public boolean updateItem(String id, ItemDTO currentItem) throws SQLException {
        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement("UPDATE ITEM SET description=?,category=?,qty=?,unitPrice=? WHERE itemCode='"+id+"'");
        statement.setObject(1, currentItem.getDescription());
        statement.setObject(2, currentItem.getCategory());
        statement.setObject(3, currentItem.getQty());
        statement.setObject(4, currentItem.getUnitPrice());
        return statement.executeUpdate() > 0;
    }

    @Override
    public ResultSet getAllItems() throws SQLException {
        Statement statement = DBConnection.getInstance().getConnection().createStatement();
        ResultSet rst = statement.executeQuery("SELECT * FROM item");
        return rst;
    }
}

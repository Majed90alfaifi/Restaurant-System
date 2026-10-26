package org.majed.project252.admin.command;

import org.majed.project252.DB.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class Admin_Receiver {
    DBConnection connection = DBConnection.getInstance();
    PreparedStatement insertItem = connection.getConnection().prepareStatement(
            "INSERT INTO menu_items (items_id, item_name, item_price, item_quantity) VALUES (?, ?, ?, ?)");
    PreparedStatement findById = connection.getConnection().prepareStatement(
            "SELECT * FROM menu_items WHERE items_id = ?");
    PreparedStatement deleteItem = connection.getConnection().prepareStatement(
            "DELETE FROM menu_items WHERE items_id = ?");
    PreparedStatement updateQuantity = connection.getConnection().prepareStatement(
            "UPDATE menu_items SET item_quantity = ? WHERE items_id = ?");
    PreparedStatement updatePrice = connection.getConnection().prepareStatement(
            "UPDATE menu_items SET item_price = ? WHERE items_id = ?");
    ResultSet resultSet;

    public Admin_Receiver() throws SQLException {
    }

    void add_item(int item_id,String item_name,int item_quantity,float item_Price) throws SQLException {
        insertItem.setInt(1, item_id);
        insertItem.setString(2, item_name);
        insertItem.setFloat(3, item_Price);
        insertItem.setInt(4, item_quantity);
        insertItem.executeUpdate();
        findById.setInt(1, item_id);
        resultSet = findById.executeQuery();
        System.out.println("Item ID |Name,Price,Quantity");
        while (resultSet.next()) {
            System.out.print(resultSet.getString(1) + "\t\t|");
            System.out.print(resultSet.getString(2) + ",");
            System.out.print(resultSet.getString(3) + "RS, ");
            System.out.print(resultSet.getString(4)+"\n");
        }
    }

    void delete_item(int item_id) throws SQLException {
        deleteItem.setInt(1, item_id);
        deleteItem.executeUpdate();
        findById.setInt(1, item_id);
        resultSet = findById.executeQuery();

    }
    void update_item_Quantity(int item_id,int item_quantity) throws SQLException {
        updateQuantity.setInt(1, item_quantity);
        updateQuantity.setInt(2, item_id);
        updateQuantity.executeUpdate();

        findById.setInt(1, item_id);
        resultSet = findById.executeQuery();
        System.out.println("Item Name | Item Quantity");
        while (resultSet.next()) {
            System.out.print(resultSet.getString(2) + "\t\t");
            System.out.print(resultSet.getString(4) + "\t");
            System.out.println();
        }
    }
    void update_item_Price(int item_id, float item_Price) throws SQLException {
        updatePrice.setFloat(1, item_Price);
        updatePrice.setInt(2, item_id);
        updatePrice.executeUpdate();

        findById.setInt(1, item_id);
        resultSet = findById.executeQuery();
        System.out.println("Item Name | Item Price");
        while (resultSet.next()) {
            System.out.print(resultSet.getString(2) + "\t\t");
            System.out.print(resultSet.getString(3) + "\t");
            System.out.println();
        }
    }


}

package org.majed.project252.admin.command;

import org.majed.project252.DB.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Admin_Receiver {
    DBConnection connection = DBConnection.getInstance();
    Statement statement = connection.getConnection().createStatement();
    ResultSet resultSet;

    public Admin_Receiver() throws SQLException {
    }

    void add_item(int item_id,String item_name,int item_quantity,float item_Price) throws SQLException {
        statement.executeUpdate("INSERT INTO menu_items (items_id, item_name, item_price, item_quantity) VALUES ("+item_id+", "+item_name+", "+item_Price+", "+item_quantity+");");
        resultSet = statement.executeQuery("SELECT * FROM menu_items where items_id ='" + item_id + "';");
        System.out.println("Item ID |Name,Price,Quantity");
        while (resultSet.next()) {
            System.out.print(resultSet.getString(1) + "\t\t|");
            System.out.print(resultSet.getString(2) + ",");
            System.out.print(resultSet.getString(3) + "RS, ");
            System.out.print(resultSet.getString(4)+"\n");
        }
    }

    void delete_item(int item_id) throws SQLException {
        statement.executeUpdate("DELETE FROM menu_items WHERE (items_id = "+item_id+");");
        resultSet = statement.executeQuery("SELECT * FROM menu_items where items_id ='" + item_id + "';");

    }
    void update_item_Quantity(int item_id,int item_quantity) throws SQLException {
        statement.executeUpdate("UPDATE menu_items SET item_quantity = " + item_quantity + " WHERE (items_id = " + item_id + ");");

        resultSet = statement.executeQuery("SELECT * FROM menu_items where items_id = " + item_id + ";");
        System.out.println("Item Name | Item Quantity");
        while (resultSet.next()) {
            System.out.print(resultSet.getString(2) + "\t\t");
            System.out.print(resultSet.getString(4) + "\t");
            System.out.println();
        }
    }
    void update_item_Price(int item_id, float item_Price) throws SQLException {
        statement.executeUpdate("UPDATE menu_items SET item_price = " + item_Price + " WHERE (items_id = " + item_id + ");");

        resultSet = statement.executeQuery("SELECT * FROM menu_items where items_id = " + item_id + ";");
        System.out.println("Item Name | Item Price");
        while (resultSet.next()) {
            System.out.print(resultSet.getString(2) + "\t\t");
            System.out.print(resultSet.getString(3) + "\t");
            System.out.println();
        }
    }


}

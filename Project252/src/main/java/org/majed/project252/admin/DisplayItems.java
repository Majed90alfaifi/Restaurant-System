package org.majed.project252.admin;

import org.majed.project252.DB.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DisplayItems {
    DBConnection connection = DBConnection.getInstance();
    Statement statement = connection.getConnection().createStatement();
    ResultSet resultSet;

    public DisplayItems() throws SQLException {
        resultSet = statement.executeQuery("SELECT * FROM menu_items;");
        System.out.println("Item ID |Name,Price,Quantity");
        while (resultSet.next()) {
            System.out.print(resultSet.getString(1) + "\t\t|");
            System.out.print(resultSet.getString(2) + ",");
            System.out.print(resultSet.getString(3) + "RS, ");
            System.out.print(resultSet.getString(4)+"\n");
        }
    }

}

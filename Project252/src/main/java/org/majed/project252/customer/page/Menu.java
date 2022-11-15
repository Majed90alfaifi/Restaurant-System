package org.majed.project252.customer.page;

import org.majed.project252.DB.DBConnection;
import org.majed.project252.customer.command.Add_Item_Command_Customer;
import org.majed.project252.customer.command.CustomerCommand;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Menu {
    int item_id;

    DBConnection connection = DBConnection.getInstance();
    Statement statement = connection.getConnection().createStatement();
    ResultSet resultSet;
    Scanner scanner = new Scanner(System.in);

    public Menu() throws SQLException {
    }

    public void displayMenu() throws SQLException {
        System.out.println("Welcome To FCIT Cafeteria");

        resultSet = statement.executeQuery("SELECT * FROM menu_items;");
        System.out.println("Item Id | Item Name | Item Price");
        while (resultSet.next()) {
            System.out.print(resultSet.getInt(1) + "\t");
            System.out.print(resultSet.getString(2) + "\t");
            System.out.print(resultSet.getString(3) + " RS\t");
            System.out.println();
        }

        while (true) {
            System.out.print("\nEnter Item ID or Enter zero to Exit :");
            item_id = scanner.nextInt();
            if (item_id == 0) {
                Recipt recipt = new Recipt();
                recipt.printReceipt();
                statement.executeUpdate("truncate cart;");
                System.exit(0);
            }else {
                CustomerCommand addCommand=new Add_Item_Command_Customer();
                addCommand.execute(item_id);
            }
        }

    }
}

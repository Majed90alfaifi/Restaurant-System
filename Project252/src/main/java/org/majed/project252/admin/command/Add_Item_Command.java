package org.majed.project252.admin.command;

import org.majed.project252.DB.DBConnection;
import org.majed.project252.admin.DisplayItems;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Add_Item_Command implements AdminCommand {
    int item_Id;
    String item_name;
    int item_Quantity;
    float item_Price;
    DisplayItems displayItems;
    private Admin_Receiver admin_receiver;
    Scanner scanner=new Scanner(System.in);
    DBConnection connection = DBConnection.getInstance();
    PreparedStatement findById = connection.getConnection().prepareStatement(
            "SELECT items_id FROM menu_items WHERE items_id = ?");
    PreparedStatement findByName = connection.getConnection().prepareStatement(
            "SELECT item_name FROM menu_items WHERE item_name = ?");
    ResultSet resultSet;

    public Add_Item_Command(Admin_Receiver admin_receiver) throws SQLException {
        this.admin_receiver = admin_receiver;
    }

    public void setItem_Id() throws SQLException {
        while (true){
            System.out.print("Enter Item ID, Must be new Id (dose`t already define): ");
            item_Id=scanner.nextInt();
            findById.setInt(1, item_Id);
            resultSet=findById.executeQuery();
            if(resultSet.next()) {
                System.out.println("\nThe ID: [" + item_Id + "] is already taken");
                continue;
            }else
                break;
        }
    }

    public void setItem_name() throws SQLException {
        System.out.print("add item name: ");
        while (true) {
            item_name = scanner.next();

            findByName.setString(1, item_name);
            resultSet = findByName.executeQuery();
            if (resultSet.next()) {
                System.out.println("\nThe name [" + item_name + "] is already taken" +
                        "\nPlease Try Another Name: ");
            } else
                break;
        }

    }

    public void setItem_Quantity() {
        System.out.print("Item Quantity: ");
        item_Quantity=scanner.nextInt();
    }

    public void setItem_Price() {
        System.out.print("Enter Item Price: ");
        item_Price=scanner.nextFloat();
    }
    @Override
    public void execute() throws SQLException {
        displayItems=new DisplayItems();
        System.out.println("*************\n  ADD ITEM\n*************");

        this.setItem_name();
        this.setItem_Id();
        this.setItem_Price();
        this.setItem_Quantity();

        admin_receiver=new Admin_Receiver();
        admin_receiver.add_item(item_Id,item_name,item_Quantity,item_Price);
    }


}



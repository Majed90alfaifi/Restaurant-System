package org.majed.project252.admin.command;

import org.majed.project252.DB.DBConnection;
import org.majed.project252.admin.DisplayItems;
import org.majed.project252.admin.page.AdminPage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Delete_Item_Command implements AdminCommand {
    int item_Id;
    DisplayItems displayItems;
    Scanner scanner=new Scanner(System.in);
    DBConnection connection = DBConnection.getInstance();
    Statement statement = connection.getConnection().createStatement();
    ResultSet resultSet;
    AdminPage adminPage;

    private Admin_Receiver admin_receiver;

    public Delete_Item_Command(Admin_Receiver admin_receiver) throws SQLException {
        this.admin_receiver = admin_receiver;
    }

    public void setItem_Id() throws SQLException {
        while (true){
            System.out.print("Enter Item ID :");
            item_Id=scanner.nextInt();
            resultSet=statement.executeQuery("SELECT item_name FROM menu_items where items_id = "+item_Id+";");
            if(resultSet.next()==false) {
                System.out.println("\nTHERE IS NO ITEM WITH THIS ID !!\nTry Again:");
            }
            else
                break;
        }
    }

    @Override
    public void execute() throws SQLException {
        displayItems=new DisplayItems();

        System.out.println("*************\n  DELETE ITEM\n*************");
        this.setItem_Id();

        admin_receiver=new Admin_Receiver();
        admin_receiver.delete_item(item_Id);
    }

}

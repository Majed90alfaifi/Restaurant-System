package org.majed.project252.admin.command;

import org.majed.project252.DB.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Update_Item_Price extends Update_Item_Command {
    int item_Id;
    String item_name;
    float item_Price;
    private Admin_Receiver admin_receiver;
    Scanner scanner=new Scanner(System.in);
    DBConnection connection = DBConnection.getInstance();
    PreparedStatement findById = connection.getConnection().prepareStatement(
            "SELECT * FROM menu_items WHERE items_id = ?");
    ResultSet resultSet;
     Update_Item_Price(Admin_Receiver admin_receiver) throws SQLException {
        super(admin_receiver);
        this.admin_receiver = admin_receiver;
    }

    @Override
    public void execute() throws SQLException {
        System.out.print("what item you want to update\nEnter its Id to update:" +
                "\nEnter 0 To >Exit");
        item_Id = scanner.nextInt();
        findById.setInt(1, item_Id);
        resultSet = findById.executeQuery();
        while (!resultSet.next()) {
            System.out.println("Enter Correct ID : ");
            item_Id = scanner.nextInt();
            findById.setInt(1, item_Id);
            resultSet = findById.executeQuery();
        }

        item_name = resultSet.getString(2);
        System.out.println("Change at " + item_name + " item");
        System.out.print("Enter New Price: ");
        item_Price=scanner.nextFloat();

        admin_receiver=new Admin_Receiver();
        admin_receiver.update_item_Price(item_Id,item_Price);

    }
}

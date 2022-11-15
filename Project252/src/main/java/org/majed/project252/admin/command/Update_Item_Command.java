package org.majed.project252.admin.command;

import org.majed.project252.admin.DisplayItems;

import java.sql.SQLException;
import java.util.Scanner;

public class Update_Item_Command implements AdminCommand {
    Scanner scanner=new Scanner(System.in);
    private Admin_Receiver admin_receiver;
    DisplayItems displayItems;
    public Update_Item_Command(Admin_Receiver admin_receiver){
        this.admin_receiver = admin_receiver;
    }
    @Override
    public void execute() throws SQLException {
        {
            System.out.println("*************\nUPDATE ITEM\n*************");
            while (true) {
                displayItems=new DisplayItems();
                System.out.println("> For change the price Enter 1");
                System.out.println("> For change the Quantity Enter 2");
                System.out.println("> Enter 0 to Exit:");
                int choice = scanner.nextInt();
                admin_receiver=new Admin_Receiver();

                if (choice == 1) {
                    Update_Item_Command update_item_command = new Update_Item_Price(admin_receiver);
                    update_item_command.execute();
                } else if (choice == 2) {
                    Update_Item_Command update_item_command = new Update_Item_Quantity(admin_receiver);
                    update_item_command.execute();

                } else if (choice == 0) {
                    break;
                } else
                    System.out.println("! Enter Correct Choice: ");
            }
        }
    }

}

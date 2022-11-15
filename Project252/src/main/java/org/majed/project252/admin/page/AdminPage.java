package org.majed.project252.admin.page;

import org.majed.project252.admin.command.*;

import java.sql.SQLException;
import java.util.Scanner;

public class AdminPage {
    AdminCommand adminCommand;
    Admin_Receiver admin_receiver;
    Scanner scanner =new Scanner(System.in);
    public void displayAdminPage() throws SQLException {
        while (true) {

            System.out.println("Welcome at Admin Page");
            System.out.println("Enter 1 for update item");
            System.out.println("Enter 2 fOR add item");
            System.out.println("Enter 3 for delete item");
            System.out.print("Enter 0 to Exit: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    adminCommand=new Update_Item_Command(admin_receiver);
                    adminCommand.execute();
                    break;
                case 2:
                    adminCommand=new Add_Item_Command(admin_receiver);
                    adminCommand.execute();
                    break;
                case 3:
                    adminCommand=new Delete_Item_Command(admin_receiver);
                    adminCommand.execute();
                    break;
                default:
                    System.out.println("Enter correct choice !");

            }
        }
    }

}

package org.majed.project252.register;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.majed.project252.DB.DBConnection;
import org.majed.project252.admin.page.AdminPage;
import org.majed.project252.customer.page.Menu;


public class Sign_in implements Register {
    String phone;
    String password;
    AdminPage adminPage ;
    Menu menu ;
    DBConnection connection = DBConnection.getInstance();
    Statement statement = connection.getConnection().createStatement();
    ResultSet resultSet;
    Scanner scanner = new Scanner(System.in);

    public Sign_in() throws SQLException {
    }

    @Override
    public void Register() throws SQLException {
        System.out.println("Enter Your Phone: ");
        checkPhoneForSignin();

        System.out.println("Enter your Password: ");
        checkPassword();

        checkAdmin_OR_Customer();
    }

    private void checkPassword() throws SQLException {
        while (true) {
            password = scanner.nextLine();
            password="'"+password+"'";
            resultSet = statement.executeQuery("SELECT * FROM customer where customerPass =" + password + "AND customerPhone =" + phone + ";");
            if (resultSet.next()) {
                break;
            }else{
                System.out.println("Wrong password !! .........\n please try again........");
            }
        }
    }
   private void checkPhoneForSignin() throws SQLException {
       while (true) {
           phone= scanner.nextLine();
           phone="'"+phone+"'";
           resultSet = statement.executeQuery("SELECT * FROM customer where customerPhone =" + phone + ";");
           if (resultSet.next()) {
               break;
           } else {
               System.out.println("Sorry >_< *We Can`t find your phone in the Database.\nEnter your phone Again .........");
           }
       }
    }

    private void checkAdmin_OR_Customer() throws SQLException {
        if(phone.equalsIgnoreCase("'0000'")) {
            adminPage=new AdminPage();
            adminPage.displayAdminPage();
        }else
            menu=new Menu();
            menu.displayMenu();
    }


}

package org.majed.project252.register;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
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
    PreparedStatement findByPhone = connection.getConnection().prepareStatement(
            "SELECT * FROM customer WHERE customerPhone = ?");
    PreparedStatement findByPhoneAndPassword = connection.getConnection().prepareStatement(
            "SELECT * FROM customer WHERE customerPass = ? AND customerPhone = ?");
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
            findByPhoneAndPassword.setString(1, password);
            findByPhoneAndPassword.setString(2, phone);
            resultSet = findByPhoneAndPassword.executeQuery();
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
           findByPhone.setString(1, phone);
           resultSet = findByPhone.executeQuery();
           if (resultSet.next()) {
               break;
           } else {
               System.out.println("Sorry >_< *We Can`t find your phone in the Database.\nEnter your phone Again .........");
           }
       }
    }

    private void checkAdmin_OR_Customer() throws SQLException {
        if(phone.equals("0000")) {
            adminPage=new AdminPage();
            adminPage.displayAdminPage();
        }else
            menu=new Menu();
            menu.displayMenu();
    }


}

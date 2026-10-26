package org.majed.project252.register;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Scanner;

import org.majed.project252.DB.DBConnection;

public class Sign_up implements Register{
    String phone;
    String password;
    String name;
    DBConnection connection = DBConnection.getInstance();
    PreparedStatement findByPhone = connection.getConnection().prepareStatement(
            "SELECT * FROM customer WHERE customerPhone = ?");
    PreparedStatement insertCustomer = connection.getConnection().prepareStatement(
            "INSERT INTO customer (`customerPhone`, `customerName`, `customerPass`) VALUES (?, ?, ?)");
    ResultSet resultSet;
    Scanner scanner = new Scanner(System.in);

    public Sign_up() throws SQLException {
        System.out.println("please enter your information :");
        System.out.println("Name: ");
        name = scanner.nextLine();


        System.out.println("Phone: ");
        phone=scanner.nextLine();
        checkPhoneForSignup();

        System.out.println("Password: ");
        password=scanner.nextLine();

    }

    private void checkPhoneForSignup() throws SQLException {
            findByPhone.setString(1, phone);
            resultSet = findByPhone.executeQuery();
            if (resultSet.next()) {
                System.out.println("YOU ALREADY HAVE AN ACCOUNT!!");
                Register sign_in = new Sign_in();
                sign_in.Register();
            }
    }

    @Override
    public void Register() throws SQLException {
        insertCustomer.setString(1, phone);
        insertCustomer.setString(2, name);
        insertCustomer.setString(3, password);
        insertCustomer.executeUpdate();

    }

}

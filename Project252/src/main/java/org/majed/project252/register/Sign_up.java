package org.majed.project252.register;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.majed.project252.DB.DBConnection;

public class Sign_up implements Register{
    String phone;
    String password;
    String name;
    DBConnection connection = DBConnection.getInstance();
    Statement statement = connection.getConnection().createStatement();
    ResultSet resultSet;
    Scanner scanner = new Scanner(System.in);

    public Sign_up() throws SQLException {
        System.out.println("please enter your information :");
        System.out.println("Name: ");
        name = scanner.nextLine();
        name="'"+name+"'";


        System.out.println("Phone: ");
        phone=scanner.nextLine();
        phone="'"+phone+"'";
        checkPhoneForSignup();

        System.out.println("Password: ");
        password=scanner.nextLine();
        password="'"+password+"'";

    }

    private void checkPhoneForSignup() throws SQLException {
            resultSet = statement.executeQuery("SELECT * FROM customer where customerPhone =" + phone + ";");
            if (resultSet.next()) {
                System.out.println("YOU ALREADY HAVE AN ACCOUNT!!");
                Register sign_in = new Sign_in();
                sign_in.Register();
            }
    }

    @Override
    public void Register() throws SQLException {
        statement.executeUpdate("INSERT INTO customer ( `customerPhone`, `customerName`, `customerPass`) VALUES ("+phone+", "+name+", "+password+")");

    }

}

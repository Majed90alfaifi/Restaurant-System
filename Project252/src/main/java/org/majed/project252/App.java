package org.majed.project252;


import java.sql.*;

import java.util.Scanner;

import org.majed.project252.register.*;
/**
 * Hello !
 * I AM MAJED
 * THIS IS CPIT 252 PROJECT
 */
public class App {
    public static void main(String[] args) throws SQLException {


        Scanner scanner =new Scanner(System.in);
        System.out.println("\n\nWELCOME TO MJ'S RESTAURANT APP");
        System.out.println("To Sign in Enter 1: ");
        System.out.println("To Sign up Enter 2: ");
        Register sign_in, sign_up;

        int choice =scanner.nextInt();
        switch (choice){
            case 1:
                System.out.println("**************************\n**********SIGN IN**********\n***************************\n");
                sign_in = new Sign_in();
                sign_in.Register();
                break;
            case 2:
                System.out.println("**************************\n**********SIGN UP**********\n***************************\n");
                sign_up = new Sign_up();
                sign_up.Register();
                //after finish sign up go to sign in page
                System.out.println("**************************\n**********SIGN IN**********\n***************************\n");
                sign_in = new Sign_in();
                sign_in.Register();
                break;
        }

    }
}

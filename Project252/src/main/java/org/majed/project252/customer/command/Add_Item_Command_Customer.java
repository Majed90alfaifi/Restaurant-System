package org.majed.project252.customer.command;

import org.majed.project252.DB.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Add_Item_Command_Customer implements CustomerCommand {
    int item_quantity;
    float item_price;
    int item_Real_quantity;

    DBConnection connection = DBConnection.getInstance();
    Statement statement = connection.getConnection().createStatement();
    ResultSet resultSet ;

    Scanner scanner =new Scanner(System.in);

    public Add_Item_Command_Customer() throws SQLException {
    }
    void total_price(int id){
        float totalPrice=item_price*item_quantity;
    }
    @Override
    public void execute(int id) throws SQLException {
        System.out.println("*************\n  ADD ITEM\n*************");
        if(!checkItemAvailability(id)){
            id=scanner.nextInt();
            this.execute(id);
        }else
        {
            this.item_Quantity(id);
            this.total_price(id);
            float totalPrice=item_price*item_quantity;
            resultSet = statement.executeQuery("SELECT item_id FROM cart where item_id ="+id+";");
            if(resultSet.next()){
                statement.executeUpdate("UPDATE cart SET item_quantity = "+item_quantity+" , total_price ="+totalPrice+" WHERE (item_id = "+id+");");
            }else {
                statement.executeUpdate("INSERT INTO cart (item_id, item_quantity, total_price) VALUES (" + id + ", " + item_quantity + ", "+totalPrice+");");
            }
        }

    }

    boolean checkItemAvailability(int item_id) throws SQLException {
        resultSet = statement.executeQuery("SELECT * FROM menu_items where items_id ="+item_id+";");
        if(resultSet.next()){
            return true;
        }else {
            System.out.println("Please enter correct Item: ");
            return false;
        }
    }


    void item_Quantity(int item_id) throws SQLException {
        resultSet = statement.executeQuery("SELECT * FROM menu_items where items_id ="+item_id+";");
        if(resultSet.next()) {
            item_Real_quantity = resultSet.getInt("item_quantity");
            item_price = resultSet.getFloat("item_price");

            System.out.println("How many of item you want ?");
            item_quantity = scanner.nextInt();
            while (item_quantity <= 0 || item_quantity > item_Real_quantity) {
                if (item_quantity <= 0) {
                    System.out.println("Enter a valid number !!");
                    item_quantity = scanner.nextInt();
                } else if (item_quantity > item_Real_quantity) {
                    System.out.println("you choose more than available");
                    item_quantity = scanner.nextInt();
                }
            }

            item_Real_quantity = item_Real_quantity - item_quantity;
            statement.executeUpdate("UPDATE menu_items SET item_quantity = " + item_Real_quantity + " WHERE (items_id = " + item_id + ");");
        }
    }


}

package org.majed.project252.customer.page;

import org.majed.project252.DB.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Recipt {
    DBConnection connection = DBConnection.getInstance();
    Statement statement = connection.getConnection().createStatement();
    ResultSet resultSet ;

    public Recipt() throws SQLException {
    }

    public void printReceipt() throws SQLException {


        ArrayList<Integer> arrayList=new ArrayList<>();
        resultSet=statement.executeQuery("SELECT item_id FROM cart;");
        while (resultSet.next()){
            arrayList.add(resultSet.getInt(1));
        }



        for (int x=0;x<arrayList.size();x++) {
            if(x==0){
                System.out.print("Item name:\t");
            }
            int id=arrayList.get(x);
            resultSet = statement.executeQuery("SELECT item_name FROM menu_items where items_id ="+id+";");
            while (resultSet.next()) {
                System.out.print(resultSet.getString(1) + "\t\t");
            }
        }
        System.out.println();

        resultSet=statement.executeQuery("SELECT item_id FROM cart;");
        for (int x=0;x<arrayList.size();x++) {
            if(x==0){
                System.out.print("item price:\t");
            }
            int id=arrayList.get(x);
            resultSet = statement.executeQuery("SELECT item_price FROM menu_items where items_id ="+id+";");
            while (resultSet.next()) {
                System.out.print(resultSet.getFloat(1) + "\t\t");
            }
        }
        System.out.println();


        resultSet=statement.executeQuery("SELECT item_id FROM cart;");
        for (int x=0;x<arrayList.size();x++) {
            if(x==0){
                System.out.print(" quantity:\t");
            }
            int id=arrayList.get(x);
            resultSet = statement.executeQuery("SELECT item_quantity FROM cart where item_id ="+id+";");
            while (resultSet.next()) {
                System.out.print(resultSet.getInt(1) + "\t\t\t");
            }
        }
        System.out.println();


        resultSet=statement.executeQuery("SELECT item_id FROM cart;");
        for (int x=0;x<arrayList.size();x++) {
            if(x==0){
                System.out.print("Total price:");
            }
            int id=arrayList.get(x);
            resultSet = statement.executeQuery("SELECT total_price FROM cart where item_id =" + id + ";");
            while (resultSet.next()) {
                System.out.print(resultSet.getFloat(1) + "\t\t");
            }
        }
        System.out.println("\n------------------------");



        //total price
        ArrayList<Float>arrayList1=new ArrayList<>();
        resultSet=statement.executeQuery("SELECT total_price FROM cart;");
        while (resultSet.next()){
            arrayList1.add(resultSet.getFloat(1));
        }
        float totalPrice=0;
        for(int i=0;i<arrayList1.size();i++){
            totalPrice=totalPrice+arrayList1.get(i);
        }
        System.out.println("Total Price :"+totalPrice);



    }


}

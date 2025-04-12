/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.javafxdb_sql_shellcode.db;

import org.example.javafxdb_sql_shellcode.App;
import org.example.javafxdb_sql_shellcode.Person;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author MoaathAlrajab
 */
public class ConnDbOps {
    final String MYSQL_SERVER_URL = "jdbc:mysql://csc311berio.mysql.database.azure.com/";
    final String DB_URL = MYSQL_SERVER_URL + "DBname";
    final String USERNAME = "berica";
    final String PASSWORD = "FARM123$";

    public boolean connectToDatabase() {
        boolean hasRegistredUsers = false;


        //Class.forName("com.mysql.jdbc.Driver");
        try {
            //First, connect to MYSQL server and create the database if not created
            Connection conn = DriverManager.getConnection(MYSQL_SERVER_URL, USERNAME, PASSWORD);
            Statement statement = conn.createStatement();
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS DBname");
            statement.close();
            conn.close();

            //Second, connect to the database and create the table "users" if cot created
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            statement = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS users ("
                    + "id INT( 10 ) NOT NULL PRIMARY KEY AUTO_INCREMENT,"
                    + "name VARCHAR(200) NOT NULL,"
                    + "email VARCHAR(200) NOT NULL UNIQUE,"
                    + "phone VARCHAR(200),"
                    + "address VARCHAR(200),"
                    + "password VARCHAR(200) NOT NULL"
                    + ")";
            statement.executeUpdate(sql);

            //check if we have users in the table users
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM users");

            if (resultSet.next()) {
                int numUsers = resultSet.getInt(1);
                if (numUsers > 0) {
                    hasRegistredUsers = true;
                }
            }

            statement.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return hasRegistredUsers;
    }

    public void queryUserByName(String name) {


        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String sql = "SELECT * FROM users WHERE name = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String address = resultSet.getString("address");
                System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email + ", Phone: " + phone + ", Address: " + address);
            }

            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listAllUsers() {


        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String sql = "SELECT * FROM users ";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String address = resultSet.getString("address");
                String password = resultSet.getString("password"); // added a get password line bc I couldn't remember some of them
                System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email + ", Phone: " + phone + ", Address: " + address + ", Password: " + password);
            }

            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertUser(String name, String email, String phone, String address, String password) {


        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String sql = "INSERT INTO users (name, email, phone, address, password) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, address);
            preparedStatement.setString(5, password);

            int row = preparedStatement.executeUpdate();

            if (row > 0) {
                System.out.println("A new user was inserted successfully.");
            }

            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /***
     * This will delete any user inputted with the same formating as when inserting
     * @param name
     * @param email
     * @param phone
     * @param address
     * @param password
     * @author Carlos Berio
     */
    public void deleteUser(String name, String email, String phone, String address, String password) {

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String sql = "DELETE FROM users WHERE name = ? AND email = ? AND phone = ? AND address = ?AND password = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, address);
            preparedStatement.setString(5, password);

            int row = preparedStatement.executeUpdate();

            if (row > 0) {
                System.out.println("A user was deleted successfully.");

            } else {
                System.out.println("No user was found with that information.");
            }

            preparedStatement.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /***
     *
     * @param email
     */

    public void editUser(String email){
        String input;
        Scanner scan  = new Scanner(System.in);

            try {
                Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                String sql = "SELECT * FROM users WHERE email = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, email);
                ResultSet resultSet = preparedStatement.executeQuery();

                   if(resultSet.next()) {
                       System.out.println("Which would you like to edit? please type one of the following:");
                       System.out.println("Name, Phone, Address, Password");
                   }else{System.out.println("No user found"); return;}

                    input = scan.next();

                    switch(input){
                        case "Name" :
                            System.out.println("What would you like to change the name to?");
                            String newName = scan.next();
                             String updateSql = "UPDATE users SET name = ? WHERE email = ?";
                             preparedStatement = conn.prepareStatement(updateSql);
                             preparedStatement.setString(1, newName);
                             preparedStatement.setString(2, email);

                             int rows = preparedStatement.executeUpdate();

                             if (rows > 0) {
                                 System.out.println("The new name is " + newName);
                             }
                             else{System.out.println("Failed to update");}

                            break;

                            case "Phone" :
                                System.out.println("What would you like to change the phone number to?");
                                String newPhone = scan.next();
                                updateSql = "UPDATE users SET phone = ? WHERE email = ?";
                                preparedStatement = conn.prepareStatement(updateSql);
                                preparedStatement.setString(1, newPhone);
                                preparedStatement.setString(2, email);

                                rows = preparedStatement.executeUpdate();

                                if (rows > 0) {
                                    System.out.println("The new phone number is " + newPhone);
                                }
                                else{System.out.println("Failed to update");}

                                break;

                            case "Address" :
                                System.out.println("What would you like to change the address to?");
                                String newAddress = scan.next();
                                updateSql = "UPDATE users SET address = ? WHERE email = ?";
                                preparedStatement = conn.prepareStatement(updateSql);
                                preparedStatement.setString(1, newAddress);
                                preparedStatement.setString(2, email);

                                rows = preparedStatement.executeUpdate();

                                if (rows > 0) {
                                    System.out.println("The new address is " + newAddress);
                                }
                                else{System.out.println("Failed to update");}

                                break;

                            case "Password" :
                                System.out.println("What would you like to change password to?");
                                String newPassword = scan.next();
                                updateSql = "UPDATE users SET password = ? WHERE email = ?";
                                preparedStatement = conn.prepareStatement(updateSql);
                                preparedStatement.setString(1, newPassword);
                                preparedStatement.setString(2, email);

                                rows = preparedStatement.executeUpdate();

                                if (rows > 0) {
                                    System.out.println("The new password is " + newPassword);
                                }
                                else{System.out.println("Failed to update");}

                                break;
                    }
                preparedStatement.close();
            } catch (SQLException e) {
            }
    }
}
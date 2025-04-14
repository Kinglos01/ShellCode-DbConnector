package org.example.javafxdb_sql_shellcode;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Scanner;

import org.example.javafxdb_sql_shellcode.db.ConnDbOps;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static ConnDbOps cdbop;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("splash"), 640, 480);
        stage.setScene(scene);
        mode("dark");
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));

        return fxmlLoader.load();
    }

    public static void mode(String mode) {
        if (mode.equals("light")) {
            scene.getStylesheets().add(App.class.getResource("light.css").toExternalForm());
        } else if (mode.equals("dark")) {
            scene.getStylesheets().add(App.class.getResource("dark.css").toExternalForm());
        }
    }

    public static void main(String[] args) {
        cdbop = new ConnDbOps();
        Scanner scan = new Scanner(System.in);
        Person person = null;

        char input;
        do {
            System.out.println(" ");
            System.out.println("============== Menu ==============");
            System.out.println("| To start GUI,           press 'g' |");
            System.out.println("| To connect to DB,       press 'c' |");
            System.out.println("| To display all users,   press 'a' |");
            System.out.println("| To insert to the DB,    press 'i' |");
            System.out.println("| To query by name,       press 'q' |");
            // added a way to delete inside the program with same formatting
            System.out.println("| To delete in the DB,    press 'd' |");
            // added a way to edit using email since it is the PK
            System.out.println("| To edit the DB,         press 't' |");
            System.out.println("| To exit,                press 'e' |");
            System.out.println("===================================");
            System.out.print("Enter your choice: ");
            input = scan.next().charAt(0);

            switch (input) {
                case 'g':
                     launch(args); //GUI
                    break;
                case 'c':
                    cdbop.connectToDatabase(); //Your existing method
                    break;
                case 'a':
                    cdbop.listAllUsers(); //all users in DB
                    break;
                case 'i':
                    System.out.print("Enter Name: ");
                    String name = scan.next();
                    System.out.print("Enter Email: ");
                    String email = scan.next();
                    System.out.print("Enter Phone: ");
                    String phone = scan.next();
                    System.out.print("Enter Address: ");
                    String address = scan.next();
                    System.out.print("Enter Password: ");
                    String password = scan.next();
                    person = new Person(name, email, phone, address, password); // adding a person to the person class
                    System.out.print("Person created in class: " + person.getName() + ' ' + person.getEmail() + ' ' + person.getPhone() + ' ' + person.getAddress() + ' ' + person.getPassword());
                    cdbop.insertUser(name, email, phone, address, password); //Your insertUser method
                    break;
                case 'q':
                    System.out.print("Enter the name to query: ");
                    String queryName = scan.next();
                   // if(person != null) {
                    //System.out.print(person.getName());}
                    cdbop.queryUserByName(queryName); //Your queryUserByName method
                    break;
                case 'd':  // delete user method
                    System.out.println("Please enter the following:");
                    System.out.print("Enter Name: ");
                    String delName = scan.next();
                    System.out.print("Enter Email: ");
                    String delEmail = scan.next();
                    System.out.print("Enter Phone: ");
                    String delPhone = scan.next();
                    System.out.print("Enter Address: ");
                    String delAddress = scan.next();
                    System.out.print("Enter Password: ");
                    String delPassword = scan.next();
//                    if(person != null) {
//                    person.removePerson(delName, delEmail, delPhone, delAddress, delPassword);}
                    cdbop.deleteUser(delName, delEmail, delPhone, delAddress, delPassword);
                    break;
                case 't':
                    System.out.print("Enter the email of the user you would like to edit: ");
                        email = scan.next();
                        cdbop.editUser(email);
                        break;
                case 'e':
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
            System.out.println(" ");
        } while (input != 'e');

        scan.close();
    }




}

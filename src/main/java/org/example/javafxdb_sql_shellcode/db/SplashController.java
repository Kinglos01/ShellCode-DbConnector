package org.example.javafxdb_sql_shellcode.db;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.example.javafxdb_sql_shellcode.App;

import java.io.IOException;

public class SplashController {

    @FXML
    private Button welcomeButton;

    @FXML
    public void OnWelcomeButtonClick(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }
}

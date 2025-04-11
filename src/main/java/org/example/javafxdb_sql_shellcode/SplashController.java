package org.example.javafxdb_sql_shellcode;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class SplashController {
    /***
     * Simple splash screen implementation
     */
    @FXML
    private Button welcomeButton;

    @FXML
    public void OnWelcomeButtonClick(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }
}

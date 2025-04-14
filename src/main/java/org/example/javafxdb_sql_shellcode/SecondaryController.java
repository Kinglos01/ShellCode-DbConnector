package org.example.javafxdb_sql_shellcode;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
public class SecondaryController {

    @FXML private Button signMode;

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    boolean light = false;

    @FXML
    private void signSwitch() {
        if (!light) {
            App.mode("light");
            light = true;
        } if(light) {
            App.mode("dark");
            light = false;
        }
    }

}
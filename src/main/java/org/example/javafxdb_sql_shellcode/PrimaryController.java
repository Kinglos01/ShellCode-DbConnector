package org.example.javafxdb_sql_shellcode;

import java.io.IOException;
import javafx.fxml.FXML;
import java.sql.*;
import javafx.scene.control.Button;


public class PrimaryController {

    @FXML
    private Button regMode;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    boolean light = false;

    @FXML
    private void regSwitch() {
            if (!light) {
                App.mode("light");
                light = true;
            }
            else{
                App.mode("dark");
                light = false;
            }

    }
}

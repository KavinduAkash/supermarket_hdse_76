package lk.ijse.supermarket_hdse_76.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import lk.ijse.supermarket_hdse_76.App;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}

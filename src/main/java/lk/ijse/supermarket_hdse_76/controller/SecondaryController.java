package lk.ijse.supermarket_hdse_76.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import lk.ijse.supermarket_hdse_76.App;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}
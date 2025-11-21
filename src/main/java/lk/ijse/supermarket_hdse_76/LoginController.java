package lk.ijse.supermarket_hdse_76;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField usernameField;
    
    @FXML
    private PasswordField passwordField;
           
    @FXML
    private void login() throws IOException {
        
        String realUsername = "KavinduAkash";
        String realPassword = "Admin@123";
        
        String username = usernameField.getText();
        String password = passwordField.getText();
        
        if(username.equals(realUsername) && password.equals(realPassword)) {
        
            System.out.println("Logged-in successfully!");
            
            App.setRoot("Customer");
            
        } else {
            
            System.out.println("Invalid username or password!");
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login message");
            alert.setHeaderText("Invalid username or password!");
            alert.show();
        
        }
        
        
    }
    
}

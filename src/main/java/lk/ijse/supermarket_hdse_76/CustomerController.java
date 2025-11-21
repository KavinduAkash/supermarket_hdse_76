package lk.ijse.supermarket_hdse_76;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CustomerController {  
    
    @FXML
    private TextField nameField;
    
    @FXML
    private TextArea addressField;
    
    @FXML
    private TextField salaryField;
    
    @FXML
    private void saveCustomer() {
    
        String name = nameField.getText();
        String address = addressField.getText();
        String salary = salaryField.getText();
        
        System.out.println(name + " - " + address + " - " + salary);
        
        try {
            Connection conn = DBConnection.getInstance().getConnection();
            
             String sql = "INSERT INTO customer (name, address, salary) VALUES (?,?,?)";
             
             PreparedStatement pstm = conn.prepareStatement(sql);

             pstm.setString(1, name);
             pstm.setString(2, address);
             pstm.setDouble(3, Double.parseDouble(salary));
             
             int results = pstm.executeUpdate();
            
             if(results > 0) {
                 
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
                 alert.setTitle("Customer Mgt");
                 alert.setHeaderText("Customer saved successfully!");
                 alert.show();
                 
             } else {
                 
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("Customer Mgt");
                 alert.setHeaderText("Something went wrong!");
                 alert.show();
                 
             }
             
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        System.out.println("Saved!!!");
        
    }
    
}

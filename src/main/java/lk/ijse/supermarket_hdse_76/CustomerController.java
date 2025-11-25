package lk.ijse.supermarket_hdse_76;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class CustomerController {  
    
    @FXML
    private TextField idField;
    
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
    
    @FXML
    private void handleSearchCustomer(KeyEvent event) {
        
        try {
            if(event.getCode() == KeyCode.ENTER) {
                String id = idField.getText();

                Connection conn = DBConnection.getInstance().getConnection();
                String sql = "SELECT * FROM customer WHERE id=?";
                
                PreparedStatement ptsm = conn.prepareStatement(sql);
                ptsm.setInt(1, Integer.parseInt(id));
                
                // SELECT - executeQuery , INSERT, UPDATE, DELETE - executeUpdate
            
                ResultSet rs = ptsm.executeQuery();
                
                if(rs.next()) {
                    int cusid = rs.getInt("id");
                    String cusName = rs.getString("name");
                    String cusAddress = rs.getString("address");
                    double cusSalary = rs.getDouble("salary");
                
                    nameField.setText(cusName);
                    addressField.setText(cusAddress);
                    salaryField.setText(String.valueOf(cusSalary));
                } else {
                    new Alert(Alert.AlertType.ERROR, "Customer not found!").show();
                }
            }
            
        } catch(Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
        
    }
    
}

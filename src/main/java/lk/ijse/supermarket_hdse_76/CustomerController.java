package lk.ijse.supermarket_hdse_76;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class CustomerController implements Initializable {  
    
    @FXML
    private TextField idField;
    
    @FXML
    private TextField nameField;
    
    @FXML
    private TextArea addressField;
    
    @FXML
    private TextField salaryField;

    
    private final CustomerModel customerModel = new CustomerModel();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        loadCustomerTable();
        
    }
    
    @FXML
    private void saveCustomer() {
    
        String name = nameField.getText();
        String address = addressField.getText();
        String salary = salaryField.getText();
        
        System.out.println(name + " - " + address + " - " + salary);
        
        try {
               
               CustomerDTO customerDTO = new CustomerDTO(name, address, Double.parseDouble(salary));
               
               boolean isSaved = customerModel.saveCustomer(customerDTO);
            
             if(isSaved) {
                 
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
                 alert.setTitle("Customer Mgt");
                 alert.setHeaderText("Customer saved successfully!");
                 alert.show();
                 
                 cleanFileds();
                 
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

                CustomerDTO customerDTO = customerModel.searchCustomer(id);
                
                if(customerDTO!=null) {
                    nameField.setText(customerDTO.getName());
                    addressField.setText(customerDTO.getAddress());
                    salaryField.setText(String.valueOf(customerDTO.getSalary()));
                } else {
                    new Alert(Alert.AlertType.ERROR, "Customer not found!").show();
                }
            }
            
        } catch(Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
        
    }
    
    @FXML
    private void handleUpdateCustomer() {
    
        try {
        
            String id = idField.getText();
            String name = nameField.getText();
            String address = addressField.getText();
            String salary = salaryField.getText();
             
             CustomerDTO customerDTO = new CustomerDTO(Integer.parseInt(id), name, address, Double.parseDouble(salary));
             
             boolean isUpdated = customerModel.updateCustomer(customerDTO);
            
             if(isUpdated) {
                 new Alert(Alert.AlertType.INFORMATION, "Customer updated successfully!").show();
                 cleanFileds();
             } else {
                 new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
             }
            
        } catch(Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
        
    } 
    
    @FXML
    private void handleDeleteCustomer() {
    
        try {
        
            String id = idField.getText();
            
             boolean isDeleted = customerModel.deleteCustomer(id);
            
             if(isDeleted) {
                 new Alert(Alert.AlertType.INFORMATION, "Customer deleted successfully!").show();
                 cleanFileds();
             } else {
                 new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
             }
            
        } catch(Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
        
    }
    
    @FXML
    private void handleResetFileds() {
        cleanFileds();
    }
    
    private void cleanFileds() {
        idField.setText("");
        nameField.setText("");
        addressField.setText("");
        salaryField.setText("");
    }
    
    private void loadCustomerTable() {
    
        try {
        
            List<CustomerDTO> customerList = customerModel.getAllCustomers();
            
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
    }
    
}

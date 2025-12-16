package lk.ijse.supermarket_hdse_76.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import lk.ijse.supermarket_hdse_76.dto.CustomerDTO;
import lk.ijse.supermarket_hdse_76.model.CustomerModel;

public class OrderController implements Initializable {

    private CustomerModel customerModel = new CustomerModel();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    } 
    
    private void loadCustomerIds() {
        try {
            List<CustomerDTO> customerList = customerModel.getAllCustomers();
            
        } catch(Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }
    
}

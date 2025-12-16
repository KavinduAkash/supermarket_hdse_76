package lk.ijse.supermarket_hdse_76.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import lk.ijse.supermarket_hdse_76.dto.CustomerDTO;
import lk.ijse.supermarket_hdse_76.model.CustomerModel;

public class OrderController implements Initializable {

    @FXML
    private ComboBox<String> comboCustomerId;
    
    private CustomerModel customerModel = new CustomerModel();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadCustomerIds();
    } 
    
    private void loadCustomerIds() {
        try {
            List<CustomerDTO> customerList = customerModel.getAllCustomers();
            
            ObservableList<String> obList = FXCollections.observableArrayList();
            
            for (CustomerDTO customerDTO : customerList) {
                obList.add(String.valueOf(customerDTO.getId()));
            }
            
            comboCustomerId.setItems(obList);
            
        } catch(Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }
    
}

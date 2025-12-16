package lk.ijse.supermarket_hdse_76.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import lk.ijse.supermarket_hdse_76.dto.CustomerDTO;
import lk.ijse.supermarket_hdse_76.dto.ItemDTO;
import lk.ijse.supermarket_hdse_76.model.CustomerModel;
import lk.ijse.supermarket_hdse_76.model.ItemModel;

public class OrderController implements Initializable {

    @FXML
    private ComboBox<String> comboCustomerId;
    
    @FXML
    private ComboBox<String> comboItemId;
     
    @FXML
    private Label lblCustomerAddressValue;

    @FXML
    private Label lblCustomerNameValue;

    @FXML
    private Label lblCustomerSalaryValue; 
    
    @FXML
    private Label lblItemNameValue;

    @FXML
    private Label lblItemPriceValue;

    @FXML
    private Label lblItemQtyValue;
    
    
    private CustomerModel customerModel = new CustomerModel();
    private ItemModel itemModel = new ItemModel();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadCustomerIds();
        loadItemIds();
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
    
    private void loadItemIds() {
        try {
            List<String> itemIdList = itemModel.getAllItemId();
            
            ObservableList<String> obList = FXCollections.observableArrayList();
            
            /*for (String id : itemIdList) {
                obList.add(id);
            }*/
            
            obList.addAll(itemIdList);
            
            comboItemId.setItems(obList);
        } catch(Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
    }
    
    @FXML
    private void handleSelectCustomerId(ActionEvent event) {

        try {
        
            String selectedId = comboCustomerId.getSelectionModel().getSelectedItem();
            CustomerDTO customerDTO = customerModel.searchCustomer(selectedId);

            lblCustomerNameValue.setText(customerDTO.getName());
            lblCustomerAddressValue.setText(customerDTO.getAddress());
            lblCustomerSalaryValue.setText(String.valueOf(customerDTO.getSalary()));
            
        } catch(Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
        
    }
    
    @FXML
    private void handleSelectItemId(ActionEvent event) {

        try {
        
            String selectedId = comboItemId.getSelectionModel().getSelectedItem();
            ItemDTO itemDTO = itemModel.searchItem(selectedId);
            
            lblItemNameValue.setText(itemDTO.getName());
            lblItemQtyValue.setText(String.valueOf(itemDTO.getQty()));
            lblItemPriceValue.setText(String.valueOf(itemDTO.getPrice()));
            
        } catch(Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong!").show();
        }
        
    }

    
}

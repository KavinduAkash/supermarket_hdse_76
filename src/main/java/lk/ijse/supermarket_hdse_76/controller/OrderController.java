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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.supermarket_hdse_76.dto.CustomerDTO;
import lk.ijse.supermarket_hdse_76.dto.ItemDTO;
import lk.ijse.supermarket_hdse_76.dto.tm.OrderItemTM;
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
    
    @FXML
    private TextField orderQtyField;
    
    
    
    @FXML
    private TableColumn<OrderItemTM, Integer> colItemId;

    @FXML
    private TableColumn<OrderItemTM, String> colItemName;

    @FXML
    private TableColumn<OrderItemTM, Double> colItemPrice;

    @FXML
    private TableColumn<OrderItemTM, Double> colItemTotal;

    @FXML
    private TableColumn<OrderItemTM, Integer> colOrderQty;
    
    @FXML
    private TableView<OrderItemTM> tblOrderItem;
    
    
    
    private CustomerModel customerModel = new CustomerModel();
    private ItemModel itemModel = new ItemModel();
    
    private ObservableList<OrderItemTM> orderItemObList = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colItemPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colOrderQty.setCellValueFactory(new PropertyValueFactory<>("orderQty"));
        colItemTotal.setCellValueFactory(new PropertyValueFactory<>("itemTotal"));
        
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
    
    @FXML
    private void handlAddToCart(ActionEvent event) {
        
        String itemId = comboItemId.getSelectionModel().getSelectedItem();
        String itemName = lblItemNameValue.getText();
        String itemQty = lblItemQtyValue.getText();
        String itemPrice = lblItemPriceValue.getText();
        String orderQty = orderQtyField.getText();
       
        
        OrderItemTM orderItemTM = new OrderItemTM(
                Integer.parseInt(itemId),
                itemName,
                Double.parseDouble(itemPrice),
                Integer.parseInt(orderQty),
                Double.parseDouble(itemPrice)*Integer.parseInt(orderQty)
        );
        
        orderItemObList.add(orderItemTM);
        
        tblOrderItem.setItems(orderItemObList);
    }

    
}

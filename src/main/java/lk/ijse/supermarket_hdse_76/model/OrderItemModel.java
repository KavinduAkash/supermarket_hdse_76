package lk.ijse.supermarket_hdse_76.model;

import java.sql.SQLException;
import java.util.List;
import lk.ijse.supermarket_hdse_76.dto.OrderItemDTO;
import lk.ijse.supermarket_hdse_76.util.CrudUtil;

public class OrderItemModel {

    private ItemModel itemModel = new ItemModel();
    
    public boolean saveOrderItems(int orderId, List<OrderItemDTO> orderItemList) throws Exception {
        
        for (OrderItemDTO orderItemDTO : orderItemList) {
            if(CrudUtil.execute(
                    "INSERT INTO order_items (order_id, item_id, qty, price) VALUES (?,?,?,?)", 
                    orderId,
                    orderItemDTO.getItemId(),
                    orderItemDTO.getQty(),
                    orderItemDTO.getPrice())) {
                
                if(!itemModel.decreaseItemQty(orderItemDTO.getItemId(), orderItemDTO.getQty())) {
                    // something went wrong
                    throw new Exception("Something went wrong when decreasing qty");
                }
                
            } else {
                // something went wrong
                throw new Exception("Something went wrong when inserting data into order items");
            }
        
        }
        
        return true;
    }
    
}

package lk.ijse.supermarket_hdse_76.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import lk.ijse.supermarket_hdse_76.db.DBConnection;
import lk.ijse.supermarket_hdse_76.dto.OrderDTO;
import lk.ijse.supermarket_hdse_76.util.CrudUtil;

public class OrderModel {
    
    private OrderItemModel orderItemModel = new OrderItemModel();
    
    public boolean placeOrder(OrderDTO orderDTO) throws Exception {
                
        Connection conn = DBConnection.getInstance().getConnection();
        
        try{
            conn.setAutoCommit(false);
            boolean isSavedOrder = CrudUtil.execute(
                "INSERT INTO orders (date, customer_id) VALUES (?,?)", 
                orderDTO.getOrderDate(),
                orderDTO.getCustomerId()
            );

            if(isSavedOrder) {
                ResultSet rs = CrudUtil.execute("SELECT id FROM orders ORDER BY id DESC LIMIT 1");
                if(rs.next()) {
                    int latestOrderId = rs.getInt("id");

                    orderItemModel.saveOrderItems(latestOrderId, orderDTO.getOrderItems());
                    
                } else {
                 // something went wrong
                 throw new Exception("Something went wrong when finding order id");
                }

            } else {
                // something went wrong
                throw new Exception("Something went wrong when inserting order");
            }
            
            conn.commit();
            return true;
            
        } catch(Exception e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
        
    }
    
}

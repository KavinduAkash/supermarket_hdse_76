package lk.ijse.supermarket_hdse_76.model;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import lk.ijse.supermarket_hdse_76.db.DBConnection;
import lk.ijse.supermarket_hdse_76.dto.OrderDTO;
import lk.ijse.supermarket_hdse_76.util.CrudUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

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
                    printOrderInvoice(latestOrderId);
                    
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
    
    public void printOrderInvoice(int orderId) throws JRException, SQLException {
    
        Connection conn = DBConnection.getInstance().getConnection();
        
        // Step 01
        InputStream reportObject = getClass().getResourceAsStream("/lk/ijse/supermarket_hdse_76/reports/invoice.jrxml");
        
        // Step 02
        JasperReport jr = JasperCompileManager.compileReport(reportObject); // this method thorws JRException
        
        // Step 03
        
        /*
        > There are can be more than one parameter (e.g. ORDER_ID)
        
        > List
        
        > Key-Value       
          e.g. ORDER_ID:21, CUSTOMER_ID:25
        
        [ORDER_ID:21, CUSTOMER_ID:25, CUSTOMER_NAME:Kavindu]
        */
        
        Map<String, Object> params = new HashMap<>();
        params.put("ORDER_ID", orderId);
        
        JasperPrint jp = JasperFillManager.fillReport(jr, params, conn); // fillReport(japerreport, params, connection_obj)
        
        // Step 04
        JasperViewer.viewReport(jp, false);
    
    }
    
}

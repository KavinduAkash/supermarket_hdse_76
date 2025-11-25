package lk.ijse.supermarket_hdse_76;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerModel {
 
    public boolean saveCustomer(String name, String address, double salary) throws SQLException {
             Connection conn = DBConnection.getInstance().getConnection();
            
             String sql = "INSERT INTO customer (name, address, salary) VALUES (?,?,?)";
             
             PreparedStatement pstm = conn.prepareStatement(sql);

             pstm.setString(1, name);
             pstm.setString(2, address);
             pstm.setDouble(3, salary);
             
             int results = pstm.executeUpdate();
             
             return results>0;
    }
    
}

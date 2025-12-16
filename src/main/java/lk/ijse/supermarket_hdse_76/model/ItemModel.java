package lk.ijse.supermarket_hdse_76.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lk.ijse.supermarket_hdse_76.util.CrudUtil;

public class ItemModel {

    public List<String> getAllItemId() throws SQLException {
        List<String> ids = new ArrayList<>();
        
        ResultSet rs = CrudUtil.execute("SELECT id FROM item");
        while(rs.next()) {
            String id = rs.getString("id");
            ids.add(id);
        }
        
        return ids;
    }
    
    public void getItem(String id) {
        
    }
    
}

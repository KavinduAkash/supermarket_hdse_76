package lk.ijse.supermarket_hdse_76.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lk.ijse.supermarket_hdse_76.dto.ItemDTO;
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
    
    public ItemDTO searchItem(String id) throws SQLException {
        
        ItemDTO itemDTO = null;
        
        ResultSet rs = CrudUtil.execute("SELECT * FROM item WHERE id=?",id);
        if(rs.next()) {    
            int itemId = rs.getInt("id");
            String itemName = rs.getString("name");
            int itemQty = rs.getInt("qty");
            double itemPrice = rs.getDouble("unit_price");
            
            itemDTO = new ItemDTO(itemId, itemName, itemQty, itemPrice);
        }
        
        return itemDTO;
    }
    
    public boolean decreaseItemQty(int itemId, int qty) throws SQLException {
    
        boolean isUpdated = CrudUtil.execute("UPDATE item SET qty=qty-? WHEREXXXXXXXXXXXXX id=?",
                qty,
                itemId);
        
        return isUpdated;
    }
}

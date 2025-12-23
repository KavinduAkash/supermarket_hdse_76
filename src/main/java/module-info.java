module lk.ijse.supermarket_hdse_76 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    requires net.sf.jasperreports.core;

    opens lk.ijse.supermarket_hdse_76.controller to javafx.fxml;
    opens lk.ijse.supermarket_hdse_76.dto to javafx.base;
    opens lk.ijse.supermarket_hdse_76.dto.tm to javafx.base;
    exports lk.ijse.supermarket_hdse_76;
    exports lk.ijse.supermarket_hdse_76.controller;
    exports lk.ijse.supermarket_hdse_76.dto;
    exports lk.ijse.supermarket_hdse_76.dto.tm;
}

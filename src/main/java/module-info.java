module com.hlanz.hwmanagertfg {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.hlanz.hwmanagertfg.Clases to javafx.fxml;
    exports com.hlanz.hwmanagertfg.Clases;
    opens com.hlanz.hwmanagertfg.Controllers to javafx.fxml;
    exports com.hlanz.hwmanagertfg.Controllers;
    opens com.hlanz.hwmanagertfg.Main to javafx.fxml;
    exports com.hlanz.hwmanagertfg.Main;
}
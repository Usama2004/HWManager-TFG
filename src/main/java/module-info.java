module com.hlanz.hwmanagertfg {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.hlanz.hwmanagertfg to javafx.fxml;
    exports com.hlanz.hwmanagertfg;
}
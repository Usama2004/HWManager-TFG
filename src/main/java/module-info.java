module com.hlanz.hwmanagertfg {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.hlanz.hwmanagertfg to javafx.fxml;
    exports com.hlanz.hwmanagertfg;
}
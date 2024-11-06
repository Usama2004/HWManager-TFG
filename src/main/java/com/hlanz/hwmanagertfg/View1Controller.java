package com.hlanz.hwmanagertfg;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class View1Controller {

    private Main mainApp; // Referencia a la clase principal MainApp

    // Método para establecer la referencia a MainApp
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    // Método manejador para cambiar a la segunda vista
    @FXML
    private void handleSwitchToView2() {
        mainApp.showView2(); // Llamar al método showView2() de MainApp
    }
}

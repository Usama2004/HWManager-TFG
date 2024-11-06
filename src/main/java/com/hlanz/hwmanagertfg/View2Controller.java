package com.hlanz.hwmanagertfg;

import javafx.fxml.FXML;

public class View2Controller {

    private Main mainApp; // Referencia a la clase principal MainApp

    //Método para establecer la referencia a MainApp
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    //Método manejador para cambiar a la primera vista
    @FXML
    private void handleSwitchToView1() {
        mainApp.showView1(); // Llamar al método showView1() de MainApp
    }
}

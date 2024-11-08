package com.hlanz.hwmanagertfg;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class loginController {

    private Main mainApp; // Referencia a la clase principal MainApp
    @FXML
    private HBox loginView_hbox_imv; // Contenedor de la imagen que sale en la ventana del Login

    // Método para establecer la referencia a MainApp
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    // Método para inicializar el controlador
    @FXML
    private void initialize() {

    }
}

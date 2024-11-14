package com.hlanz.hwmanagertfg;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class loginController {

    private Main mainApp; // Referencia a la clase principal MainApp

    @FXML
    private TextField textField_password;

    @FXML
    private TextField textField_user;

    @FXML
    void button_login_pulsado() {
        String txt_usuario = textField_user.getText();
        String txt_password = textField_password.getText();
    }

    @FXML
    void button_register_pulsado() {

    }

    // Método para establecer la referencia a MainApp
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
}

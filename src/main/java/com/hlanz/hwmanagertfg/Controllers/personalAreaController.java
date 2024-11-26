package com.hlanz.hwmanagertfg.Controllers;

import com.hlanz.hwmanagertfg.Main.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;

import java.util.ResourceBundle;

public class personalAreaController implements Initializable {

    private Main mainApp; // Referencia a la clase principal MainApp
    @FXML
    private CheckBox checkbox_completado;

    @FXML
    private CheckBox checkbox_pendiente;

    @FXML
    private ChoiceBox<String> choicebox_categoria;

    @FXML
    private DatePicker datepicker_fecha;

    @FXML
    void boton_crearPulsado() {

    }

    //Método para establecer la referencia a MainApp
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {

    }
}

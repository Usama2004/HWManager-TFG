package com.hlanz.hwmanagertfg;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.Screen;

public class Main extends Application {

    private StackPane rootPane; // El StackPane principal que contendrá todas las vistas

    @Override
    public void start(Stage primaryStage) throws Exception {
        rootPane = new StackPane(); // Inicializar el StackPane

        // Cargar las vistas desde los archivos FXML
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("loginView.fxml"));
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("view2.fxml"));

        // Cargar las vistas y verificar los tipos
        Node view1 = loader1.load();
        Node view2 = loader2.load();
        // Añadir las vistas al StackPane
        rootPane.getChildren().addAll(view1, view2);

        // Obtener los controladores de las vistas
        loginController controller1 = loader1.getController();
        controller1.setMainApp(this); // Pasar la referencia de MainApp al controlador de la vista 1

        View2Controller controller2 = loader2.getController();
        controller2.setMainApp(this); // Pasar la referencia de MainApp al controlador de la vista 2

        // Inicialmente, mostrar solo la primera vista
        ((Node) loader2.getRoot()).setVisible(false);

        // Crear la escena con el StackPane
        Scene scene = new Scene(rootPane, 800, 600);
        primaryStage.setScene(scene); // Establecer la escena en el escenario principal
        // Maximizar la ventana y deshabilitar el redimensionamiento
        primaryStage.setMaximized(true); // Maximiza la ventana
        primaryStage.setResizable(false); // Evita que la ventana se pueda redimensionar
        primaryStage.show(); // Mostrar el escenario

        // Obtener las dimensiones de la ventana después de que se haya mostrado
        System.out.println("Ancho de la ventana: " + primaryStage.getWidth());
        System.out.println("Alto de la ventana: " + primaryStage.getHeight());
    }

    // Método para mostrar la primera vista
    public void showView1() {
        rootPane.getChildren().get(0).setVisible(true); // Hacer visible la primera vista
        rootPane.getChildren().get(1).setVisible(false); // Hacer invisible la segunda vista
    }

    // Método para mostrar la segunda vista
    public void showView2() {
        rootPane.getChildren().get(0).setVisible(false); // Hacer invisible la primera vista
        rootPane.getChildren().get(1).setVisible(true); // Hacer visible la segunda vista
    }

    public static void main(String[] args) {
        launch(args); // Lanzar la aplicación JavaFX
    }
}

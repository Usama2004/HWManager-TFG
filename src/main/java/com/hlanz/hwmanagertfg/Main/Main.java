package com.hlanz.hwmanagertfg.Main;

import com.hlanz.hwmanagertfg.Controllers.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    //Este el StackPane principal que contendrá todas las vistas
    private StackPane rootPane;
    //Variables de instancia para el controlador
    private personalAreaController personalAreaController;
    private loginController loginController;
    private registerController registerController;
    private crearTarea_PersonalAreaController crearTarea_PersonalAreaController;
    private groupController groupController;
    private createGroupController createGroupController;
    private contenidoGrupoController contenidoGrupoController;
    private crearApartadoGrupoController crearApartadoGrupoController;
    private calendarioController calendarioController;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Inicializamos el StackPane
        rootPane = new StackPane();

        // Cargarmos las vistas desde los archivos FXML
        FXMLLoader login = new FXMLLoader(getClass().getResource("/com/hlanz/hwmanagertfg/Views/loginView.fxml"));
        FXMLLoader register = new FXMLLoader(getClass().getResource("/com/hlanz/hwmanagertfg/Views/registerView.fxml"));
        FXMLLoader personalArea = new FXMLLoader(getClass().getResource("/com/hlanz/hwmanagertfg/Views/personalAreaView.fxml"));
        FXMLLoader crearTareaPersonalArea = new FXMLLoader(getClass().getResource("/com/hlanz/hwmanagertfg/Views/crearTareaPersonalAreaView.fxml"));
        FXMLLoader group = new FXMLLoader(getClass().getResource("/com/hlanz/hwmanagertfg/Views/groupView.fxml"));
        FXMLLoader createGroup = new FXMLLoader(getClass().getResource("/com/hlanz/hwmanagertfg/Views/createGroupView.fxml"));
        FXMLLoader contenidoGroup = new FXMLLoader(getClass().getResource("/com/hlanz/hwmanagertfg/Views/contenidoGrupoView.fxml"));
        FXMLLoader crearApartadoGrupo = new FXMLLoader(getClass().getResource("/com/hlanz/hwmanagertfg/Views/crearApartadoGrupoView.fxml"));
        FXMLLoader calendario = new FXMLLoader(getClass().getResource("/com/hlanz/hwmanagertfg/Views/calendarioView.fxml"));

        //Almacenamos las vistas en variables tipo Node para poder trabajar con ellas
        Node loginView = login.load();
        Node registerView = register.load();
        Node personalAreaView = personalArea.load();
        Node crearTareaPersonalAreaView = crearTareaPersonalArea.load();
        Node groupView = group.load();
        Node createGroupView = createGroup.load();
        Node contenidoGrupoView = contenidoGroup.load();
        Node crearApartadoGrupoView = crearApartadoGrupo.load();
        Node calendarioView = calendario.load();

        // Añadimos las vistas al StackPane para poder mostrar unas y ocultar otras en base a
        //nuestras preferencias.
        rootPane.getChildren().addAll(loginView, registerView, personalAreaView,
                crearTareaPersonalAreaView, groupView, createGroupView, contenidoGrupoView,
                crearApartadoGrupoView, calendarioView);

        // Obtener los controladores de las vistas
        loginController = login.getController();
        loginController.setMainApp(this); // Pasar la referencia de MainApp al controlador de la vista 1

        registerController = register.getController();
        registerController.setMainApp(this); // Pasar la referencia de MainApp al controlador de la vista 2

        personalAreaController = personalArea.getController();
        personalAreaController.setMainApp(this);

        crearTarea_PersonalAreaController = crearTareaPersonalArea.getController();
        crearTarea_PersonalAreaController.setMainApp(this);

        groupController = group.getController();
        groupController.setMainApp(this);

        createGroupController = createGroup.getController();
        createGroupController.setMainApp(this);

        contenidoGrupoController = contenidoGroup.getController();
        contenidoGrupoController.setMainApp(this);

        crearApartadoGrupoController = crearApartadoGrupo.getController();
        crearApartadoGrupoController.setMainApp(this);

        calendarioController = calendario.getController();
        calendarioController.setMainApp(this);
        /*
        La razón por la que se usa setMainApp es para pasar una referencia de la clase
        principal Main a los controladores. Esto permite que los controladores
        puedan llamar a los métodos del Main para cambiar entre las vistas.

        RAZONES POR LAS QUE SE DEBE PASAR UNA REFERENCIA DE MAIN A LOS CONTROLADORES:

        - Acceso a Métodos de Cambio de Vista: Los controladores necesitan llamar a los
        métodos showView1().....showView¿?() del Main para cambiar entre las vistas. Al pasar
        la referencia de Main, los controladores pueden acceder a estos métodos.

        - Comunicación entre Controladores y Main: Esto facilita la comunicación
        entre los controladores y la clase principal, permitiendo una mejor gestión
        de las vistas y la lógica de la aplicación.
        */

        //Tenemos que mostrar una vista en principio, por eso mostramos la vista del login
        //y ocultmos las demás vistas. Se tiene que hacer el casting a Node debido a que en
        //principio se tiene un Object por lo cual no podemos acceder al setVisible.
        ((Node) login.getRoot()).setVisible(true);
        ((Node) register.getRoot()).setVisible(false);
        ((Node) personalArea.getRoot()).setVisible(false);
        ((Node) crearTareaPersonalArea.getRoot()).setVisible(false);
        ((Node) group.getRoot()).setVisible(false);
        ((Node) createGroup.getRoot()).setVisible(false);
        ((Node) contenidoGroup.getRoot()).setVisible(false);
        ((Node) crearApartadoGrupo.getRoot()).setVisible(false);
        ((Node) calendario.getRoot()).setVisible(false);

        // Creamos la escena con el StackPane
        Scene scene = new Scene(rootPane, 675, 460);
        primaryStage.setScene(scene); // Establecer la escena en el escenario principal
        primaryStage.setResizable(false);
        primaryStage.show(); // Mostrar el escenario
    }

    //Metodos para ir cambiando entre las diferentes vistas

    // Método para mostrar la 1 vista
    public void showLoginView() {
        rootPane.getChildren().forEach(node -> node.setVisible(false));
        rootPane.getChildren().get(0).setVisible(true); // Mostrar la primera vista
    }

    // Método para mostrar la 2 vista
    public void showRegisterView() {
        rootPane.getChildren().forEach(node -> node.setVisible(false));
        rootPane.getChildren().get(1).setVisible(true); // Mostrar la segunda vista
    }

    // Método para mostrar la 3 vista
    public void showPersonalAreaView() {
        rootPane.getChildren().forEach(node -> node.setVisible(false));
        rootPane.getChildren().get(2).setVisible(true); // Mostrar la tercera vista
        personalAreaController.cargarTareas(); // Llamar al método cargarTareas del controlador
    }

    // Método para mostrar la 4 vista
    public void showCrearTareaPersonalAreaView() {
        rootPane.getChildren().forEach(node -> node.setVisible(false));
        rootPane.getChildren().get(3).setVisible(true); // Mostrar la cuarta vista
    }

    // Método para mostrar la 5 vista
    public void showGroupView() {
        rootPane.getChildren().forEach(node -> node.setVisible(false));
        rootPane.getChildren().get(4).setVisible(true); // Mostrar la quinta vista
    }

    // Método para mostrar la 6 vista
    public void showCreateGroupView() {
        rootPane.getChildren().forEach(node -> node.setVisible(false));
        rootPane.getChildren().get(5).setVisible(true); // Mostrar la sexta vista
    }

    // Método para mostrar la 7 vista
    public void showContenidoGroupView() {
        rootPane.getChildren().forEach(node -> node.setVisible(false));
        rootPane.getChildren().get(6).setVisible(true); // Mostrar la séptima vista
    }

    // Método para mostrar la 8 vista
    public void showCrearApartadoGrupoView() {
        rootPane.getChildren().forEach(node -> node.setVisible(false));
        rootPane.getChildren().get(7).setVisible(true); // Mostrar la octava vista
    }

    // Método para mostrar la 9 vista
    public void showCalendarioView() {
        rootPane.getChildren().forEach(node -> node.setVisible(false));
        rootPane.getChildren().get(8).setVisible(true); // Mostrar la novena vista
    }

    public static void main(String[] args) {
        launch(args); // Lanzar la aplicación JavaFX
    }
}

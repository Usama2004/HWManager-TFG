package com.hlanz.hwmanagertfg.Clases;

public class UserSession {
    // Variable estática que contiene la única instancia de la clase
    private static UserSession instance;

    // Variables para almacenar información del usuario
    private int userId;
    private String username;

    // Constructor privado para evitar la creación de instancias desde fuera de la clase
    private UserSession() {}

    //Método estático para obtener la única instancia de la clase
    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    // Métodos para establecer y obtener el ID del usuario
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    // Métodos para establecer y obtener el nombre de usuario
    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}

package com.example.pruebaapi;

public class Empleado {
    // id, nombre, domicilio, telefono, email, habilitado(int), password
    private  int id;
    private String nombre;
    private String domicilio;
    private String telefono;
    private String email;
    private String password;
    private int habilitado;

    public Empleado(int id, String nombre, String domicilio, String telefono, String email, String password, int habilitado) {
        this.id = id;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
        this.habilitado = habilitado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(int habilitado) {
        this.habilitado = habilitado;
    }
}

package com.example.usuario.app_agenda1;

/**
 * Created by usuario on 04/12/2017.
 */

public class Usuario {
    private int id;
    private String ci;
    private String email;
    private String lastName;
    private String name;
    private String userName;

    public Usuario() {
    }

    public Usuario(int id,String ci, String email, String lastName, String name, String userName) {
        this.ci = ci;
        this.id = id;
        this.email = email;
        this.lastName = lastName;
        this.name = name;
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "ci='" + ci + '\'' +
                ", email='" + email + '\'' +
                ", lastName='" + lastName + '\'' +
                ", name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}

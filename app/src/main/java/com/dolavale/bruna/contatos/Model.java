package com.dolavale.bruna.contatos;

public class Model {

    private String phoneNumber;
    private String name;
    private String email;
    private String city;

    public Model(String phoneNumber, String name, String email, String city) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.email = email;
        this.city = city;
    }

    @Override
    public String toString() {
        return name + "\n" + email + "\n" + phoneNumber + "\n" + city + "\n";
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

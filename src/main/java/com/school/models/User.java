package com.school.models;

public abstract class User {

    protected String name;
    protected String surname;
    protected String password;
    protected Integer id;
    protected String mail;
    private String status;

    public User(){}

    public User(String name, String surname, String password, String mail, String status) {

        this.name = name;
        this.surname = surname;
        this.password = password;
        this.mail = mail;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override

    public String toString(){

        String info = String.format("\nName: %s\nSurname: $%s%nLogin: %s%n", this.name, this.surname, this.id);
        return info;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public String getSurname(){
        return this.surname;
    }

    public Integer getId(){
        return this.id;
    }

    public void setSurname(String surname){
        this.surname = surname;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return this.password;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public void setMail(String mail){
        this.mail = mail;
    }

    public String getMail(){
        return this.mail;
    }

}
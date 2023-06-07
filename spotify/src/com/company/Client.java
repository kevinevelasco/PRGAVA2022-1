package com.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class Client implements Serializable {

    private static Long nextId = 0L; //variable estática que se va a usar en un método de clase - que también es estática.

    protected Long id;
    protected String user;
    protected String password;
    protected String name;
    protected String lastName;
    protected Integer age;

    public Client(Long id, String user, String password, String name, String lastName, Integer age) {
        this.id = getNextId();
        this.user = user;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    private Long getNextId() {
    Long next = ++nextId;
    return next;
    }

    public Client(){

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


}

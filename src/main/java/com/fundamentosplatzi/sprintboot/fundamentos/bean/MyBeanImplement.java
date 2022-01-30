package com.fundamentosplatzi.sprintboot.fundamentos.bean;

public class MyBeanImplement implements MyBean {
    @Override
    public void print() {
        System.out.println("Hola desde mi implementacion propia en el bean");
    }
}

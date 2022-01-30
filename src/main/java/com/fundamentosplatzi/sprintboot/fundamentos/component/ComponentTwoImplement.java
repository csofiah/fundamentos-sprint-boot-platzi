package com.fundamentosplatzi.sprintboot.fundamentos.component;

import org.springframework.stereotype.Component;

@Component
public class ComponentTwoImplement implements ComponentDependency {
    @Override
    public void saludar() {
        System.out.println("Hola mundo desde componente2");
    }
}

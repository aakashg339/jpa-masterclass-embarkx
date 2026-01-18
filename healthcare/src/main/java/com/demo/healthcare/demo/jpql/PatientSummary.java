package com.demo.healthcare.demo.jpql;

public class PatientSummary {
    
    private String name;
    
    private int age;
    
    public PatientSummary(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "PatientSummary [name=" + name + ", age=" + age + "]";
    }

}

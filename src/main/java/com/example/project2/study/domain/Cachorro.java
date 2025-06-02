package com.example.project2.study.domain;

public class Cachorro extends AnimalBase implements Animal {
    @Override
    public String eat() {
        return "RAÇÃO";
    }

    @Override
    public Long sleep() {
        return Long.valueOf(5);
    }

    @Override
    public String makeSound() {
        return "AU AU AU";
    }

    @Override
    public String move() {
        return "QUADRUPEDE";
    }
}

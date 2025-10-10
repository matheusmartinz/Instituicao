package com.example.project.study.domain;

public class Passaro implements Animal{
    @Override
    public String eat() {
        return "SEMENTE";
    }

    @Override
    public Long sleep() {
        return 10L;
    }

    @Override
    public String makeSound() {
        return "PIU PIU";
    }

    @Override
    public String move() {
        return "VOANDO";
    }
}

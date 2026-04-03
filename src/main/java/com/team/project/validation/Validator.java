package com.team.project.validation;

public abstract class Validator {
    protected Validator next;

    public void setNext(Validator next) {
        this.next = next;
    }

    public abstract boolean validate(String data);
}

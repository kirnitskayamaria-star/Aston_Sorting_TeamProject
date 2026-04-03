package com.team.project.validation;

public class EmptyValidator extends Validator {
    @Override
    public boolean validate(String data) {
        if (data == null || data.trim().isEmpty()) {
            System.out.println("[Error] Data cannot be empty!");
            return false;
        }
        return next == null || next.validate(data);
    }
}

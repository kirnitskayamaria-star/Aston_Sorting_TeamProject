package com.team.project.validation;

public class NumberValidator extends Validator {
    @Override
    public boolean validate(String data) {
        try {
            int value = Integer.parseInt(data);
            if (value <= 0) {
                System.out.println("[Error] Number must be positive: " + data);
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("[Error] Not a valid number: " + data);
            return false;
        }
        return next == null || next.validate(data);
    }
}

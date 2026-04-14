package com.team.project.validation;

public class CarValidator {

    public static boolean validateAll(String model, String power, String year) {
        Validator stringChain = new EmptyValidator();

        Validator numberChain = new EmptyValidator();
        numberChain.setNext(new NumberValidator());
        boolean isModelOk = stringChain.validate(model);
        boolean isPowerOk = numberChain.validate(power);
        boolean isYearOk = numberChain.validate(year);

        return isModelOk && isPowerOk && isYearOk;
    }
}

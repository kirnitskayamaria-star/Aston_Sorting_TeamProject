package com.team.project.validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class CarValidatorTest {
    @Test
    @DisplayName("Валидатор должен отклонять некорректные данные")
    void validatorShouldReturnFalse() {
        boolean isValid = CarValidator.validateAll("Lada", "lada", "t");
        assertFalse(isValid, "Данные 'lada' и 't' не должны считаться валидными");
    }
}

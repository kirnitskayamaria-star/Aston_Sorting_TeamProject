package com.team.project.validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CarValidatorTest {
    @Test
    @DisplayName("Валидатор должен отклонять некорректные данные")
    void validatorShouldReturnFalse() {
        boolean isValid = CarValidator.validateAll("Lada", "lada", "t");
        assertFalse(isValid, "Данные 'lada' и 't' не должны считаться валидными");
    }

    @Test
    @DisplayName("Проверка валидатора с корректными значениями")
    void validatorShouldReturnTrueForValidData() {
        boolean isValid = CarValidator.validateAll("Toyota", "150", "2020");
        assertTrue(isValid, "Валидные данные (Toyota, 150, 2020) должны быть приняты");
    }

    @Test
    @DisplayName("Проверка валидатора на пустые строки")
    void validatorShouldReturnFalseForEmptyFields() {
        assertAll(
                () -> assertFalse(CarValidator.validateAll("", "100", "2010"), "Пустая модель"),
                () -> assertFalse(CarValidator.validateAll("BMW", "  ", "2010"), "Пробелы в мощности"),
                () -> assertFalse(CarValidator.validateAll("Audi", "150", ""), "Пустой год")
        );
    }
}

package com.team.project.validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CarValidatorTest {
    @Test
    @DisplayName("Negative check: reject non-numeric and incorrect data")
    void validatorShouldReturnFalse() {
        boolean isValid = CarValidator.validateAll("Lada", "lada", "t");
        assertFalse(isValid, "Alphabetic values like 'lada' and 't' should not be valid for numeric fields");
    }

    @Test
    @DisplayName("Positive check: accept valid model and correct numeric data")
    void validatorShouldReturnTrueForValidData() {
        boolean isValid = CarValidator.validateAll("Toyota", "150", "2020");
        assertTrue(isValid, "Correct data (Toyota, 150, 2020) should be accepted");
    }

    @Test
    @DisplayName("Negative check: reject empty strings and blank fields")
    void validatorShouldReturnFalseForEmptyFields() {
        assertAll(
                () -> assertFalse(CarValidator.validateAll("", "100", "2010"), "Empty model"),
                () -> assertFalse(CarValidator.validateAll("BMW", "  ", "2010"), "Blank power field"),
                () -> assertFalse(CarValidator.validateAll("Audi", "150", ""), "Empty year")
        );
    }
}

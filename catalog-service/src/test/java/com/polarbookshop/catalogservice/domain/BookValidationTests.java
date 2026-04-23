package com.polarbookshop.catalogservice.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

class BookValidationTests {
    
    private static Validator validator;
    
    @BeforeAll
    static void setUp() {
	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	validator = factory.getValidator();
    }

    @Test
    void whenAllFieldsValidThenValidationSucceeds() {
	var book = new Book("1234567890", "Title First", "Sumeet Patil", 6.86);
	Set<ConstraintViolation<Book>> voilations = validator.validate(book);
	assertThat(voilations).isEmpty();
    }
    
    @Test
    void whenIsbnDefinedButIncorrectThenValidationFails() {
	var book = new Book("123","Title First", "Sumeet Patil", 6.86);
	Set<ConstraintViolation<Book>> violation = validator.validate(book);
	assertThat(violation).hasSize(1).isNotEmpty();
	assertThat(violation.iterator().next().getMessage()).isEqualTo("The ISBN format must follow the standards ISBN-10 or ISBN-13.");
    }
}

package com.karim;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class EmailValidatorTest {

    private final EmailValidator underTest = new EmailValidator();

    @ParameterizedTest
    @CsvSource({
            "hello@amigoscode.com, true",
            "meow+meowington@amigoscode.com, true",
            "hello@amigoscodecom, false",
            "nelson, false",
            "helloamigoscode.com, false",
            "'', false",
    })
    void canValidateEmail(String email, boolean expected) {
        // when
        var actual = underTest.test(email);
        // then
        assertThat(actual).isEqualTo(expected);
    }

}
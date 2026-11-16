package com.karim;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class  CalculatorTest {
    private Calculator underTest;

    @BeforeEach
    void setUp() {
        underTest = new Calculator();
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void canAdd2Numbers() {
        // 1 - given
        var number1 = 3;
        var number2 = 3;
        // 2 - when
        var actual = underTest.add(number1, number2);
        // 3 - then
        var expected = 6;
        assertThat(actual).isEqualTo(expected);
    }
}
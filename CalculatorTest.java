package org.example.lesson14juni;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void calculateResultTest() {
        String string = "10+20/3-5*5" ;
        String expected= "10.0 + 20.0 / 3.0 - 5.0 * 5.0 = 25.0";
        Calculator calculator=new Calculator();
        String actual= calculator.calculateResult(string);
       assertEquals(expected, actual);
    }
}
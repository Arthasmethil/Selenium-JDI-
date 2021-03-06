package com.epam.tc.hw1.functions;

import com.epam.tat.module4.Calculator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class AbstractCalculatorOperationTest {
    Calculator calculator;

    @BeforeClass(groups = {"simpleOperations", "complexOperations"})
    public void setUp() {
        calculator = new Calculator();
    }

    @AfterClass(groups = {"simpleOperations", "complexOperations"})
    public void tearDown() {
        calculator = null;
    }
}

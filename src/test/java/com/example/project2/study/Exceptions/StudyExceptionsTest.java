package com.example.project2.study.Exceptions;

import org.testng.annotations.Test;

public class StudyExceptionsTest {

    @Test(expectedExceptions = StudyExceptionsTest.class,
    expectedExceptionsMessageRegExp = "FALHOU AI")
    public void test() {
        throw new StudyExceptions("FALHOU AI");
    }
}

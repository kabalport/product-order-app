package com.example.productorderapp;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FirstTest {
    @Test
    void test(){
        Assertions.assertEquals("1","1");
    }

    @Test
    void test2(){
        Approvals.verify("Hello World!");
    }

}

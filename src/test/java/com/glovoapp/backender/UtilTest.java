package com.glovoapp.backender;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilTest {
    @Test
    void getProperty() {
        assertEquals("5",Util.getProperty("hide.strategy.howFar"));
    }



}
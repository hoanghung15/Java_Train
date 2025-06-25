package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    Main main = new Main();

    @Test
    void tong() {
        assertEquals(9,main.tong(7,2));
    }

    @Test
    void sum() {
        assertEquals(9,Main.sum(7,3));
    }
}
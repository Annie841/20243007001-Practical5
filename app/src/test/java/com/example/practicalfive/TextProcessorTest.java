package com.example.practicalfive;

import org.junit.Test;
import static org.junit.Assert.*;

public class TextProcessorTest {

    TextProcessor processor = new TextProcessor();

    @Test
    public void testBeijing() {
        assertEquals("Beijing",
                processor.processMunicipality("beijing"));
    }

    @Test
    public void testShanghai() {
        assertEquals("Shanghai",
                processor.processMunicipality("SHANGHAI"));
    }

    @Test
    public void testTianjin() {
        assertEquals("Tianjin",
                processor.processMunicipality("tianjin"));
    }

    @Test
    public void testChongqing() {
        assertEquals("Chongqing",
                processor.processMunicipality("ChOnGqInG"));
    }

    @Test
    public void testGuangzhou() {
        assertNull(processor.processMunicipality("Guangzhou"));
    }

    @Test
    public void testEmptyInput() {
        assertNull(processor.processMunicipality(""));
    }

    @Test
    public void testWhitespaceInput() {
        assertNull(processor.processMunicipality("   "));
    }

    @Test
    public void testNullInput() {
        assertNull(processor.processMunicipality(null));
    }
}
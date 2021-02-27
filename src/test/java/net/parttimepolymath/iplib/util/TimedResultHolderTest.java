package net.parttimepolymath.iplib.util;

import net.parttimepolymath.iplib.model.IPRanges;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimedResultHolderTest {
    private TimedResultHolder instance;

    @BeforeEach
    void setUp() {
        instance = new TimedResultHolder(new IPRanges(), 1);
    }

    @Test
    void getRanges() {
        assertNotNull(instance.getRanges());
    }

    @Test
    void hasRanges() throws InterruptedException {
        assertTrue(instance.hasRanges());
        Thread.sleep(1100);
        assertFalse(instance.hasRanges());
    }

    @Test
    void setResult() throws InterruptedException {
        assertTrue(instance.hasRanges());
        Thread.sleep(1100);
        assertFalse(instance.hasRanges());
        instance.setResult(new IPRanges());
        assertTrue(instance.hasRanges());
    }
}
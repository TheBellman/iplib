package net.parttimepolymath.iplib;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IPRangeTest {

    private static IPRange instance;

    @BeforeAll
    static void setUp() throws IOException, InterruptedException {
        instance = new IPRange();
    }

    @Test
    void getServices() {
        List<String> result = instance.getServices();
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    void getRegions() {
        List<String> result = instance.getRegions();
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    void getPrefixes() {
        List<String> result = instance.getPrefixes(false);
        assertNotNull(result);
        assertFalse(result.isEmpty());

        result = instance.getPrefixes(true);
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    void getPrefixesWithRegion() {
        List<String> allPrefixes = instance.getPrefixes(false);
        List<String> result = instance.getPrefixes(false, "eu-west-2");
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertTrue(result.size() < allPrefixes.size());

        // without region filter, should be the same as the non-filtered result
        assertEquals(allPrefixes.size(), instance.getPrefixes(false, null).size());
        assertEquals(allPrefixes.size(), instance.getPrefixes(false, "").size());

        allPrefixes = instance.getPrefixes(true);
        result = instance.getPrefixes(true, "eu-west-2");
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertTrue(result.size() < allPrefixes.size());

        assertEquals(allPrefixes.size(), instance.getPrefixes(true, null).size());
        assertEquals(allPrefixes.size(), instance.getPrefixes(true, "").size());
    }

    @Test
    void getPrefixesWithService() {
        List<String> allPrefixes = instance.getPrefixes(false, "eu-west-2");
        List<String> result = instance.getPrefixes(false, "eu-west-2", "S3");
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertTrue(result.size() < allPrefixes.size());

        // without region filter, should be the same as the non-filtered result
        assertEquals(allPrefixes.size(), instance.getPrefixes(false, "eu-west-2", null).size());
        assertEquals(allPrefixes.size(), instance.getPrefixes(false, "eu-west-2", "").size());

        allPrefixes = instance.getPrefixes(true, "eu-west-2");
        result = instance.getPrefixes(true, "eu-west-2", "S3");
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertTrue(result.size() < allPrefixes.size());

        assertEquals(allPrefixes.size(), instance.getPrefixes(true, "eu-west-2", null).size());
        assertEquals(allPrefixes.size(), instance.getPrefixes(true, "eu-west-2", "").size());

    }
}
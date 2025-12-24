package net.parttimepolymath.iplib.model;

import tools.jackson.databind.ObjectMapper;
import net.parttimepolymath.utils.ObjectMapperFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IPV6PrefixTest {

    private IPV6Prefix instanceOne;
    private IPV6Prefix instanceTwo;
    private final ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();

    @BeforeEach
    void setUp() {
        instanceOne = new IPV6Prefix("2a05:d07a:a000::/40", "eu-south-1", "AMAZON", "eu-south-1");
        instanceTwo = new IPV6Prefix("2a05:d070:e000::/40", "me-south-1", "AMAZON", "me-south-1");
    }

    @Test
    void getPrefix() {
        assertEquals("2a05:d07a:a000::/40", instanceOne.getPrefix());
    }

    @Test
    void getRegion() {
        assertEquals("eu-south-1", instanceOne.getRegion());
    }

    @Test
    void getService() {
        assertEquals("AMAZON", instanceOne.getService());
    }

    @Test
    void getNetworkBorderGroup() {
        assertEquals("eu-south-1", instanceOne.getNetworkBorderGroup());
    }

    @Test
    void setPrefix() {
        instanceOne.setPrefix("2a05:d070:e000::/40");
        assertEquals("2a05:d070:e000::/40", instanceOne.getPrefix());
    }

    @Test
    void setRegion() {
        instanceOne.setRegion("me-south-1");
        assertEquals("me-south-1", instanceOne.getRegion());
    }

    @Test
    void setService() {
        instanceOne.setService("S3");
        assertEquals("S3", instanceOne.getService());
    }

    @Test
    void setNetworkBorderGroup() {
        instanceOne.setNetworkBorderGroup("me-south-1");
        assertEquals("me-south-1", instanceOne.getNetworkBorderGroup());
    }

    @Test
    void testEquals() {
        assertEquals(new IPV6Prefix("2a05:d07a:a000::/40", "eu-south-1", "AMAZON", "eu-south-1"), instanceOne);
        assertNotEquals(instanceOne, instanceTwo);
        assertNotEquals(null, instanceTwo);
    }

    @Test
    void testHashCode() {
        assertEquals(instanceOne.hashCode(),
                new IPV6Prefix("2a05:d07a:a000::/40", "eu-south-1", "AMAZON", "eu-south-1").hashCode());
        assertNotEquals(instanceOne.hashCode(), instanceTwo.hashCode());
        assertNotEquals(instanceOne.hashCode(), new IPV4Prefix().hashCode());
    }

    @Test
    void testToString() {
        assertEquals("IPV6Prefix(prefix=2a05:d07a:a000::/40, region=eu-south-1, service=AMAZON, networkBorderGroup=eu-south-1)", instanceOne.toString());
    }
}
package net.parttimepolymath.iplib.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.parttimepolymath.iplib.util.ObjectMapperFactory;
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
        assertEquals(instanceOne, new IPV6Prefix("2a05:d07a:a000::/40", "eu-south-1", "AMAZON", "eu-south-1"));
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

    @Test
    void toJson() throws JsonProcessingException {
        String result = mapper.writeValueAsString(instanceOne);
        assertEquals("{\"ipv6_prefix\":\"2a05:d07a:a000::/40\",\"region\":\"eu-south-1\",\"service\":\"AMAZON\"," +
                "\"network_border_group\":\"eu-south-1\"}", result);
    }

    @Test
    void fromJson() throws JsonProcessingException {
        IPV6Prefix result = mapper.readValue("{\"ipv6_prefix\":\"2a05:d07a:a000::/40\",\"region\":\"eu-south-1\"," +
                "\"service\":\"AMAZON\",\"network_border_group\":\"eu-south-1\"}", IPV6Prefix.class);
        assertEquals(instanceOne, result);
    }
}
package net.parttimepolymath.iplib.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.parttimepolymath.utils.ObjectMapperFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IPV4PrefixTest {

    private IPV4Prefix instanceOne;
    private IPV4Prefix instanceTwo;
    private final ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();

    @BeforeEach
    void setUp() {
        instanceOne = new IPV4Prefix("3.5.140.0/22", "ap-northeast-2", "AMAZON", "ap-northeast-2");
        instanceTwo = new IPV4Prefix("15.230.56.104/31", "us-east-1", "AMAZON", "us-east-1");
    }

    @Test
    void getPrefix() {
        assertEquals("3.5.140.0/22", instanceOne.getPrefix());
    }

    @Test
    void getRegion() {
        assertEquals("ap-northeast-2", instanceOne.getRegion());
    }

    @Test
    void getService() {
        assertEquals("AMAZON", instanceOne.getService());
    }

    @Test
    void getNetworkBorderGroup() {
        assertEquals("ap-northeast-2", instanceOne.getNetworkBorderGroup());
    }

    @Test
    void setPrefix() {
        instanceOne.setPrefix("15.230.39.60/31");
        assertEquals("15.230.39.60/31", instanceOne.getPrefix());
    }

    @Test
    void setRegion() {
        instanceOne.setRegion("us-east-2");
        assertEquals("us-east-2", instanceOne.getRegion());
    }

    @Test
    void setService() {
        instanceOne.setService("S3");
        assertEquals("S3", instanceOne.getService());
    }

    @Test
    void setNetworkBorderGroup() {
        instanceOne.setNetworkBorderGroup("us-east-2");
        assertEquals("us-east-2", instanceOne.getNetworkBorderGroup());
    }

    @Test
    void testEquals() {
        assertEquals(instanceOne, new IPV4Prefix("3.5.140.0/22", "ap-northeast-2", "AMAZON", "ap-northeast-2"));
        assertNotEquals(instanceOne, instanceTwo);
        assertNotEquals(null, instanceTwo);
    }

    @Test
    void testHashCode() {
        assertEquals(instanceOne.hashCode(),
                new IPV4Prefix("3.5.140.0/22", "ap-northeast-2", "AMAZON", "ap-northeast-2").hashCode());
        assertNotEquals(instanceOne.hashCode(), instanceTwo.hashCode());
        assertNotEquals(instanceOne.hashCode(), new IPV4Prefix().hashCode());
    }

    @Test
    void testToString() {
        assertEquals("IPV4Prefix(prefix=3.5.140.0/22, region=ap-northeast-2, service=AMAZON, networkBorderGroup=ap-northeast-2)", instanceOne.toString());
    }

    @Test
    void toJson() throws JsonProcessingException {
        String result = mapper.writeValueAsString(instanceOne);
        assertEquals("{\"ip_prefix\":\"3.5.140.0/22\",\"region\":\"ap-northeast-2\",\"service\":\"AMAZON\"," +
                "\"network_border_group\":\"ap-northeast-2\"}", result);
    }

    @Test
    void fromJson() throws JsonProcessingException {
        IPV4Prefix result = mapper.readValue("{\"ip_prefix\":\"3.5.140.0/22\",\"region\":\"ap-northeast-2\"," +
                "\"service\":\"AMAZON\",\"network_border_group\":\"ap-northeast-2\"}", IPV4Prefix.class);
        assertEquals(instanceOne, result);
    }
}
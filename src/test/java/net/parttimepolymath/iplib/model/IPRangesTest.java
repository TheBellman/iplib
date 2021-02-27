package net.parttimepolymath.iplib.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.parttimepolymath.iplib.util.ObjectMapperFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class IPRangesTest {

    private IPRanges instance;
    private IPV4Prefix ipv4Prefix;
    private IPV6Prefix ipv6Prefix;
    private final ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();

    @BeforeEach
    void setUp() {
        ipv4Prefix = new IPV4Prefix("15.230.56.104/31", "us-east-1", "AMAZON", "us-east-1");
        ipv6Prefix = new IPV6Prefix("2a05:d070:e000::/40", "me-south-1", "AMAZON", "me-south-1");
        instance = new IPRanges("1614415453", "2021-02-27-08-44-13", Collections.singletonList(ipv4Prefix),
                Collections.singletonList(ipv6Prefix));
    }

    @Test
    void getSyncToken() {
        assertEquals("1614415453", instance.getSyncToken());
    }

    @Test
    void getCreateDate() {
        assertEquals("2021-02-27-08-44-13", instance.getCreateDate());
    }

    @Test
    void getPrefixes() {
        assertEquals(ipv4Prefix, instance.getPrefixes().get(0));
    }

    @Test
    void getIpv6Prefixes() {
        assertEquals(ipv6Prefix, instance.getIpv6Prefixes().get(0));
    }

    @Test
    void setSyncToken() {
        instance.setSyncToken("1441545316");
        assertEquals("1441545316", instance.getSyncToken());
    }

    @Test
    void setCreateDate() {
        instance.setCreateDate("2020-02-27-08-44-13");
        assertEquals("2020-02-27-08-44-13", instance.getCreateDate());
    }

    @Test
    void setPrefixes() {
        instance.setPrefixes(new ArrayList<>());
        assertNotEquals(null, instance.getPrefixes());
        assertTrue(instance.getPrefixes().isEmpty());
    }

    @Test
    void setIpv6Prefixes() {
        instance.setIpv6Prefixes(new ArrayList<>());
        assertNotEquals(null, instance.getIpv6Prefixes());
        assertTrue(instance.getIpv6Prefixes().isEmpty());
    }

    @Test
    void testEquals() {
        assertEquals(instance, new IPRanges("1614415453", "2021-02-27-08-44-13",
                Collections.singletonList(ipv4Prefix), Collections.singletonList(ipv6Prefix)));
        assertNotEquals(instance, new IPRanges());
    }

    @Test
    void testHashCode() {
        assertEquals(instance.hashCode(), new IPRanges("1614415453", "2021-02-27-08-44-13",
                Collections.singletonList(ipv4Prefix), Collections.singletonList(ipv6Prefix)).hashCode());
        assertNotEquals(instance.hashCode(), new IPRanges().hashCode());
    }

    @Test
    void testToString() {
        assertEquals("IPRanges(syncToken=1614415453, createDate=2021-02-27-08-44-13, prefixes=[IPV4Prefix(prefix=15" +
                ".230.56.104/31, region=us-east-1, service=AMAZON, networkBorderGroup=us-east-1)], " +
                "ipv6Prefixes=[IPV6Prefix(prefix=2a05:d070:e000::/40, region=me-south-1, service=AMAZON, " +
                "networkBorderGroup=me-south-1)])", instance.toString());
    }

    @Test
    void toJson() throws JsonProcessingException {
        String result = mapper.writeValueAsString(instance);
        assertEquals("{\"syncToken\":\"1614415453\",\"createDate\":\"2021-02-27-08-44-13\"," +
                "\"prefixes\":[{\"ip_prefix\":\"15.230.56.104/31\",\"region\":\"us-east-1\",\"service\":\"AMAZON\"," +
                "\"network_border_group\":\"us-east-1\"}]," +
                "\"ipv6_prefixes\":[{\"ipv6_prefix\":\"2a05:d070:e000::/40\",\"region\":\"me-south-1\"," +
                "\"service\":\"AMAZON\",\"network_border_group\":\"me-south-1\"}]}", result);
    }
    @Test
    void fromJson() throws JsonProcessingException {
        IPRanges result = mapper.readValue("{\"syncToken\":\"1614415453\",\"createDate\":\"2021-02-27-08-44-13\"," +
                "\"prefixes\":[{\"ip_prefix\":\"15.230.56.104/31\",\"region\":\"us-east-1\",\"service\":\"AMAZON\"," +
                "\"network_border_group\":\"us-east-1\"}]," +
                "\"ipv6_prefixes\":[{\"ipv6_prefix\":\"2a05:d070:e000::/40\",\"region\":\"me-south-1\"," +
                "\"service\":\"AMAZON\",\"network_border_group\":\"me-south-1\"}]}", IPRanges.class);
        assertEquals(instance, result);
    }

}
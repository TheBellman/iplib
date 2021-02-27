package net.parttimepolymath.iplib;

import net.parttimepolymath.iplib.model.IPRanges;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class IPRangeFetcherTest {

    // note that this does an actual GET on the endpoint.
    @Test
    void fetchRanges() throws IOException, InterruptedException {
        IPRanges result = IPRangeFetcher.fetchRanges();
        assertNotNull(result);
    }
}
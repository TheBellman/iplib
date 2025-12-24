package net.parttimepolymath.iplib;

import net.parttimepolymath.utils.ObjectMapperFactory;
import tools.jackson.databind.ObjectMapper;
import net.parttimepolymath.iplib.model.IPRanges;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * Provides a way to fetch the a set of IP Ranges from AWS.
 */
public final class IPRangeFetcher {
    private static final ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
    private static final String URL = "https://ip-ranges.amazonaws.com/ip-ranges.json";

    /**
     * fetch the full set of ranges.
     * @return an IPRange instance or null.
     * @throws IOException if the API call fails.
     * @throws InterruptedException if the API fails to complete returning the data.
     */
    public static IPRanges fetchRanges() throws IOException, InterruptedException {
        try (HttpClient client = HttpClient
                .newBuilder()
                .connectTimeout(Duration.ofSeconds(15))
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build()) {

            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(URL)).timeout(Duration.ofSeconds(15)).GET().build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                return null;
            }

            return mapper.readValue(response.body(), IPRanges.class);
        }
    }
}
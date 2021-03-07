package net.parttimepolymath.iplib;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.parttimepolymath.iplib.model.IPRanges;
import net.parttimepolymath.utils.ObjectMapperFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public final class IPRangeFetcher {
    private static final ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
    private static final String URL = "https://ip-ranges.amazonaws.com/ip-ranges.json";

    public static IPRanges fetchRanges() throws IOException, InterruptedException {
        HttpClient client = HttpClient
                .newBuilder()
                .connectTimeout(Duration.ofSeconds(15))
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(URL))
                .timeout(Duration.ofSeconds(15))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode()!=200) {
            return null;
        }

        return mapper.readValue(response.body(), IPRanges.class);
    }
}
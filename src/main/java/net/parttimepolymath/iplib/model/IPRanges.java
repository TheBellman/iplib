package net.parttimepolymath.iplib.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.jcip.annotations.NotThreadSafe;

import java.util.List;

/**
 * very simple POJO to to represent the incoming JSON.
 *
 * @author Robert Hook
 * @since 2021-02-27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@NotThreadSafe
public final class IPRanges {
    /**
     * identifier of the version of the data.
     */
    @JsonProperty("syncToken")
    private String syncToken;

    /**
     * the date of creation, in the form YYYY-MM-DD-HH-mm-ss
     */
    @JsonProperty("createDate")
    private String createDate;

    /**
     * the list of IPV4 records.
     */
    @JsonProperty("prefixes")
    List<IPV4Prefix> prefixes;

    /**
     * the list of IPV6 records.
     */
    @JsonProperty("ipv6_prefixes")
    List<IPV6Prefix> ipv6Prefixes;
}

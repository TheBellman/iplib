package net.parttimepolymath.iplib.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.jcip.annotations.NotThreadSafe;

/**
 * very simple POJO to to represent the IPV6 prefixes in the incoming JSON.
 *
 * @author Robert Hook
 * @since 2021-02-27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@NotThreadSafe
public final class IPV6Prefix {
    /**
     * IPV4 CIDR block.
     */
    @JsonProperty("ipv6_prefix")
    private String prefix;

    /**
     * the AWS region.
     */
    @JsonProperty("region")
    private String region;

    /**
     * the AWS service identifier.
     */
    @JsonProperty("service")
    private String service;

    /**
     * the name of the border group.
     */
    @JsonProperty("network_border_group")
    private String networkBorderGroup;
}

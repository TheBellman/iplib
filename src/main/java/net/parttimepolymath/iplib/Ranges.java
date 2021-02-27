package net.parttimepolymath.iplib;

import java.util.List;

/**
 * This interface describes the behaviour of the range provider, and is provided as a convenience for testing.
 */
public interface Ranges {
    /**
     * get the list of defined services.
     * @return a non-null but possibly empty list.
     */
    List<String> getServices();

    /**
     * get the list of defined regions.
     * @return a non-null but possibly empty list.
     */
    List<String> getRegions();

    /**
     * get the list of prefixes.
     * @param ipv6 if true, the IPV6 list is returned, otherwise the IPV4 list.
     * @return a non-null but possibly empty list.
     */
    List<String> getPrefixes(boolean ipv6);

    /**
     * get the list of prefixes.
     * @param ipv6 if true, the IPV6 list is returned, otherwise the IPV4 list.
     * @param region the region to filter the list by. Will be ignored if null or blank.
     * @return a non-null but possibly empty list.
     */
    List<String> getPrefixes(boolean ipv6, String region);

    /**
     * get the list of prefixes.
     * @param ipv6 if true, the IPV6 list is returned, otherwise the IPV4 list.
     * @param region the region to filter the list by. Will be ignored if null or blank.
     * @param service the service to filter the list by. Will be ignored if null or blank.
     * @return a non-null but possibly empty list.
     */
    List<String> getPrefixes(boolean ipv6, String region, String service);

}

package net.parttimepolymath.iplib;

import net.jcip.annotations.ThreadSafe;
import net.parttimepolymath.iplib.model.IPRanges;
import net.parttimepolymath.iplib.model.IPV4Prefix;
import net.parttimepolymath.iplib.model.IPV6Prefix;
import net.parttimepolymath.iplib.util.ResultHolder;
import net.parttimepolymath.iplib.util.TimedResultHolder;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Provider of ranges.
 */
@ThreadSafe
public final class IPRange implements Ranges {
    private ResultHolder holder;

    /**
     * default constructor. This will attempt to load the required data from AWS on construction.
     * @throws IOException if there was a failure to fetch from AWS.
     * @throws InterruptedException if the fetch attempt was interrupted.
     */
    public IPRange() throws IOException, InterruptedException {
        this(300);
    }

    /**
     * alternate constructor. This will attempt to load the required data from AWS on construction
     * @param ttlSeconds this specifies how long the fetched data will be retained before it is considered stale and re-fetched.
     * @throws IOException if there was a failure to fetch from AWS.
     * @throws InterruptedException if the fetch attempt was interrupted.
     */
    public IPRange(int ttlSeconds) throws IOException, InterruptedException {
        holder = new TimedResultHolder(null, ttlSeconds);
        refreshHolder();
    }

    /**
     * refreshes the retained set of data from AWS. This will do nothing if the retained data is not stale.
     * @throws IOException if there was a failure to fetch from AWS.
     * @throws InterruptedException if the fetch attempt was interrupted.
     */
    private void refreshHolder() throws IOException, InterruptedException {
        if (!holder.hasRanges()) {
            holder.setResult(IPRangeFetcher.fetchRanges());
        }
    }

    /**
     * get the data from the holder, refreshing the holder if necessary.
     * @return a well defined but possibly empty IPRanges.
     */
    private IPRanges retrieveFromHolder() {
        try {
            refreshHolder();
        } catch (Exception ex) {
            return new IPRanges("", "", Collections.emptyList(), Collections.emptyList());
        }
        return holder.getRanges();
    }

    @Override
    public List<String> getServices() {
        IPRanges result = retrieveFromHolder();
        return Stream.concat(
                result.getPrefixes().stream().map(IPV4Prefix::getService),
                result.getIpv6Prefixes().stream().map(IPV6Prefix::getService))
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getRegions() {
        IPRanges result = retrieveFromHolder();
        return Stream.concat(
                result.getPrefixes().stream().map(IPV4Prefix::getRegion),
                result.getIpv6Prefixes().stream().map(IPV6Prefix::getRegion))
                .distinct()
                .sorted()
                .collect(Collectors.toList());    }

    @Override
    public List<String> getPrefixes(boolean ipv6) {
        IPRanges result = retrieveFromHolder();
        if (ipv6) {
            return result.getIpv6Prefixes().stream()
                    .map(IPV6Prefix::getPrefix)
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList());
        } else {
            return result.getPrefixes().stream()
                    .map(IPV4Prefix::getPrefix)
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList());
        }
    }

    @Override
    public List<String> getPrefixes(boolean ipv6, String region) {
        if (region==null || region.isEmpty()) {
            return getPrefixes(ipv6);
        }

        IPRanges result = retrieveFromHolder();
        if (ipv6) {
            return result.getIpv6Prefixes().stream()
                    .filter(x -> region.equals(x.getRegion()))
                    .map(IPV6Prefix::getPrefix)
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList());
        } else {
            return result.getPrefixes().stream()
                    .filter(x -> region.equals(x.getRegion()))
                    .map(IPV4Prefix::getPrefix)
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList());
        }
    }

    @Override
    public List<String> getPrefixes(boolean ipv6, String region, String service) {
        if (service == null || service.isEmpty()) {
            return getPrefixes(ipv6, region);
        }
        IPRanges result = retrieveFromHolder();
        if (ipv6) {
            return result.getIpv6Prefixes().stream()
                    .filter(x -> region.equals(x.getRegion()))
                    .filter(x -> service.equals(x.getService()))
                    .map(IPV6Prefix::getPrefix)
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList());
        } else {
            return result.getPrefixes().stream()
                    .filter(x -> region.equals(x.getRegion()))
                    .filter(x -> service.equals(x.getService()))
                    .map(IPV4Prefix::getPrefix)
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList());
        }
    }
}

package net.parttimepolymath.iplib.util;

import net.parttimepolymath.iplib.model.IPRanges;
import java.time.Instant;

/**
 * This class is used to hold onto an IPranges for a certain amount of time.
 */
public class TimedResultHolder implements ResultHolder {
    private IPRanges instance = null;
    private Instant age;
    private final int ttlSeconds;

    /**
     * Primary constructor.
     * @param result the IPranges to hold onto.
     * @param ttlSeconds after this many seconds the result is "stale"
     */
    public TimedResultHolder(IPRanges result, int ttlSeconds) {
        this.ttlSeconds = ttlSeconds;
        setResult(result);
    }

    /**
     * refresh the contained result with a new result.
     * @param result the result to set
     */
    @Override
    public void setResult(IPRanges result) {
        this.instance = result;
        this.age = Instant.now();
    }

    /**
     * get the contained value. If the value is more than ttlSeconds old, it has gone stale and will become null.
     * @return the contained value, or null.
     */
    @Override
    public IPRanges getRanges() {
        if (Instant.now().minusSeconds(ttlSeconds).isAfter(age)) {
            instance = null;
        }
        return instance;
    }

    /**
     * does the holder contain a value at all?
     * @return true of it does.
     */
    @Override
    public boolean hasRanges() {
        IPRanges result = getRanges();
        return result != null;
    }
}

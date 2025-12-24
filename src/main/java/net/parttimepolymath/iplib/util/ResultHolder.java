package net.parttimepolymath.iplib.util;

import net.parttimepolymath.iplib.model.IPRanges;

/**
 * Instances of this hold an IPRanges set that has been returned.
 */
public interface ResultHolder {
    /**
     * get the contained value if it is available..
     * @return the contained value, or null.
     */
    IPRanges getRanges();

    /**
     * does the holder contain a value at all?
     * @return true of it does.
     */
    boolean hasRanges();

    /**
     * refresh the contained result with a new result.
     * @param result the result to set
     */
    void setResult(IPRanges result);
}

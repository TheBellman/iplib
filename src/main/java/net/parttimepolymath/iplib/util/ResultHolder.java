package net.parttimepolymath.iplib.util;

import net.parttimepolymath.iplib.model.IPRanges;

/**
 * Instances of this hold an IPRanges set that has been returned.
 */
public interface ResultHolder {
    IPRanges getRanges();
    boolean hasRanges();
    void setResult(IPRanges result);
}

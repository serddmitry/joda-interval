package com.github.serddmitry.jodainterval;

import org.joda.time.LocalDate;

/**
 * An interval that may have one of it's bounds not set.
 * For example, a partial interval could be one of:
 * <ul>
 *     <li>(-∞ .. 2013-02-07]</li>
 *     <li>[2013-02-10 .. +∞)</li>
 *     <li>[2013-02-10 .. 2013-02-25]</li>
 * </ul>
 * Therefore, it's neither possible to tell the size of an interval (it can be ∞), nor iterate over it.<br/>
 * For more specific interface, see {@link LocalDateInterval}
 * <p/>
 * Created on 07/02/13
 * @author d.serdiuk
 */
public interface LocalDateIntervalPartial {
    /** @return true if date lies within the interval */
    boolean contains(LocalDate date);
}

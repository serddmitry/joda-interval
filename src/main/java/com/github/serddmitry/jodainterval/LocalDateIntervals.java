package com.github.serddmitry.jodainterval;

import static com.google.common.base.Preconditions.checkNotNull;

import org.joda.time.LocalDate;

/**
 * Static factory methods that create implementations of
 * {@link LocalDateInterval} or {@link LocalDateIntervalPartial}<br/>
 * All methods don't permit nulls and will throw descriptive NullPointerExceptions if null passed.<br/>
 * Created on 07/02/13
 * @author d.serdiuk
 */
public class LocalDateIntervals {
    /** An interval [from .. to] */
    public static LocalDateInterval includingLast(LocalDate from, LocalDate to) {
        return new LocalDateIntervalImpl(from, to);
    }

    /** An interval [from .. to) - equivalent to [from .. to-1] */
    public static LocalDateInterval excludingLast(LocalDate from, LocalDate to) {
        checkNotNull(to, "upper bound of the interval cannot be null");
        return new LocalDateIntervalImpl(from, to.minusDays(1));
    }

    /** An interval (-∞ .. to] */
    public static LocalDateIntervalPartial upToAndIncluding(LocalDate to) {
        return new LocalDateIntervalWithUpperBound(to);
    }

    /** An interval (-∞ .. to) - equivalent to (-∞ .. to-1] */
    public static LocalDateIntervalPartial upToAndExcluding(LocalDate to) {
        checkNotNull(to, "upper bound of the interval cannot be null");
        return new LocalDateIntervalWithUpperBound(to.minusDays(1));
    }

    /** An interval [from .. ∞) */
    public static LocalDateIntervalPartial sinceAndIncluding(LocalDate from) {
        return new LocalDateIntervalWithLowerBound(from);
    }

    /** An interval (from .. ∞) - equivalent to [from+1 .. ∞) */
    public static LocalDateIntervalPartial sinceAndExcluding(LocalDate from) {
        checkNotNull(from, "lower bound of the interval cannot be null");
        return new LocalDateIntervalWithLowerBound(from.plusDays(1));
    }
}

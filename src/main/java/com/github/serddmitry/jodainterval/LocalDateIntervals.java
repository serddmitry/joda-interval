package com.github.serddmitry.jodainterval;

import org.joda.time.LocalDate;

/**
 * TODO: describe class
 * <p/>
 * Created on 07/02/13
 *
 * @author d.serdiuk
 */
public class LocalDateIntervals {
    public static LocalDateInterval includingLast(LocalDate from, LocalDate to) {
        return new LocalDateIntervalImpl(from, to);
    }

    public static LocalDateInterval excludingLast(LocalDate from, LocalDate to) {
        return new LocalDateIntervalImpl(from, to.minusDays(1));
    }

    public static LocalDateIntervalPartial upToAndIncluding(LocalDate date) {
        // TODO
        return null;
    }

    public static LocalDateIntervalPartial upToAndExcluding(LocalDate date) {
        // TODO
        return null;
    }

    public static LocalDateIntervalPartial sinceAndIncluding(LocalDate date) {
        // TODO
        return null;
    }

    public static LocalDateIntervalPartial sinceAndExcluding(LocalDate date) {
        // TODO
        return null;
    }
}

package com.github.serddmitry.jodainterval;

import org.joda.time.LocalDate;

/**
 * A finite interval of Dates expressed by JodaTime LocalDate.<br/>
 * Implementation is immutable.<br/>
 * Supports iteration over days from the interval.<br/>
 * <pre>
 * LocalDateInterval loanInterval = LocalDateIntervals.includingLast(loanTakenDate, today);
 *
 * for (LocalDate date: loanInterval) {
 *     calculateInterest(date);
 * }
 *
 * int loanLength = loanInterval.getDays();
 * </pre>
 * Created on 07/02/13
 * @author d.serdiuk
 */
public interface LocalDateInterval extends Iterable<LocalDate>, LocalDateIntervalPartial {
    /** The first date from the interval, always not null */
    LocalDate getFirst();

    /** The last date from the interval, always not null */
    LocalDate getLast();

    /**
     * Amount of days in the interval.
     * For example, for an interval with first date = 1 Jan and last date 3 Jan getDays() will return 3.
     */
    int getDays();
}

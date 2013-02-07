package com.github.serddmitry.jodainterval;

import org.joda.time.LocalDate;

/**
 * Created on 07/02/13
 *
 * @author d.serdiuk
 */
public interface LocalDateInterval extends Iterable<LocalDate>, LocalDateIntervalPartial {
    LocalDate getFirst();
    LocalDate getLast();
    int getDays();
}

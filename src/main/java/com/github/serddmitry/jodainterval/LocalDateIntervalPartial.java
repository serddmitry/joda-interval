package com.github.serddmitry.jodainterval;

import org.joda.time.LocalDate;

/**
 * TODO: describe class
 * <p/>
 * Created on 07/02/13
 *
 * @author d.serdiuk
 */
public interface LocalDateIntervalPartial {
    boolean contains(LocalDate date);
}

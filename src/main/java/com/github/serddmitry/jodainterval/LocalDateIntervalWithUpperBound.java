package com.github.serddmitry.jodainterval;

import org.joda.time.LocalDate;

/**
 * TODO: describe class
 * <p/>
 * Created on 07/02/13
 *
 * @author d.serdiuk
 */
public class LocalDateIntervalWithUpperBound implements LocalDateIntervalPartial {
    @Override
    public boolean contains(LocalDate date) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}

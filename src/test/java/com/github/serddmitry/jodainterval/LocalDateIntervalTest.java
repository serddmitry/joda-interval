package com.github.serddmitry.jodainterval;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.joda.time.LocalDate;
import org.junit.Test;

/**
 * Created on 17/01/13
 * @author d.serdiuk
 */
public class LocalDateIntervalTest {
    @Test
    public void testIntervalWithFromDateSameAsToDate_hasOneDay() {
        // do
        LocalDateInterval interval = new LocalDateInterval(someDate, someDate);

        // verify
        assertEquals(1, interval.getDays());
    }

    private LocalDate someDate = new LocalDate();

    @Test
    public void testIterator_iterHasNextTrue() {
        assertTrue(getOneDayIterator(someDate).hasNext());
    }

    private Iterator<LocalDate> getOneDayIterator(LocalDate date) {
        return new LocalDateInterval(date, date).iterator();
    }

    private Iterator<LocalDate> getTwoDaysIterator(LocalDate date) {
        return new LocalDateInterval(date, date.plusDays(1)).iterator();
    }

    @Test
    public void testIterator_iterNextReturnsValue() {
        assertEquals(someDate, getOneDayIterator(someDate).next());
    }

    @Test
    public void testIterator_iterNextReturnsNextValues() {
        // given
        Iterator<LocalDate> iter = getTwoDaysIterator(someDate);

        // verify
        assertEquals(someDate, iter.next());
        assertEquals(someDate.plusDays(1), iter.next());

    }

    @Test
    public void testIterator_atTheEndOfIteration_hasNextIsFalse() {
        // given
        Iterator<LocalDate> iter = getOneDayIterator(someDate);

        // do
        iter.next();

        // verify
        assertFalse(iter.hasNext());
    }

    @Test (expected = NoSuchElementException.class)
    public void testIterator_atTheEndOfIteration_nextThrowsNoSuchElementException() {
        // given
        Iterator<LocalDate> iter = getOneDayIterator(someDate);

        // do
        iter.next();
        iter.next();
    }
}

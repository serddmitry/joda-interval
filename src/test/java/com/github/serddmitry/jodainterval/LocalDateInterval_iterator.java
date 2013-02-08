package com.github.serddmitry.jodainterval;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.joda.time.LocalDate;
import org.junit.Test;

/**
 * Created on 07/02/13
 * @author d.serdiuk
 */
public class LocalDateInterval_iterator {
    private LocalDate someDate = new LocalDate();

    @Test
    public void testIterator_iterHasNextTrue() {
        assertTrue(getOneDayIterator(someDate).hasNext());
    }

    private Iterator<LocalDate> getOneDayIterator(LocalDate date) {
        return new LocalDateIntervalImpl(date, date).iterator();
    }

    private Iterator<LocalDate> getTwoDaysIterator(LocalDate date) {
        return new LocalDateIntervalImpl(date, date.plusDays(1)).iterator();
    }

    @Test
    public void testIterator_iterNextReturnsValue() {
        assertEquals(someDate, getOneDayIterator(someDate).next());
    }

    @Test
    public void test_iterNextReturnsNextValues() {
        Iterator<LocalDate> iter = getTwoDaysIterator(someDate);

        assertEquals(someDate, iter.next());
        assertEquals(someDate.plusDays(1), iter.next());
    }

    @Test
    public void test_atTheEndOfIteration_hasNextIsFalse() {
        Iterator<LocalDate> iter = getOneDayIterator(someDate);

        iter.next();

        assertFalse(iter.hasNext());
    }

    @Test (expected = NoSuchElementException.class)
    public void test_atTheEndOfIteration_nextThrowsNoSuchElementException() {
        Iterator<LocalDate> iter = getOneDayIterator(someDate);

        iter.next();
        iter.next();
    }

    @Test (expected = UnsupportedOperationException.class)
    public void testRemove_operationNotSupportedException() {
        Iterator<LocalDate> iter = getOneDayIterator(someDate);

        iter.remove();
    }
}

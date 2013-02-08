package com.github.serddmitry.jodainterval;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.joda.time.LocalDate;
import org.junit.Test;

/**
 * Created on 08/02/13
 * @author d.serdiuk
 */
public class LocalDateIntervalWithLowerBoundTest {
    private LocalDate today = new LocalDate();
    private LocalDate yesterday = today.minusDays(1);
    private LocalDate tomorrow = today.plusDays(1);

    @Test (expected = NullPointerException.class)
    public void testSinceAndIncluding_NullPassed_npe() {
        LocalDateIntervals.sinceAndIncluding(null);
    }

    @Test (expected = NullPointerException.class)
    public void testSinceAndExcluding_NullPassed_npe() {
        LocalDateIntervals.sinceAndExcluding(null);
    }

    @Test
    public void testSinceAndIncluding_today_containsToday() {
        LocalDateIntervalPartial interval = LocalDateIntervals.sinceAndIncluding(today);

        assertTrue(interval.contains(today));
    }

    @Test
    public void testSinceAndIncluding_today_containsTomorrow() {
        LocalDateIntervalPartial interval = LocalDateIntervals.sinceAndIncluding(today);

        assertTrue(interval.contains(tomorrow));
    }

    @Test
    public void testSinceAndIncluding_today_doesntContainYesterday() {
        LocalDateIntervalPartial interval = LocalDateIntervals.sinceAndIncluding(today);

        assertFalse(interval.contains(yesterday));
    }


    @Test
    public void testSinceAndExcluding_yesterday_containsToday() {
        LocalDateIntervalPartial interval = LocalDateIntervals.sinceAndExcluding(yesterday);

        assertTrue(interval.contains(today));
    }

    @Test
    public void testSinceAndExcluding_yesterday_containsTomorrow() {
        LocalDateIntervalPartial interval = LocalDateIntervals.sinceAndExcluding(yesterday);

        assertTrue(interval.contains(tomorrow));
    }

    @Test
    public void testSinceAndExcluding_yesterday_doesntContainYesterday() {
        LocalDateIntervalPartial interval = LocalDateIntervals.sinceAndExcluding(yesterday);

        assertFalse(interval.contains(yesterday));
    }

    @Test
    public void testHashCodeContract() {
        LocalDateIntervalPartial interval1 = LocalDateIntervals.sinceAndIncluding(yesterday);
        LocalDateIntervalPartial interval2 = LocalDateIntervals.sinceAndIncluding(yesterday);
        LocalDateIntervalPartial interval3 = LocalDateIntervals.sinceAndIncluding(today);

        assertTrue(interval1.hashCode() == interval2.hashCode());
        assertTrue(interval1.equals(interval2));
        assertFalse(interval1.hashCode() == interval3.hashCode());
        assertFalse(interval1.equals(interval3));
    }

    @Test
    public void testSinceAndIncludingTomorrowEqualsToSinceAndExcludingToday() {
        LocalDateIntervalPartial interval1 = LocalDateIntervals.sinceAndIncluding(tomorrow);
        LocalDateIntervalPartial interval2 = LocalDateIntervals.sinceAndExcluding(today);

        assertEquals(interval1, interval2);
    }
}

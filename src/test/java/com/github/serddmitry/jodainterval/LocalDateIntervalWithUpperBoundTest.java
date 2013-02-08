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
public class LocalDateIntervalWithUpperBoundTest {
    private LocalDate today = new LocalDate();
    private LocalDate yesterday = today.minusDays(1);
    private LocalDate tomorrow = today.plusDays(1);

    @Test(expected = NullPointerException.class)
    public void testUpToAndIncluding_NullPassed_npe() {
        LocalDateIntervals.upToAndIncluding(null);
    }

    @Test (expected = NullPointerException.class)
    public void testUpToAndExcluding_NullPassed_npe() {
        LocalDateIntervals.upToAndExcluding(null);
    }

    @Test
    public void testUpToAndIncluding_today_containsToday() {
        LocalDateIntervalPartial interval = LocalDateIntervals.upToAndIncluding(today);

        assertTrue(interval.contains(today));
    }

    @Test
    public void testUpToAndIncluding_today_containsYesterday() {
        LocalDateIntervalPartial interval = LocalDateIntervals.upToAndIncluding(today);

        assertTrue(interval.contains(yesterday));
    }

    @Test
    public void testUpToAndIncluding_today_doesntContainTomorrow() {
        LocalDateIntervalPartial interval = LocalDateIntervals.upToAndIncluding(today);

        assertFalse(interval.contains(tomorrow));
    }


    @Test
    public void testUpToAndExcluding_tomorrow_containsToday() {
        LocalDateIntervalPartial interval = LocalDateIntervals.upToAndExcluding(tomorrow);

        assertTrue(interval.contains(today));
    }

    @Test
    public void testUpToAndExcluding_tomorrow_containsYesterday() {
        LocalDateIntervalPartial interval = LocalDateIntervals.upToAndExcluding(tomorrow);

        assertTrue(interval.contains(yesterday));
    }

    @Test
    public void testUpToAndExcluding_tomorrow_doesntContainTomorrow() {
        LocalDateIntervalPartial interval = LocalDateIntervals.upToAndExcluding(tomorrow);

        assertFalse(interval.contains(tomorrow));
    }

    @Test
    public void testHashCodeContract() {
        LocalDateIntervalPartial interval1 = LocalDateIntervals.upToAndIncluding(yesterday);
        LocalDateIntervalPartial interval2 = LocalDateIntervals.upToAndIncluding(yesterday);
        LocalDateIntervalPartial interval3 = LocalDateIntervals.upToAndIncluding(today);

        assertTrue(interval1.hashCode() == interval2.hashCode());
        assertTrue(interval1.equals(interval2));
        assertFalse(interval1.hashCode() == interval3.hashCode());
        assertFalse(interval1.equals(interval3));
    }

    @Test
    public void testUpToAndIncludingYesterdayEqualsToUpToAndExcludingToday() {
        LocalDateIntervalPartial interval1 = LocalDateIntervals.upToAndIncluding(yesterday);
        LocalDateIntervalPartial interval2 = LocalDateIntervals.upToAndExcluding(today);

        assertEquals(interval1, interval2);
    }
}


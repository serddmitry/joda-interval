/*
 * Copyright 2013 Dmitry Serdiuk
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.serddmitry.jodainterval;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.joda.time.LocalDate;
import org.junit.Test;

/**
 * Created on 17/01/13
 * @author d.serdiuk
 */
public class LocalDateIntervalTest {
    private LocalDate today = new LocalDate();
    private LocalDate yesterday = today.minusDays(1);
    private LocalDate tomorrow = today.plusDays(1);

    @Test
    public void testIncludingLast_FromDateSameAsToDate_hasOneDay() {
        LocalDateInterval interval = LocalDateIntervals.includingLast(today, today);

        assertEquals(1, interval.getDays());
    }

    @Test
    public void testIncludingLast_FromDateSameAsToDate_firstDateInIntervalEqualsToLast() {
        LocalDateInterval interval = LocalDateIntervals.includingLast(today, today);

        assertEquals(interval.getFirst(), interval.getLast());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testIncludingLast_FromDateIsTodayAndToDateIsYesterday_exception() {
        LocalDateIntervals.includingLast(today, yesterday);
    }

    @Test
    public void testIncludingLast_FromAndToDateAreToday_containsToday() {
        LocalDateInterval interval = LocalDateIntervals.includingLast(today, today);

        assertTrue(interval.contains(today));
    }

    @Test
    public void testIncludingLast_FromAndToDateAreToday_doesntContainTomorrowNorYesterday() {
        LocalDateInterval interval = LocalDateIntervals.includingLast(today, today);

        assertFalse(interval.contains(tomorrow));
        assertFalse(interval.contains(yesterday));
    }

    @Test (expected = IllegalArgumentException.class)
    public void testExcludingLast_FromDateSameAsToDate_exception() {
        LocalDateIntervals.excludingLast(today, today);
    }

    @Test
    public void testExcludingLast_FromDateIsTodayAndToDateIsTomorrow_hasOneDay() {
        LocalDateInterval interval = LocalDateIntervals.excludingLast(today, tomorrow);

        assertEquals(1, interval.getDays());
    }

    @Test
    public void testExcludingLast_FromDateIsTodayAndToDateIsTomorrow_firstDateInIntervalEqualsToLast() {
        LocalDateInterval interval = LocalDateIntervals.excludingLast(today, tomorrow);

        assertEquals(interval.getFirst(), interval.getLast());
    }

    @Test
    public void testExcludingLast_FromDateIsTodayAndToDateIsTomorrow_containsToday() {
        LocalDateInterval interval = LocalDateIntervals.excludingLast(today, tomorrow);

        assertTrue(interval.contains(today));
    }

    @Test
    public void testExcludingLast_FromDateIsTodayAndToDateIsTomorrow_doesntContainYesterdayNorTomorrow() {
        LocalDateInterval interval = LocalDateIntervals.excludingLast(today, tomorrow);

        assertFalse(interval.contains(yesterday));
        assertFalse(interval.contains(tomorrow));
    }

    @Test
    public void testExcludingLast_hashCodeContract() {
        LocalDateInterval interval = LocalDateIntervals.excludingLast(yesterday, tomorrow);
        LocalDateInterval interval2 = LocalDateIntervals.excludingLast(yesterday, tomorrow);
        LocalDateInterval interval3 = LocalDateIntervals.excludingLast(yesterday, today);

        assertTrue(interval.equals(interval2));
        assertTrue(interval.hashCode() == interval2.hashCode());
        assertFalse(interval.equals(interval3));
        assertFalse(interval.hashCode() == interval3.hashCode());
    }

    @Test
    public void testIncludingTodayAndExcludingTomorrow_equal() {
        LocalDateInterval interval = LocalDateIntervals.includingLast(yesterday, today);
        LocalDateInterval interval2 = LocalDateIntervals.excludingLast(yesterday, tomorrow);

        assertEquals(interval, interval2);
        assertEquals(interval.hashCode(), interval2.hashCode());
    }

    @Test (expected = NullPointerException.class)
    public void testIncluding_NullPassedAsFirstArgument_npe() {
        LocalDateIntervals.includingLast(null, today);
    }

    @Test (expected = NullPointerException.class)
    public void testIncluding_NullPassedAsSecondArgument_npe() {
        LocalDateIntervals.includingLast(today, null);
    }

    @Test (expected = NullPointerException.class)
    public void testExcluding_NullPassedAsFirstArgument_npe() {
        LocalDateIntervals.excludingLast(null, today);
    }

    @Test (expected = NullPointerException.class)
    public void testExcluding_NullPassedAsSecondArgument_npe() {
        LocalDateIntervals.excludingLast(today, null);
    }
}

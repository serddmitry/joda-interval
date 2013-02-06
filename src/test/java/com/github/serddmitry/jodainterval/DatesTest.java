package com.github.serddmitry.jodainterval;

import static org.junit.Assert.assertEquals;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.junit.Test;

/**
 * Created on 06/02/13
 * @author d.serdiuk
 */
public class DatesTest {
    @Test
    public void testEarlierReadablePartial_yesterdayAndToday_yesterdayIsReturned() {
        // given
        LocalDate today = new LocalDate();
        LocalDate yesterday = today.minusDays(1);

        // do + verify
        assertEquals(yesterday, Dates.earlier(today, yesterday));
        assertEquals(yesterday, Dates.earlier(yesterday, today));
    }

    @Test
    public void testLaterReadablePartial_yesterdayAndToday_todayIsReturned() {
        // given
        LocalDate today = new LocalDate();
        LocalDate yesterday = today.minusDays(1);

        // do + verify
        assertEquals(today, Dates.later(today, yesterday));
        assertEquals(today, Dates.later(yesterday, today));
    }

    @Test
    public void testEarlierReadableInstant_nowAndSecondAgo_secondAgoIsReturned() {
        // given
        DateTime now = new DateTime();
        DateTime secondAgo = now.minusSeconds(1);

        // do + verify
        assertEquals(secondAgo, Dates.earlier(now, secondAgo));
        assertEquals(secondAgo, Dates.earlier(secondAgo, now));
    }

    @Test
    public void testLaterReadableInstant_nowAndSecondAgo_nowIsReturned() {
        // given
        DateTime now = new DateTime();
        DateTime secondAgo = now.minusSeconds(1);

        // do + verify
        assertEquals(now, Dates.later(now, secondAgo));
        assertEquals(now, Dates.later(secondAgo, now));
    }
}

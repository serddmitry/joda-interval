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

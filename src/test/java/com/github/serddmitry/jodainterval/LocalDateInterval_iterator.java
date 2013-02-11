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

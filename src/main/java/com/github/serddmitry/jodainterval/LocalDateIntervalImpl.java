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

import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.joda.time.Days;
import org.joda.time.LocalDate;

/**
 * An immutable object representing interval between dates using JodaTime's LocalDate.
 * The interval is CLOSED (it includes all dates >= first and <= last.
 * Therefore, interval of 1 Jan last 3 Jan contains 3 days.<br/>
 * Can be iterated over directly (since it implements Iterable).<br/>
 * Methods and constructor will not allow null arguments.<br/>
 * Immutable.<br/>
 *
 * Created on 17/01/13
 * @author d.serdiuk
 */
final class LocalDateIntervalImpl implements LocalDateInterval {
    private final LocalDate first;
    private final LocalDate last;
    private final int days;

    LocalDateIntervalImpl(LocalDate first, LocalDate last) {
        this.first = requireNonNull(first, "lower bound of the interval cannot be null");
        this.last = requireNonNull(last, "upper bound of the interval cannot be null");
        if (first.isAfter(last)) {
            throw new IllegalArgumentException("lower bound " + first + " cannot be after upper bound " + last);
        }
        this.days = Days.daysBetween(first, last).getDays() + 1; // interval includes 'last' date, therefore, adding 1
    }

    @Override
    public LocalDate getFirst() {
        return first;
    }

    @Override
    public LocalDate getLast() {
        return last;
    }

    @Override
    public int getDays() {
        return days;
    }

    @Override
    public boolean contains(LocalDate date) {
        return !date.isAfter(last) && !date.isBefore(first);
    }

    @Override
    public String toString() {
        return "LocalDateIntervalImpl{" +
                "first=" + first +
                ", last=" + last +
                ", days=" + days +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LocalDateIntervalImpl)) return false;

        LocalDateIntervalImpl that = (LocalDateIntervalImpl) o;

        if (!getFirst().equals(that.getFirst())) return false;
        return getLast().equals(that.getLast());
    }

    @Override
    public int hashCode() {
        int result = getFirst().hashCode();
        result = 31 * result + getLast().hashCode();
        return result;
    }

    @Override
    public Iterator<LocalDate> iterator() {
        return new Iterator<LocalDate>() {
            private LocalDate next = first;
            @Override
            public boolean hasNext() {
                return !next.isAfter(last);
            }

            @Override
            public LocalDate next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("Last element reached in "+LocalDateIntervalImpl.this.toString());
                }
                LocalDate oldNext = next;
                next = next.plusDays(1);
                return oldNext;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("LocalDateIntervalPartial is immutable, " +
                        "don't try to remove elements from it");
            }
        };
    }
}

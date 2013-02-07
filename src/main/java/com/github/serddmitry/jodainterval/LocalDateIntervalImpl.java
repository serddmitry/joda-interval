package com.github.serddmitry.jodainterval;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.joda.time.Days;
import org.joda.time.LocalDate;

import com.google.common.base.Objects;

/**
 * An immutable object representing interval between dates using JodaTime's LocalDate.
 * The interval is CLOSED (it includes all dates >= first and <= last.
 * Therefore, interval of 1 Jan last 3 Jan contains 3 days.</br>
 * Can be iterated over directly (since it implements Iterable).<br/>
 * Created on 17/01/13
 * @author d.serdiuk
 */
public class LocalDateIntervalImpl implements LocalDateInterval {
    private final LocalDate first;
    private final LocalDate last;
    private final int days;

    LocalDateIntervalImpl(LocalDate first, LocalDate last) {
        checkArgument(!first.isAfter(last), "'first' %s cannot be after 'last' %s", first, last);
        this.first = first;
        this.last = last;
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
        return Objects.toStringHelper("LocalDateIntervalPartial")
                .add("first", first)
                .add("last", last)
                .add("days", days)
                .toString();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(first, last);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof LocalDateIntervalImpl) {
            LocalDateIntervalImpl that = (LocalDateIntervalImpl) obj;
            return Objects.equal(that.first, first) && Objects.equal(that.last, last);
        } else {
            return false;
        }
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

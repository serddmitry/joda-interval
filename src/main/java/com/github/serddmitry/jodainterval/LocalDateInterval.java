package com.github.serddmitry.jodainterval;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.joda.time.Days;
import org.joda.time.LocalDate;

import com.google.common.base.Objects;

/**
 * An immutable object representing interval between dates using JodaTime's LocalDate.
 * The interval is CLOSED (it includes all dates >= from and <= to.
 * Therefore, interval of 1 Jan to 3 Jan contains 3 days.</br>
 * Can be iterated over directly (since it implements Iterable).<br/>
 * Created on 17/01/13
 * @author d.serdiuk
 */
public class LocalDateInterval implements Iterable<LocalDate> {
    private final LocalDate from;
    private final LocalDate to;
    private final int days;

    public LocalDateInterval(LocalDate from, LocalDate to) {
        checkArgument(!from.isAfter(to), "'from' %s cannot be after 'to' %s", from, to);
        this.from = from;
        this.to = to;
        this.days = Days.daysBetween(from, to).getDays() + 1; // interval includes 'to' date, therefore, adding 1
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }

    public int getDays() {
        return days;
    }

    public boolean includes(LocalDate date) {
        return !date.isAfter(to) && !date.isBefore(from);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("from", from)
                .add("to", to)
                .add("days", days)
                .toString();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(from, to);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof LocalDateInterval) {
            LocalDateInterval that = (LocalDateInterval) obj;
            return Objects.equal(that.from, from) && Objects.equal(that.to, to);
        } else {
            return false;
        }
    }

    @Override
    public Iterator<LocalDate> iterator() {
        return new Iterator<LocalDate>() {
            private LocalDate next = from;
            @Override
            public boolean hasNext() {
                return !next.isAfter(to);
            }

            @Override
            public LocalDate next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("Last element reached in "+LocalDateInterval.this.toString());
                }
                LocalDate oldNext = next;
                next = next.plusDays(1);
                return oldNext;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("LocalDateInterval is immutable, " +
                        "don't try to remove elements from it");
            }
        };
    }
}

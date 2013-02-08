package com.github.serddmitry.jodainterval;

import static com.google.common.base.Preconditions.checkNotNull;

import org.joda.time.LocalDate;

import com.google.common.base.Objects;

/**
 * Created on 07/02/13
 * @author d.serdiuk
 */
final class LocalDateIntervalWithLowerBound implements LocalDateIntervalPartial {
    private final LocalDate first;

    LocalDateIntervalWithLowerBound(LocalDate first) {
        this.first = checkNotNull(first, "lower bound of the interval cannot be null");
    }

    @Override
    public boolean contains(LocalDate date) {
        return !date.isBefore(first);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper("LocalDateInterval")
                .add("first", first)
                .add("last", "∞")
                .toString();
    }

    @Override
    public int hashCode() {
        return first.hashCode();
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof LocalDateIntervalWithLowerBound) {
            LocalDateIntervalWithLowerBound that = (LocalDateIntervalWithLowerBound) obj;
            return this.first.equals(that.first);
        }
        return false;
    }
}

package com.github.serddmitry.jodainterval;

import static com.google.common.base.Preconditions.checkNotNull;

import org.joda.time.LocalDate;

import com.google.common.base.Objects;

/**
 * Created on 07/02/13
 * @author d.serdiuk
 */
final class LocalDateIntervalWithUpperBound implements LocalDateIntervalPartial {
    private final LocalDate last;

    public LocalDateIntervalWithUpperBound(LocalDate last) {
        this.last = checkNotNull(last, "upper bound of the interval cannot be null");
    }

    @Override
    public boolean contains(LocalDate date) {
        return !date.isAfter(last);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper("LocalDateInterval")
                .add("first", "-∞")
                .add("last", last)
                .toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof LocalDateIntervalWithUpperBound) {
            LocalDateIntervalWithUpperBound that = (LocalDateIntervalWithUpperBound) obj;
            return that.last.equals(this.last);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return last.hashCode();
    }
}

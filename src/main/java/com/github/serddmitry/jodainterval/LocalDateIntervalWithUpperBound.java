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

import org.joda.time.LocalDate;

/**
 * Created on 07/02/13
 * @author d.serdiuk
 */
final class LocalDateIntervalWithUpperBound implements LocalDateIntervalPartial {
    private final LocalDate last;

    public LocalDateIntervalWithUpperBound(LocalDate last) {
        this.last = requireNonNull(last, "upper bound of the interval cannot be null");
    }

    @Override
    public boolean contains(LocalDate date) {
        return !date.isAfter(last);
    }

    @Override
    public String toString() {
        return "LocalDateIntervalWithUpperBound{" +
                "first=-∞, " +
                "last=" + last +
                '}';
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

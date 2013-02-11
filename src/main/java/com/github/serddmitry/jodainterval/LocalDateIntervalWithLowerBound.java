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

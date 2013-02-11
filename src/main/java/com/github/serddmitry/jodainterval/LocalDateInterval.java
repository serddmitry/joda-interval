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

import org.joda.time.LocalDate;

/**
 * A finite interval of Dates expressed by JodaTime LocalDate.<br/>
 * Implementation is immutable.<br/>
 * Supports iteration over days from the interval.<br/>
 * <pre>
 * LocalDateInterval loanInterval = LocalDateIntervals.includingLast(loanTakenDate, today);
 *
 * for (LocalDate date: loanInterval) {
 *     calculateInterest(date);
 * }
 *
 * int loanLength = loanInterval.getDays();
 * </pre>
 * Created on 07/02/13
 * @author d.serdiuk
 */
public interface LocalDateInterval extends Iterable<LocalDate>, LocalDateIntervalPartial {
    /** The first date from the interval, always not null */
    LocalDate getFirst();

    /** The last date from the interval, always not null */
    LocalDate getLast();

    /**
     * Amount of days in the interval.
     * For example, for an interval with first date = 1 Jan and last date 3 Jan getDays() will return 3.
     */
    int getDays();
}

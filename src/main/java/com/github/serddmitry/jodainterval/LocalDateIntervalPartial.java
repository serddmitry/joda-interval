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
 * An interval that may have one of it's bounds not set.
 * For example, a partial interval could be one of:
 * <ul>
 *     <li>(-∞ .. 2013-02-07]</li>
 *     <li>[2013-02-10 .. +∞)</li>
 *     <li>[2013-02-10 .. 2013-02-25]</li>
 * </ul>
 * Therefore, it's neither possible to tell the size of an interval (it can be ∞), nor iterate over it.<br/>
 * For more specific interface, see {@link LocalDateInterval}
 * <p/>
 * Created on 07/02/13
 * @author d.serdiuk
 */
public interface LocalDateIntervalPartial {
    /** @return true if date lies within the interval */
    boolean contains(LocalDate date);
}

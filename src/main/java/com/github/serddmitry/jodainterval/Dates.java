package com.github.serddmitry.jodainterval;

import static com.google.common.base.Preconditions.checkNotNull;

import org.joda.time.ReadableInstant;
import org.joda.time.ReadablePartial;

/**
 * Provides useful static methods to use with joda-time date and time classes.
 * The methods have the same meaning as Math.min and Math.max, only for dates.<br/>
 * All methods will not tolerate nulls and throw descriptive NPEs.
 * If dates are compared and appear to be equal, the instance passed as a first argument is returned.
 * <p/>
 * Created on 06/02/13
 * @author d.serdiuk
 */
public class Dates {
    /**
     * @throws NullPointerException if any passed argument is null
     * @return earlier of two dates
     */
    public static <T extends ReadablePartial> T earlier(T date1, T date2) {
        checkNotNull(date1, "cannot get earlier of two dates if first date is null");
        checkNotNull(date2, "cannot get earlier of two dates if second date is null");
        return date1.compareTo(date2) <= 0 ? date1 : date2;
    }

    /**
     * @throws NullPointerException if any passed argument is null
     * @return later of two dates
     */
    public static <T extends ReadablePartial> T later(T date1, T date2) {
        checkNotNull(date1, "cannot get later of two dates if first date is null");
        checkNotNull(date2, "cannot get later of two dates if second date is null");
        return date1.compareTo(date2) >= 0? date1: date2;
    }

    /**
     * @throws NullPointerException if any passed argument is null
     * @return earlier of two dates
     */
    public static <T extends ReadableInstant> T earlier(T date1, T date2) {
        checkNotNull(date1, "cannot get earlier of two dates if first date is null");
        checkNotNull(date2, "cannot get earlier of two dates if second date is null");
        return date1.compareTo(date2) <= 0? date1: date2;
    }

    /**
     * @throws NullPointerException if any passed argument is null
     * @return later of two dates
     */
    public static <T extends ReadableInstant> T later(T date1, T date2) {
        checkNotNull(date1, "cannot get later of two dates if first date is null");
        checkNotNull(date2, "cannot get later of two dates if second date is null");
        return date1.compareTo(date2) >= 0? date1: date2;
    }
}

## LocalDateInterval

LocalDateInterval is an abstraction that holds, well, an interval of dates, starting from start date 
and up to (and including) end date. 
Upon construction, arguments are validated to make sure that they represent a valid
interval.
```
LocalDateInterval loanInterval = LocalDateInterval.includingLast(loanTakenDate, today);
```
Provides useful methods of checking whether a particular date is within interval,
```
if (loanInterval.contains(repaymentDate)) {
  ...
}
```
amount of days in the interval
```
int loanLength = loanInterval.getDays();
```
and an iterator:
```
for (LocalDate date: loanInterval) {
  calculateInterest(date);
}
```
LocalDateInterval extends LocalDateIntervalPartial interface.
## LocalDateIntervalPartial
A more generic abstraction that represents an interval that can have only a single bound so it
neither supports iteration but only allows checking whether any particular date is within the interval.
An examples of partial interval:

- (-∞ .. 2013-02-07]
- [2013-02-10 .. +∞)
- [2013-02-10 .. 2013-02-25]

Static factory methods in ```LocalDateIntervals``` support creation of both open and closed intervals:
- excludingLast(LocalDate, LocalDate)
- includingLast(LocalDate, LocalDate)
- sinceAndExcluding(LocalDate)
- sinceAndIncluding(LocalDate)
- upToAndExcluding(LocalDate)
- upToAndIncluding(LocalDate)

## Dates.earlier(a,b) and Dates.later(a,b)
With static methods in Dates class, we can make the code prettier when in need to keep dates in boundaries.
```
LocalDate loanStartThisMonth = Dates.later(loanStart, firstDayOfThisMonth);
LocalDate loanEndThisMonth = Dates.ealier(loanEnd, lastDayOfThisMonth);
```
instead of
```
LocalDate loanStartThisMonth = loanStart.isAfter(firstDayOfThisMonth) ? loanStart: firstDayOfThisMonth;
LocalDate loanEndThisMonth = loanEnd.isBefore(lastDayOfThisMonth) ? loanEnd: endDayOfThisMonth;
```
Additionally, a NPE with meaningful message is thrown if either date is null.


-------------------------------

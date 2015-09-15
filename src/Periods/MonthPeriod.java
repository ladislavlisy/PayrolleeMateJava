package Periods;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class MonthPeriod implements Comparable<MonthPeriod>, Cloneable {
	public static final int PRESENT = 0;
	public static final int TERM_BEG_FINISHED = 32;
	public static final int TERM_END_FINISHED =  0;
	public static final int WEEKSUN_SUNDAY = 0;
	public static final int WEEKMON_SUNDAY = 7;

	public static int dayOfWeekMonToSun(DayOfWeek periodDateCwd)
	{
		// DayOfWeek Sunday = 7,
		// Monday = 1, Tuesday = 2, Wednesday = 3, Thursday = 4, Friday = 5, Saturday = 6,
		return periodDateCwd.getValue();
	}

	public static MonthPeriod createFromYearAndMonth(int year, int month)
	{
		return new MonthPeriod(year*100 + month);
	}

	public static MonthPeriod empty()
	{
		return new MonthPeriod(PRESENT);
	}

	public static MonthPeriod beginYear(int year)
	{
		return new MonthPeriod(year*100 + 1);
	}

	public static MonthPeriod endYear(int year)
	{
		return new MonthPeriod(year*100 + 12);
	}

	private int code;
	
	public MonthPeriod(int code)
	{
		this.code = code;
	}
	
	public MonthPeriod(MonthPeriod element)
	{
		this.code = element.code;
	}

	public MonthPeriod(int year, int month)
	{
		this(year*100 + month);
	}

	public int getCode() { return code; }

	public int year()
	{
		return (code / 100);
	}

	public byte month()
	{
		return (byte)(code % 100);
	}

	public int yearInt()
	{
		return (code / 100);
	}

	public int monthInt()
	{
		return (code % 100);
	}

	public int monthOrder()
	{
		return (Math.max(0, yearInt() - 2000)*12 + monthInt());
	}

	public int daysInMonth()
	{
		return YearMonth.of(yearInt(), monthInt()).lengthOfMonth();
	}

	public LocalDate beginOfMonth()
	{
		return LocalDate.of(yearInt(), monthInt(), 1);
	}

	public LocalDate endOfMonth()
	{
		return LocalDate.of(yearInt(), monthInt(), daysInMonth());
	}

	public LocalDate dateOfMonth(int dayOrdinal)
	{
		int periodDay = Math.min(Math.max(1, dayOrdinal), daysInMonth());

		return LocalDate.of(yearInt(), monthInt(), periodDay);
	}

	public int weekDayOfMonth(int dayOrdinal)
	{
		LocalDate periodDate = dateOfMonth(dayOrdinal);

		DayOfWeek periodDateCwd = periodDate.getDayOfWeek();

		return dayOfWeekMonToSun(periodDateCwd);
	}

	public String description()
	{
		LocalDate firstPeriodDay = beginOfMonth();
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH);
		return firstPeriodDay.format(dateFormatter);
	}

	@Override
	public int compareTo(MonthPeriod other) {
		return (this.code - other.code);
	}
	
	@Override
	public boolean equals(Object other)
	{
		if (this == other)
		{
			return true;
		} else if (other == null) {
		    return false;
		} else if (other instanceof MonthPeriod) {
			MonthPeriod otherPeriod = (MonthPeriod) other;
			return this.isEqualToPeriod(otherPeriod);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
        int prime = 31;
        int result = super.hashCode();

        result += prime * result + this.code;

        return result;		
	}

	public boolean isEqualToPeriod(MonthPeriod other)
	{
		return (this.code == other.code);
	}

	@Override
	public String toString()
	{
		return Integer.toString(this.code);
	}

	@Override
	public Object clone()
	{
		MonthPeriod otherPeriod;
		try
		{
			otherPeriod = (MonthPeriod)super.clone();
		}
		catch (CloneNotSupportedException e)
		{
			throw new Error();
		}

		// Deep clone member fields here
		otherPeriod.code = this.code;
		return otherPeriod;
	}

}

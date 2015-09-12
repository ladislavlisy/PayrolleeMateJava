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

	private int code;
	
	public MonthPeriod(int code)
	{
		this.code = code;
	}
	
	public MonthPeriod(MonthPeriod element)
	{
		this.code = element.code;
	}

	public static MonthPeriod Empty()
	{
		return new MonthPeriod(PRESENT);
	}
	
	public static MonthPeriod BeginYear(int year)
	{
		return new MonthPeriod(year*100 + 1);
	}

	public static MonthPeriod EndYear(int year)
	{
		return new MonthPeriod(year*100 + 12);
	}

	public MonthPeriod(int year, int month)
	{
		this(year*100 + month);
	}

	public int Code() { return code; }

	public int Year()
	{
		return (code / 100);
	}

	public byte Month()
	{
		return (byte)(code % 100);
	}

	public int YearInt()
	{
		return (int)(code / 100);
	}

	public int MonthInt()
	{
		return (int)(code % 100);
	}

	public int MonthOrder()
	{
		return (Math.max(0, YearInt() - 2000)*12 + MonthInt());
	}

	public int DaysInMonth()
	{
		return YearMonth.of(YearInt(), MonthInt()).lengthOfMonth();
	}

	public LocalDate BeginOfMonth()
	{
		return LocalDate.of(YearInt(), MonthInt(), 1);
	}

	public LocalDate EndOfMonth()
	{
		return LocalDate.of(YearInt(), MonthInt(), DaysInMonth());
	}

	public LocalDate DateOfMonth(int dayOrdinal)
	{
		int periodDay = Math.min (Math.max (1, dayOrdinal), DaysInMonth ());

		return LocalDate.of(YearInt(), MonthInt(), periodDay);
	}

	public int WeekDayOfMonth(int dayOrdinal)
	{
		LocalDate periodDate = DateOfMonth(dayOrdinal);

		return DayOfWeekMonToSun(periodDate);
	}

	public static int DayOfWeekMonToSun(LocalDate periodDate)
	{
		DayOfWeek periodDateCwd = periodDate.getDayOfWeek();
		// DayOfWeek Sunday = 7,
		// Monday = 1, Tuesday = 2, Wednesday = 3, Thursday = 4, Friday = 5, Saturday = 6, 
		return periodDateCwd.getValue();
	}

	public String Description()
	{
		LocalDate firstPeriodDay = BeginOfMonth();
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

        result += prime * result + (int)this.code;

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

	public Object Clone()
	{
		MonthPeriod otherPeriod = (MonthPeriod)this.Clone();
		otherPeriod.code = this.code;
		return otherPeriod;
	}

	
}

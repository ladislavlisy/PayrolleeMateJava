package Periods;

public class SpanOfYears implements Comparable<SpanOfYears>, Cloneable {
	public static final int END_YEAR_ARRAY = 2100;
	public static final int END_YEAR_INTER = 2099;

	public static SpanOfYears Year(int year)
	{
		return new SpanOfYears(year, year);
	}

	public static SpanOfYears Years(int from, int upto)
	{
		return new SpanOfYears(from, upto);
	}

	private int yearFrom;
	private int yearUpto;
	
	public SpanOfYears ()
	{
		this.yearFrom = 0;
		this.yearUpto = 0;
	}

	public SpanOfYears (int from, int upto)
	{
		this.yearFrom = from;
		this.yearUpto = upto;
	}

	public int YearFrom() { return yearFrom; }
	public int YearUpto() { return yearUpto; }


	public boolean isEqualToInterval(SpanOfYears other)
	{
		return (this.yearFrom == other.yearFrom && this.yearUpto == other.yearUpto);
	}

	@Override
	public int compareTo(SpanOfYears other) {
		Integer yearFromInt = this.yearFrom;
		Integer yearUptoInt = this.yearUpto;
		
		if (this.yearFrom != other.yearFrom)
		{
			return yearFromInt.compareTo(other.yearFrom);
		}
		return yearUptoInt.compareTo(other.yearUpto);
	}

	@Override
	public boolean equals(Object other)
	{
		if (this == other)
		{
			return true;
		} else if (other == null) {
		    return false;
		} else if (other instanceof SpanOfYears) {
			SpanOfYears otherInterval = (SpanOfYears) other;
			return this.isEqualToInterval(otherInterval);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
        int prime = 31;
        int result = super.hashCode();

        result += prime * result + this.yearFrom;

        result += prime * result + this.yearUpto;
        
        return result;		
	}

	public String ClassName()
	{
		StringBuilder classNameBuilder = new StringBuilder(Integer.toString(this.yearFrom));

		if (yearFrom != yearUpto) {
			classNameBuilder.append("to").append(Integer.toString(this.yearUpto));
		}
		return classNameBuilder.toString();
	}

	@Override
	public String toString()
	{
		return this.ClassName();
	}

	public Object Clone()
	{
		SpanOfYears otherInterval = (SpanOfYears)this.Clone();
		otherInterval.yearFrom = this.yearFrom;
		otherInterval.yearUpto = this.yearUpto;
		return otherInterval;
	}

}

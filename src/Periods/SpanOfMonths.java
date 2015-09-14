package Periods;

/**
 * Created by ladislavlisy on 12/09/15.
 */
public class SpanOfMonths implements Comparable<SpanOfMonths>, Cloneable {
    public static SpanOfMonths createFromYear(int year)
    {
        return new SpanOfMonths(MonthPeriod.beginYear(year), MonthPeriod.endYear(year));
    }

    public static SpanOfMonths createFromMonth(MonthPeriod period)
    {
        return new SpanOfMonths(period, period);
    }

    private MonthPeriod periodFrom;
    private MonthPeriod periodUpto;

    public SpanOfMonths ()
    {
        this.periodFrom = MonthPeriod.empty();
        this.periodUpto = MonthPeriod.empty();
    }

    public SpanOfMonths (MonthPeriod from, MonthPeriod upto)
    {
        this.periodFrom = from;
        this.periodUpto = upto;
    }

    public SpanOfMonths (MonthPeriod period)
    {
        this.periodFrom = (MonthPeriod)period.clone();
        this.periodUpto = (MonthPeriod)period.clone();
    }

    public MonthPeriod getPeriodFrom() { return periodFrom; }
    public MonthPeriod getPeriodUpto() { return periodUpto; }


    public boolean isEqualToInterval(SpanOfMonths other)
    {
        return (this.periodFrom == other.periodFrom && this.periodUpto == other.periodUpto);
    }

    @Override
    public int compareTo(SpanOfMonths other) {
        if (this.periodFrom != other.periodFrom)
        {
            return this.periodFrom.compareTo(other.periodFrom);
        }
        return (this.periodUpto.compareTo(other.periodUpto));
    }

    @Override
    public boolean equals(Object other)
    {
        if (this == other)
        {
            return true;
        } else if (other == null) {
            return false;
        } else if (other instanceof SpanOfMonths) {
            SpanOfMonths otherInterval = (SpanOfMonths) other;
            return this.isEqualToInterval(otherInterval);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = super.hashCode();

        result += prime * result + this.periodFrom.hashCode();

        result += prime * result + this.periodUpto.hashCode();

        return result;
    }

    public String className()
    {
        StringBuilder classNameBuilder = new StringBuilder(this.periodFrom.toString());

        if (periodFrom != periodUpto) {
            classNameBuilder.append("to").append(this.periodUpto.toString());
        }
        return classNameBuilder.toString();
    }

    @Override
    public String toString()
    {
        return this.className();
    }

    @Override
    public Object clone()
    {
        SpanOfMonths otherInterval;
        try
        {
            otherInterval = (SpanOfMonths)super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            throw new Error();
        }

        // Deep clone member fields here
        otherInterval.periodFrom = (MonthPeriod)this.periodFrom.clone();
        otherInterval.periodUpto = (MonthPeriod)this.periodUpto.clone();
        return otherInterval;
    }

}

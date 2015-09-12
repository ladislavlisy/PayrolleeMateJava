package Periods;

import static org.junit.Assert.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Test;

public class TestSpanOfYears {

	@Test
	public void Should_Return_IntervalName_2013 ()
	{
		SpanOfYears testInterval = new SpanOfYears(2013, 2013);
		String testName = testInterval.ClassName ();
		assertEquals ("2013", testName);
	}

	@Test
	public void Should_Return_IntervalName_2011to2013 ()
	{
		SpanOfYears testInterval = new SpanOfYears(2011, 2013);
		String testName = testInterval.ClassName ();
		assertEquals ("2011to2013", testName);
	}

	@Test
	public void Should_Return_IntervalArray_2011_2015 ()
	{
		int[] testChangeYears = new int[] {2011,2012,2014,2016,2017,0};

		SeqOfYears testYearArray = new SeqOfYears (testChangeYears);

		List<SpanOfYears> expIntervalArray = Stream.of(
				new SpanOfYears(2011, 2011),
				new SpanOfYears(2012, 2013),
				new SpanOfYears(2014, 2015),
				new SpanOfYears(2016, 2016),
				new SpanOfYears(2017, 2099)).
				sequential().
				collect(Collectors.toList());

		List<SpanOfYears> testIntervalArray = testYearArray.toYearsIntervalList();
		assertEquals(expIntervalArray, testIntervalArray);
	}

	@Test
	public void Should_Return_Interval_2011_For_Period_2011 ()
	{
		int[] testChangeYears = new int[] {2011,2012,2014,2016,2017,0};

		SeqOfYears testYearArray = new SeqOfYears (testChangeYears);

		MonthPeriod testPeriod = new MonthPeriod (2011, 1);
		SpanOfYears expInterval = new SpanOfYears(2011, 2011);
		SpanOfYears testInterval = testYearArray.yearsIntervalForPeriod(testPeriod);
		assertEquals (expInterval, testInterval);
	}

	@Test
	public void Should_Return_Interval_2016_For_Period_2016 ()
	{
		int[] testChangeYears = new int[] {2011,2012,2014,2016,2017,0};

		SeqOfYears testYearArray = new SeqOfYears (testChangeYears);

		MonthPeriod testPeriod = new MonthPeriod (2016, 1);
		SpanOfYears expInterval = new SpanOfYears(2016, 2016);
		SpanOfYears testInterval = testYearArray.yearsIntervalForPeriod(testPeriod);
		assertEquals (expInterval, testInterval);
	}

	@Test
	public void Should_Return_Interval_2012to2013_For_Period_2013 ()
	{
		int[] testChangeYears = new int[] {2011,2012,2014,2016,2017,0};

		SeqOfYears testYearArray = new SeqOfYears (testChangeYears);

		MonthPeriod testPeriod = new MonthPeriod (2013, 1);
		SpanOfYears expInterval = new SpanOfYears(2012, 2013);
		SpanOfYears testInterval = testYearArray.yearsIntervalForPeriod(testPeriod);
		assertEquals (expInterval, testInterval);
	}

	@Test
	public void Should_Return_Interval_2017to2099_For_Period_2018 ()
	{
		int[] testChangeYears = new int[] {2011,2012,2014,2016,2017,0};

		SeqOfYears testYearArray = new SeqOfYears (testChangeYears);

		MonthPeriod testPeriod = new MonthPeriod (2018, 1);
		SpanOfYears expInterval = new SpanOfYears(2017, 2099);
		SpanOfYears testInterval = testYearArray.yearsIntervalForPeriod(testPeriod);
		assertEquals (expInterval, testInterval);
	}
}

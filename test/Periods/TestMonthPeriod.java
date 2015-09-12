package Periods;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestMonthPeriod {

	static final int testPeriodCodeJan = 201401;
	static final int testPeriodCodeFeb = 201402;
	static final int testPeriodCode501 = 201501;
	static final int testPeriodCode402 = 201402;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void Should_Compare_Different_Periods_AsEqual_When_2014_01()
	{
		MonthPeriod testPeriodOne = new MonthPeriod (testPeriodCodeJan);

		MonthPeriod testPeriodTwo = new MonthPeriod (testPeriodCodeJan);

		assertEquals(testPeriodOne, testPeriodTwo);
	}
	@Test
	public void Should_Compare_Different_Periods_AsEqual_When_2014_02()
	{
		MonthPeriod testPeriodOne = new MonthPeriod (testPeriodCodeFeb);

		MonthPeriod testPeriodTwo = new MonthPeriod (testPeriodCodeFeb);

		assertEquals(testPeriodOne, testPeriodTwo);
	}
	@Test
	public void Should_Compare_Different_Periods_SameYear_AsGreater()
	{
		MonthPeriod testPeriodOne = new MonthPeriod (testPeriodCodeJan);

		MonthPeriod testPeriodTwo = new MonthPeriod (testPeriodCodeFeb);

		assertNotEquals(testPeriodTwo, testPeriodOne);

		assertTrue(testPeriodTwo.compareTo(testPeriodOne) > 0);
	}
	@Test
	public void Should_Compare_Different_Periods_SameYear_AsLess()
	{
		MonthPeriod testPeriodOne = new MonthPeriod (testPeriodCodeJan);

		MonthPeriod testPeriodTwo = new MonthPeriod (testPeriodCodeFeb);

		assertNotEquals(testPeriodOne, testPeriodTwo);

		assertTrue(testPeriodOne.compareTo(testPeriodTwo) < 0);
	}
	@Test
	public void Should_Compare_Different_Periods_SameMonth_AsGreater()
	{
		MonthPeriod testPeriodOne = new MonthPeriod (testPeriodCodeJan);

		MonthPeriod testPeriodTwo = new MonthPeriod (testPeriodCode501);

		assertNotEquals(testPeriodTwo, testPeriodOne);

		assertTrue(testPeriodTwo.compareTo(testPeriodOne) > 0);
	}
	@Test
	public void Should_Compare_Different_Periods_SameMonth_AsLess()
	{
		MonthPeriod testPeriodOne = new MonthPeriod (testPeriodCodeJan);

		MonthPeriod testPeriodTwo = new MonthPeriod (testPeriodCode501);

		assertNotEquals(testPeriodOne, testPeriodTwo);

		assertTrue(testPeriodOne.compareTo(testPeriodTwo) < 0);
	}
	@Test
	public void Should_Compare_Different_Periods_DifferentYear_AsGreater()
	{
		MonthPeriod testPeriodOne = new MonthPeriod (testPeriodCode402);

		MonthPeriod testPeriodTwo = new MonthPeriod (testPeriodCode501);

		assertNotEquals(testPeriodTwo, testPeriodOne);

		assertTrue(testPeriodTwo.compareTo(testPeriodOne) > 0);
	}
	@Test
	public void Should_Compare_Different_Periods_DifferentYear_AsLess()
	{
		MonthPeriod testPeriodOne = new MonthPeriod (testPeriodCode402);

		MonthPeriod testPeriodTwo = new MonthPeriod (testPeriodCode501);

		assertNotEquals(testPeriodOne, testPeriodTwo);

		assertTrue(testPeriodOne.compareTo(testPeriodTwo) < 0);
	}
	@Test
	public void Should_Return_Periods_Year_And_Month_2014_01()
	{
		MonthPeriod testPeriodOne = new MonthPeriod (testPeriodCodeJan);

		assertEquals(testPeriodOne.year(), 2014);
		assertEquals(testPeriodOne.month(), 1);

		assertEquals(testPeriodOne.yearInt(), 2014);
		assertEquals(testPeriodOne.monthInt(), 1);
	}
	@Test
	public void Should_Return_Periods_Year_And_Month_2014_02()
	{
		MonthPeriod testPeriodTwo = new MonthPeriod (testPeriodCodeFeb);

		assertEquals(testPeriodTwo.year(), 2014);
		assertEquals(testPeriodTwo.month(), 2);

		assertEquals(testPeriodTwo.yearInt(), 2014);
		assertEquals(testPeriodTwo.monthInt(), 2);
	}
	@Test
    public void Should_Return_Periods_Month_And_Year_Description()
	{
		MonthPeriod testPeriodJan = new MonthPeriod (testPeriodCodeJan);
		MonthPeriod testPeriodFeb = new MonthPeriod (testPeriodCodeFeb);
		MonthPeriod testPeriod501 = new MonthPeriod (testPeriodCode501);
		MonthPeriod testPeriod402 = new MonthPeriod (testPeriodCode402);

		assertEquals(("January 2014"), testPeriodJan.description());
		assertEquals(("February 2014"), testPeriodFeb.description());
		assertEquals(("January 2015"), testPeriod501.description());
		assertEquals(("February 2014"), testPeriod402.description());
	}
}

package Periods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class SeqOfYears {
	public static final int END_YEAR_ARRAY = 2100;
	public static final int END_YEAR_INTER = 2099;

	private List<Integer> milestones;

	public SeqOfYears (int[] years)
	{
		Comparator<Integer> sortYears = (year1, year2) -> {
		    Integer compYear1 = (year1==0 ? END_YEAR_ARRAY : year1);
		    Integer compYear2 = (year2==0 ? END_YEAR_ARRAY : year2);
			 			 
		    return compYear1.compareTo(compYear2);
		  };
		this.milestones = Arrays.stream(years).boxed().sorted(sortYears).collect(Collectors.toList());	
	}

	public SpanOfYears yearsIntervalForPeriod(MonthPeriod period)
	{
		BiFunction<SpanOfYears,Integer,SpanOfYears> forPeriodAccumulator = (agr, x) -> {
			Integer intFrom = agr.getYearFrom();
			Integer intUpto = agr.getYearUpto();
			Integer intYear = x;
			if (x == 0)
			{
				intYear = END_YEAR_ARRAY;
			}
			if (period.year() >= intYear)
			{
				intFrom = intYear;
			}
			if (period.year() < intYear && intUpto == 0)
			{
				intUpto = (intYear-1);
			}
			return new SpanOfYears(intFrom, intUpto);
		};
		BinaryOperator<SpanOfYears> forPeriodCombiner = (agr1, agr2) -> (agr2);
		SpanOfYears initsSpan = new SpanOfYears();
		SpanOfYears validSpan = milestones.stream().sequential().reduce(initsSpan, forPeriodAccumulator, forPeriodCombiner);
		return validSpan;
	}

	public List<SpanOfYears> toYearsIntervalList()
	{
		BiFunction<List<SpanOfYears>,Integer,List<SpanOfYears>> toListAccumulator = (agr, x) -> {
			List<SpanOfYears> firsts = agr.stream().sequential().filter((y) -> (y.getYearUpto() != 0)).collect(Collectors.toList());
			
			if (agr.size() == 0)
			{
				firsts.add(new SpanOfYears(x, 0));
				return firsts;
			}
			else
			{
				SpanOfYears last = agr.get(agr.size()-1);	
	
				if (x == 0)
				{
					Integer historyFrom = last.getYearFrom();
					Integer historyUpto = END_YEAR_INTER;
	
					firsts.add(new SpanOfYears(historyFrom, historyUpto));
					return firsts;
				}
				else
				{
					Integer historyFrom = last.getYearFrom();
					Integer historyUpto = Math.max((x-1), historyFrom);
	
					firsts.add(new SpanOfYears(historyFrom, historyUpto));
					firsts.add(new SpanOfYears(x, 0));
					return firsts;
				}
			}
		};

		BinaryOperator<List<SpanOfYears>> toListCombiner = (agr1, agr2) -> (agr2);

		List<SpanOfYears> history = milestones.stream().sequential().reduce(new ArrayList<SpanOfYears>(), toListAccumulator, toListCombiner);
		
		SpanOfYears lastPart = history.get(history.size() - 1);

		if (lastPart.getYearUpto() == 0)
		{
			List<SpanOfYears> firstPart = history.stream().sequential().filter((y) -> (y.getYearUpto() != 0)).collect(Collectors.toList());

			Integer historyFrom = lastPart.getYearFrom();
			Integer historyUpto = lastPart.getYearFrom();

			firstPart.add(new SpanOfYears(historyFrom, historyUpto));
			return firstPart;
		}
		return history;
	}

}

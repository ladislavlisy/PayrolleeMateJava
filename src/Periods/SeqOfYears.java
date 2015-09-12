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

	public List<Integer> milestones;

	public SeqOfYears (int[] years)
	{
		Comparator<Integer> sortYears = (year1, year2) -> {
		    Integer compYear1 = (year1==0 ? END_YEAR_ARRAY : year1);
		    Integer compYear2 = (year2==0 ? END_YEAR_ARRAY : year2);
			 			 
		    return compYear1.compareTo(compYear2);
		  };
		this.milestones = Arrays.stream(years).boxed().sorted(sortYears).collect(Collectors.toList());	
	}

	public SpanOfYears SpanForPeriod(MonthPeriod period)
	{
		BiFunction<SpanOfYears,Integer,SpanOfYears> spanOfPeriodReducer = (agr, x) -> {
			Integer intFrom = agr.YearFrom();
			Integer intUpto = agr.YearUpto();
			Integer intYear = x;
			if (x == 0)
			{
				intYear = END_YEAR_ARRAY;
			}
			if (period.Year() >= intYear)
			{
				intFrom = intYear;
			}
			if (period.Year() < intYear && intUpto == 0)
			{
				intUpto = (intYear-1);
			}
			return new SpanOfYears(intFrom, intUpto);
		};
		BinaryOperator<SpanOfYears> spanOfPeriodAccept = (agr1, agr2) -> {
			return agr2;
		};
		SpanOfYears initsSpan = new SpanOfYears();
		SpanOfYears validSpan = milestones.stream().sequential().reduce(initsSpan, spanOfPeriodReducer, spanOfPeriodAccept);
		return validSpan;
	}

	public List<SpanOfYears> ToArray()
	{
		BiFunction<List<SpanOfYears>,Integer,List<SpanOfYears>> toArrayReducer = (agr, x) -> {
			List<SpanOfYears> firsts = agr.stream().sequential().filter((y) -> (y.YearUpto() != 0)).collect(Collectors.toList());
			
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
					Integer historyFrom = last.YearFrom();
					Integer historyUpto = END_YEAR_INTER;
	
					firsts.add(new SpanOfYears(historyFrom, historyUpto));
					return firsts;
				}
				else
				{
					Integer historyFrom = last.YearFrom();
					Integer historyUpto = Math.max((x-1), historyFrom);
	
					firsts.add(new SpanOfYears(historyFrom, historyUpto));
					firsts.add(new SpanOfYears(x, 0));
					return firsts;
				}
			}
		};

		BinaryOperator<List<SpanOfYears>> toArrayAccept = (agr1, agr2) -> {
			return agr2;
		};
		List<SpanOfYears> history = milestones.stream().sequential().reduce(new ArrayList<SpanOfYears>(), toArrayReducer, toArrayAccept);
		
		SpanOfYears historylast = history.get(history.size()-1);	

		if (historylast.YearUpto() == 0)
		{
			List<SpanOfYears> historyFirsts = history.stream().sequential().filter((y) -> (y.YearUpto() != 0)).collect(Collectors.toList());

			Integer historyFrom = historylast.YearFrom();
			Integer historyUpto = historylast.YearFrom();

			historyFirsts.add(new SpanOfYears(historyFrom, historyUpto));
			return historyFirsts;
		}
		return history;
	}

}

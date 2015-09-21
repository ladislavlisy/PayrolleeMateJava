package Periods;

import com.codepoetics.protonpack.StreamUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SeqOfYears {
	public static final int END_YEAR_ARRAY = 2100;
	public static final int END_YEAR_INTER = 2099;

	private static int transformZeroToUpto(int year) {
		return (year == 0) ? SeqOfYears.END_YEAR_ARRAY : year;
	}

	private static SpanOfYears transformYearsToSpan(int yearFrom, int yearUpto) {
		int tranUpto = SeqOfYears.transformZeroToUpto(yearUpto);
		int spanUpto = (tranUpto == yearFrom) ? tranUpto : (tranUpto - 1);
		return new SpanOfYears(yearFrom, spanUpto);
	}

	private static boolean selectForPeriod(SpanOfYears span, MonthPeriod period) {
		return period.year() >= span.getYearFrom() && period.year() <= span.getYearUpto();
	}

	public static List<SpanOfYears> initFromArray(int[] years) {
        Comparator<Integer> sortYears = (year1, year2) -> {
            Integer compYear1 = (year1==0 ? END_YEAR_ARRAY : year1);
            Integer compYear2 = (year2==0 ? END_YEAR_ARRAY : year2);

            return compYear1.compareTo(compYear2);
        };
        BiFunction<Integer,Integer,SpanOfYears> zipCombiner = (yearFrom, yearUpto) -> {
            int tranUpto = SeqOfYears.transformZeroToUpto(yearUpto);
            int spanUpto = (tranUpto == yearFrom) ? tranUpto : (tranUpto - 1);
            return new SpanOfYears(yearFrom, spanUpto);
        };
        List<Integer> sortedYears = Arrays.stream(years).boxed().sorted(sortYears).collect(Collectors.toList());
        List<Integer> beginsYears = sortedYears.stream().sequential().filter((year) -> year != 0).collect(Collectors.toList());
        List<Integer> finishYears = sortedYears.stream().sequential().skip(1).collect(Collectors.toList());
        Stream<SpanOfYears> sortedZiped = StreamUtils.zip(beginsYears.stream().sequential(), finishYears.stream().sequential(), zipCombiner);
        return sortedZiped.collect(Collectors.toList());
    }

    private List<SpanOfYears> milestones;

	public SeqOfYears (int[] years)
	{
		this.milestones = initFromArray(years);
	}

	public SpanOfYears yearsIntervalForPeriod(MonthPeriod period)
	{
        Predicate<SpanOfYears> selectForPeriod = (span) -> {
            return period.year() >= span.getYearFrom() && period.year() <= span.getYearUpto();
		};
		List<SpanOfYears> validSpans = milestones.stream().sequential().filter(selectForPeriod).collect(Collectors.toList());
		return validSpans.get(0);
	}

    public List<SpanOfYears> toYearsIntervalList()
	{
		return new ArrayList<SpanOfYears>(milestones);
	}

}

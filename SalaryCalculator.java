import java.util.Random;

public class SalaryCalculator {
    enum Applause {
        EXCELLENT, AMAZING, SUPER, FANTASTIC
    }

    // Symbolic constants
    private static final double BASE_SALARY = 200.0;
    private static final double COMMISSION_RATE = 0.09;
    private static final double[] SALARY_RANGES = new double[]{200, 300, 400, 500, 600, 700, 800, 900, 1000};
    
    private final int[] countRanges = new int[SALARY_RANGES.length];

    public double calculateTotalSalary(double salesAmount) {
        return BASE_SALARY + (COMMISSION_RATE * salesAmount);
    }

    public void updateSalaryRanges(double totalSalary) {
        for (int i = 0; i < SALARY_RANGES.length; i++) {
            if (totalSalary >= SALARY_RANGES[i] &&
                totalSalary < (i == SALARY_RANGES.length - 1 ? Double.POSITIVE_INFINITY : SALARY_RANGES[i + 1])) {
                countRanges[i]++;
                break;
            }
        }
    }

    public String getSalaryTable() {
        StringBuilder sb = new StringBuilder();
        sb.append("Range\t\t\tNumber\n");
        for (int i = 0; i < SALARY_RANGES.length; i++) {
            String rangeString = i == SALARY_RANGES.length - 1
                    ? String.format("$%.0f and over", SALARY_RANGES[i])
                    : String.format("$%.0f-$%.0f", SALARY_RANGES[i], SALARY_RANGES[i + 1] - 1);
            sb.append(String.format("%-20s%d\n", rangeString, countRanges[i]));
        }
        return sb.toString();
    }

    public String getRandomApplause() {
        Applause[] applauseOptions = Applause.values();
        Random random = new Random();
        Applause selectedApplause = applauseOptions[random.nextInt(applauseOptions.length)];
        return selectedApplause + "!";
    }
}

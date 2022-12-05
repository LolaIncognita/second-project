import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class YearlyReport {
    public int year;
    public HashMap<Integer, YearlyReportByMonth> monthsData = new HashMap<>();

    public YearlyReport(int year, String path) {
        this.year = year;

        String contentYearReport = readFileContentsOrNull(path);
        String[] linesYearReport = contentYearReport.split("\r?\n");

        for (int i = 1; i < linesYearReport.length; i++) {
            String lineYearReport = linesYearReport[i];
            String[] parts = lineYearReport.split(",");
            int month = Integer.parseInt(parts[0]);
            int sum = Integer.parseInt(parts[1]);
            boolean isExpense = Boolean.parseBoolean(parts[2]);

            if (!monthsData.containsKey(month)) {
                monthsData.put(month, new YearlyReportByMonth(month));
            }

            YearlyReportByMonth oneMonthData = monthsData.get(month);
            if (isExpense) {
                oneMonthData.expenses += sum;
            } else {
                oneMonthData.income += sum;
            }
        }

    }

    public int getProfitOfMonth(int month) {
        YearlyReportByMonth oneMonthData = monthsData.get(month);
        return oneMonthData.income - oneMonthData.expenses;
    }

    public int getTotalIncomesOfMonth(int month) {
        YearlyReportByMonth oneMonthData = monthsData.get(month);
        return oneMonthData.income;
    }

    public int getTotalExpensesOfMonth(int month) {
        YearlyReportByMonth oneMonthData = monthsData.get(month);
        return oneMonthData.expenses;
    }

    public int monthlyAverageExpenses() {
        int yearExpenses = 0;
        int monthlyAverageExpense;
        int monthCount = 0;

        for (YearlyReportByMonth oneMonthData : monthsData.values()) {
            yearExpenses += oneMonthData.expenses;
            monthCount++;
        }
        monthlyAverageExpense = yearExpenses / monthCount;
        return monthlyAverageExpense;
    }

    public int monthlyAverageIncome() {
        int yearIncome = 0;
        int monthlyAverageIncome;
        int monthCount = 0;

        for (YearlyReportByMonth oneMonthData : monthsData.values()) {
            yearIncome += oneMonthData.income;
            monthCount++;
        }
        monthlyAverageIncome = yearIncome / monthCount;
        return monthlyAverageIncome;
    }


    private String readFileContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException var3) {
            System.out.println("Невозможно прочитать файл с годовым отчётом. Возможно, файл не находится " +
                    "в нужной директории.");
            return null;
        }
    }
}

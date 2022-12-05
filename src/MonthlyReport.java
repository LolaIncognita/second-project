import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReport {
    int month;

    public HashMap<Integer, ArrayList<MonthlyLineReport>> DataOfMonth = new HashMap<>();


    public MonthlyReport (int month, String path) {
        this.month = month;

        String contentMonthReport = readFileContentsOrNull(path);
        String[] linesMonthReport = contentMonthReport.split("\r?\n");
        ArrayList<MonthlyLineReport> monthlyLineReports = new ArrayList<>();

        for (int i = 0; i < linesMonthReport.length; i++) {
            String oneLineMonthReport = linesMonthReport[i];
            String[] parts = oneLineMonthReport.split(",");

            MonthlyLineReport lineMonthReport = new MonthlyLineReport();
            lineMonthReport.itemName = parts[0];
            lineMonthReport.isExpense = Boolean.parseBoolean(parts[1]);
            lineMonthReport.quantity = Integer.parseInt(parts[2]);
            lineMonthReport.sumOfOne = Integer.parseInt(parts[3]);

            monthlyLineReports.add(lineMonthReport);
        }
        DataOfMonth.put(month, monthlyLineReports);
    }

    public void getMaxProfitableItem(int month) {
        ArrayList<MonthlyLineReport> monthlyLineReports = DataOfMonth.get(month);
        int maxIncomByItem = 0;
        String maxProfitableItem = null;

        for (MonthlyLineReport oneLineReport: monthlyLineReports) {
            if(oneLineReport.isExpense = false) {
                int itemIncome = oneLineReport.quantity * oneLineReport.sumOfOne;
                if(maxIncomByItem < itemIncome) {
                    maxIncomByItem = itemIncome;
                    maxProfitableItem = oneLineReport.itemName;
                }
            }
        }
        System.out.println("Самый доходный товар: " + maxProfitableItem + ".");
        System.out.println("Доход составил: " + maxIncomByItem + " рублей.");
    }

    public void getMaxExpensiveItem(int month) {
        ArrayList<MonthlyLineReport> monthlyLineReports = DataOfMonth.get(month);
        int maxExpenseByItem = 0;
        String maxExpensiveItem = null;

        for (MonthlyLineReport oneLineReport: monthlyLineReports) {
            if(oneLineReport.isExpense = true) {
                int itemExpense = oneLineReport.quantity * oneLineReport.sumOfOne;
                if(maxExpenseByItem < itemExpense) {
                    maxExpenseByItem = itemExpense;
                    maxExpensiveItem = oneLineReport.itemName;
                }
            }
        }
        System.out.println("Товар с самой большой тратой: " + maxExpensiveItem + ".");
        System.out.println("Расходы по нему составили: " + maxExpenseByItem + " рублей.");
    }

    public int getTotalExpenses(int month) {
        ArrayList<MonthlyLineReport> monthlyLineReports = DataOfMonth.get(month);
        int totalExpenses = 0;

        for (MonthlyLineReport oneLineReport: monthlyLineReports) {
            if(oneLineReport.isExpense = true) {
                int expense = oneLineReport.quantity * oneLineReport.sumOfOne;
                totalExpenses += expense;
            }
        }
        return totalExpenses;
    }

    public int getTotalIncomes(int month) {
        ArrayList<MonthlyLineReport> monthlyLineReports = DataOfMonth.get(month);
        int totalIncomes = 0;

        for (MonthlyLineReport oneLineReport: monthlyLineReports) {
            if(oneLineReport.isExpense = false) {
                int income = oneLineReport.quantity * oneLineReport.sumOfOne;
                totalIncomes += income;
            }
        }
        return totalIncomes;
    }


    private String readFileContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException var3) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится " +
                    "в нужной директории.");
            return null;
        }
    }

    public String getMonthName(int month) {
        String monthName = null;
        switch (month) {
            case 1:
                monthName = "январь";
                break;
            case 2:
                monthName = "февраль";
                break;
            case 3:
                monthName = "март";
                break;
            case 4:
                monthName = "апрель";
                break;
            case 5:
                monthName = "май";
                break;
            case 6:
                monthName = "июнь";
                break;
            case 7:
                monthName = "июль";
                break;
            case 8:
                monthName = "август";
                break;
            case 9:
                monthName = "сентябрь";
                break;
            case 10:
                monthName = "октябрь";
                break;
            case 11:
                monthName = "ноябрь";
                break;
            case 12:
                monthName = "декабрь";
                break;
        }
        return monthName;
    }
}


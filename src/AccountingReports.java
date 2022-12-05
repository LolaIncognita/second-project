import java.util.ArrayList;
import java.util.Scanner;

public class AccountingReports {
    public static void main(String[] args) {
        int countOfMonth = 3;
        int year = 2021;
        String yearPath = null;

        YearlyReport yearReport = new YearlyReport(year, yearPath);
        ArrayList<MonthlyReport> monthlyReports = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        loop:
        while (true) {


            printMenu();

            int command;
            try {
                String input = scanner.next();
                command = Integer.parseInt(input);
                switch (command) {
                    case 1:
                        for (int i = 1; i <= countOfMonth; i++) {
                            String monthPath = "resources/m.20210" + i + ".csv";
                            MonthlyReport monthlyReport = new MonthlyReport(i, monthPath);
                            monthlyReports.add(monthlyReport);
                        }
                        break;
                    case 2:
                        yearPath = "resources/m.20210" + year + ".csv";
                        yearReport = new YearlyReport(year, yearPath);
                        break;
                    case 3:
                        int isMistake = 0;
                        for (int i = 1; i <= countOfMonth; i++) {
                           MonthlyReport monthlyReport = monthlyReports.get(i);
                            if (yearReport.getTotalExpensesOfMonth(i) != monthlyReport.getTotalExpenses(i)
                                    && yearReport.getTotalIncomesOfMonth(i) != monthlyReport.getTotalIncomes(i)) {
                                System.out.println("Расхождения обнаружены в " + i + " месяце.");
                                isMistake++;
                            }
                        }
                        if (isMistake != 0) {
                            System.out.println("Операция успешно завершена. Расхождения не обнаружены.");
                        }
                    case 4:
                        for (int i = 1; i <= countOfMonth; i++) {
                            MonthlyReport monthlyReport = monthlyReports.get(i-1);
                            System.out.println("Месяц: " + monthlyReport.getMonthName(i) + " " + year + " года.");
                            monthlyReport.getMaxProfitableItem(i);
                            monthlyReport.getMaxExpensiveItem(i);
                        }

                    case 5:
                        System.out.println("Информация по годовому отчёту за " + year + " год.");
                        System.out.println("Прибыль в разрезе месяцев составила:");
                        for (int i = 1; i <= countOfMonth; i++) {
                            System.out.println("- за " + i + " месяц: " + yearReport.getProfitOfMonth(i)
                                    + " рублей");
                        }
                        System.out.println("Средний расход в разрезе месяцев составил: "
                                + yearReport.monthlyAverageExpenses()
                                + " рублей.");
                        System.out.println("Средний доход в разрезе месяцев составил: "
                                + yearReport.monthlyAverageIncome()
                                + " рублей.");
                    case 0:
                        System.out.println("Введите код для выхода из программы");
                        int code = scanner.nextInt();
                        if (code == 123) {
                            break loop;
                        } else {
                            System.out.println("Введён неверный код");
                        }
                        break;
                    default:
                        System.out.println("Данная команда пока не предусмотрена");
                        break;
                }
            } catch (Exception e) {
                System.out.println("введите число, а не строку");
            }
        }
    }

    public static void printMenu() {
        System.out.println("Выберете действие:");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("0 - Выход");
    }
}

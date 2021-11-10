import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String[][][] zubMatrix = createMatrix();
        System.out.println("Добро пожаловать в самый неудобный ежедневник 21-го века!");
        start(zubMatrix);
    }

    private static void start(String[][][] zubMatrix) {
        int indexMonth = enterMonth() - 1;
        int indexDay = enterDay(indexMonth) - 1;
        printDay(indexMonth, indexDay, zubMatrix);
        int indexTime = enterTime();
        System.out.println(String.valueOf(indexDay + 1) + " " + month(indexMonth) + ", " + week(indexMonth, indexDay));
        System.out.println(time(indexTime) + " " + zubMatrix[indexMonth][indexDay][indexTime]);
        if (enterString()) {
            zubMatrix[indexMonth][indexDay][indexTime] = scanStr();
            printDay(indexMonth, indexDay, zubMatrix);
            start(zubMatrix);
        } else {
            start(zubMatrix);
        }
    }


    private static String scanStr() {
        System.out.println("Введите запись");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private static boolean enterString() {
        int enter;
        do {
            System.out.println("Если жалаете сделать запись в этой ячейке, введите '1', желаете вернуться в корень - '2'");
            enter = scanInt();
        } while ((enter < 1) || (enter > 2));
        if (enter == 1) {
            return true;
        } else {
            return false;
        }

    }

    private static int enterTime() {
        int time;
        do {
            System.out.println("Выбирете нужный период (введите первый час периода):");
            time = scanInt();
        } while ((time < 0) || (time >= 24));
        return time;
    }

    private static void printDay(int indexMonth, int indexDay, String[][][] zubMatrix) {
        System.out.println("");
        System.out.println(String.valueOf(indexDay + 1) + " " + month(indexMonth) + ", " + week(indexMonth, indexDay));
        for (int i = 0; i < zubMatrix[indexMonth][indexDay].length; i++) {
            System.out.println(time(i) + " " + zubMatrix[indexMonth][indexDay][i]);
        }
    }

    private static int enterDay(int indexMonth) {
        int day;
        do {
            System.out.println("Введите число нужного дня:");
            day = scanInt();
        } while ((day < 0) || (day > daysOfMonth1(indexMonth)));
        return day;
    }

    private static int enterMonth() {
        int month;
        do {
            System.out.println("Введите номер месяца:");
            month = scanInt();
        } while ((month < 0) || (month >= 13));
        return month;
    }

    private static int scanInt() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private static String[][][] createMatrix() {
        String[][][] zubMatrix = new String[12][][];
        for (int i = 0; i < zubMatrix.length; i++) {
            zubMatrix[i] = new String[daysOfMonth1(i)][24];
        }
        for (int i = 0; i < zubMatrix.length; i++) {
            for (int j = 0; j < zubMatrix[i].length; j++) {
                for (int k = 0; k < zubMatrix[i][j].length; k++) {
                    zubMatrix[i][j][k] = "_________________";
                }
            }
        }
        return zubMatrix;
    }

    public static String month(int index) {
        String result = switch (index) {
            case 0 -> "Января";
            case 1 -> "Февраля";
            case 2 -> "Марта";
            case 3 -> "Апреля";
            case 4 -> "Мая";
            case 5 -> "Июня";
            case 6 -> "Июля";
            case 7 -> "Августа";
            case 8 -> "Сентября";
            case 9 -> "Октября";
            case 10 -> "Ноября";
            case 11 -> "Декабря";
            default -> "Ошибка индекса";
        };
        return result;
    }

    //Выдает день недели. На вход ИНДЕКСЫ месяца и дня.
    public static String week(int month, int day) {
        int sdvig = 5;
        int daysOfMonths = 0;
        for (int i = 0; i < month; i++) {
            daysOfMonths += daysOfMonth1(i);
        }
        int del = (sdvig + daysOfMonths + day) % 7;
        String result2 = switch (del) {
            case 0 -> "Понедельник";
            case 1 -> "Вторник";
            case 2 -> "Среда";
            case 3 -> "Четверг";
            case 4 -> "Пятница";
            case 5 -> "Суббота";
            case 6 -> "Воскресенье";
            default -> "Ошибка индекса 02";
        };
        return result2;
    }

    //Возвращает время по индексу:
    public static String time(int index3) {
        int endPeriod = index3 + 1;
        String result3 = String.format("%d:00 - %d:00", index3, endPeriod);
        return result3;
    }

    private static int daysOfMonth1(int i) {
        int[] dayOfMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        return dayOfMonth[i];
    }
}





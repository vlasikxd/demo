import java.util.Scanner;

public class Main {

    private static boolean rome;

    public static void main(String[] args) throws Exception {
        rome = true;
        Scanner input_ = new Scanner(System.in);
        String input = input_.nextLine();
        System.out.println(calc(input));
    }

    public static String calc(String input) throws Exception {
        String[] res = input.split(" ");

        if (res.length > 3) {
            throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        if (res.length < 3) {
            throw new Exception("строка не является математической операцией");
        }

        int[] numbers = getNumbers(res);
        if (numbers[0] > 10 | numbers[1] > 10 | numbers[0] < 1 | numbers[1] < 1) {
            throw new Exception("Используйте только арабские или римские числа от 1 до 10");
        }

        if (rome) {
            return getRome(getResult(numbers, res[1]));
        } else {
            return String.valueOf(getResult(numbers, res[1]));
        }
    }

    static int[] getNumbers(String[] a) throws Exception {
        int count = 0;
        int[] result = new int[2];

        for (int i = 0; i < 3; i += 2) {
            try {
                result[count] = Integer.parseInt(a[i]);
                count++;
            } catch (Exception ignored) {
            }
        }

        if (count == 1) {
            throw new Exception("используются одновременно разные системы счисления");
        }
        if (count == 2) {
            rome = false;
            return result;
        }

        try {
            return new int[]{Rome.valueOf(a[0]).getArabic(), Rome.valueOf(a[2]).getArabic()};
        } catch (Exception b) {
            throw new Exception("Используйте только арабские или римские числа от 1 до 10");
        }
    }

    static String getRome(int number) {
        return "I".repeat(number)
                .replace("IIIII", "V")
                .replace("IIII", "IV")
                .replace("VV", "X")
                .replace("VIV", "IX")
                .replace("XXXXX", "L")
                .replace("XXXX", "XL")
                .replace("LL", "C")
                .replace("LXL", "XC")
                .replace("CCCCC", "D")
                .replace("CCCC", "CD")
                .replace("DD", "M")
                .replace("DCD", "CM");
    }

    static int getResult(int[] numbers, String sign) throws Exception {
        int result = switch (sign) {
            case "+" -> numbers[0] + numbers[1];
            case "-" -> numbers[0] - numbers[1];
            case "*" -> numbers[0] * numbers[1];
            case "/" -> numbers[0] / numbers[1];
            default ->
                    throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        };
        if (rome & result < 1) {
            throw new Exception("В римской системе нет отрицательных чисел");
        } else {
            return result;
        }
    }
}
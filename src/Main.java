import java.util.Scanner;

public class Main {

    private static boolean rome = true;

    public static void main(String[] args) throws Exception {
        Scanner input_ = new Scanner(System.in);
        String input = input_.nextLine();
        System.out.println(calc(input));
    }

    public static String calc(String input) throws Exception {
        String[] res = input.split(" ");

        if (res.length > 3) {
            throw new Exception("//т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        if (res.length < 3) {
            throw new Exception("строка не является математической операцией");
        }
        int[] numbers = getNumbers(res[0], res[2]);
        if (numbers[0] > 10 | numbers[1] > 10) {
            throw new Exception("введенные числа должны быть от 1 до 10");
        }

        if (rome) {
            return getRome(getResult(numbers, res[1]));
        } else {
            return String.valueOf(getResult(numbers, res[1]));
        }
    }
    static int[] getNumbers(String num1, String num2) throws Exception {
        try {
            int[] numbers = {Integer.parseInt(num1), Integer.parseInt(num2)};
            rome = false;
            return numbers;
        } catch (Exception a) {
            try {
                return new int[]{Rome.valueOf(num1).getArabic(), Rome.valueOf(num2).getArabic()};
            } catch (Exception b) {
                throw new Exception("используются одновременно разные системы счисления");
            }
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
            throw new Exception("D римской системе нет отрицательных чисел");
        } else {
            return result;
        }
    }
}
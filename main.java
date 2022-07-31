import java.util.Arrays;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws Exception{

        Scanner s= new Scanner(System.in);
        System.out.println("Введите математическое выражение: ");
        String operation = s.nextLine();

        String [] operands = operation.split("\\s+");

        if (operands.length < 3) {
            throw new Exception("Введенное выражение не является математической операцией");
        }
        if (operands.length > 3) {
            throw new Exception("Формат математической операции не соответствует заданию - требуется два операнда и один оператор (+, -, *, /)");
        }

        String a = operands[0], operator = operands[1], b = operands[2];

        if ((toInt(a) < 1) || (toInt(a) > 10) || (toInt(b) < 1) || (toInt(b) > 10)) {
            throw new Exception("Введено число вне диапазона значения. Допускается ввод цифр от 1 до 10");
        }

        if (toArabic(a) == -1 && toArabic(b) > -1 || toArabic(b) == -1 && toArabic(a) > -1) {
            throw new Exception("Используются одновременно разные системы счисления");
        }

            int result = 0;
            switch (operator){
                case "+"-> result = toInt(a) + toInt(b);
                case "-"-> result = toInt(a) - toInt(b);
                case "*" -> result = toInt(a) * toInt(b);
                case "/"-> result = toInt(a) / toInt(b);
        }

        if (toArabic(a) != -1){
            if (result < 0 || result == 0) {
                throw new Exception("В римской системе исчисления отсутствуют ноль и отрицательные числа");
            }else{
            System.out.println(toRoman(result));
            }
        }else{
            System.out.println(result);
        }

    }

//////////////////////////////////////////////////////////////////////////////////
     static int toInt(String str) throws Exception{
        int x = 0;
        if (toArabic(str)>-1){
            x = toArabic(str);
        }else{
            try {
                x = Integer.parseInt(str);
            } catch (NumberFormatException e) {
                throw new Exception("Неверный формат строки. Введи римскую или арабскую цифру от 1 до 10");
            }
        }
        return x;
    }

//////////////////////////////////////////////////////////////////////////////////
    static String toRoman(int a){
        String[] roman = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] arabic = {100, 90, 50, 40, 10, 9, 5, 4, 1};
        String res = "";
        for (int i = 0; i < arabic.length; i++) {
            while ( arabic[i] <= a) {
                res += roman[i];
                a -= arabic[i];
            }
        }
        return res;
    }
//////////////////////////////////////////////////////////////////////////////////
    static int toArabic(String roman) {
        return switch (roman) {
            case "I" -> 1;
            case "II" -> 2;
            case "III" -> 3;
            case "IV" -> 4;
            case "V" -> 5;
            case "VI" -> 6;
            case "VII" -> 7;
            case "VIII" -> 8;
            case "IX" -> 9;
            case "X" -> 10;
            default -> -1;
        };
    }

}




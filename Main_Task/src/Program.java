import java.time.Month;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.OptionalInt;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Program {

    /*Реализовать следующие программы:
    1. Приветствовать любого пользователя при вводе его имени через командную строку.
    2. Отобразить в окне консоли аргументы командной строки в обратном порядке.
    3. Вывести заданное количество случайных чисел с переходом и без перехода на новую строку.
    4. Ввести целые числа как аргументы командной строки, подсчитать их сумму (произведение) и вывести результат на консоль.
    5. Ввести число от 1 до 12. Вывести на консоль название месяца, соответствующего данному числу. Осуществить проверку корректности ввода чисел.*/

    private static final String MESSAGE_ABOUT_INCORRECT_DATA = "Input wrong data! Repeat please.";
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // task 1
        welcomeUser();
        // task 2
        displayDataInReverseOrder();
        // task 3
        displayRandomValues();
        // task 4
        outputResultOfCountingValues();
        // task 5
        displayNameOfMonth();
    }
//----------------------------------------------------------------------------------------------------------------------
    // 1. Приветствовать любого пользователя при вводе его имени через командную строку.
    private static void welcomeUser() {
        System.out.println("--------------- TASK 1 ---------------");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Welcome, " + name + "!");
    }
//----------------------------------------------------------------------------------------------------------------------
    // 2. Отобразить в окне консоли аргументы командной строки в обратном порядке.
    private static void displayDataInReverseOrder() {
        System.out.println("--------------- TASK 2 ---------------");
        System.out.print("Enter something: ");
        String line = scanner.nextLine();
        System.out.print("Output: ");
        for (int i = line.length() - 1; i >= 0; i--) {
            System.out.print(line.charAt(i));
        }
        System.out.println();
    }
//----------------------------------------------------------------------------------------------------------------------
    // 3. Вывести заданное количество случайных чисел с переходом и без перехода на новую строку.
    private static void displayRandomValues() {
        System.out.println("--------------- TASK 3 ---------------");
        int length = setLengthOfArray();
        int[] array = getArrayOfRandomValues(length);
        System.out.println("Output numbers with a new line transition: ");
        Arrays.stream(array).forEach(System.out::println);
        System.out.print("Output numbers without switching to a new line: ");
        Arrays.stream(array).forEach(i -> System.out.print(i + " "));
        System.out.println();
    }

    // 3.1. Метод возвращает значение переменной типа int, которое >= 0. Значение переменной будет задавать размер массива.
    private static int setLengthOfArray() {
        int amount = -1;
        while (true) {
            System.out.print("Set amount of random numbers: ");
            try {
                amount = scanner.nextInt();
                if (amount >= 0) {
                    break;
                } else {
                    System.out.println(MESSAGE_ABOUT_INCORRECT_DATA);
                }
            } catch (InputMismatchException e) {
                System.out.println(MESSAGE_ABOUT_INCORRECT_DATA);
                scanner.nextLine();
            }
        }
        return amount;
    }

    // 3.2. Метод возвращает массив типа int, элементы которого инициализированы рандомными значениями в диапозоне [0; 100).
    private static int[] getArrayOfRandomValues(int length) {
        int[] array = new int[length];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 100);
        }
        return array;
    }
//----------------------------------------------------------------------------------------------------------------------
    // 4. Ввести целые числа как аргументы командной строки, подсчитать их сумму (произведение) и вывести результат на консоль.
    private static void outputResultOfCountingValues() {
        System.out.println("--------------- TASK 4 ---------------");
        int length = setLengthOfArray();
        int[] array = getArrayOfIntegerValuesFromCommandLine(length);
        int sum = getSum(array);
        System.out.println("The sum of the numbers is: " + sum);
        int product = getProductOfAllArrayValues(array);
        System.out.println("The product of all numbers is: " + product);
    }

    // 4.1. Метод возвращает массив типа int, элементы которого инициализированы значениями из командной строки.
    private static int[] getArrayOfIntegerValuesFromCommandLine(int length) {
        int[] array = new int[length];
        for (int i = 0; i < array.length; i++) {
            while (true) {
                System.out.print("Enter an integer value: ");
                try {
                    array[i] = scanner.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println(MESSAGE_ABOUT_INCORRECT_DATA);
                    scanner.nextLine();
                }
            }
        }
        return array;
    }

    // 4.2. Метод возвращает сумму значений всех элементов массива
    private static int getSum(int[] array) {
        int sum = IntStream.of(array).sum();
        return sum;
    }

    // 4.3. Метод возвращает произведение значений всех элементов массива
    private static int getProductOfAllArrayValues(int[] array) {
        int product = 0;
        IntStream stream = IntStream.of(array);
        OptionalInt result = stream.reduce((x, y) -> x * y);
        product = result.getAsInt();
        return product;
    }
//----------------------------------------------------------------------------------------------------------------------
    // 5.1. Ввести число от 1 до 12. Вывести на консоль название месяца, соответствующего данному числу. Осуществить проверку корректности ввода чисел.
    private static void displayNameOfMonth() {
        System.out.println("--------------- TASK 5 ---------------");
        int monthNumber = setMonthValue();
        Month month = getMonth(monthNumber);
        System.out.println("The month is: " + month.name());
    }

    // 5.2. Метод возвращает значение переменной типа int, которое > 0 && < 13.
    private static int setMonthValue() {
        int monthNumber = 0;
        while (true) {
            System.out.print("Set month value: ");
            try {
                monthNumber = scanner.nextInt();
                if (monthNumber > 0 && monthNumber < 13) {
                    break;
                } else {
                    System.out.println(MESSAGE_ABOUT_INCORRECT_DATA);
                }
            } catch (InputMismatchException e) {
                System.out.println(MESSAGE_ABOUT_INCORRECT_DATA);
                scanner.nextLine();
            }
        }
        return monthNumber;
    }

    // 5.3. Метод возвращает строку с названием месяца
    private static Month getMonth(int monthNumber) {
        Month month = Month.of(monthNumber);
        return month;
    }
}

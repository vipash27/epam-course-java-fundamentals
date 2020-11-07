import java.util.InputMismatchException;
import java.util.Scanner;

public class Program {

/*1. Ввести n чисел с консоли.
2. Найти самое короткое и самое длинное число. Вывести найденные числа и их длину.
3. Вывести числа в порядке возрастания (убывания) значений их длины.
4. Вывести на консоль те числа, длина которых меньше (больше) средней длины по всем числам, а также длину.
5. Найти число, в котором количество различных цифр минимально. Если таких чисел несколько, найти первое из них.
6. Найти количество чисел, содержащих только четные цифры, а среди оставшихся — количество чисел с равным числом четных и нечетных цифр.
7. Найти число, цифры в котором идут в строгом порядке возрастания. Если таких чисел несколько, найти первое из них.
8. Найти число, состоящее только из различных цифр. Если таких чисел несколько, найти первое из них.*/

    private static final String MESSAGE_ABOUT_INCORRECT_DATA = "Input wrong data! Repeat please.";
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // task 1
        int[] array = getArrayOfIntegerValues();
        // task 2
        displayShortestAndLongestValue(array);
        // task 3
        displayValuesInDescendingAndAscendingOrderOfTheirLength(array);
        // task 4
        displayValuesWhoseLengthIsLessAndMoreAverageLengthAllNumbers(array);
        // task 5
        int valueWithMinimumCountDifferentDigits = getValueInWhichNumberOfDistinctDigitsIsMinimal(array);
        System.out.println("The value in which the number of distinct digits is minimal is: " + valueWithMinimumCountDifferentDigits);
        // task 6
        displayCountOfNumbersContainingOnlyEvenDigits(array);
        // task 7
        displayFirstNumberWithDigitsInStrictAscendingOrder(array);
        // task 8
        displayNumberContainingOnlyDifferentDigits(array);
    }

    // 1.1. Ввести n чисел с консоли.
    private static int[] getArrayOfIntegerValues() {
        System.out.println("--------------- TASK 1 ---------------");
        int length = setAmountOfValues();
        int[] array = setArrayOfIntegerValuesFromCommandLine(length);
        return array;
    }

    // 1.2. Метод возвращает массив типа int, элементы которого инициализированы значениями из командной строки.
    private static int[] setArrayOfIntegerValuesFromCommandLine(int length) {
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

    // 1.3. Метод возвращает значение переменной типа int, которое >= 0. Значение переменной будет задавать размер массива.
    private static int setAmountOfValues() {
        int lengthOfArray = 0;
        while (true) {
            System.out.print("Enter n numbers. n: ");
            try {
                lengthOfArray = scanner.nextInt();
                if (lengthOfArray > 0) {
                    break;
                } else {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println(MESSAGE_ABOUT_INCORRECT_DATA);
                scanner.nextLine();
            }
        }
        return lengthOfArray;
    }

    // 2.1. Найти самое короткое и самое длинное число. Вывести найденные числа и их длину.
    private static void displayShortestAndLongestValue(int[] array) {
        System.out.println("--------------- TASK 2 ---------------");
        int shortestValue = getShortestValue(array);
        int shortestValueLength = getCountsOfDigits(shortestValue);
        System.out.println("The shortest value is: " + shortestValue + ". Its length is: " + shortestValueLength);
        int longestValue = getLongestValue(array);
        int longestValueLength = getCountsOfDigits(longestValue);
        System.out.println("The longest value is: " + longestValue + ". Its length is: " + longestValueLength);
    }

    // 2.2. Найдем самое короткое число в массиве
    private static int getShortestValue(int[] array) {
        int shortestValueLength = getCountsOfDigits(array[0]);
        int index = 0;
        for (int i = 1; i < array.length; i++) {
            int valueLength = getCountsOfDigits(array[i]);
            if (shortestValueLength > valueLength) {
                shortestValueLength = valueLength;
                index = i;
            }
        }
        return array[index];
    }

    // 2.3. Найдем самое длинное число в массиве
    private static int getLongestValue(int[] array) {
        int longestValueLength = getCountsOfDigits(array[0]);
        int index = 0;
        for (int i = 1; i < array.length; i++) {
            int valueLength = getCountsOfDigits(array[i]);
            if (longestValueLength < valueLength) {
                longestValueLength = valueLength;
                index = i;
            }
        }
        return array[index];
    }

    // 3.1. Вывести числа в порядке возрастания (убывания) значений их длины.
    private static void displayValuesInDescendingAndAscendingOrderOfTheirLength(int[] array) {
        System.out.println("--------------- TASK 3 ---------------");
        int[] arr1 = sortArrayValuesInAscendingOrderOfTheirLength(array);
        System.out.print("Values in ascending order of their length: ");
        for (int i : arr1) {
            System.out.print(i + " ");
        }
        System.out.println();
        int[] arr2 = sortArrayValuesInDescendingOrderOfTheirLength(array);
        System.out.print("Values in descending order of their length: ");
        for (int i : arr2) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    // 3.2. Метод возвращает массив с отсортированными значениями в порядке возрастания их длины
    private static int[] sortArrayValuesInAscendingOrderOfTheirLength(int[] array) {
        int[] arr = array;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - (i + 1); j++) {
                int currentValueOfArrayElement = getCountsOfDigits(arr[j]);
                int nextValueOfArrayElement = getCountsOfDigits(arr[j] + 1);
                if (currentValueOfArrayElement > nextValueOfArrayElement) {
                    int box = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = box;
                }
            }
        }
        return arr;
    }

    // 3.3. Метод возвращает массив с отсортированными значениями в порядке убывания их длины
    private static int[] sortArrayValuesInDescendingOrderOfTheirLength(int[] array) {
        int[] arr = array;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - (i + 1); j++) {
                int currentValueOfArrayElement = getCountsOfDigits(arr[j]);
                int nextValueOfArrayElement = getCountsOfDigits(arr[j + 1]);
                if (currentValueOfArrayElement < nextValueOfArrayElement) {
                    int box = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = box;
                }
            }
        }
        return arr;
    }

    // 4.1. Вывести на консоль те числа, длина которых меньше (больше) средней длины по всем числам, а также длину.
    private static void displayValuesWhoseLengthIsLessAndMoreAverageLengthAllNumbers(int[] array) {
        System.out.println("--------------- TASK 4 ---------------");
        double average = getAverageValuesLength(array);
        displayValuesWhoseLengthIsLessAverageLengthAllNumbers(array, average);
        displayValuesWhoseLengthIsMoreAverageLengthAllNumbers(array, average);
    }

    // 4.2. Метод возвращает среднюю длину всех чисел
    private static double getAverageValuesLength(int[] array) {
        double averageLengthOfValuesOfAllArrayElements = 0;
        if (array.length > 0) {
            double sum = 0;
            for (int i = 0; i < array.length; i++) {
                int lengthOfCurrentValueOfArrayElement = getCountsOfDigits(array[i]);
                sum += lengthOfCurrentValueOfArrayElement;
            }
            averageLengthOfValuesOfAllArrayElements = sum / array.length;
        }
        return averageLengthOfValuesOfAllArrayElements;
    }

    // 4.3. Метод выводит на консоль те числа, длина которых меньше средней длины по всем числам, а также длину
    private static void displayValuesWhoseLengthIsLessAverageLengthAllNumbers(int[] array, double average) {
        System.out.println("Values whose length is less than the average length of all numbers are: ");
        for (int value : array) {
            int valueLength = getCountsOfDigits(value);
            if (valueLength < average) {
                System.out.println(value + " Its length is: " + valueLength);
            }
        }
    }

    // 4.4. Метод выводит на консоль те числа, длина которых больше средней длины по всем числам, а также длину
    private static void displayValuesWhoseLengthIsMoreAverageLengthAllNumbers(int[] array, double average) {
        System.out.println("Values whose length is more than the average length of all numbers are: ");
        for (int value : array) {
            int valueLength = getCountsOfDigits(value);
            if (valueLength > average) {
                System.out.println(value + " Its length is: " + valueLength);
            }
        }
    }

    // 5.1. Найти число, в котором количество различных цифр минимально. Если таких чисел несколько, найти первое из них.
    private static int getValueInWhichNumberOfDistinctDigitsIsMinimal(int[] array) {
        System.out.println("--------------- TASK 5 ---------------");
        int indexOfArrayElement = 0;
        int valueWithMinimumCountDifferentDigits = array[indexOfArrayElement];
        int minimumCountDifferentDigits = getCountsOfDigits(array[indexOfArrayElement]);
        for (int i = 0; i < array.length; i++) {
            int countDifferentDigits = 0;
            int[] interimArray = getInterimArray(array[i]);
            for (int element : interimArray) {
                if (element > 0) {
                    countDifferentDigits++;
                }
            }
            if (countDifferentDigits < minimumCountDifferentDigits) {
                minimumCountDifferentDigits = countDifferentDigits;
                indexOfArrayElement = i;
            }
        }
        valueWithMinimumCountDifferentDigits = array[indexOfArrayElement];
        return valueWithMinimumCountDifferentDigits;
    }

    // 5.2. Метод возвращает массив, состоящий из 10 элементов. Элементу массива присваивается значение равное 1, если символ аргумента соответствует условию.
    private static int[] getInterimArray(int element) {
        int[] array = new int[10];
        int absoluteValueOfNumber = Math.abs(element);
        while (absoluteValueOfNumber > 0) {
            array[absoluteValueOfNumber % 10] = 1;
            absoluteValueOfNumber /= 10;
        }
        return array;
    }

    // 6.1. Найти количество чисел, содержащих только четные цифры, а среди оставшихся — количество чисел с равным числом четных и нечетных цифр.
    private static void displayCountOfNumbersContainingOnlyEvenDigits(int[] array) {
        System.out.println("--------------- TASK 6 ---------------");
        int countValuesWithEvenDigits = 0;
        int countValueWithEqualNumberOfEvenAndOddDigits = 0;
        for (int element : array) {
            boolean flag = true;
            char[] chars = Integer.toString(Math.abs(element)).toCharArray();
            for (char letter : chars) {
                int digit = Character.getNumericValue(letter);
                if (digit % 2 != 0) {
                    flag = false;
                    countValueWithEqualNumberOfEvenAndOddDigits += getValueWithEqualNumberOfEvenAndOddDigits(chars);
                    break;
                }
            }
            if (flag) {
                countValuesWithEvenDigits++;
            }
        }
        System.out.println("Количество чисел, содержащих только четные цифры: " + countValuesWithEvenDigits);
        System.out.println("Количество чисел с равным числом четных и нечетных цифр: " + countValueWithEqualNumberOfEvenAndOddDigits);
    }

    // 6.2. Метод возвращает 1, если в числе равное количество четных и нечетных цифр
    private static int getValueWithEqualNumberOfEvenAndOddDigits(char[] chars) {
        int flag = 0;
        int countEvenDigit = 0;
        int countOddDigit = 0;
        for (char letter : chars) {
            int digit = Character.getNumericValue(letter);
            if (digit % 2 == 0) {
                countEvenDigit++;
            } else {
                countOddDigit++;
            }
        }
        if (countEvenDigit == countOddDigit) {
            flag = 1;
        }
        return flag;
    }

    // 7. Найти число, цифры в котором идут в строгом порядке возрастания. Если таких чисел несколько, найти первое из них.
    private static void displayFirstNumberWithDigitsInStrictAscendingOrder(int[] array) {
        System.out.println("--------------- TASK 7 ---------------");
        for (int element : array) {
            char[] chars = Integer.toString(Math.abs(element)).toCharArray();
            for (int i = 0; i < chars.length - 1; i++) {
                if (chars[i] >= chars[i + 1]) {
                    break;
                } else if (i == chars.length - 2) {
                    System.out.println("Число, цифры в котором идут в строгом порядке возрастания: " + element);
                    return;
                }
            }
        }
        System.out.println("Таких чисел нет!");
    }

    // 8. Найти число, состоящее только из различных цифр. Если таких чисел несколько, найти первое из них.
    private static void displayNumberContainingOnlyDifferentDigits(int[] array) {
        System.out.println("--------------- TASK 8 ---------------");
        for (int element : array) {
            boolean isDistinct = true;
            char[] chars = String.valueOf(Math.abs(element)).toCharArray();
            for (int i = 0; i < chars.length - 1; i++) {
                for (int j = i + 1; j < chars.length; j++) {
                    if (chars[i] == chars[j]) {
                        isDistinct = false;
                        break;
                    }
                }
                if (!isDistinct) {
                    break;
                } else if (i == chars.length - 2) {
                    System.out.println("Число (первое в списке), состоящее только из различных цифр: " + element);
                    return;
                }
            }
        }
        System.out.println("Таких чисел нет!");
    }

    // Метод возвращает длину числа.
    private static int getCountsOfDigits(int number) {
        return String.valueOf(Math.abs(number)).length();
    }


}

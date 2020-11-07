import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Program {

/*
1. Ввести с консоли n - размерность матрицы a [n] [n]. Задать значения элементов матрицы в интервале значений от -M до M с помощью генератора случайных чисел (класс Random).
2. Упорядочить строки (столбцы) матрицы в порядке возрастания значений элементов k-го столбца (строки).
3. Найти и вывести наибольшее число возрастающих (убывающих) элементов матрицы, идущих подряд.
4. Найти сумму элементов матрицы, расположенных между первым и вторым положительными элементами каждой строки.
5. Найти максимальный элемент в матрице и удалить из матрицы все строки и столбцы, его содержащие.
*/

    private static final String MESSAGE_ABOUT_INCORRECT_DATA = "Input wrong data! Repeat please.";
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // task 1
        int[][] matrix = getMatrixWithRandomValues();
        displayMatrixValues(matrix);
        // task 2
        toOrderRowsAndColumnsOfMatrix(matrix);
        // task 3
        displayLargestNumberOfMatrixElements(matrix);
        // task 4
        System.out.println("Сумма элементов матрицы, расположенных между первым и вторым положительными элементами каждой строки: "
                + getSumOfMatrixElements(matrix));
        // task 5
        getMaxMatrixElementAndRemoveRowsAndColumnsThatContainIt(matrix);
    }

//----------------------------------------------------------------------------------------------------------------------
    // 1. Ввести с консоли n - размерность матрицы a [n] [n]. Задать значения элементов матрицы в интервале значений от -M до M с помощью генератора случайных чисел (класс Random).
    private static int[][] getMatrixWithRandomValues() {
        System.out.println("--------------- TASK 1 ---------------");
        int[][] matrix = getMatrix();
        int range = getRangeOfValues();
        initOfArrayElementsWithRandomValues(matrix, range);
        return matrix;
    }

    // Метод инициализирует элементы матрицы значениями, заданные в определенном интервале
    private static void initOfArrayElementsWithRandomValues(int[][] matrix, int range) {
        for (int[] array : matrix) {
            for (int i = 0; i < array.length; i++) {
                array[i] = (int) (Math.random() * (2 * range + 1)) - range;
            }
        }
    }

    private static int getRangeOfValues() {
        System.out.println("Задать интервал значений элементов матрицы.");
        int range = setPositiveValue();
        return range;
    }

    private static int[][] getMatrix() {
        System.out.println("Задать размерность матрицы.");
        int length = setPositiveValue();
        int[][] matrix = new int[length][length];
        return matrix;
    }
//----------------------------------------------------------------------------------------------------------------------
    // 2. Упорядочить строки (столбцы) матрицы в порядке возрастания значений элементов k-го столбца (строки).
    private static void toOrderRowsAndColumnsOfMatrix(int[][] matrix) {
        System.out.println("--------------- TASK 2 ---------------");
        int[][] matrixBySortedRows = toOrderMatrixRowsInAscendingOrderOfValues(matrix);
        displayMatrixValues(matrixBySortedRows);
        int[][] matrixBySortedColumns = toOrderMatrixColumnsInAscendingOrderOfValues(matrix);
        displayMatrixValues(matrixBySortedColumns);
        System.out.println("======================================================================");
        displayMatrixValues(matrix);
    }

    // 2.1. Метод упорядочитывает строки матрицы в порядке возрастания значений элементов k-го столбца.
    private static int[][] toOrderMatrixRowsInAscendingOrderOfValues(int[][] matrix) {
        int[][] array = copyingElementsOfMatrixToAnotherMatrix(matrix);
        System.out.print("Задать номер столбца матрицы. ");
        int index = setIndexOfRowOrColumnOfMatrix(array.length);
        System.out.println("Упорядоченные строки матрицы в порядке возрастания значений элементов " + (index + 1) + " столбца.");
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - (i + 1); j++) {
                if (array[j][index] > array[j + 1][index]) {
                    int[] box = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = box;
                }
            }
        }
        return array;
    }

    // 2.2. Метод упорядочитывает столбцы матрицы в порядке возрастания значений элементов k-ой строки.
    private static int[][] toOrderMatrixColumnsInAscendingOrderOfValues(int[][] matrix) {
        int[][] array = copyingElementsOfMatrixToAnotherMatrix(matrix);
        System.out.print("Задать номер строки матрицы. ");
        int index = setIndexOfRowOrColumnOfMatrix(array.length);
        System.out.println("Упорядоченные столбцы матрицы в порядке возрастания значений элементов " + (index + 1) + " строки.");
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - (i + 1); j++) {
                if (array[index][j] > array[index][j + 1]) {
                    for (int k = 0; k < array.length; k++) {
                        int box = array[k][j];
                        array[k][j] = array[k][j + 1];
                        array[k][j + 1] = box;
                    }
                }
            }
        }
        return array;
    }

    private static int setIndexOfRowOrColumnOfMatrix(int length) {
        int index = 0;
        while (true) {
            index = setPositiveValue();
            if (index <= length) {
                index--;
                break;
            } else {
                System.out.println(MESSAGE_ABOUT_INCORRECT_DATA);
            }
        }
        return index;
    }
//----------------------------------------------------------------------------------------------------------------------
    // 3. Найти и вывести наибольшее число возрастающих (убывающих) элементов матрицы, идущих подряд.
    private static void displayLargestNumberOfMatrixElements(int[][] matrix) {
        System.out.println("--------------- TASK 3 ---------------");
        System.out.println("Наибольшее число возрастающих элементов матрицы, идущих подряд: " + countLargestNumberOfIncreasingMatrixElements(matrix));
        System.out.println("Наибольшее число убывающих элементов матрицы, идущих подряд: " + countLargestNumberOfDecreasingMatrixElements(matrix));
    }

    // Метод возвращает наибольшее число возрастающих элементов матрицы, идущих подряд
    private static int countLargestNumberOfIncreasingMatrixElements(int[][] matrix) {
        int totalCount = 0;
        for (int[] array : matrix) {
            int count = 0;
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] < array[i + 1]) {
                    count++;
                    if (count >= totalCount) {
                        totalCount = count + 1;
                    }
                } else {
                    count = 0;
                }
            }
        }
        return totalCount;
    }

    // Метод возвращает наибольшее число убывающих элементов матрицы, идущих подряд
    private static int countLargestNumberOfDecreasingMatrixElements(int[][] matrix) {
        int totalCount = 0;
        for (int[] array : matrix) {
            int count = 0;
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] > array[i + 1]) {
                    count++;
                    if (count >= totalCount) {
                        totalCount = count + 1;
                    }
                } else {
                    count = 0;
                }
            }
        }
        return totalCount;
    }
//----------------------------------------------------------------------------------------------------------------------
    // 4. Найти сумму элементов матрицы, расположенных между первым и вторым положительными элементами каждой строки.
    private static int getSumOfMatrixElements(int[][] matrix) {
        System.out.println("--------------- TASK 4 ---------------");
        int totalSum = 0;
        for (int[] array : matrix) {
            int sum = 0;
            int count = 0;
            for (int element : array) {
                if (element > 0) {
                    count++;
                    if (count == 2) {
                        totalSum += sum;
                        break;
                    }
                } else if (count == 1) {
                    sum += element;
                }
            }
        }
        return totalSum;
    }
//----------------------------------------------------------------------------------------------------------------------
    // 5. Найти максимальный элемент в матрице и удалить из матрицы все строки и столбцы, его содержащие.
    private static void getMaxMatrixElementAndRemoveRowsAndColumnsThatContainIt(int[][] matrix) {
        System.out.println("--------------- TASK 5 ---------------");
        int[][] array = copyingElementsOfMatrixToAnotherMatrix(matrix);
        int maxElement = getMaxElementOfMatrix(array);
        int[][] coordinateMatrix = getCoordinateMatrix(array, maxElement);
        List<Integer> list = getList(coordinateMatrix, array);
        displayListItems(list);
    }

    // Метод возвращает максимальное значение в матрице
    private static int getMaxElementOfMatrix(int[][] matrix) {
        int maxElement = matrix[0][0];
        for (int[] array : matrix) {
            for (int element : array) {
                if (element > maxElement) {
                    maxElement = element;
                }
            }
        }
        return maxElement;
    }

    private static int[][] getCoordinateMatrix(int[][] matrix, int maxElement) {
        int length = matrix.length;
        int[][] coordinateMatrix = new int[2][length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == maxElement) {
                    coordinateMatrix[0][i] = 1;
                    coordinateMatrix[1][j] = 1;
                }
            }
        }
        return coordinateMatrix;
    }

    private static List<Integer> getList(int[][] coordinateMatrix, int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < coordinateMatrix[0].length; i++) {
            for (int j = 0; j < coordinateMatrix[1].length; j++) {
                if (coordinateMatrix[0][i] == 1) {
                    break;
                } else if (coordinateMatrix[1][j] == 0) {
                    list.add(matrix[i][j]);
                }
            }
        }
        return list;
    }

    private static void displayListItems(List<Integer> list) {
        System.out.print("Displaying list items: ");
        for (int item : list) {
            System.out.print(item + " ");
        }
    }
//----------------------------------------------------------------------------------------------------------------------
    private static int setPositiveValue() {
        int value = 0;
        while (true) {
            System.out.print("Set a positive value: ");
            try {
                value = scanner.nextInt();
                if (value > 0) {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println(MESSAGE_ABOUT_INCORRECT_DATA);
                scanner.nextLine();
            }
        }
        return value;
    }

    private static void displayMatrixValues(int[][] matrix) {
        System.out.println("Displaying matrix elements:");
        for (int[] array : matrix) {
            for (int element : array) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }

    // Метод копирует элементы матрицы в другую матрицу
    private static int[][] copyingElementsOfMatrixToAnotherMatrix(int[][] matrix) {
        int length = matrix.length;
        int[][] array = new int[length][length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                array[i][j] = matrix[i][j];
            }
        }
        return array;
    }
}

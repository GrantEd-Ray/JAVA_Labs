import java.util.Scanner;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

public class Main {
    public static int[] StringToIntArray(String arrayInput)
    {
        String[] stringArray = arrayInput.split(" ");

        int[] res = new int[stringArray.length];
        for (int i = 0; i < stringArray.length; i++)
        {
            res[i] = Integer.parseInt(stringArray[i]);
        }
        return res;
    }

    public static String task1(String input)
    {
        String subStr = "";
        String maxSubStr = "";
        for (int i = 0; i < input.length(); i++)
        {
            for (int j = i; j < input.length(); j++)
            {
                if (!subStr.contains(input.substring(j, j + 1)))
                {
                    subStr += input.substring(j, j + 1);
                }
                else
                {
                    if (subStr.length() > maxSubStr.length())
                    {
                        maxSubStr = subStr;
                    }
                    subStr = "";
                }
            }
            if (subStr.length() > maxSubStr.length())
            {
                maxSubStr = subStr;
            }
            subStr = "";
        }
        if (subStr.length() > maxSubStr.length())
        {
            maxSubStr = subStr;
        }

        return maxSubStr;
    }

    public static int[] task2(int[] array1, int[] array2)
    {
        int[] resultArray = new int[array1.length + array2.length];

        int i = 0;
        int j = 0;
        int k = 0;

        while (i < array1.length && j < array2.length) {
            if (array1[i] < array2[j]) {
                resultArray[k++] = array1[i++];
            } else {
                resultArray[k++] = array2[j++];
            }
        }

        while (i < array1.length) {
            resultArray[k++] = array1[i++];
        }

        while (j < array2.length) {
            resultArray[k++] = array2[j++];
        }

        return resultArray;
    }

    public static int task3(int[] nums)
    {
        int maxSum = nums[0];  // начальная максимальная сумма (первый элемент)
        int currentSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    public static int[][] task4(int[][]matrix)
    {
        int rows = matrix.length;
        int columns = matrix[0].length;

        int[][] rotated = new int[columns][rows];

        for (int i = 0; i < columns; i++)
        {
            for (int j = 0; j < rows; j++)
            {
                rotated[i][j] = matrix[rows - j - 1][i];
            }
        }

        return rotated;
    }

    public static int[] task5(int[] nums, int target)
    {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++)
        {
            int complement = target - nums[i];
            if (map.containsKey(complement))
            {
                return new int[] {complement, nums[i]};
            }
            map.put(nums[i], i);
        }
        return null;
    }

    public static int task6(int[][] nums)
    {
        int sum = 0;
        for (int[] array : nums)
        {
            for (int element : array)
            {
                sum += element;
            }
        }
        return sum;
    }

    public static int[] task7(int[][] nums)
    {
        int[] res = new int[nums.length];

        for (int i = 0; i < nums.length; i++)
        {
            int maxElement = nums[i][0];
            for (int element : nums[i])
            {
                maxElement = Math.max(maxElement, element);
            }
            res[i] = maxElement;
        }
        return  res;
    }

    public static int[][] task8(int[][]matrix)
    {
        int rows = matrix.length;
        int columns = matrix[0].length;

        int[][] rotated = new int[columns][rows];

        for (int i = 0; i < columns; i++)
        {
            for (int j = 0; j < rows; j++)
            {
                rotated[i][j] = matrix[j][columns - i - 1];
            }
        }
        return rotated;
    }

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите номер задачи: ");
        int userChoice = in.nextInt();
        in = new Scanner(System.in);

        switch (userChoice)
        {
            case 1:
                System.out.print("Введите строку: ");
                String inputString = in.nextLine();

                String task1Res = task1(inputString);
                System.out.printf("Наибольшая подстрока с уникальными элементами: %s", task1Res);
                break;
            case 2:
                System.out.print("Введите первый массив чисел (числа разделить пробелом): ");
                String array1Input = in.nextLine();
                int[] array1 = StringToIntArray(array1Input);
                Arrays.sort(array1);

                System.out.print("Введите второй массив чисел (числа разделить пробелом): ");
                String array2Input = in.nextLine();
                int[] array2 = StringToIntArray(array2Input);
                Arrays.sort(array2);

                int[] task2Res = task2(array1, array2);
                System.out.print("Новый массив: ");
                for (int value : task2Res) {
                    System.out.print(value + " ");
                }
                break;
            case 3:
                System.out.print("Введите массив чисел (числа разделить пробелом): ");
                String arrayInput = in.nextLine();
                int[] userInput = StringToIntArray(arrayInput);

                int task3Res = task3(userInput);
                System.out.printf("Максимальная сумма подмассива: %s", task3Res);
                break;
            case 4:
                System.out.print("Ввдедите кол-во строк: ");
                int rows = in.nextInt();
                System.out.print("Ввдедите кол-во столбцов: ");
                int columns = in.nextInt();
                in = new Scanner(System.in);

                int[][] twoDimArray = new int[rows][columns];
                System.out.println("Заполните массив (числа разделить пробелом)");
                for (int i = 1; i < rows + 1; i++)
                {
                    int[] numArray;
                    while (true)
                    {
                        System.out.print(i + ": ");
                        String arrayString = in.nextLine();
                        numArray = StringToIntArray(arrayString);

                        if (numArray.length <= columns) // Не превосходит ли размер введенного массива указанной длины
                            break;
                        else
                        {
                            System.out.print("Длина массива выше указанного. Попробуйте еще раз");
                        }
                    }
                    for (int j = 0; j < numArray.length; j++)
                    {
                        twoDimArray[i - 1][j] = numArray[j];
                    }
                    if (numArray.length < columns) // Если введенный массив меньше заданной длины, то оставшиеся клетки заполняем нулями
                    {
                        for (int j = numArray.length + 1; j < columns; j++)
                        {
                            twoDimArray[i - 1][j] = 0;
                        }
                    }
                }

                System.out.println("Повернутый массив: ");
                int[][] task4Res = task4(twoDimArray);
                for (int i = 0; i < task4Res.length; i++)
                {
                    for (int j = 0; j < task4Res[0].length; j++)
                    {
                        System.out.print(task4Res[i][j] + " ");
                    }
                    System.out.println();
                }

                break;
            case 5:
                System.out.print("Введите массив чисел (числа разделить пробелом): ");
                arrayInput = in.nextLine();
                int[] nums = StringToIntArray(arrayInput);

                System.out.print("Введите искомую сумму: ");
                int searchSum = in.nextInt();

                int[] task5Res = task5(nums, searchSum);

                if (task5Res != null)
                {
                    System.out.println("Пара: " + task5Res[0] + ", " + task5Res[1]);
                }
                else
                {
                    System.out.println("Пара не найдена");
                }

                break;
            case 6:
                System.out.print("Ввдедите кол-во строк: ");
                rows = in.nextInt();
                System.out.print("Ввдедите кол-во столбцов: ");
                columns = in.nextInt();
                in = new Scanner(System.in);

                twoDimArray = new int[rows][columns];
                System.out.println("Заполните массив (числа разделить пробелом)");
                for (int i = 1; i < rows + 1; i++)
                {
                    int[] numArray;
                    while (true)
                    {
                        System.out.print(i + ": ");
                        String arrayString = in.nextLine();
                        numArray = StringToIntArray(arrayString);

                        if (numArray.length <= columns) // Не превосходит ли размер введенного массива указанной длины
                            break;
                        else
                        {
                            System.out.print("Длина массива выше указанного. Попробуйте еще раз");
                        }
                    }
                    for (int j = 0; j < numArray.length; j++)
                    {
                        twoDimArray[i - 1][j] = numArray[j];
                    }
                    if (numArray.length < columns) // Если введенный массив меньше заданной длины, то оставшиеся клетки заполняем нулями
                    {
                        for (int j = numArray.length + 1; j < columns; j++)
                        {
                            twoDimArray[i - 1][j] = 0;
                        }
                    }
                }

                int task6Res = task6(twoDimArray);
                System.out.println("Сумма: " + task6Res);
                break;
            case 7:
                System.out.print("Ввдедите кол-во строк: ");
                rows = in.nextInt();
                System.out.print("Ввдедите кол-во столбцов: ");
                columns = in.nextInt();
                in = new Scanner(System.in);

                twoDimArray = new int[rows][columns];
                System.out.println("Заполните массив (числа разделить пробелом)");
                for (int i = 1; i < rows + 1; i++)
                {
                    int[] numArray;
                    while (true)
                    {
                        System.out.print(i + ": ");
                        String arrayString = in.nextLine();
                        numArray = StringToIntArray(arrayString);

                        if (numArray.length <= columns) // Не превосходит ли размер введенного массива указанной длины
                            break;
                        else
                        {
                            System.out.print("Длина массива выше указанного. Попробуйте еще раз");
                        }
                    }
                    for (int j = 0; j < numArray.length; j++)
                    {
                        twoDimArray[i - 1][j] = numArray[j];
                    }
                    if (numArray.length < columns) // Если введенный массив меньше заданной длины, то оставшиеся клетки заполняем нулями
                    {
                        for (int j = numArray.length + 1; j < columns; j++)
                        {
                            twoDimArray[i - 1][j] = 0;
                        }
                    }
                }

                int[] task7Res = task7(twoDimArray);
                for (int num : task7Res)
                {
                    System.out.print(num + " ");
                }
                System.out.println();
                break;
            case 8:
                System.out.print("Ввдедите кол-во строк: ");
                rows = in.nextInt();
                System.out.print("Ввдедите кол-во столбцов: ");
                columns = in.nextInt();
                in = new Scanner(System.in);

                twoDimArray = new int[rows][columns];
                System.out.println("Заполните массив (числа разделить пробелом)");
                for (int i = 1; i < rows + 1; i++)
                {
                    int[] numArray;
                    while (true)
                    {
                        System.out.print(i + ": ");
                        String arrayString = in.nextLine();
                        numArray = StringToIntArray(arrayString);

                        if (numArray.length <= columns) // Не превосходит ли размер введенного массива указанной длины
                            break;
                        else
                        {
                            System.out.print("Длина массива выше указанного. Попробуйте еще раз");
                        }
                    }
                    for (int j = 0; j < numArray.length; j++)
                    {
                        twoDimArray[i - 1][j] = numArray[j];
                    }
                    if (numArray.length < columns) // Если введенный массив меньше заданной длины, то оставшиеся клетки заполняем нулями
                    {
                        for (int j = numArray.length + 1; j < columns; j++)
                        {
                            twoDimArray[i - 1][j] = 0;
                        }
                    }
                }

                System.out.println("Повернутый массив: ");
                int[][] task8Res = task8(twoDimArray);
                for (int i = 0; i < task8Res.length; i++)
                {
                    for (int j = 0; j < task8Res[0].length; j++)
                    {
                        System.out.print(task8Res[i][j] + " ");
                    }
                    System.out.println();
                }
                break;
            default:
                System.out.print("Данного номера не существует");
                break;
        }
    }
}

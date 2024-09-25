import java.util.Scanner;

public class Main
{
    public static void task1()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите число: ");
        int currentNumber = in.nextInt();
        int steps = 0;

        while (currentNumber > 1)
        {
            if (currentNumber % 2 == 0)
            {
                currentNumber /= 2;
                steps++;
            }
            else
            {
                currentNumber = currentNumber * 3 + 1;
                steps++;
            }
        }

        System.out.printf("Результат: %d", steps);
    }

    public static void task2()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите кол-во чисел: ");
        int amount = in.nextInt();
        int sum = 0;
        int number;

        for (int i = 0; i < amount; i++)
        {
            number = in.nextInt();
            if (i % 2 == 0)
            {
                sum += number;
            }
            else
            {
                sum -= number;
            }
        }

        System.out.printf("Результат: %d", sum);
    }

    public static void task3()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите координаты: ");
        int goalX = in.nextInt();
        int goalY = in.nextInt();
        int currentX = 0;
        int currentY = 0;
        int steps = 0;
        boolean stopSignal = false;

        System.out.println("Введите указания");
        while (!stopSignal)
        {
            String direction = in.next();
            int move;

            switch (direction)
            {
                case "север":
                    move = in.nextInt();
                    if (currentY != goalY)
                    {
                        currentY += move;
                        steps++;
                    }
                    break;
                case "юг":
                    move = in.nextInt();
                    if (currentY != goalY)
                    {
                        currentY -= move;
                        steps++;
                    }
                    break;
                case "восток":
                    move = in.nextInt();
                    if (currentX != goalX)
                    {
                        currentX += move;
                        steps++;
                    }
                    break;
                case "запад":
                    move = in.nextInt();
                    if (currentX != goalX)
                    {
                        currentX -= move;
                        steps++;
                    }
                    break;
                case "стоп":
                    stopSignal = true;
                    break;
                default:
                    System.out.println("Неверное введенное слово");
            }
        }

        System.out.printf("Минимум шагов: %d", steps);
    }

    public static void task4()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите количество дорог: ");
        int roads = in.nextInt();
        int maxHeight = -1;
        int roadWithMaxHeight = 0;

        for (int road = 1; road <= roads; road++)
        {
            System.out.printf("Введите количество туннелей на дороге %d: ", road);
            int tunnels = in.nextInt();
            int roadMaxHeight = -1;
            for (int i = 0; i < tunnels; i++)
            {
                int height = in.nextInt();
                if (roadMaxHeight < 0)
                {
                    roadMaxHeight = Math.max(roadMaxHeight, height);
                }
                else
                {
                    roadMaxHeight = Math.min(roadMaxHeight, height);
                }
            }

            if (roadMaxHeight > maxHeight)
            {
                maxHeight = roadMaxHeight;
                roadWithMaxHeight = road;
            }
        }

        System.out.println(roadWithMaxHeight);
        System.out.println(maxHeight);

    }

    public static void task5()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите трехзначное число: ");
        int userNumber = in.nextInt();
        int firstDigit = userNumber % 10;
        int secondDigit = userNumber / 10 % 10;
        int thirdDigit = userNumber / 100;

        int res = (firstDigit + secondDigit + thirdDigit) % 2;

        if (res == 1)
        {
            System.out.println("Данное число не является дважды четным");
        }
        else
        {
            System.out.println("Данное число является дважды четным");
        }
    }

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите номер задачи: ");
        int task = in.nextInt();

        switch (task)
        {
            case 1:
                task1();
                break;
            case 2:
                task2();
                break;
            case 3:
                task3();
                break;
            case 4:
                task4();
                break;
            case 5:
                task5();
                break;
            default:
                System.out.println("Нет такой задачи");
                break;
        }
    }
}

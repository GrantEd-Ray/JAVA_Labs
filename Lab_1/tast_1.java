import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
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

        System.out.print(steps);
    }
}

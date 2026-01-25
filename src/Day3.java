import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class Day3
{
    public static long totalJoltage;
    public static int maxDigit;
    public static int index;

    public static void main(String[] args) throws FileNotFoundException
    {
        File f = new File("Day3_Input.txt");
        Scanner s = new Scanner(f);
        while(s.hasNext())
        {
            String bank = s.next();
            //totalJoltage += getMaxJoltage(bank);
            totalJoltage += getMaxJoltage2(bank);
        }
        System.out.println(totalJoltage);
    }

    public static int getMaxJoltage(String bank)
    {
        int maxJoltage = 0;
        index = 0;
        for(int b = 1; b <= 2; b++)
        {
            maxDigit = parseInt(bank.substring(index, index + 1));
            findMaxDigit(bank, b, 2);
            maxJoltage = maxJoltage * 10 + maxDigit;
        }
        return maxJoltage;
    }

    public static long getMaxJoltage2(String bank)
    {
        long maxJoltage = 0;
        index = 0;
        for(int b = 1; b <= 12; b++)
        {
            maxDigit = parseInt(bank.substring(index, index + 1));
            findMaxDigit(bank, b, 12);
            maxJoltage = maxJoltage * 10 + maxDigit;
        }
        return maxJoltage;
    }

    public static void findMaxDigit(String bank, int batteryNum, int maxBatteryNum)
    {
        int prevMaxDigit = maxDigit;
        for(int i = index; i + maxBatteryNum - batteryNum < bank.length(); i++)
        {
            int potential = parseInt(bank.substring(i, i + 1));
            if(potential > maxDigit)
            {
                maxDigit = potential;
                index = i + 1;
            }
        }
        if(maxDigit == prevMaxDigit) index++;
    }
}

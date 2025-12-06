import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import static java.lang.Long.parseLong;

public class Day2
{
    public static long sumOfInvalids = 0;

    public static void main(String[] args) throws FileNotFoundException
    {
        File f = new File("Day2_Input.txt");
        Scanner s = new Scanner(f).useDelimiter(",");
        while(s.hasNext())
        {
            String range = s.next();
            long min = parseLong(range.substring(0, range.indexOf("-")));
            long max = parseLong(range.substring(range.indexOf("-") + 1));
            //validity(min, max);
            validity2(min, max);
        }
        System.out.println(sumOfInvalids);
    }

    public static void validity(long min, long max)
    {
        for(long i = min; i <= max; i++)
        {
            String id = "" + i;
            String firstHalf = id.substring(0, id.length() / 2);
            String lastHalf = id.substring(id.length() / 2);
            if(firstHalf.equals(lastHalf)) sumOfInvalids += parseLong(id);
        }
    }

    public static void validity2(long min, long max)
    {
        for(long i = min; i <= max; i++)
        {
            String id = "" + i;
            for(int l = 1; l <= id.length() / 2; l++)
            {
                if(id.length() % l == 0)
                {
                    if(check(id, l))
                    {
                        sumOfInvalids += parseLong(id);
                        break;
                    }
                }
            }
        }
    }

    public static boolean check(String id, int length)
    {
        boolean check = true;
        String sequence = id.substring(0, length);
        for(int i = length; i <= id.length() - length; i += length)
        {
            String potential = id.substring(i, i + length);
            if(!potential.equals(sequence))
            {
                check = false;
                break;
            }
        }
        return check;
    }
}

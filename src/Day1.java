import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class Day1
{
    public static int location = 50;
    public static int zeros = 0;

    public static void main(String[] args) throws FileNotFoundException
    {
        File f = new File("Day1_Input.txt");
        Scanner s = new Scanner(f);
        while(s.hasNext())
        {
            String combination = s.next();
            //rotation(combination);
            rotation2(combination);
        }
        System.out.println(zeros);
    }

    public static void rotation(String s)
    {
        boolean direction = s.charAt(0) == 'R';
        int magnitude = parseInt(s.substring(1));
        for(int i = 0; i < magnitude; i++)
        {
            if(direction) location += 1;
            else location -= 1;
            if(location > 99) location = 0;
            if(location < 0) location = 99;
        }
        if(location == 0) zeros++;
    }

    public static void rotation2(String s)
    {
        boolean direction = s.charAt(0) == 'R';
        int magnitude = parseInt(s.substring(1));
        for(int i = 0; i < magnitude; i++)
        {
            if(direction) location += 1;
            else location -= 1;
            if(location > 99) location = 0;
            if(location < 0) location = 99;
            if(location == 0) zeros++;
        }
    }
}

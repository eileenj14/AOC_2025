import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day7
{
    public static int numOfColumns = 0;
    public static int numOfRows = 0;
    public static String[][] diagram;
    public static int numOfSplits = 0;

    public static void main(String[] args) throws FileNotFoundException
    {
        createDiagram();
        getNumOfSplits();
        System.out.println(numOfSplits);
    }

    public static void getNumOfSplits()
    {

    }

    public static void createDiagram() throws FileNotFoundException
    {
        File f = new File("Day7_Input.txt");
        Scanner s = new Scanner(f);
        while(s.hasNextLine())
        {
            String row = s.nextLine();
            numOfColumns = row.length();
            numOfRows++;
        }
        s = new Scanner(f);
        diagram = new String[numOfColumns][numOfRows];
        while(s.hasNextLine())
        {
            for(int r = 0; r < numOfRows; r++)
            {
                String row = s.nextLine();
                for(int c = 0; c < numOfColumns; c++)
                {
                    diagram[c][r] = row.substring(0, 1);
                    row = row.substring(1);
                }
            }
        }
    }
}

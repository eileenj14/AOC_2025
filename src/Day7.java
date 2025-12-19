import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day7
{
    public static int numOfColumns = 0;
    public static int numOfRows = 0;
    public static String[][] diagram;
    public static int numOfSplits = 0;
    public static int numOfTimelines = 0;

    public static void main(String[] args) throws FileNotFoundException
    {
        createDiagram();
        getNumOfSplits();
        System.out.println(numOfSplits);
        getNumOfTimelines();
        System.out.println(numOfTimelines);
    }

    public static void getNumOfSplits()
    {
        for(int c = 0; c < numOfColumns; c++)
        {
            if(diagram[c][0].equals("S"))
            {
                diagram[c][1] = "|";
                break;
            }
        }
        for(int r = 1; r < numOfRows - 1; r++)
        {
            for(int c = 0; c < numOfColumns; c++)
            {
                if(diagram[c][r].equals("|"))
                {
                    if(diagram[c][r + 1].equals("^"))
                    {
                        diagram[c - 1][r + 1] = "|";
                        diagram[c + 1][r + 1] = "|";
                        numOfSplits++;
                    }
                    else diagram[c][r + 1] = "|";
                }
            }
        }
    }

    public static void getNumOfTimelines()
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

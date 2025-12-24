import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day4
{
    public static int numOfColumns = 0;
    public static int numOfRows = 0;
    public static String[][] diagram;
    public static int numOfAccessibleRolls = 0;
    public static int totalNumOfAccessibleRolls = 0;

    public static void main(String[] args) throws FileNotFoundException
    {
        createDiagram();
        //getNumOfAccessibleRolls();
        //System.out.println(numOfAccessibleRolls);
        getNumOfAccessibleRolls2();
        System.out.println(totalNumOfAccessibleRolls);
    }

    public static void getNumOfAccessibleRolls()
    {
        for(int x = 0; x < numOfColumns; x++)
        {
            for(int y = 0; y < numOfRows; y++)
            {
                if(check(x, y)) numOfAccessibleRolls++;
            }
        }
    }

    public static void getNumOfAccessibleRolls2()
    {
        while(true)
        {
            numOfAccessibleRolls = 0;
            for(int x = 0; x < numOfColumns; x++)
            {
                for(int y = 0; y < numOfRows; y++)
                {
                    if(diagram[x][y].equals("X")) diagram[x][y] = ".";
                    if(check(x, y))
                    {
                        numOfAccessibleRolls++;
                        diagram[x][y] = "X";
                    }
                }
            }
            totalNumOfAccessibleRolls += numOfAccessibleRolls;
            if(numOfAccessibleRolls == 0) break;
        }
    }

    public static boolean check(int columnNum, int rowNum)
    {
        String roll = diagram[columnNum][rowNum];
        if(roll.equals(".")) return false;
        int numOfAdjacentRolls = 0;
        for(int x = columnNum - 1; x <= columnNum + 1; x++)
        {
            for(int y = rowNum - 1; y <= rowNum + 1; y++)
            {
                if(x > -1 && x < numOfColumns && y > -1 && y < numOfRows)
                {
                    if(diagram[x][y].equals("@")) numOfAdjacentRolls++;
                }
            }
        }
        return numOfAdjacentRolls < 5;
    }

    public static void createDiagram() throws FileNotFoundException
    {
        File f = new File("Day4_Input.txt");
        Scanner s = new Scanner(f);
        while(s.hasNextLine())
        {
            String row = s.nextLine();
            numOfColumns = row.length();
            numOfRows++;
        }
        s = new Scanner(f);
        diagram = new String[numOfColumns][numOfRows];
        for(int r = 0; r < numOfRows; r++)
        {
            String row = s.nextLine();
            for(int c = 0; c < numOfColumns; c++)
            {
                diagram[c][r] = row.substring(c, c + 1);
            }
        }
    }
}

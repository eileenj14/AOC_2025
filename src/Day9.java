import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;
import static java.lang.Integer.parseInt;

public class Day9
{
    public static int numOfCoords;
    public static int numOfAreas;
    public static int[][] tileLocations;
    public static long[] areas;

    public static void main(String[] args) throws FileNotFoundException
    {
        storeTileLocations();
        calculateAreas();
        System.out.println(getLargestArea());
    }

    public static long getLargestArea()
    {
        Arrays.sort(areas);
        return areas[numOfAreas - 1];
    }

    public static void calculateAreas()
    {
        int temp = numOfCoords - 1;
        while(temp > 0)
        {
            numOfAreas += temp;
            temp--;
        }
        areas = new long[numOfAreas];
        int count = 0;
        for(int c = 0; c < numOfCoords; c++)
        {
            for(int i = c + 1; i < numOfCoords; i++)
            {
                areas[count] = (long) Math.abs(tileLocations[0][c] - tileLocations[0][i] + 1) *
                        Math.abs(tileLocations[1][c] - tileLocations[1][i] + 1);
                count++;
            }
        }
    }

    public static void storeTileLocations() throws FileNotFoundException
    {
        File f = new File("Day9_Input.txt");
        Scanner s = new Scanner(f);
        while(s.hasNextLine())
        {
            String coord = s.nextLine();
            numOfCoords++;
        }
        tileLocations = new int[2][numOfCoords];
        s = new Scanner(f);
        while(s.hasNextLine())
        {
            for(int c = 0; c < numOfCoords; c++)
            {
                String coord = s.nextLine() + ",";
                for(int i = 0; i < 2; i++)
                {
                    tileLocations[i][c] = parseInt(coord.substring(0, coord.indexOf(",")));
                    coord = coord.substring(coord.indexOf(",") + 1);
                }
            }
        }
    }
}

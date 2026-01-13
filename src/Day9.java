import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import static java.lang.Integer.parseInt;

public class Day9
{
    public static int numOfCoords;
    public static int[][] tileLocations;
    public static List<Long> areas;

    public static void main(String[] args) throws FileNotFoundException
    {
        storeTileLocations();
        System.out.println(getLargestArea1());
        System.out.println(getLargestArea2());
    }

    public static long getLargestArea2()
    {
        return 0;
    }

    public static long getLargestArea1()
    {
        areas = new ArrayList<>();
        for(int c = 0; c < numOfCoords; c++)
        {
            for(int i = c + 1; i < numOfCoords; i++)
            {
                areas.addLast((long) Math.abs(tileLocations[0][c] - tileLocations[0][i] + 1) *
                        Math.abs(tileLocations[1][c] - tileLocations[1][i] + 1));
            }
        }
        Collections.sort(areas);
        return areas.getLast();
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

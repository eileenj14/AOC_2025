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
    public static String[][] floor;
    public static List<Long> areas;

    public static void main(String[] args) throws FileNotFoundException
    {
        storeTileLocations();
        createFloor();
        //System.out.println(getLargestArea1());
        System.out.println(getLargestArea2());
    }

    public static long getLargestArea2()
    {
        areas = new ArrayList<>();
        for(int c = 0; c < numOfCoords; c++)
        {
            for(int i = c + 1; i < numOfCoords; i++)
            {

            }
        }
        Collections.sort(areas);
        return areas.getLast();
    }

    public static boolean check(int xCoord, int yCoord, String direction)
    {
        return false;
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

    public static void createFloor()
    {
        int largestXCoord = tileLocations[0][0];
        int largestYCoord = tileLocations[1][0];
        for(int c = 1; c < numOfCoords; c++)
        {
            if(tileLocations[0][c] > largestXCoord) largestXCoord = tileLocations[0][c];
            if(tileLocations[1][c] > largestYCoord) largestYCoord = tileLocations[1][c];
        }
        largestXCoord += 2;
        largestYCoord += 2;
        floor = new String[largestXCoord][largestYCoord];
        floor[tileLocations[0][0]][tileLocations[1][0]] = "#";
        for(int c = 1; c < numOfCoords; c++)
        {
            connectRedTiles(tileLocations[0][c - 1], tileLocations[1][c - 1],
                    tileLocations[0][c], tileLocations[1][c]);
            floor[tileLocations[0][c]][tileLocations[1][c]] = "#";
        }
        connectRedTiles(tileLocations[0][numOfCoords - 1], tileLocations[1][numOfCoords - 1],
                tileLocations[0][0], tileLocations[1][0]);
        for(int x = 0; x < largestXCoord; x++)
        {
            for(int y = 0; y < largestYCoord; y++) if(floor[x][y] == null) floor[x][y] = ".";
        }
        for(int y = 0; y < largestYCoord; y++)
        {
            for(int x = 0; x < largestXCoord; x++)
            {
                System.out.print(floor[x][y] + " ");
            }
            System.out.println();
        }
    }

    public static void connectRedTiles(int prevX, int prevY, int thisX, int thisY)
    {
        if(thisX == prevX)
        {
            if(thisY < prevY)
            {
                for(int y = thisY + 1; y < prevY; y += 1) floor[thisX][y] = "#";
            }
            else
            {
                for(int y = prevY + 1; y < thisY; y += 1) floor[thisX][y] = "#";
            }
        }
        else
        {
            if(thisX < prevX)
            {
                for(int x = thisX + 1; x < prevX; x += 1) floor[x][thisY] = "#";
            }
            else
            {
                for(int x = prevX + 1; x < thisX; x += 1) floor[x][thisY] = "#";
            }
        }
    }
}

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Day9
{
    public static int numOfCoords;
    public static int maxXCoord;
    public static int maxYCoord;
    public static int[] xCoords;
    public static int[] yCoords;
    public static int[] compressedXCoords;
    public static int[] compressedYCoords;
    public static byte[][] floor;
    public static List<Long> areas;

    public static void main(String[] args) throws FileNotFoundException
    {
        storeTileLocations();
        compressCoords();
        createFloor();
        System.out.println(getLargestArea1());
        System.out.println(getLargestArea2());
    }

    public static long getLargestArea1()
    {
        areas = new ArrayList<>();
        for(int c = 0; c < numOfCoords; c++)
        {
            for(int i = c + 1; i < numOfCoords; i++)
            {
                areas.addLast((long) Math.abs(xCoords[c] - xCoords[i] + 1) *
                        Math.abs(yCoords[c] - yCoords[i] + 1));
            }
        }
        Collections.sort(areas);
        return areas.getLast();
    }

    public static long getLargestArea2()
    {
        areas = new ArrayList<>();
        for(int c = 0; c < numOfCoords; c++)
        {
            for(int i = c + 1; i < numOfCoords; i++)
            {
                if(validPoint(compressedXCoords[c], compressedYCoords[i]) &&
                        validPoint(compressedXCoords[i], compressedYCoords[c]))
                {
                    areas.addLast((long) Math.abs(xCoords[c] - xCoords[i] + 1) *
                        Math.abs(yCoords[c] - yCoords[i] + 1));
                }
            }
        }
        Collections.sort(areas);
        return areas.getLast();
    }

    public static boolean validPoint(int xCoord, int yCoord)
    {
        int xIntersects = 0;
        int yIntersects = 0;
        if(xCoord == 0) xCoord++;
        if(yCoord == 0) yCoord++;
        for(int x = xCoord; x < maxXCoord + 1; x++)
        {
            if(floor[x][yCoord] == 1 && floor[x - 1][yCoord] == 0 && floor[x + 1][yCoord] == 0) xIntersects++;
            else
            {
                if(floor[x][yCoord] == 1 && floor[x - 1][yCoord] == 0 && floor[x + 1][yCoord] == 1)
                {
                    int x2 = x;
                    while(floor[x2 + 1][yCoord] == 1) x2++;
                    if(floor[x][yCoord + 1] != floor[x2][yCoord + 1]) xIntersects++;
                }
            }
        }
        for(int y = yCoord; y < maxYCoord + 1; y++)
        {
            if(floor[xCoord][y] == 1 && floor[xCoord][y - 1] == 0 && floor[xCoord][y + 1] == 0) yIntersects++;
            else
            {
                if(floor[xCoord][y] == 1 && floor[xCoord][y - 1] == 0 && floor[xCoord][y + 1] == 1)
                {
                    int y2 = y;
                    while(floor[xCoord][y2 + 1] == 1) y2++;
                    if(floor[xCoord + 1][y] != floor[xCoord][y2 + 1]) yIntersects++;
                }
            }
        }
        return xIntersects % 2 == 1 && yIntersects % 2 == 1;
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
        xCoords = new int[numOfCoords];
        yCoords = new int[numOfCoords];
        s = new Scanner(f);
        for(int c = 0; c < numOfCoords; c++)
        {
            String coord = s.nextLine();
            xCoords[c] = parseInt(coord.substring(0, coord.indexOf(",")));
            yCoords[c] = parseInt(coord.substring(coord.indexOf(",") + 1));
        }
    }

    public static void compressCoords()
    {
        Set<Integer> uniqueXCoords = new HashSet<>();
        Set<Integer> uniqueYCoords = new HashSet<>();
        for(int coord : xCoords) uniqueXCoords.add(coord);
        for(int coord : yCoords) uniqueYCoords.add(coord);
        List<Integer> sortedX = new ArrayList<>(uniqueXCoords);
        List<Integer> sortedY = new ArrayList<>(uniqueYCoords);
        Collections.sort(sortedX);
        Collections.sort(sortedY);
        Map<Integer, Integer> compressedMapX = new HashMap<>();
        Map<Integer, Integer> compressedMapY = new HashMap<>();
        for(int c = 0; c < sortedX.size(); c++)
        {
            compressedMapX.put(sortedX.get(c), c * 2);
            compressedMapY.put(sortedY.get(c), c * 2);
        }
        compressedXCoords = new int[numOfCoords];
        compressedYCoords = new int[numOfCoords];
        for(int c = 0; c < numOfCoords; c++)
        {
            compressedXCoords[c] = compressedMapX.get(xCoords[c]);
            compressedYCoords[c] = compressedMapY.get(yCoords[c]);
        }
    }

    public static void createFloor()
    {
        maxXCoord = compressedXCoords[0];
        maxYCoord = compressedYCoords[0];
        for(int c = 0; c < compressedXCoords.length; c++)
        {
            if(compressedXCoords[c] > maxXCoord) maxXCoord = compressedXCoords[c];
            if(compressedYCoords[c] > maxYCoord) maxYCoord = compressedYCoords[c];
        }
        floor = new byte[maxXCoord + 2][maxYCoord + 2];
        floor[compressedXCoords[0]][compressedYCoords[0]] = 1;
        for(int c = 1; c < numOfCoords; c++)
        {
            connectRedTiles(compressedXCoords[c - 1], compressedYCoords[c - 1],
                    compressedXCoords[c], compressedYCoords[c]);
            floor[compressedXCoords[c]][compressedYCoords[c]] = 1;
        }
        connectRedTiles(compressedXCoords[numOfCoords - 1], compressedYCoords[numOfCoords - 1],
                compressedXCoords[0], compressedYCoords[0]);
    }

    public static void connectRedTiles(int prevX, int prevY, int thisX, int thisY)
    {
        int increment = 1;
        if(thisX == prevX)
        {
            if(thisY < prevY) increment = -1;
            for(int y = prevY + increment; y != thisY; y += increment) floor[thisX][y] = 1;
        }
        else
        {
            if(thisX < prevX) increment = -1;
            for(int x = prevX + increment; x != thisX; x += increment) floor[x][thisY] = 1;
        }
    }
}

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
    public static char[][] floor;
    public static List<Long> areas;

    public static void main(String[] args) throws FileNotFoundException
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

        Set<Integer> uniqueX = new HashSet<>();
        Set<Integer> uniqueY = new HashSet<>();
        for(int coord : xCoords) uniqueX.add(coord);
        for(int coord : yCoords) uniqueY.add(coord);
        List<Integer> sortedUniqueX = new ArrayList<>(uniqueX);
        List<Integer> sortedUniqueY = new ArrayList<>(uniqueY);
        Collections.sort(sortedUniqueX);
        Collections.sort(sortedUniqueY);
        Map<Integer, Integer> compressedMapX = new HashMap<>();
        Map<Integer, Integer> compressedMapY = new HashMap<>();
        for(int c = 0; c < sortedUniqueX.size(); c++)
        {
            compressedMapX.put(sortedUniqueX.get(c), c);
            compressedMapY.put(sortedUniqueY.get(c), c);
        }
        compressedXCoords = new int[numOfCoords];
        compressedYCoords = new int[numOfCoords];
        for(int c = 0; c < numOfCoords; c++)
        {
            compressedXCoords[c] = compressedMapX.get(xCoords[c]);
            compressedYCoords[c] = compressedMapY.get(yCoords[c]);
        }

        createFloor();

        System.out.println(getLargestArea1());
        System.out.println(getLargestArea2());
    }

    public static long getLargestArea1()
    {
        areas = new ArrayList<>();
        for(int c = 0; c < numOfCoords; c++)
        {
            for(int i = c + 2; i < numOfCoords; i++)
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
            for(int i = c + 2; i < numOfCoords; i++)
            {
                if(validRectangle(c, i))
                {
                    areas.addLast((long) Math.abs(xCoords[c] - xCoords[i] + 1) *
                        Math.abs(yCoords[c] - yCoords[i] + 1));
                }
            }
        }
        Collections.sort(areas);
        return areas.getLast();
    }

    public static boolean validRectangle(int index1, int index2)
    {
        int x1 = compressedXCoords[index1];
        int y1 = compressedYCoords[index1];
        int x2 = compressedXCoords[index2];
        int y2 = compressedYCoords[index2];
        if(x2 < x1)
        {
            x1 = x2;
            x2 = compressedXCoords[index1];
        }
        if(y2 < y1)
        {
            y1 = y2;
            y2 = compressedYCoords[index1];
        }
        for(int x = x1; x < x2 + 1; x++)
        {
            for(int y = y1; y < y2 + 1; y++)
            {
                if(floor[x][y] == '.') return false;
            }
        }
        return true;
    }

    public static void createFloor()
    {
        maxXCoord = compressedXCoords[0];
        maxYCoord = compressedYCoords[0];
        for(int c = 0; c < numOfCoords; c++)
        {
            if(compressedXCoords[c] > maxXCoord) maxXCoord = compressedXCoords[c];
            if(compressedYCoords[c] > maxYCoord) maxYCoord = compressedYCoords[c];
        }
        floor = new char[maxXCoord + 1][maxYCoord + 1];
        floor[compressedXCoords[0]][compressedYCoords[0]] = '#';
        for(int c = 1; c < numOfCoords; c++)
        {
            placePerimeterTiles(compressedXCoords[c - 1], compressedYCoords[c - 1],
                    compressedXCoords[c], compressedYCoords[c]);
            floor[compressedXCoords[c]][compressedYCoords[c]] = '#';
        }
        placePerimeterTiles(compressedXCoords[numOfCoords - 1], compressedYCoords[numOfCoords - 1],
                compressedXCoords[0], compressedYCoords[0]);
        defineInsideOutside();
    }

    public static void placePerimeterTiles(int prevX, int prevY, int thisX, int thisY)
    {
        int increment = 1;
        if(thisX == prevX)
        {
            if(thisY < prevY) increment = -1;
            for(int y = prevY + increment; y != thisY; y += increment) floor[thisX][y] = 'X';
        }
        else
        {
            if(thisX < prevX) increment = -1;
            for(int x = prevX + increment; x != thisX; x += increment) floor[x][thisY] = 'X';
        }
    }

    public static void defineInsideOutside()
    {
        for(int x = 0; x < maxXCoord + 1; x++)
        {
            if(floor[x][0] == 0)
            {
                int y = 0;
                while(floor[x][y] == 0 && y < maxYCoord)
                {
                    floor[x][y] = '.';
                    y++;
                }
            }
            if(floor[x][maxYCoord] == 0)
            {
                int y = maxYCoord;
                while(floor[x][y] == 0 && y > 0)
                {
                    floor[x][y] = '.';
                    y--;
                }
            }
        }
        for(int y = 0; y < maxYCoord + 1; y++)
        {
            if(floor[0][y] == '.')
            {
                int x = 0;
                while((floor[x][y] == '.' || floor[x][y] == 0) && x < maxXCoord)
                {
                    floor[x][y] = '.';
                    x++;
                }
            }
            if(floor[maxXCoord][y] == '.')
            {
                int x = maxXCoord;
                while((floor[x][y] == '.' || floor[x][y] == 0) && x > 0)
                {
                    floor[x][y] = '.';
                    x--;
                }
            }
        }
        for(int x = 0; x < maxXCoord + 1; x++)
        {
            for(int y = 0; y < maxYCoord + 1; y++)
            {
                if(floor[x][y] == 0) floor[x][y] = 'X';
            }
        }
    }
}

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import static java.lang.Integer.parseInt;

public class Day8
{
    public static int numOfCoords;
    public static int numOfDistances;
    public static int[][] coords;
    public static int[][] distances;
    public static List<Integer> groupSizes;
    public static List<Integer> lastTwoXCoords = new ArrayList<>(Arrays.asList(0, 0));

    public static void main(String[] args) throws FileNotFoundException
    {
        createList();
        calculateDistances();
        //System.out.println(multiplyThreeLargestSizes(1000));
        System.out.println(multiplyLastTwoXCoords());
    }

    public static int multiplyThreeLargestSizes(int numOfConnections)
    {
        for(int c = 0; c < numOfConnections; c++) createAConnection();
        Collections.sort(groupSizes);
        return groupSizes.getLast() * groupSizes.get(numOfCoords - 2) * groupSizes.get(numOfCoords - 3);
    }

    public static int multiplyLastTwoXCoords()
    {
        while(!groupSizes.contains(numOfCoords)) createAConnection();
        return lastTwoXCoords.get(0) * lastTwoXCoords.get(1);
    }

    public static void createAConnection()
    {
        int shortestDistance = distances[0][0];
        int index = 0;
        for(int d = 1; d < numOfDistances; d++)
        {
            if(distances[0][d] < shortestDistance && distances[0][d] > 0)
            {
                shortestDistance = distances[0][d];
                index = d;
            }
        }
        distances[0][index] = 0;
        int oldGroupNum = coords[3][distances[2][index]];
        int newGroupNum = coords[3][distances[1][index]];
        if(oldGroupNum != newGroupNum)
        {
            for(int c = 0; c < numOfCoords; c++)
            {
                if(coords[3][c] == oldGroupNum) coords[3][c] = newGroupNum;
            }
            groupSizes.set(newGroupNum, groupSizes.get(newGroupNum) + groupSizes.get(oldGroupNum));
            groupSizes.set(oldGroupNum, 0);
            lastTwoXCoords.set(0, coords[0][distances[1][index]]);
            lastTwoXCoords.set(1, coords[0][distances[2][index]]);
        }
    }

    public static void createList() throws FileNotFoundException
    {
        File f = new File("Day8_Input.txt");
        Scanner s = new Scanner(f);
        while(s.hasNextLine())
        {
            String coord = s.nextLine();
            numOfCoords++;
        }
        coords = new int[4][numOfCoords];
        groupSizes = new ArrayList<>();
        s = new Scanner(f);
        while(s.hasNextLine())
        {
            for(int c = 0; c < numOfCoords; c++)
            {
                String coord = s.nextLine() + ",";
                for(int i = 0; i < 3; i++)
                {
                    coords[i][c] = parseInt(coord.substring(0, coord.indexOf(",")));
                    coord = coord.substring(coord.indexOf(",") + 1);
                }
                coords[3][c] = c;
                groupSizes.addLast(1);
            }
        }
    }

    public static void calculateDistances()
    {
        int temp = numOfCoords - 1;
        while(temp > 0)
        {
            numOfDistances += temp;
            temp--;
        }
        distances = new int[3][numOfDistances];
        int count = 0;
        for(int c = 0; c < numOfCoords; c++)
        {
            for(int i = c + 1; i < numOfCoords; i++)
            {
                distances[0][count] = (int) Math.sqrt((Math.pow(coords[0][c] - coords[0][i], 2) +
                        Math.pow(coords[1][c] - coords[1][i], 2) + Math.pow(coords[2][c] - coords[2][i], 2)));
                distances[1][count] = c;
                distances[2][count] = i;
                count++;
            }
        }
    }
}

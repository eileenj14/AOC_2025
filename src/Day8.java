import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class Day8
{
    public static int numOfCoords = 0;
    public static int numOfDistances = 0;
    public static int[][] list;
    public static int[][] distances;

    public static void main(String[] args) throws FileNotFoundException
    {
        createList();
        calculateDistances();
        System.out.println(connectJunctionBoxes(10));
    }

    public static int connectJunctionBoxes(int numOfConnections)
    {
        for(int c = 0; c < numOfConnections; c++)
        {
            long shortestDistance = distances[0][0];
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
            if(list[3][distances[1][index]] == list[3][distances[2][index]]) numOfConnections++;
            else
            {
                if(list[3][distances[1][index]] < list[3][distances[2][index]])
                    list[3][distances[2][index]] = list[3][distances[1][index]];
                else list[3][distances[1][index]] = list[3][distances[2][index]];
            }
        }
        List<Integer> circuits = new ArrayList<>(Arrays.asList(0));
        List<Integer> sizes = new ArrayList<>(Arrays.asList(1));
        for(int c = 0; c < numOfCoords; c++)
        {
        }
    }

    public static void calculateDistances()
    {
        numOfDistances = 0;
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
                distances[0][count] = (int)(Math.pow(list[0][c] - list[0][i], 2) +
                        Math.pow(list[1][c] - list[1][i], 2) + Math.pow(list[2][c] - list[2][i], 2));
                distances[1][count] = c;
                distances[2][count] = i;
                count++;
            }
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
        list = new int[4][numOfCoords];
        s = new Scanner(f);
        while(s.hasNextLine())
        {
            for(int c = 0; c < numOfCoords; c++)
            {
                String coord = s.nextLine() + ",";
                for(int i = 0; i < 3; i++)
                {
                    list[i][c] = parseInt(coord.substring(0, coord.indexOf(",")));
                    coord = coord.substring(coord.indexOf(",") + 1);
                }
                list[3][c] = c;
            }
        }
    }
}

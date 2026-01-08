import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class Day8
{
    public static int numOfCoords = 0;
    public static int numOfDistances = 0;
    public static int[][] list;
    public static long[][] distances;

    public static void main(String[] args) throws FileNotFoundException
    {
        createList();
        calculateDistances();
        connectJunctionBoxes(10);
    }

    public static void connectJunctionBoxes(int numOfConnections)
    {
        for(int s = 0; s < numOfConnections; s++)
        {
            long shortest = distances[0][0];
            int index = 0;
            for(int d = 1; d < numOfDistances; d++)
            {
                if(distances[0][d] < shortest && distances[0][d] > 0)
                {
                    shortest = distances[0][d];
                    index = d;
                }
            }
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
        distances = new long[3][numOfDistances];
        int count = 0;
        for(int c = 0; c < numOfCoords; c++)
        {
            for(int i = c + 1; i < numOfCoords; i++)
            {
                distances[0][count] = (long)(Math.pow(list[0][c] - list[0][i], 2) + Math.pow(list[1][c] - list[1][i], 2) + Math.pow(list[2][c] - list[2][i], 2));
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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class Day8
{
    public static int[][] list;
    public static int numOfCoords = 0;

    public static void main(String[] args) throws FileNotFoundException
    {
        createList();
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

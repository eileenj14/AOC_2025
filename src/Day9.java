import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day9
{
    public static int[][] tileLocations;
    public static int[][] areas;

    public static void main(String[] args) throws FileNotFoundException
    {
        storeTileLocations();
        calculateAreas();
    }

    public static void calculateAreas()
    {
    }

    public static void storeTileLocations() throws FileNotFoundException
    {
        File f = new File("Day9_Input.txt");
        Scanner s = new Scanner(f);
    }
}

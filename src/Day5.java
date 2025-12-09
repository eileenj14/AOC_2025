import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import static java.lang.Long.parseLong;

public class Day5
{
    public static int numOfRanges = 0;
    public static int numOfIds = -1;
    public static List<Long> idRangeMins;
    public static List<Long> idRangeMaxes;
    public static List<Long> ids;

    public static void main(String[] args) throws FileNotFoundException
    {
        createDatabaseFile();
        System.out.println(getNumOfAvailableFreshIds());
        System.out.println(getNumOfFreshIds());
    }

    public static int getNumOfAvailableFreshIds()
    {
        int numOfAvailableFreshIds = 0;
        for(int i = 0; i < numOfIds; i++)
        {
            for(int r = 0; r < numOfRanges; r++)
            {
                if(ids.get(i) >= idRangeMins.get(r) && ids.get(i) <= idRangeMaxes.get(r))
                {
                    numOfAvailableFreshIds++;
                    break;
                }
            }
        }
        return numOfAvailableFreshIds;
    }

    public static long getNumOfFreshIds()
    {
        long totalNumOfFreshIds = 0;
        for(int r = 0; r < numOfRanges; r++)
        {
            long numOfFreshIds = idRangeMaxes.get(r) - idRangeMins.get(r) + 1;
            totalNumOfFreshIds += numOfFreshIds;
        }
        return totalNumOfFreshIds;
    }

    public static void createDatabaseFile() throws FileNotFoundException
    {
        File f = new File("Day5_Input.txt");
        Scanner s = new Scanner(f);
        while(s.hasNextLine())
        {
            String potential = s.nextLine();
            if(potential.indexOf("-") > 0) numOfRanges++;
            else numOfIds++;
        }
        s = new Scanner(f);
        idRangeMins = new ArrayList<>();
        idRangeMaxes = new ArrayList<>();
        ids = new ArrayList<>();
        for(int n = 0; n < numOfRanges; n++)
        {
            String range = s.next();
            idRangeMins.add(parseLong(range.substring(0, range.indexOf("-"))));
            idRangeMaxes.add(parseLong(range.substring(range.indexOf("-") + 1)));
        }
        for(int n = 0; n < numOfIds; n++) ids.add(s.nextLong());
        boolean changed = true;
        while(changed)
        {
            int prevNumOfRanges = numOfRanges;
            mergeRanges();
            if(numOfRanges == prevNumOfRanges) changed = false;
        }
    }

    public static void mergeRanges()
    {
        List<Long> ranges = new ArrayList<>();
        ranges.add(idRangeMins.getFirst());
        ranges.add(idRangeMaxes.getFirst());
        for(int n = 1; n < numOfRanges; n++)
        {
            long newMin = idRangeMins.get(n);
            long newMax = idRangeMaxes.get(n);
            boolean merged = false;
            for(int r = 0; r < ranges.size(); r += 2)
            {
                long oldMin = ranges.get(r);
                long oldMax = ranges.get(r + 1);
                if(newMin >= oldMin && newMax <= oldMax)
                {
                    merged = true;
                    break;
                }
                if(oldMin >= newMin && oldMax <= newMax)
                {
                    merged = true;
                    ranges.set(r, newMin);
                    ranges.set(r + 1, newMax);
                    break;
                }
                if(newMax >= oldMin && newMax <= oldMax)
                {
                    merged = true;
                    ranges.set(r, newMin);
                    break;
                }
                if(newMin >= oldMin && newMin <= oldMax)
                {
                    merged = true;
                    ranges.set(r + 1, newMax);
                    break;
                }
            }
            if(!merged)
            {
                ranges.add(newMin);
                ranges.add(newMax);
            }
        }
        idRangeMins = new ArrayList<>();
        idRangeMaxes = new ArrayList<>();
        for(int r = 0; r < ranges.size(); r += 2)
        {
            idRangeMins.add(ranges.get(r));
            idRangeMaxes.add(ranges.get(r + 1));
        }
        numOfRanges = idRangeMins.size();
    }
}

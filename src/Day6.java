import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class Day6
{
    public static int numOfProblems = 0;
    public static int numOfInputsPerProblem = 0;
    public static int[][] problemNums;
    public static String[] problemOps;

    public static void main(String[] args) throws FileNotFoundException
    {
        createHomework();
        //System.out.println(getTotalOfAnswers());
        createHomework2();
        //System.out.println(getTotalOfAnswers());
    }

    public static long getTotalOfAnswers()
    {
        long totalOfAnswers = 0;
        for(int p = 0; p < numOfProblems; p++)
        {
            long answer = problemNums[p][0];
            for(int i = 1; i < numOfInputsPerProblem; i++)
            {
                if(problemOps[p].equals("*")) answer *= problemNums[p][i];
                else answer += problemNums[p][i];
            }
            totalOfAnswers += answer;
        }
        return totalOfAnswers;
    }

    public static void createHomework() throws FileNotFoundException
    {
        File f = new File("Day6_Input.txt");
        Scanner s = new Scanner(f);
        String line = s.nextLine();
        String[] lineArray = line.split("\\s+");
        numOfProblems = lineArray.length;
        numOfInputsPerProblem = 3;
        s = new Scanner(f);
        problemNums = new int[numOfProblems][numOfInputsPerProblem];
        problemOps = new String[numOfProblems];
        int count = 0;
        while(s.hasNextLine())
        {
            line = s.nextLine();
            lineArray = line.trim().split("\\s+");
            numOfProblems = lineArray.length;
            if(count < numOfInputsPerProblem)
            {
                for(int p = 0; p < numOfProblems; p++) problemNums[p][count] = parseInt(lineArray[p]);
                count++;
            }
            else for(int p = 0; p < numOfProblems; p++) problemOps[p] = lineArray[p];
        }
    }

    public static void createHomework2() throws FileNotFoundException
    {
        File f = new File("Day6_Input.txt");
        Scanner s = new Scanner(f);
        numOfInputsPerProblem = 4;
        problemNums = new int[numOfProblems][numOfInputsPerProblem];
        String[] lines = new String[3];
        for(int i = 0; i < 3; i++)
        {
            lines[i] = s.nextLine();
        }
        int p = 0;
        while(!lines[0].isEmpty())
        {
            int i = 0;
            int spaces = 0;
            int num = 0;
            for(int l = 0; l < 3; l++)
            {
                if(lines[l].charAt(0) == ' ')
                {
                    spaces++;
                    lines[l] = lines[l].substring(1);
                }
                else
                {
                    num = num * 10 + parseInt(lines[l].substring(0, 1));
                    lines[l] = lines[l].substring(1);
                }
            }
        }
        for(int x = 0; x < numOfInputsPerProblem; x++) System.out.println(problemNums[0][x]);
    }
}

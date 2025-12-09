import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day6
{
    public static int numOfProblems = 0;
    public static int numOfInputsPerProblem = 3;
    public static int[][] problemNums;
    public static String[] problemOps;

    public static void main(String[] args) throws FileNotFoundException
    {
        //createHomework();
        //System.out.println(getTotalOfAnswers());
        createHomework2();
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
                for(int p = 0; p < numOfProblems; p++) problemNums[p][count] = Integer.parseInt(lineArray[p]);
                count++;
            }
            else for(int p = 0; p < numOfProblems; p++) problemOps[p] = lineArray[p];
        }
    }

    public static void createHomework2()
    {

    }
}

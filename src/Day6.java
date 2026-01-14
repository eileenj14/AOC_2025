import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import static java.lang.Integer.parseInt;

public class Day6
{
    public static int numOfProblems = 0;
    public static int numOfInputsPerProblem = 4;
    public static int[][] problemNums;
    public static List<String> problemOps;

    public static void main(String[] args) throws FileNotFoundException
    {
        createHomework();
        System.out.println(getTotalOfAnswers());
        createHomework2();
        System.out.println(getTotalOfAnswers());
    }

    public static long getTotalOfAnswers()
    {
        long totalOfAnswers = 0;
        for(int p = 0; p < numOfProblems; p++)
        {
            long answer = problemNums[p][0];
            for(int i = 1; i < numOfInputsPerProblem; i++)
            {
                if(problemOps.get(p).equals("*"))
                {
                    if(problemNums[p][i] != 0) answer *= problemNums[p][i];
                }
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
        problemOps = new ArrayList<>();
        for(int i = 0; i < numOfInputsPerProblem; i++)
        {
            line = s.nextLine();
            lineArray = line.trim().split("\\s+");
            for(int p = 0; p < numOfProblems; p++) problemNums[p][i] = parseInt(lineArray[p]);
        }
        line = s.nextLine();
        lineArray = line.trim().split("\\s+");
        for(int p = 0; p < numOfProblems; p++) problemOps.addLast(lineArray[p]);
    }

    public static void createHomework2() throws FileNotFoundException
    {
        File f = new File("Day6_Input.txt");
        Scanner s = new Scanner(f);
        String[] lines = new String[numOfInputsPerProblem];
        for(int i = 0; i < numOfInputsPerProblem; i++) lines[i] = s.nextLine();
        int maxLength = 0;
        for(int i = 0; i < numOfInputsPerProblem; i++)
        {
            if(lines[i].length() > maxLength) maxLength = lines[i].length();
        }
        for(int i = 0; i < numOfInputsPerProblem; i++)
        {
            while(lines[i].length() != maxLength) lines[i] += " ";
        }
        problemNums = new int[numOfProblems][numOfInputsPerProblem];
        for(int p = 0; p < numOfProblems; p++)
        {
            for(int i = 0; i < numOfInputsPerProblem + 1; i++)
            {
                String num = "";
                for(int l = 0; l < numOfInputsPerProblem; l++)
                {
                    if(lines[l].length() - 1 != -1)
                    {
                        num += lines[l].substring(lines[l].length() - 1);
                        lines[l] = lines[l].substring(0, lines[l].length() - 1);
                    }
                }
                if(num.isBlank()) break;
                else problemNums[p][i] = parseInt(num.trim());
            }
        }
        Collections.reverse(problemOps);
    }
}

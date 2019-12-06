
import java.util.Scanner;
import java.io.FileWriter;
import java.util.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;


public class CreateTxtFilesOfExperiment{
    private int numberOfFilesCreate;

    public CreateTxtFilesOfExperiment()
    {
        this.numberOfFilesCreate = 0;
    }
    //@param i start from 0 to input user's numberOfFiles with specific number of forlders,become 0 when all files are created
    //@param j start from 0 to input user's number of forlders,become 0 every time a file is created
    //@param input user's numberOfFiles
    //@param input user's n = number of folders
    //@param flag is for header line
    public void createTxtFiles(StringBuilder path)
    {
        int n = 0;
        int numberOfFiles = 0;
        int i = 0;
        int j = 0;
        int exit = 1;
        StringBuilder ret = new StringBuilder();
        String directory = path.append("Data").toString();
        String extension = ".txt";
        int randomNumber;
        int numberOfDisksAlg1 = 0;
        int numberOfDisksAlg2 = 0;
        int currentNumberOfFilesCreate = 0;
        boolean flag = true;
        Greedy greedy = new Greedy(false);
        List<Folder> currentFileList = new List<>();
        CreateCsvFile csvBuilder = new CreateCsvFile();
        //create Data folder if doesn't exit , and delete all old experiment's files if exist
        try {
			File dir = new File(directory);
            dir.mkdir();
            for (File file : dir.listFiles()) {
                if (file.getName().endsWith(extension) && !file.delete()) {
                    throw new IOException();
                }
            }
		} catch (IOException e) {
			System.out.println("Problem occurs when deleting files");
			e.printStackTrace();
        }
        
        Scanner input = new Scanner(System.in);

        while(exit != 0)
        {
                
            numberOfDisksAlg1 = 0;
            numberOfDisksAlg2 = 0;
            currentNumberOfFilesCreate = 0;
            while(true)
            {
                input = new Scanner(System.in);
                try
                {
                    System.out.println("Give the number of Folders");
                    n = input.nextInt();
                    System.out.println("Give the number of Files");
                    numberOfFiles = input.nextInt();
                    break;
                }
                catch (InputMismatchException e)
                {
                    System.out.println("Wrong number format");
                }
            }

            while(i < numberOfFiles)
            {
                //create experiment's files
                currentNumberOfFilesCreate++;
                ret.append(directory);
                ret.append("/numberOfFolders_");
                ret.append(n);
                ret.append("_");
                ret.append(numberOfFilesCreate);
                ret.append(".txt");
                try
                {
                    FileWriter writer = new FileWriter(ret.toString(), false);
                    i++;
                    this.numberOfFilesCreate++;
                    PrintWriter line = new PrintWriter(writer);
                    Random r = new Random();
                    while (j < n)
                    {
                        randomNumber = r.nextInt(1000000);
                        line.println(r.nextInt(1000000));
                        Folder current = new Folder(randomNumber);
                        currentFileList.insert(current);
                        j++;
                    }
                    writer.close();
                    j = 0;
                } 
                catch (IOException e) 
                {
                    e.printStackTrace();
                }
                //delete the value of String builder because i want to be empty fro the next file
                ret.delete(0,  ret.length());
                
                //run greedy algorithm without sort for a file of specific numbers of Files
                greedy.greedyAlg(currentFileList.getHead());
                numberOfDisksAlg1 = numberOfDisksAlg1 + greedy.getNumberOfDisks();

                //run greedy algorithm without sort for a file of specific numbers of Files
                currentFileList.sort();
                greedy.greedyAlg(currentFileList.getHead());
                numberOfDisksAlg2 = numberOfDisksAlg2 + greedy.getNumberOfDisks();
                //remove all folder so when i create next file this list should be empty
                while(!currentFileList.isEmpty())
                {
                    currentFileList.remove();
                }
            }
            //let user to stop or continue to create files
            while(true)
            {
                input = new Scanner(System.in);
                try
                {
                    System.out.println("press 0 to stop");
                    exit = input.nextInt();
                    break;
                }
                catch (InputMismatchException e)
                {
                    System.out.println("Wrong number format");
                }
            }
            i = 0;
            //create csv file,flag is for header line
            csvBuilder.createCsvFile(n, path, numberOfDisksAlg1, numberOfDisksAlg2, currentNumberOfFilesCreate, flag);
            flag = false;
        }
        input.close();
    }
}
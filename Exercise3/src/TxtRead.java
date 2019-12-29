import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TxtRead{
    private String path;

    public TxtRead(String path)
    {
        this.path = path;
    }

    public int[][] read()
    {
        int count = 0;
        int[][] inputData = new int[0][0];
        try
        {
            try
            {
                BufferedReader br = new BufferedReader(new FileReader(path));
                String line;
                int size = 0;
                String[] temp = new String[2];
                line = br.readLine();
                size = Integer.parseInt(line);
                inputData = new int[size][2];
                count++;
                while ((line = br.readLine()) != null)
                {
                    try
                    {
                        if (line.split(" ").length != 2)
                        {
                            throw new IndexOutOfBoundsException();
                        }
                    }catch(IndexOutOfBoundsException e)
                    {
                        System.out.println("All lines after first line should have 2 numbers. " + "Check line: " + (count + 1));
                        System.exit(1);
                    }
                    try
                    {
                        if (count > size)
                        {
                            throw new IndexOutOfBoundsException();
                        }
                    }catch(IndexOutOfBoundsException e)
                    {
                        System.out.println("File has lower number of elements than first row declares");
                        System.exit(1);
                    }
                    temp = line.split(" ");
                    for (int j = 0; j < 2; j++)
                    {         
                        inputData[count-1][j] = Integer.parseInt(temp[j]);
                        try
                        {
                            if (Integer.parseInt(temp[j]) < 0 || Integer.parseInt(temp[j]) > 100)
                            {
                                throw new NumberFormatException();
                            }
                        }catch(NumberFormatException e)
                        {
                            System.out.println("File should have numbers 0-100. " + "Check line: " + (count + 1) + " column: " + (j+1));
                            System.exit(1);
                        }
                    }
                    count++;
                }
                br.close();
                try
                {
                    if (size != (count - 1))
                    {
                        throw new IndexOutOfBoundsException();
                    }
                }catch(IndexOutOfBoundsException e)
                {
                    System.out.println("File has higher number of elements than first row declares");
                    System.exit(1);
                }
            }
            catch(NumberFormatException e)
            {
                System.out.println("File should have 1 number at first line or file doesn't contain only numbers. " + "Check line: " + (count + 1));
                System.exit(1);
            }
            
        }
        catch(IOException e)
        {
            System.out.println("File doesn't exist");
            System.exit(1);
        }
        System.out.println("Loading the data from the file was successful");
        return inputData;
    }
}
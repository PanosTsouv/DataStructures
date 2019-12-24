import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class ReadCsvFile
{
    private boolean exist = true;
    private boolean maxDifference = true;
    private int max = 0;
    private int min = 2147483646;
    //ckech if user's input exist and if maxDifference>=1000
    public void readCsvFile(StringBuilder path, int n, boolean flag){
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(path.toString()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (count >= 1)
                {
                    if (flag){
                        if (Integer.parseInt(values[0]) > this.max)
                        {
                            this.max = Integer.parseInt(values[0]);
                        }
                        if (Integer.parseInt(values[0]) < this.min)
                        {
                            this.min = Integer.parseInt(values[0]);
                        }
                    }
                    if (Integer.parseInt(values[0]) != n)
                    {
                        this.exist = true;
                    }
                    else
                    {
                        this.exist = false;
                    }
                    if (n > this.max)
                    {
                        this.max = n;
                    }
                    if (n < this.min)
                    {
                        this.min = n;
                    }
                    if((this.max - this.min) < 900)
                    {
                        this.maxDifference = true;
                    }
                    else
                    {
                        this.maxDifference = false;
                    }
                }
                count++;
                if (!this.exist && count >= 2)
                {
                    break;
                }
            }
        }
        catch(IOException e)
        {
            System.out.println("File doesn't exist");
            System.exit(1);
        }
    }

    public boolean getExist()
    {
        return this.exist;
    }

    public boolean getMaxDifference()
    {
        return this.maxDifference;
    }
}
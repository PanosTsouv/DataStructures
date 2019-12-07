import java.io.FileWriter;
import java.io.IOException;
public class CreateCsvFile
{
    public void createCsvFile(int n, StringBuilder path, int numberOfDisksAlg1, int numberOfDisksAlg2, int currentNumberOfFilesCreate, boolean flag)
    {
        try
        {
            if(flag)
            {
                path.delete(path.toString().length()-"Data".length(),path.toString().length());
                path.append("results.csv");
            }
            FileWriter writer1 = new FileWriter(path.toString(), true);
            if(flag)
            {
                writer1.append("Folders");
                writer1.append(",");
                writer1.append("Without Sort(Alg1)");
                writer1.append(",");
                writer1.append("With Sort(Alg2)");
                writer1.append("\n");
            }
            writer1.append(String.valueOf(n));
            writer1.append(",");
            writer1.append(String.valueOf(numberOfDisksAlg1/currentNumberOfFilesCreate));
            writer1.append(",");
            writer1.append(String.valueOf(numberOfDisksAlg2/currentNumberOfFilesCreate));
            writer1.append("\n");
            writer1.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
}
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TxtRead{
    private String path;

    public TxtRead(String path)
    {
        this.path = path;
    }

    public List<Folder> readTxt()
    {
        List<Folder> temp = new List<>();
        String line = "";
        //throw exception if a file doesn't exist
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(path));
            //if every line doesn't have 1 number throw exception
            try
            {
                //read until a line is null
                while ((line = br.readLine()) != null)
                {
                    //check if a number is between 0-1000000 and if not throw exception
                    try
                    {
                        if (Integer.parseInt(line) < 0 || Integer.parseInt(line) > 1000000)
                        {
                            throw new IOException();
                        }
                    }
                    catch(IOException e)
                    {
                        System.out.println("Numbers should be 0-1000000");
                        System.exit(1);
                    }
                    //create new folder with reading size and add it in temp list
                    Folder current = new Folder(Integer.parseInt(line));
                    temp.insert(current);
                }
                br.close();
            }
            catch(NumberFormatException e)
            {
                System.out.println("File should have numbers,every line should be only 1 number");
                System.exit(1);
            }
        }
        catch(IOException e)
        {
            System.out.println("File doesn't exist");
            System.exit(1);
        }
        return temp;
    }
}
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TxtRead{
    private String path;
    private int eRow = 0;
    private int eColumn = 0;

    public TxtRead(String path)
    {
        this.path = path;
        this.eRow = 0;
        this.eColumn = 0;
    }
    public String[][] read()
    {
        String [][] inputArray = null;
        String [] coord = new String[2];
        String [] temp = null;
        int rows = 0;
        int columns = 0;
        int countException = 0;
        int count = 0;
        int positionI = 0;
        int positionJ = 0;
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            int i = 0;
            //read until a line is null
            while ((line = br.readLine()) != null)
            {
                //this if mean that we are at first and second row else for others lines
                if (count < 2)
                {
                    //if first row don't have 2 elements,print the reason of an exception
                    try
                    {
                        if (line.split(" ").length != 2 && count == 0)
                        {
                            throw new IndexOutOfBoundsException();
                        }
                    }catch(IndexOutOfBoundsException e)
                    {
                        System.out.println("Array cords should have 2 elements(rows-columns) at first row of txt file");
                        System.out.println("Check also if you have spaces at start of line or more than 1 space between elements");
                        System.exit(1);
                    }
                    //if second row don't have 2 elements,print the reason of an exception
                    try
                    {
                        if (line.split(" ").length != 2 && count == 1)
                        {
                            throw new IndexOutOfBoundsException();
                        }
                    }catch(IndexOutOfBoundsException e)
                    {
                        System.out.println("E cords should have 2 elements(row-column) at second row or first row of txt file is missing");
                        System.out.println("Check also if you have spaces at start of line or more than 1 space between elements");
                        System.exit(1);
                    }
                    coord = line.split(" ");
                    count++;
                    //we save array's bounds
                    if (count == 1)
                    {
                        try{
                            rows = Integer.parseInt(coord[0]);
                            columns = Integer.parseInt(coord[1]);
                            inputArray = new String[rows][columns]; 
                            temp = new String[columns];
                        }catch(NumberFormatException e){
                            System.out.println("Coords should be numbers");
                            System.exit(1);
                        }
                    }
                    //we save entrance coords
                    if (count == 2)
                    {
                        this.eRow = Integer.parseInt(coord[0]);
                        this.eColumn = Integer.parseInt(coord[1]);
                    }
                }else
                {
                    //check if a line has more elements than given bounds,print the reason of an exception
                    try
                    {
                        if(line.split(" ").length != temp.length)
                        {
                            throw new IndexOutOfBoundsException();
                        } 
                        if (i >= rows)
                        {
                            throw new IndexOutOfBoundsException();
                        }
                    }catch(IndexOutOfBoundsException e)
                    {
                        System.out.println("Array has wrong bounds or");
                        System.out.println("check also if you have spaces at start of line or more than 1 space between elements");
                        System.exit(1);
                    }
                    temp = line.split(" ");
                    for (int j = 0; j < columns; j++)
                    {         
                        inputArray[i][j] = temp[j];
                    }
                    i++;
                }
            }
            br.close();
            //check if array have more or less rows of given bounds,print the reason of an exception
            try
            { 
                if (i != rows)
                {
                    throw new IndexOutOfBoundsException();
                }
            }catch(IndexOutOfBoundsException e)
            {
                System.out.println("Array has wrong bounds");
                System.exit(1);
            }
        }catch(IOException e)
        {
            System.out.println("File doesn't exist");
            System.exit(1);
        }
        //check if array has 0-1-E
        try
        {
            for (int i = 0; i < inputArray.length; i++)
            {
                for (int j = 0; j < inputArray[0].length; j++)
                {
                    if (!inputArray[i][j].equals("1") && !inputArray[i][j].equals("0") && !inputArray[i][j].equals("E"))
                    {
                        throw new IOException();
                    }
                }
            }
        }catch(IOException e
        ){
            System.out.println("Txt file has wrong characters.Give olny 0-1-E.Also E should be english letter");
            countException++;
        }
        count = 0;
        //check if array has more than one entrance
        try
        {
            for (int i = 0; i < inputArray.length; i++)
            {
                for (int j = 0; j < inputArray[0].length; j++)
                {
                    if (inputArray[i][j].equals("E"))
                    {
                        count++;
                        positionI = i;
                        positionJ = j;
                    }
                }
            }
            if (count != 1)
            {
                throw new IOException();
            }
        }catch(IOException e)
        {
            System.out.println("Txt file should have one time character E");
            System.exit(1);
        }
        //check if entrace has same bounds with given bounds
        try
        {
            if(positionI != this.eRow || positionJ != this.eColumn)
            {
                throw new IOException();
            }
        }catch(IOException e)
        {
            System.out.println("Wrong entrance's bounds");
            countException++;
        }
        //check if entrance is at corners
        try
        {
            if ((positionI == 0 && positionJ == 0) || (positionI == 0 && positionJ == columns-1) || (positionJ == 0 && positionI == rows-1) || (positionJ == columns-1 && positionI == rows-1))
            {
                throw new IOException();
            }
        }catch(IOException e)
        {
            System.out.println("Entrance can't be at labyrinth's corners");
            countException++;
        }
        //check if entrance isnt at bounds of array
        try
        {
            if (positionI != 0 && positionJ != 0 && positionJ != columns-1 && positionI != rows-1)
            {
                throw new IOException();
            }
        }catch(IOException e)
        {
            System.out.println("Entrance can't be inside labyrinth");
            countException++;
        }
        if (countException != 0)
        {
            System.exit(1);
        }
        return inputArray;
    }
    
    public int getStartRow()
    {
        return this.eRow;
    }

    public int getStartColumn()
    {
        return this.eColumn;
    }
}
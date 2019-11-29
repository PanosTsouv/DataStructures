import java.java.lang.Comparable;

public class Folder implements Comparable<Folder> {
    private int folderSize;

    public Folder()
    {
        this.folderSize = 0;
    }

    public Folder(int folderSize)
    {
        this.folderSize = folderSize;
    }

    public int getFolderSize()
    {
        return folderSize;
    }

    @Override
    public int compareTo(Folder secondFolder)
    {
        if (this.getFolderSize() > secondFolder.getFolderSize())
        {
            return 1;
        }
        else if (this.getFolderSize() == secondFolder.getFolderSize())
        {
            return 0;
        }
        else
        {
            return -1;
        }
    }

    public String toString()
    {
        return "Size-> " + folderSize;
    }

}
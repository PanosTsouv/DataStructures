import java.lang.Comparable;

public class Disk implements Comparable<Disk>{
    
    private int diskId;
    private List<Folder> folders;
    private int freeSpace;

    public Disk()
    {
        this.diskId = 0;
        this.folders = new List<>();
        this.freeSpace = 1000000;
    }

    public Disk(int diskId, int freeSpace)
    {
        this.diskId = diskId;
        this.folders = new List<>();
        this.freeSpace = freeSpace;
    }

    public int getFreeSpace()
    {
        return this.freeSpace;
    }

    public List<Folder> getFolders()
    {
        return this.folders;
    }

    public void setId(int diskId)
    {
        this.diskId = diskId;
    }

    public void setFreeSpace(int freeSpace)
    {
        this.freeSpace = freeSpace;
    }

    @Override
    public int compareTo(Disk secondDisk)
    {
        if (this.getFreeSpace() > secondDisk.getFreeSpace())
        {
            return 1;
        }
        else if (this.getFreeSpace() == secondDisk.getFreeSpace())
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
        return "id " + diskId + " " + freeSpace;
    }
}
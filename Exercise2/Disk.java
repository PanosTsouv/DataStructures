import java.java.lang.Comparable;

public class Disk<T> implements Comparable<Disk>{
    
    private T diskId;
    private List<T> folders;
    private double freeSpace;

    public Disk()
    {
        this.disk = null;
        this.folders = null;
        this.freeSpace = 1000000;
    }

    public Disk(T diskId)
    {
        this.diskId = diskId;
        this.folders = null;
        this.freeSpace = 1000000;
    }

    public double getFreeSpace()
    {
        return this.freeSpace;
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
        return "ID: " + diskId + "->Space: " + freeSpace;
    }

}
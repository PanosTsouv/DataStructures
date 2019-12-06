public class Greedy
{
    private int numberOfDisks = 0;
    private boolean flag;

    public Greedy(boolean flag)
    {
        this.flag = flag;
    }

    public void greedyAlg (Node<Folder> head)
    {
        MaxPQ<Disk> disks = new MaxPQ<>();
        int diskId = 0;
        int diskSum = 0;
        int count = 0;
        
        while(head != null)
        {
            if(disks.size() == 0)
            {
                Disk temp = new Disk();
                disks.insert(temp);
                temp.getFolders().insert(head.getData());
                temp.setFreeSpace(temp.getFreeSpace() - head.getData().getFolderSize());
                diskId++;
            }
            else if(disks.getMaxWithoutRemove().getFreeSpace() < head.getData().getFolderSize())
            {
                Disk temp = new Disk();
                temp.getFolders().insert(head.getData());
                temp.setId(diskId);
                temp.setFreeSpace(temp.getFreeSpace() - head.getData().getFolderSize());
                disks.insert(temp);
                diskId++;
            }else if(disks.getMaxWithoutRemove().getFreeSpace() >= head.getData().getFolderSize())
            {
                Disk temp = disks.getMaxWithoutRemove();
                temp.getFolders().insert(head.getData());
                temp.setFreeSpace(temp.getFreeSpace() - head.getData().getFolderSize());
                disks.sink(1);
            }
            diskSum = diskSum + head.getData().getFolderSize();
            count++;
            head = head.getNext();
        }
        this.numberOfDisks = disks.size();

        if(this.flag){
            System.out.println("Sum of all folders = " + diskSum + " TB");
            System.out.println("Total number of disks used = " + disks.size());
            while(disks.size() != 0)
            {
                Disk output = disks.getMax();
                System.out.print(output);
                if(count <= 100)
                {
                    System.out.print(": ");
                    Node<Folder> current = output.getFolders().getHead();
                    while(current != null)
                    {
                        System.out.print(current.getData().getFolderSize() + " ");
                        current = current.getNext();
                    }
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public int getNumberOfDisks()
    {
        return this.numberOfDisks;
    }

    public void setNumberOfDisks(int numberOfDisks)
    {
        this.numberOfDisks = numberOfDisks;
    }
}
public class Main{
    public static void main(String[] args) {
        List<Disk> a = new List<>();
        a.insert(new Disk(1));
        a.insert(new Disk(2));
        a.insert(new Disk(3));
        System.out.println(a.toString());
        Folder folder1 = new Folder(10000);
        Folder folder2 = new Folder(30000);
        Folder folder3 = new Folder(50000);
        Folder folder4 = new Folder(70000);
        Folder folder5 = new Folder(70000);
        Folder folder6 = new Folder(80000);
        Folder folder7 = new Folder(90000);
        Folder folder8 = new Folder(100000);
        Folder folder9 = new Folder(110000);
        Folder folder10 = new Folder(120000);

        System.out.println(a.remove().compareTo(a.remove()));
        System.out.print(folder1.compareTo(folder2));
        Folder [] folders = new Folder[10];
        folders[0] = folder1;
        folders[1] = folder2;
        folders[2] = folder3;
        folders[3] = folder4;
        folders[4] = folder5;
        folders[5] = folder6;
        folders[6] = folder7;
        folders[7] = folder8;
        folders[8] = folder9;
        folders[9] = folder10;

        for (int i = 0; i < folders.length; i++)
        {
            System.out.println(folders[i].toString());
        }

        System.out.println();
        System.out.println();

        Sort.mergeSort(folders, 0, folders.length-1);

        for (int i = 0; i < folders.length; i++)
        {
            System.out.println(folders[i].toString());
        }
        
    }
}
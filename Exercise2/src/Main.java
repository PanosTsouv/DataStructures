public class Main{
    public static void main(String[] args) {
        StringBuilder path = new StringBuilder();

        for(int i = 0; i < args.length; i++){
            path.append(args[i]);
        }
        
        TxtRead reader = new TxtRead(path.toString());
        List<Folder> folders;
        Greedy greedy = new Greedy(true);
        folders = reader.readTxt();

        greedy.greedyAlg(folders.getHead());

        path.delete(path.toString().length()-"folders.txt".length(),path.toString().length());
        System.out.println(path.toString());
        System.out.println("You can create your experiment's files");
        CreateTxtFilesOfExperiment output = new CreateTxtFilesOfExperiment();
        output.createTxtFiles(path);
    }
}
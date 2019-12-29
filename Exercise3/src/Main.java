public class Main{
    public static void main(String[] args) {
        
        String path = "";
        int[][] inputData;
        TwoDTree inputTree = new TwoDTree();
        for(int i = 0; i < args.length; i++){
            path = path + args[i];
        }
        TxtRead input = new TxtRead(path);
        inputData = input.read();
        System.out.println();
        System.out.println("The 2dTree from file's data is:");
        for (int i = 0; i < inputData.length; i++)
        {
            inputTree.insert(new Point(inputData[i][0], inputData[i][1]));
        }
        System.out.println();
        Menu util = new Menu(inputTree);
        util.menu();  
    }   
}
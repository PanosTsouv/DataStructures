import java.util.NoSuchElementException;

public class Thiseas{
    public static void main(String[] args) {
        String path = "";
        StringStack<Item> stack = new StringStackImpl<>();
        String [][] inputArray;
        Item current = null;

        for(int i = 0; i < args.length; i++){
            path = path + args[i];
        }

        TxtRead input = new TxtRead(path);
        inputArray = input.read();

        current = new Item(input.getStartRow(), input.getStartColumn());
        stack.push(current);
        inputArray[stack.peek().getI()][stack.peek().getJ()] = "1";
        
        //first move when we are at entrance
        //stack.peek().getI() and stack.peek().getJ() return the coords of entrance because current item is entrance
        //so we check if entrance is at first or last row or first or last column to avoid null pointer exception
        //At these four cases we check if entrance have 0 right and left or up and down because if it has then it isnt labyrinth
        //And if we find 0 we make move and chhose 0 to 1 else labyrinth dont have exit
        //**pop before push is for entrance because we dont want to check it again **
        if(stack.peek().getI() == 0)
        {
            if(inputArray[stack.peek().getI()][(stack.peek().getJ() + 1)].equals("0") || inputArray[stack.peek().getI()][stack.peek().getJ() - 1].equals("0"))
            {
                System.out.println("Entrance can't have right-left exits(0),should have walls(1)");
                System.exit(1);
            }
            if(inputArray[stack.peek().getI() + 1][stack.peek().getJ()].equals("0"))
            {
                current = new Item(stack.peek().getI() + 1, stack.peek().getJ());
                stack.pop();
                stack.push(current);
                inputArray[stack.peek().getI()][stack.peek().getJ()] = "1";
            }
            else
            {
                System.out.println("labyrinth don't have exit");
                System.exit(1);
            }
        }
        else if(stack.peek().getJ() == 0)
        {
            if(inputArray[stack.peek().getI() - 1][stack.peek().getJ()].equals("0") || inputArray[stack.peek().getI() + 1][stack.peek().getJ()].equals("0"))
            {
                System.out.println("Entrance can't have up-down exits(0),should have walls(1)");
                System.exit(1);
            }
            if(inputArray[stack.peek().getI()][(stack.peek().getJ() + 1)].equals("0"))
            {
                current = new Item(stack.peek().getI(), stack.peek().getJ() +1);
                stack.pop();
                stack.push(current);
                inputArray[stack.peek().getI()][stack.peek().getJ()] = "1";
            }
            else
            {
                System.out.println("labyrinth don't have exit");
                System.exit(1);
            }
        }
        else if(stack.peek().getJ() == inputArray[0].length-1)
        {
            if(inputArray[stack.peek().getI() - 1][stack.peek().getJ()].equals("0") || inputArray[stack.peek().getI() + 1][stack.peek().getJ()].equals("0"))
            {
                System.out.println("Entrance can't have up-down exits(0),should have walls(1)");
                System.exit(1);
            }
            if(inputArray[stack.peek().getI()][stack.peek().getJ() - 1].equals("0"))
            {
                current = new Item(stack.peek().getI(), stack.peek().getJ() - 1);
                stack.pop();
                stack.push(current);
                inputArray[stack.peek().getI()][stack.peek().getJ()] = "1";
            }
            else
            {
                System.out.println("labyrinth don't have exit");
                System.exit(1);
            }
        }
        else if(stack.peek().getI() == inputArray.length-1)
        {
            if(inputArray[stack.peek().getI()][(stack.peek().getJ() + 1)].equals("0") || inputArray[stack.peek().getI()][stack.peek().getJ() - 1].equals("0"))
            {
                System.out.println("Entrance can't have right-left exits(0),should have walls(1)");
                System.exit(1);
            }
            if(inputArray[stack.peek().getI() - 1][stack.peek().getJ()].equals("0"))
            {
                current = new Item(stack.peek().getI() - 1, stack.peek().getJ());
                stack.pop();
                stack.push(current);
                inputArray[stack.peek().getI()][stack.peek().getJ()] = "1";
            }
            else
            {
                System.out.println("labyrinth don't have exit");
                System.exit(1);
            }
        }

        //moves to find an exit
        //while stop when an item of stack is at bounds of array so it is exit and print it
        //we check for 0 and if we find one we move,push item at stack,and make it 1
        //if we dont find exit and all items around current(current is top of stack) is 1 then we pop from stack until we find again 0
        //if array dont have 0, we pop all items of stack and we print a no-exit message
        while((stack.peek().getI() != 0 && stack.peek().getJ() != 0 && stack.peek().getI() != inputArray.length-1 && stack.peek().getJ() != inputArray[0].length-1))
        {
            if(inputArray[stack.peek().getI()][stack.peek().getJ() - 1].equals("0"))
            {
                current = new Item(stack.peek().getI(), stack.peek().getJ() - 1);
                stack.push(current);
                inputArray[stack.peek().getI()][stack.peek().getJ()] = "1";
            }
            else if(inputArray[stack.peek().getI() + 1][stack.peek().getJ()].equals("0"))
            {
                current = new Item(stack.peek().getI() + 1, stack.peek().getJ());
                stack.push(current);
                inputArray[stack.peek().getI()][stack.peek().getJ()] = "1";
            }
            else if(inputArray[stack.peek().getI() - 1][stack.peek().getJ()].equals("0"))
            {
                current = new Item(stack.peek().getI() - 1, stack.peek().getJ());
                stack.push(current);
                inputArray[stack.peek().getI()][stack.peek().getJ()] = "1";
            }
            else if(inputArray[stack.peek().getI()][(stack.peek().getJ() + 1)].equals("0"))
            {
                current = new Item(stack.peek().getI(), stack.peek().getJ() +1);
                stack.push(current);
                inputArray[stack.peek().getI()][stack.peek().getJ()] = "1";
            }
            else if(inputArray[stack.peek().getI()][stack.peek().getJ() - 1].equals("1") && inputArray[stack.peek().getI() + 1][stack.peek().getJ()].equals("1") 
            && inputArray[stack.peek().getI() - 1][stack.peek().getJ()].equals("1") && inputArray[stack.peek().getI()][(stack.peek().getJ() + 1)].equals("1")){
                try{
                    stack.pop();
                    current = stack.peek();
                }catch(NoSuchElementException e){
                    System.out.println("labyrinth don't have exit");
                    System.exit(1);
                }
            }
        }
        System.out.println("One labyrinth's exit is row: " + current.getI() + " column: " + current.getJ());
    }
}
import java.util.Scanner;

public class Menu{
    private TwoDTree userInput;

    public Menu(TwoDTree userInput)
    {
        this.userInput = userInput;
    }

    public void menu()
    {
        int inputNumber = 1;
        Scanner input = new Scanner(System.in);
        while(inputNumber != 0)
        {
            System.out.println("MENU:");
            System.out.println("0.   Exit");
            System.out.println("1.   Compute the size of the tree");
            System.out.println("2.   Insert a new point");
            System.out.println("3.   Search if a given point exists in the tree");
            System.out.println("4.   Provide a query rectangle");
            System.out.println("5.   Provide a query point");
            System.out.println("6.   Print the 2dTree");
            while(true)
            {
                try
                {
                    System.out.println("Choose a number");
                    inputNumber = Integer.parseInt(input.nextLine());
                    if(inputNumber >= 0 && inputNumber <= 6)
                    {
                        break;
                    }
                    else
                    {
                        System.out.println("Number should be 0-6. Try again!");
                    }
                }
                catch (NumberFormatException e)
                {
                    System.out.println("Wrong number format. Try again!");
                }
            }
            
            if(inputNumber == 1)
            {
                System.out.println("The size of the tree is: " + this.userInput.size());
                System.out.println();
            }
            if(inputNumber == 2)
            {
                System.out.println("Enter the coords of your point which you want to add: ");
                Point temp = userPoint(input);
                this.userInput.insert(temp);
                System.out.println();
            }
            if(inputNumber == 3)
            {
                System.out.println("Enter the coords of your point which you want to search: ");
                Point temp = userPoint(input);
                if(this.userInput.search(temp))
                {
                    System.out.println("The point " + temp + " exist in the 2dTree");
                }
                else
                {
                    System.out.println("The point " + temp + " doesn't exist in the 2dTree");
                }
                System.out.println();
            }
            if(inputNumber == 4)
            {
                System.out.println("Enter the x coords of your query rectangle: ");
                Point tempX = userPoint(input);
                System.out.println("Enter the y coords of your query rectangle: ");
                Point tempY = userPoint(input);
                Rectangle temp = new Rectangle(tempX.getX(), tempX.getY(), tempY.getX(), tempY.getY());
                System.out.println("Your query rectangle is: " + temp);
                StringQueueWithOnePointer<Point> queue = this.userInput.rangeSearch(temp);
                if(!queue.isEmpty())
                {
                    System.out.println("Your query rectangle contains the points below: ");
                    queue.printQueue(System.out);
                }
                else
                {
                    System.out.println("Your query rectangle doesn't contains points");
                }
                System.out.println();
            }
            if(inputNumber == 5)
            {
                System.out.println("Enter the coords of your query point: ");
                Point temp = userPoint(input);
                System.out.println("Nearest neighbor of query point " + temp + " is the point: " + this.userInput.nearestNeighbor(temp));
                System.out.println();
            }
            if(inputNumber == 6)
            {
                System.out.println("The 2dTree is:");
                this.userInput.print2D(this.userInput.getHead());
                System.out.println();
            }
            if(inputNumber != 0)
            {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Point userPoint(Scanner input)
    {
        String line = "";
        String coords[] = new String[2];
        Point inputPoint = new Point(0, 0);
        while(true){
            while (true)
            {
                try
                {
                    line = input.nextLine();
                    if (line.split(" ").length != 2)
                    {
                        throw new IndexOutOfBoundsException();
                    }
                    break;
                }catch(IndexOutOfBoundsException e)
                {
                    System.out.println("You should give 2 numbers for coords. Try again!");
                }
            }
            coords = line.split(" ");
            try
            {
                inputPoint.setX(Integer.parseInt(coords[0]));
                inputPoint.setY(Integer.parseInt(coords[1]));
                if((inputPoint.getX() < 0 || inputPoint.getX() > 100 ) || (inputPoint.getY() < 0 || inputPoint.getY() > 100))
                {
                    throw new NumberFormatException();
                }
                break;
            }
            catch(NumberFormatException e)
            {
                System.out.println("Coords should be integer numbers 0-100. Try again!");
            }
        }
        return inputPoint;
    }
}
public class TwoDTree{

    private TreeNode<Point> head;
    private int size;
    static final int COUNT = 20;

    public TwoDTree()
    {
        this.head = null;
        this.size = 0;
    }

    public TreeNode<Point> getHead()
    {
        return this.head;
    }

    public int size()
    {
        return this.size;
    }

    public boolean isEmpty()
    {
        return this.head == null;
    }

    public void insert(Point item)
    {
        this.head = insert(this.head, item, 0);
    }

    private TreeNode<Point> insert(TreeNode<Point> node, Point item, int count)
    {
        if (node == null)
        {
            this.size++;
            TreeNode<Point> newNode = new TreeNode<>(item);
            newNode.setLevel(count);
            if(newNode.getLevel()==0)
            {
                System.out.println("The root of the tree is the point: " + newNode.getData() + " at level: " + newNode.getLevel());
            }
            else
            {
                System.out.println("The new point of the tree is: " + newNode.getData() + " at level: " + newNode.getLevel());
            }
            return newNode;
        }

        if (node.getData().getX() == item.getX() && node.getData().getY() == item.getY())
        {
            System.out.println("2dTree contains this point: " + item);
            return node;
        }

        if(((count % 2) == 0 && node.getData().getX() > item.getX()) || ((count % 2) == 1 && node.getData().getY() > item.getY()))
        {
            node.setLeft(insert(node.getLeft(), item, ++count));
        }
        else
        {
            node.setRight(insert(node.getRight(), item, ++count));
        }
        return node;
    }

    public boolean search(Point item)
    {
        return search(this.head, item, 0);
    }

    private boolean search(TreeNode<Point> node, Point item, int count)
    {
        if (node == null)
        {
            return false;
        }

        if (node.getData().getX() == item.getX() && node.getData().getY() == item.getY())
        {
            return true;
        }

        if(((count % 2) == 0 && node.getData().getX() > item.getX()) || ((count % 2) == 1 && node.getData().getY() > item.getY()))
        {
            return search(node.getLeft(), item, ++count);
        }
        else
        {
            return search(node.getRight(), item, ++count);
        }
    }
    //left-child rectangle
    private Rectangle findLeftRectangle(Rectangle parentNodeRectangle, TreeNode<Point> parentNode)
    {
        //check if node is vertical
        if((parentNode.getLevel() % 2) == 0)
        {
            return new Rectangle(parentNodeRectangle.getXMin(), parentNode.getData().getX(), parentNodeRectangle.getYMin(), parentNodeRectangle.getYMax());
        }
        else
        {
            return new Rectangle(parentNodeRectangle.getXMin(), parentNodeRectangle.getXMax(), parentNodeRectangle.getYMin(), parentNode.getData().getY());
        }
    }
    //right-child rectangle
    private Rectangle findRightRectangle(Rectangle parentNodeRectangle, TreeNode<Point> parentNode)
    {
        //check if node is vertical
        if((parentNode.getLevel() % 2) == 0)
        {
            return new Rectangle(parentNode.getData().getX(), parentNodeRectangle.getXMax(), parentNodeRectangle.getYMin(), parentNodeRectangle.getYMax());
        }
        else
        {
            return new Rectangle(parentNodeRectangle.getXMin(), parentNodeRectangle.getXMax(), parentNode.getData().getY(), parentNodeRectangle.getYMax());
        }
    }

    public StringQueueWithOnePointer<Point> rangeSearch(Rectangle userInputRectangle)
    {
        StringQueueWithOnePointer<Point> queue = new StringQueueWithOnePointer<>();
        rangeSearch(this.getHead(), userInputRectangle, new Rectangle(0, 100, 0, 100), queue);
        return queue;
    }

    private void rangeSearch(TreeNode<Point> node, Rectangle userInputRectangle, Rectangle nodeRectangle, StringQueueWithOnePointer<Point> queue)
    {
        if(node == null)
        {
            return;
        }

        if(userInputRectangle.intersects(nodeRectangle))
        {
            if(userInputRectangle.contains(node.getData()))
            {
                queue.put(node.getData());
            }
            rangeSearch(node.getLeft(), userInputRectangle, findLeftRectangle(nodeRectangle, node), queue);
            rangeSearch(node.getRight(), userInputRectangle, findRightRectangle(nodeRectangle, node), queue);
        }
    }

    public Point nearestNeighbor(Point userInputPoint)
    {
        return nearestNeighbor(this.getHead(), null, new Rectangle(0, 100, 0, 100), userInputPoint);
    }

    private Point nearestNeighbor(TreeNode<Point> node, Point nearestPoint, Rectangle nodeRectangle, Point userInputPoint)
    {
        if(node == null)
        {
            return nearestPoint;
        }

        double distanceInputPointFromNearest = 0.0;
        double distanceInputPointFromNodeRectangle = 0.0;

        //we dont want to check the distance from userInputPoint if the nearestPoint is null(for example if check root we dont have nearest point)
        if (nearestPoint != null)
        {
            distanceInputPointFromNearest = userInputPoint.squareDistanceTo(nearestPoint);
            distanceInputPointFromNodeRectangle = nodeRectangle.squareDistanceTo(userInputPoint);
        }

        if(node.getLevel() == 0)
        {
            nearestPoint = node.getData();
        }

        //do all code above if we check root or for all others nodes if distanceInputPointFromNearest > distanceInputPointFromNodeRectangle
        if(node.getLevel() == 0 || distanceInputPointFromNearest > distanceInputPointFromNodeRectangle)
        {
            //if current node has lower distance than nearest from point then the current node is nearest
            if(distanceInputPointFromNearest > userInputPoint.squareDistanceTo(node.getData()))
            {
                nearestPoint = node.getData();
            }

            //if node level is even number we have vertical node so we check x coordinate else node has odd level so is horizontal
            //if userInputPoint x or y is lower than node's x or y, we check first the left child....else we check first the right child
            if((userInputPoint.getX() < node.getData().getX() && (node.getLevel() % 2 == 0) ) || (userInputPoint.getY() < node.getData().getY() && (node.getLevel() % 2 == 1) ))
            {
                nearestPoint = nearestNeighbor(node.getLeft(), nearestPoint, findLeftRectangle(nodeRectangle, node), userInputPoint);
                nearestPoint = nearestNeighbor(node.getRight(), nearestPoint, findRightRectangle(nodeRectangle, node), userInputPoint);
            }
            else
            {
                nearestPoint = nearestNeighbor(node.getRight(), nearestPoint, findRightRectangle(nodeRectangle, node), userInputPoint);
                nearestPoint = nearestNeighbor(node.getLeft(), nearestPoint, findLeftRectangle(nodeRectangle, node), userInputPoint);
            }
        }
        return nearestPoint;
    }

    private void print2dTree(TreeNode<Point> node, int space)
    {
        if (node == null)
        {
            return;
        }

        space += COUNT;

        print2dTree(node.getRight(), space);

        System.out.print("\n");
        for (int i = COUNT; i < space; i++)
        {
            System.out.print(" ");
        }
        System.out.print(node.getData() + "(" + node.getLevel() + ")" + "\n");

        print2dTree(node.getLeft(), space);
    }

    public void print2D(TreeNode<Point> head)
    {
        print2dTree(head, 0);
    }
}
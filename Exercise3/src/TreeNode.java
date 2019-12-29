public class TreeNode<T>
{
    private T item;
    private TreeNode<T> left;
    private TreeNode<T> right;
    private int level;

    public TreeNode()
    {
        item = null;
        this.left = null;
        this.right = null;
    }

    public TreeNode(T item)
    {
        this.item = item;
        this.left = null;
        this.right = null;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    public int getLevel()
    {
        return level;
    }

    public T getData()
    {
        return this.item;
    }

    public void setData(T item)
    {
        this.item = item;
    }

    public TreeNode<T> getLeft()
    {
        return this.left;
    }

    public void setLeft(TreeNode<T> left)
    {
        this.left = left;
    }

    public TreeNode<T> getRight()
    {
        return this.right;
    }

    public void setRight(TreeNode<T> right)
    {
        this.right = right;
    }
}
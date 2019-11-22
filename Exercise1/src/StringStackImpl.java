import java.io.PrintStream;
import java.util.NoSuchElementException;

public class StringStackImpl<T> implements StringStack<T>{

    private int size = 0;
    private Node<T> top = null;

    public StringStackImpl()
    {
        this.size = 0;
        this.top = null;
    }

    @Override
    public boolean isEmpty()
    {
        return this.top == null;
    }

    @Override
    public void push(T item)
    {
        Node<T> pushNode = new Node<>(item);

        if (isEmpty())
        {
            this.top = pushNode;
        }else
        {
            pushNode.setNext(this.top);
            this.top = pushNode;
        }
        this.size++;
    }

    @Override
    public T pop() throws NoSuchElementException
    {
        if (isEmpty())
        {
            throw new NoSuchElementException("Stack is Empty");
        }

        T item = top.getData();

        if (this.size == 1)
        {
            this.top = null;
            this.size--;
        }else
        {
            top = top.getNext();
            this.size--;
        }
        return item;
    }

    @Override
    public T peek() throws NoSuchElementException
    {
        if (isEmpty())
        {
            throw new NoSuchElementException("Stack is Empty");
        }else
        {
            return top.getData();
        }
    }

    @Override
    public void printStack(PrintStream stream)
    {
        Node<T> node = this.top;

        for(int i = 0; i < this.size(); i++)
        {
            stream.println(node.getData());
            stream.flush();
            node = node.getNext();
        }
    }

    @Override
    public int size()
    {
        return this.size;
    }












}
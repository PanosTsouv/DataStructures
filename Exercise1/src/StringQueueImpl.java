import java.io.PrintStream;
import java.util.NoSuchElementException;

public class StringQueueImpl<T> implements StringQueue<T>{
    private int size;
    private Node<T> head;
    private Node<T> tail;

    public StringQueueImpl()
    {
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    @Override
    public boolean isEmpty()
    {
        return this.head == null;
    }

    @Override
    public void put(T item)
    {
        Node<T> putNode = new Node<>(item);

        if(isEmpty())
        {
            this.head = putNode;
            this.tail = putNode;
        }else
        {
            this.tail.setNext(putNode);
            this.tail = putNode;
        }
        size++;
    }

    @Override
    public T get() throws NoSuchElementException
    {
        if(isEmpty())
        {
            throw new NoSuchElementException("Queue is Empty");
        }

        T item = this.head.getData();

        if (this.head == this.tail)
        {
            this.head = this.tail = null;
        }else
        {
            this.head = this.head.getNext();
        }
        size--;
        return item;
    }

    @Override
    public T peek() throws NoSuchElementException
    {
        if(isEmpty())
        {
            throw new NoSuchElementException("Queue is Empty");
        }else
        {
            return this.head.getData();
        }
    }

    @Override
    public void printQueue(PrintStream stream)
    {
        Node<T> node = this.head;

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
import java.io.PrintStream;
import java.util.NoSuchElementException; 

public class StringQueueWithOnePointer<T> implements StringQueue<T>{
    private int size;
    private Node<T> tail;

    public StringQueueWithOnePointer()
    {
        this.size = 0;
        this.tail = null;
    }

    @Override
    public boolean isEmpty()
    {
        return this.tail == null;
    }

    @Override
    public void put(T item)
    {
        Node<T> putNode = new Node<>(item);

        if(isEmpty())
        {
            putNode.setNext(putNode);
        }else
        {
            putNode.setNext(tail.getNext());
            this.tail.setNext(putNode);

        }
        this.tail = putNode;
        size++;
    }

    @Override
    public T get() throws NoSuchElementException
    {
        if(isEmpty())
        {
            throw new NoSuchElementException("Queue is Empty");
        }

        T item = this.tail.getNext().getData();

        if (this.tail.getNext() == this.tail)
        {
            this.tail = null;
        }else
        {
            this.tail.setNext(this.tail.getNext().getNext());
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
            return this.tail.getNext().getData();
        }
    }

    @Override
    public void printQueue(PrintStream stream)
    {
        if(isEmpty())
        {
            stream.println("Queue is empty");
            return;
        }
        Node<T> node = this.tail.getNext();

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
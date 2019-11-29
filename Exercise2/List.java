import java.io.PrintStream;
import java.util.NoSuchElementException;

public class List<T>{

    private Node<T> tail;
    private int size;

    public List()
    {
        this.tail = null;
        this.size = 0;
    }
    public boolean isEmpty()
    {
        return size == 0;
    }

    public void insert(T item)
    {
        Node<T> current = new Node<>(item);

        if (isEmpty())
        {
            current.setNext(current);
        }
        else{
            current.setNext(this.tail.getNext());
            this.tail.setNext(current);
        }
        this.tail = current;
        this.size++;
    }

    public T remove() throws NoSuchElementException
    {
        if (isEmpty())
        {
            throw new NoSuchElementException();
        }

        T item = this.tail.getNext().getData();

        this.tail.setNext(this.tail.getNext().getNext());
        this.size--;
        return item;
    }

    public int size()
    {
        return this.size;
    }

    public Node<T> getTail(){
        return this.tail;
    }

    public void setTail(Node<T> tail)
    {
        this.tail = tail;
    }

    public String toString() 
    {
        if (isEmpty()) 
        {
            return "List is empty";
        }

        Node current = tail.getNext();

        StringBuilder ret = new StringBuilder();

        int i = 0;

        // while not at end of list, output current node's data
        ret.append("HEAD -> ");

        while (i != this.size()) {
            ret.append(current.data.toString());

            if (i != this.size()-1)
                ret.append(" -> ");

            current = current.getNext();
            i++;
        }

        ret.append(" <- TAIL");

        return ret.toString();
    }





}
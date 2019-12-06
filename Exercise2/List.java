import java.util.NoSuchElementException;

public class List<T extends Comparable<T>>{

    private Node<T> tail;
    private Node<T> head;
    private int size;

    public List()
    {
        this.head = null;
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
            this.head = current;
            this.tail = current;
        }
        else
        {
            current.setNext(this.head);
            this.head = current;
        }
        this.size++;
    }

    public T remove() throws NoSuchElementException
    {
        if (isEmpty())
        {
            throw new NoSuchElementException();
        }

        T item = this.head.getData();

        if(this.head == this.tail)
        {
            this.head = this.tail = null;
        }
        else
        {
            this.head = this.head.getNext();
        }
        this.size--;
        return item;
    }

    public int size()
    {
        return this.size;
    }

    public Node<T> getHead(){
        return this.head;
    }

    public Node<T> getTail(){
        return this.tail;
    }

    public void setHead(Node<T> head)
    {
        this.head = head;
        if (this.size == 0)
        {
            this.size++;
        }
    }

    public String toString() 
    {
        if (isEmpty()) 
        {
            return "List is empty";
        }

        Node<T> current = this.head;

        StringBuilder ret = new StringBuilder();

        // while not at end of list, output current node's data
        ret.append("HEAD -> ");

        while (current != null) {
            ret.append(current.getData().toString());

            if (current.getNext() != null)
                ret.append(" -> ");

            current = current.getNext();
        }

        ret.append(" <- TAIL");

        return ret.toString();
    }

    public void sort() {
        // No need to sort if list is empty or has a single element
        if (head == null || head == tail)
            return;

        Node<T> newHead = null;
        Node<T> newTail = null;

        while (head != null) {
            // get next item
            Node<T> tmp = head;

            // move head
            head = head.getNext();

            // move swap to new-sorted list
            if (newHead == null) {
                newHead = newTail = tmp;
                tmp.setNext(null);
            } else {
                Node<T> prev = null;
                Node<T> iterator = newHead;

                // iterate newList until we get to a point where our data is smaller or reach the end
                while (iterator != null && iterator.getData().compareTo(tmp.getData()) >= 0) {
                    prev = iterator;
                    iterator = iterator.getNext();
                }

                // reached the point where we should place the node

                // if prev == null then its the head of the list
                if (prev == null)
                    newHead = tmp;
                else
                    prev.setNext(tmp);

                // if iterator == null then its the tail of the list
                tmp.setNext(iterator);
                if(iterator == null)
                    newTail = tmp;
            }
        }

        head = newHead;
        tail = newTail;
    }
}
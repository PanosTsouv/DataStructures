import java.util.NoSuchElementException;

public class MaxPQ<T extends Comparable<T>>{
    private int size;
    protected T[] heap;
    
    public MaxPQ()
    {
        size = 0;
        heap = (T[]) new Comparable[5];
    }

    public boolean isEmpty()
    {
        return this.size == 0;
    }

    public void insert(T item)
    {
        if(this.size == this.heap.length-1)
        {
            resize(2*this.heap.length);
        }
        this.heap[++size] = item;
        swim(this.size);
    }

    public T getMax()
    {
        T item = this.getMaxWithoutRemove();
        swap(1, this.size());
        size--;
        sink(1);
        return item;
    }

    public T getMaxWithoutRemove() throws NoSuchElementException
    {
        if (isEmpty())
        {
            throw new NoSuchElementException();
        }
        return this.heap[1];
    }

    public void swap(int first, int second)
    {
        T temp = this.heap[first];
        this.heap[first] = this.heap[second];
        this.heap[second] = temp;
    }

    public void swim(int objectPosition)
    {
        int parent = objectPosition/2;
        while(objectPosition > 1 && less(parent, objectPosition))
        {
            swap(objectPosition, parent);
            objectPosition = objectPosition/2;
            parent = objectPosition/2;
        }

    }

    public void sink(int objectPosition)
    {
        while(2*objectPosition <= this.size())
        {
            int temp = 2*objectPosition;
            if(temp < this.size() && this.less(2*objectPosition, 2*objectPosition + 1))
            {
                temp++;
            }
            if (!less(objectPosition, temp))
            {
                break;
            }
            swap(objectPosition, temp); 
            objectPosition = temp;
        }
    }

    public int size()
    {
        return this.size;
    }
    
    public void resize(int newSize)
    {
        T[] temp = (T[]) new Comparable[newSize];
        for(int i = 1; i <= this.size; i++)
        {
            temp[i] = this.heap[i];
        }
        this.heap = temp;
        temp = null;
    }

    public boolean less(int first, int second)
    {
        if (this.heap[first].compareTo(this.heap[second]) < 0)
        {
            return true;
        }
        return false;
    }
}
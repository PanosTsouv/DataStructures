public class Sort<T>{
    public static <T extends Comparable<T>> void mergeSort(T[] arrayToMerge, int l, int r)
    {
        if(r <= l)
        {
            return;
        }
        int m = (r+l)/2;
        mergeSort(arrayToMerge, l, m);
        mergeSort(arrayToMerge, m+1, r);
        merge(arrayToMerge, l, m, r);
    }

    public static <T extends Comparable<T>> void merge(T[] arrayToMerge, int l, int m, int r)
    {
        int i;
        int j;
        T[] temp =(T[])new Comparable[arrayToMerge.length];

        for (i = m+1; i > l; i--)
        {
            temp[i-1] = arrayToMerge[i-1];
        }
        for (j = m; j < r; j++)
        {
            temp[j+1] = arrayToMerge[r + m - j];
        }
        for (int k = l; k <= r; k++)
        {
            if (temp[j].compareTo(temp[i]) >= 0)
            {
                arrayToMerge[k] = temp[j--];
            }
            else 
            {
                arrayToMerge[k] = temp[i++]; 
            }
        }
    }



}
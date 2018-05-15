//Alec Marcum
//COMP 282

public class MinIntHeap
{
    private int maxCap, size;
    private int[] mHeap;

    //constructor that sets max capacity of the heap to m
    public MinIntHeap(int m)
    {
        maxCap = m;
        mHeap = new int[maxCap+1];
        size = 0;
    }

    //constructor that sets max capacity of heap to m; initializes the heap with the b values.
    //Use a private method called buildheap
    public MinIntHeap(int[] b, int m)
    {
        maxCap = m;
        mHeap = new int[maxCap+1];
        buildheap(b);
        size = b.length;

    }


    //-------------- public methods -------------------------------
    public boolean isEmpty()
    {
        return size == 0;
    }

    public int size()
    {
        return size;
    }

    public void heapInsert(int v)
    {
        if (size == maxCap)
        {
            System.out.println("ERROR: Heap is full! Did not insert " + v);
            return;
        }
        size++;
        int id = size;
        mHeap[id] = v;
        bubbleup(id);
    }

    public int removeMin()
    {
        if (this.isEmpty())
        {
            System.out.print("ERROR: Heap is empty!");
            return -9999;
        }
        int min = mHeap[1];
        mHeap[1] = mHeap[size];
        mHeap[size] = 0;
        bubbledown(1);
        size--;
        return min;
    }

    public int min()
    {
        if (this.isEmpty())
        {
            System.out.print("ERROR: Heap is empty!");
            return -9999;
        }
        return mHeap[1];
    }

    // Print the heap values in  heap array order
    public void printHeapValues()
    {
        for (int i = 1; i < size+1; i++)
        {
            System.out.print(mHeap[i]);
            if (i < size)
                System.out.print(" ");
            else
                System.out.print(" \n");
        }

    }

    // Return a copy of the heap values in heap array order.
    public int[] getHeapValues()
    {
        int[] arr = new int[size()];
        for (int i = 1; i < size()+1; i++)
        {
            arr[i-1] = mHeap[i];
        }
        return arr;
    }

    //Uses heapsort algorithm to sort the array b. This is the only static method.
    public static void heapSort(int[] b)
    {
        int n = b.length;

        //build initial heap
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(b, n, i);

        //goes through each node
        for (int i=n-1; i>=0; i--)
        {
            //switch root and last
            int temp = b[0];
            b[0] = b[i];
            b[i] = temp;

            //heapify reduced heap
            heapify(b, i, 0);
        }
    }


    //private methods ---- including bubbleup, bubbledown and buildheap ----
    private void buildheap(int[] arr)
    {
        if (arr.length > 0)
        {
            for (int i = 0; i < arr.length; i++)
            {
                heapInsert(arr[i]);
            }
        }
    }

    private void bubbleup(int k)
    {
        int parent = k / 2;
        int current = k;
        while (current > 1 && mHeap[parent] > mHeap[current])
        {
            change(current, parent);
            current = parent;
            parent = parent / 2;
        }
    }

    private void bubbledown(int k)
    {
        int smallest = k;
        int leftChildIdx = 2 * k;
        int rightChildIdx = 2 * k + 1;
        if (leftChildIdx < size() && mHeap[smallest] > mHeap[leftChildIdx])
        {
            smallest = leftChildIdx;
        }
        if (rightChildIdx < size() && mHeap[smallest] > mHeap[rightChildIdx])
        {
            smallest = rightChildIdx;
        }
        if (smallest != k)
        {

            change(k, smallest);
            bubbledown(smallest);
        }
    }

    private void change(int a, int b)
    {
        int temp = mHeap[a];
        mHeap[a] = mHeap[b];
        mHeap[b] = temp;
    }

    private static void heapify(int arr[], int n, int i)
    {
        int largest = i;
        int lhs = 2*i + 1;  
        int rhs = 2*i + 2;  

        // if left > root
        if (lhs < n && arr[lhs] > arr[largest])
            largest = lhs;

        // if right > largest
        if (rhs < n && arr[rhs] > arr[largest])
            largest = rhs;
        
        if (largest != i)
        {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            heapify(arr, n, largest);
        }
    }

}

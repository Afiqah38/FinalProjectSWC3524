import java.util.PriorityQueue;
import java.util.Comparator;

public class MinHeap {

    private PriorityQueue<Integer> heap;

    public MinHeap() {
        heap = new PriorityQueue<>();
    }

    public void insert(int value) {
        heap.add(value);
    }

    public int extractMin() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        return heap.poll();
    }

    public int peekMin() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        return heap.peek();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public int size() {
        return heap.size();
    }

    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap();

        minHeap.insert(10);
        minHeap.insert(3);
        minHeap.insert(15);
        minHeap.insert(1);

        System.out.println("Min-Heap Extract Min: " + minHeap.extractMin()); // Output: 1
        System.out.println("Min-Heap Extract Min: " + minHeap.extractMin()); // Output: 3


        System.out.println("Min-Heap Peek Min: " + minHeap.peekMin());   // Output: 10
        System.out.println("Min-Heap Size: " + minHeap.size());           // Output: 1
    }
}
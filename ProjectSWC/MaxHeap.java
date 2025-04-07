import java.util.PriorityQueue;
import java.util.Comparator;

class MaxHeap {

    private PriorityQueue<Integer> heap;

    public MaxHeap() {
        heap = new PriorityQueue<>(Comparator.reverseOrder());
    }

    public void insert(int value) {
        heap.add(value);
    }

    public int extractMax() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        return heap.poll();
    }

     public int peekMax() {
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
        MaxHeap maxHeap = new MaxHeap();

        maxHeap.insert(10);
        maxHeap.insert(3);
        maxHeap.insert(15);
        maxHeap.insert(1);

        System.out.println("Max-Heap Extract Max: " + maxHeap.extractMax()); // Output: 15
        System.out.println("Max-Heap Extract Max: " + maxHeap.extractMax()); // Output: 10

        System.out.println("Max-Heap Peek Max: " + maxHeap.peekMax());  // Output: 3
        System.out.println("Max-Heap Size: " + maxHeap.size());          // Output: 2
    }
}
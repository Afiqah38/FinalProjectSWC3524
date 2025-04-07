import java.util.Arrays;

public class InsertionAndBinarySearch {

    // Insertion Sort
    public static String insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            // Move elements of arr[0..i-1] that are greater than key, 
            // to one position ahead of their current position
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
        return Arrays.toString(arr);
    }

    // Binary Search
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Check if target is present at mid
            if (arr[mid] == target)
                return mid;

            // If target greater, ignore left half
            if (arr[mid] < target)
                left = mid + 1;

            // If target is smaller, ignore right half
            else
                right = mid - 1;
        }

        // if we reach here, then element was not present
        return -1;
    }

    public static void main(String[] args) {
        // Sorting and Searching
        int[] arr = {8, 3, 5, 1, 9, 2};
        System.out.println("Sorted Array: " + insertionSort(arr));
        System.out.println("Binary Search (5 found at index): " + binarySearch(arr, 5));
    }
}
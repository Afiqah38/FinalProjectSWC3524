import java.util.*;
public class GreedyTSP {

    // Distance Matrix
    static int[][] distanceMatrix = {
        {0, 12, 18, 22},  // A 
        {12, 0, 40, 30},  // B
        {18, 40, 0, 35},  // C
        {22, 30, 35, 0}   // D
    };

    
    static String [] locations = 
        {"Warehouse A", "Warehouse B", "Center C", "Center D"};
    

    // Method to solve TSP using Greedy Algorithm (Nearest Neighbor)
    public static void greedyTSP(int start) {
        int n = distanceMatrix.length;
        boolean[] visited = new boolean[n]; // Track visited locations
        int[] route = new int[n + 1]; // Store route (including return to start)
        int currentLocation = start;
        visited[currentLocation] = true;
        route[0] = start;
        int totalDistance = 0;
        
        StringBuilder routePath = new StringBuilder(locations[start]);

        for (int i = 1; i < n; i++) {  
            int nearestLocation = -1;
            int minDistance = Integer.MAX_VALUE;

            // Find the nearest unvisited location
            for (int loc = 0; loc < n; loc++) {
                if (!visited[loc] && distanceMatrix[currentLocation][loc] < minDistance) {
                    nearestLocation = loc;
                    minDistance = distanceMatrix[currentLocation][loc];
                }
            }

            // Move to the nearest location
            if (nearestLocation != -1) {
                visited[nearestLocation] = true;
                route[i] = nearestLocation;
                totalDistance += minDistance;
                routePath.append(" -> ").append(locations[nearestLocation]);
                currentLocation = nearestLocation;
            }
        }

        // Return to the starting location
        totalDistance += distanceMatrix[currentLocation][start];
        route[n] = start;
        routePath.append(" -> ").append(locations[start]);

        // Print results
        System.out.println(routePath + " | Total Cost: " + totalDistance);
    }

    public static void main(String[] args) {
        greedyTSP(0); // Start from Warehouse A (index 0)
    }
}
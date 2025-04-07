import java.util.*;

public class BacktrackingTSP{

    // Distance Matrix (Adjacency Matrix)
    static int[][] distanceMatrix = {
            {0, 12, 18, 22},
            {12, 0, 40, 30},
            {18, 40, 0, 35},
            {22, 30, 35, 0}
    };

    // Location names
    static String[] locations = {"Warehouse A", "Warehouse B", "Center C", "Center D"};

    // Backtracking TSP
    public static int backtrackingTSP(int[][] dist) {
        int n = dist.length;
        boolean[] visited = new boolean[n];
        Arrays.fill(visited, false);
        int startNode = 0;
        visited[startNode] = true;
        int cost = 0;
        int minCost = Integer.MAX_VALUE;

        return tspBacktracking(startNode, dist, visited, n, 1, cost, minCost);
    }

    private static int tspBacktracking(int pos, int[][] dist, boolean[] visited,
                                             int n, int count, int cost, int minCost) {
        if (count == n) {
            int returnDist = dist[pos][0];
            cost += returnDist;
            return Math.min(minCost, cost);
        }

        int minCurrentCost = minCost; // Use a local variable to track the min cost in the current call

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                int currentCost = dist[pos][i];
                int resultCost = tspBacktracking(i, dist, visited, n, count + 1, cost + currentCost, minCurrentCost);
                minCurrentCost = Math.min(minCurrentCost, resultCost);
                visited[i] = false; // Backtrack
            }
        }
        return minCurrentCost;
    }

    public static void main(String[] args) {
        int optimalCost = backtrackingTSP(distanceMatrix);
        System.out.println("Optimal Cost (Backtracking): " + optimalCost);
    }
}
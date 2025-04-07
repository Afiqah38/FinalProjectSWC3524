import java.util.*;

public class DynamicProgrammingTSP {

    // Distance Matrix (Adjacency Matrix)
    static int[][] distanceMatrix = {
            {0, 12, 18, 22},  //A
            {12, 0, 40, 30},  //B
            {18, 40, 0, 35},  //C
            {22, 30, 35, 0}   //D
    };

    // Location names
    static String[] locations = {"Warehouse A", "Warehouse B", "Center C", "Center D"};

    // Dynamic Programming TSP
    public static int dynamicProgrammingTSP(int[][] dist) {
        int n = dist.length;
        int VISITED_ALL = (1 << n) - 1;
        int[][] memo = new int[n][1 << n];
        int startNode = 0;

        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }

        return dynamicProgrammingTSPHelper(startNode, 1, dist, memo, VISITED_ALL);
    }

    private static int dynamicProgrammingTSPHelper(int pos, int mask, int[][] dist,
                                                   int[][] memo, int VISITED_ALL) {
        if (mask == VISITED_ALL) {
            return dist[pos][0];
        }

        if (memo[pos][mask] != -1) {
            return memo[pos][mask];
        }

        int minAns = Integer.MAX_VALUE;
        for (int next = 0; next < dist.length; next++) {
            if ((mask & (1 << next)) == 0) {
                int newAns = dist[pos][next] + dynamicProgrammingTSPHelper(next,
                        mask | (1 << next), dist, memo, VISITED_ALL);
                if (newAns < minAns) {
                    minAns = newAns;
                }
            }
        }
        return memo[pos][mask] = minAns;
    }

    public static void main(String[] args) {
        int optimalCost = dynamicProgrammingTSP(distanceMatrix);
        System.out.println("Optimal Cost (DP) : " + optimalCost);
    }
}
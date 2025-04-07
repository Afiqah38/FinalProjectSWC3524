import java.util.Arrays;

public class DivideAndConquerTSP {

    private static int[][] distanceMatrix;
    private static int numLocations;
    private static int minCost;
    private static int[] bestRoute;
    private static boolean[] visited;

    public DivideAndConquerTSP(int[][] distanceMatrix) {
        DivideAndConquerTSP.distanceMatrix = distanceMatrix;
        DivideAndConquerTSP.numLocations = distanceMatrix.length;
        DivideAndConquerTSP.minCost = Integer.MAX_VALUE;
        DivideAndConquerTSP.bestRoute = new int[numLocations];
        DivideAndConquerTSP.visited = new boolean[numLocations];
    }

    public static String divideAndConquerTSP() {
        int startLocation = 0; // You can choose any starting location
        bestRoute[0] = startLocation;
        visited[startLocation] = true;
        divideAndConquerHelper(startLocation, 1, 0);

        StringBuilder routeString = new StringBuilder();
        for (int i = 0; i < numLocations; i++) {
            routeString.append("Location ").append((char)('A' + bestRoute[i])).append(" -> ");
        }
        routeString.append("Location ").append((char)('A' + bestRoute[0])); // Return to start

        return "Route: " + routeString.toString() + " | Optimal Cost (Divide and Conquer): " + minCost;
    }

    private static void divideAndConquerHelper(int currentLocation, int locationCount, int currentCost) {
        if (locationCount == numLocations) {
            int costToReturn = distanceMatrix[currentLocation][0]; // Cost to return to start
            int totalCost = currentCost + costToReturn;
            if (totalCost < minCost) {
                minCost = totalCost;
                System.arraycopy(bestRoute, 0, bestRoute, 0, numLocations);
            }
            return;
        }

        for (int i = 0; i < numLocations; i++) {
            if (!visited[i]) {
                visited[i] = true;
                bestRoute[locationCount] = i;
                divideAndConquerHelper(i, locationCount + 1, currentCost + distanceMatrix[currentLocation][i]);
                visited[i] = false; // Backtrack
            }
        }
    }

    public static void main(String[] args) {
        int[][] distanceMatrix = {
                {0, 12, 18, 22},
                {12, 0, 40, 30},
                {18, 40, 0, 35},
                {22, 30, 35, 0}
        };

        DivideAndConquerTSP tsp = new DivideAndConquerTSP(distanceMatrix);
        System.out.println(divideAndConquerTSP());
    }
}
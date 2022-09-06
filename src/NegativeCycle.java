import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class NegativeCycle {
	
	private static int lastNodeOnNthRelaxation = -1;
    	
	private static int negativeCycle(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost) {
        // write your code here
    	int n = adj.length;
        int[] distances = new int[n];
        int[] parentsTree = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(parentsTree, -1);
        distances[0] = 0;
        for(int v = 0; v < n; v++) {
        	relaxEdges(adj, cost, distances, parentsTree, v == n-1);
        }
        int x = lastNodeOnNthRelaxation;
        for(int v = 0; v < n; v++) {
        	if(x!=-1) {
        		x = parentsTree[x];
        	}
        	else break;
        }
        int y = x;
        for(int v = 0; v < n; v++) {
        	if(x!=-1) x = parentsTree[x];
        	if(x == y && x!=-1) return 1;
        }
    	return 0;
    }

	private static void relaxEdges(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int[] distances,
			int[] parentsTree, boolean isNthIteration) {
		// TODO Auto-generated method stub
		for(int i = 0; i < adj.length; i++) {
			List<Integer> nodeEdges = adj[i];
			for(int jIdx = 0; jIdx < nodeEdges.size(); jIdx++) {
				int j = nodeEdges.get(jIdx);
				int newCost = parentsTree[i] == -1 ? cost[i].get(jIdx) : distances[i] + cost[i].get(jIdx);
				if(distances[j] > newCost) {
					distances[j] = newCost;
					parentsTree[j] = i;
					if(isNthIteration) {
						lastNodeOnNthRelaxation = j;
					}
				}
			}
 		}
	}

	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            cost[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }
        System.out.println(negativeCycle(adj, cost));
    }
}


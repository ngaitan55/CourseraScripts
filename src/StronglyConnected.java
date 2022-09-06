import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class StronglyConnected {
	static int clock = 0;
    private static int numberOfStronglyConnectedComponents(ArrayList<Integer>[] adj) {
        //write your code here
    	int n = adj.length;
    	boolean[] visited = new boolean[n];
    	int numSCC = 0;
    	int[] graphSinksPostVisit = getReverseGraphSource(adj);
    	for(int i = n - 1; i >= 0; i--) {
    		int next = graphSinksPostVisit[i];
    		if(!visited[next]) {
    			dfs(next, adj, visited);
    			numSCC++;
    		}
    	}
        return numSCC;
    }
    
    private static void dfs(int next, ArrayList<Integer>[] adj, boolean[] visited) {
		// TODO Auto-generated method stub
    	visited[next] = true;
    	ArrayList<Integer> neighbors = adj[next];
    	for(int j:neighbors) {
    		if(!visited[j]) dfs(j, adj, visited);
    	}
    	return;
	}

	private static int[] getReverseGraphSource(ArrayList<Integer>[] adj) {
		// TODO Auto-generated method stub
    	int n = adj.length;
    	int[] postVisit = new int[n];
    	ArrayList<Integer>[] revAdj = (ArrayList<Integer>[])new ArrayList[n];
    	for (int i = 0; i < n; i++) {
    		revAdj[i] = new ArrayList<Integer>();
        }
    	for(int i = 0; i < n; i++) {
    		ArrayList<Integer> edges = adj[i];
    		for(int e:edges) {
    			revAdj[e].add(i);
    		}
    	}
    	boolean[] visitedRev = new boolean[n];
		for(int i = 0; i < n; i++) {
	    	if(!visitedRev[i]) dfsAndGetPostvisit(i, revAdj, visitedRev, postVisit);
		}
		return postVisit;
	}

	private static void dfsAndGetPostvisit(int i, ArrayList<Integer>[] adj, boolean[] visited, int[] postVisit) {
    	visited[i] = true;
    	ArrayList<Integer> neighbors = adj[i];
    	for(int j:neighbors) {
    		if(!visited[j]) dfsAndGetPostvisit(j, adj, visited, postVisit);
    	}
    	postVisit[clock] = i;
    	clock++;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
        }
        System.out.println(numberOfStronglyConnectedComponents(adj));
    }
}


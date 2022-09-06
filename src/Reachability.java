import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Reachability {
    private static int reach(ArrayList<Integer>[] adj, int x, int y) {
        int[] components = new int[adj.length];
        boolean[] visited = new boolean[adj.length];
        int c = 0;
        for(int n = 0; n < adj.length; n++) {
        	if(!visited[n]) BFSAndCluster(adj, visited, components, n, ++c);
        	if(visited[x] && visited[y]) return yReachableFromX(components, x,y);
        }
        return 0;
    }
    
    private static int yReachableFromX(int[] components, int x, int y) {
    	return components[x] == components[y] ? 1:0;
    }
    
    private static void BFSAndCluster(ArrayList<Integer>[] adj, boolean[] visited, int[] components, int n, int c) {
    	Queue<Integer> queue = new LinkedList<>();
    	queue.add(n);
		visited[n] = true;
    	while(!queue.isEmpty()) {
    		int i = queue.remove();
    		components[i] = c;
    		List<Integer> edges = adj[i];
    		for(int e : edges) {
    			if(!visited[e]) {
    				components[e] = c;
    				queue.add(e);
    				visited[e] = true;
    			}
    		}
    	}
    }


    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
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
			    adj[y - 1].add(x - 1);
			}
			int x = scanner.nextInt() - 1;
			int y = scanner.nextInt() - 1;
			System.out.println(reach(adj, x, y));
		}
    }
}


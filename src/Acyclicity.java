import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Stream;

public class Acyclicity {
    private static int acyclic(ArrayList<Integer>[] adj) {
        //write your code here
    	int n = adj.length;
    	boolean [] visited = new boolean[n];
    	boolean[] recStack = new boolean[n];
        for(int i = 0; i < n;i++) {
        	if(visited[i]) continue;
        	if(DFSToCheckCycles(adj, visited, recStack, i)) return 1;
        }
    	return 0;
    }
    
    private static boolean DFSToCheckCycles(ArrayList<Integer>[] adj, boolean[] visited, boolean[] recStack, int i) {
    	visited[i] = true;
    	List<Integer> neighbors = adj[i];
    	recStack[i] = true;
    	for(int j : neighbors) {
    		if(!visited[j] && DFSToCheckCycles(adj, visited, recStack, j)) return true;
    		else if (recStack[j]) return true;
    	}
    	recStack[i] = false;
    	return false;
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
        System.out.println(acyclic(adj));
    }
}

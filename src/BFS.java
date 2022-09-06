import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class BFS {
    private static int distance(ArrayList<Integer>[] adj, int s, int t) {
        //write your code here
        return BFSAndGetDistance(adj, s, t);
    }
   

    private static int BFSAndGetDistance(ArrayList<Integer>[] adj, int s, int t) {
		// TODO Auto-generated method stub
    	int n = adj.length;
    	int[] distanceLayers = new int[n];
    	boolean[] visited = new boolean[n];
    	Queue<Integer> queue = new LinkedList<>();
    	queue.add(s);
    	visited[s] = true;
    	distanceLayers[s] = 0; 
    	while(!queue.isEmpty()) {
    		int i = queue.remove();
    		List<Integer> neighbors = adj[i];
    		for(int j : neighbors) {
    			if(!visited[j]) {
    				queue.add(j);
    				visited[j] = true;
        			distanceLayers[j] = distanceLayers[i] + 1;
        			if(j==t) return distanceLayers[j];
    			}
    		}
    	}
		return -1;
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
            adj[y - 1].add(x - 1);
        }
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, x, y));
    }
}


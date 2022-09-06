import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Bipartite {
    
	public static final int COLOR_UNDETERMINED = 0;
	public static final int COLOR_WHITE = 1;
	public static final int COLOR_BLACK = 2;
	
	private static int bipartite(ArrayList<Integer>[] adj) {
        //write your code here
		int n = adj.length;
		int[] colors = new int[n];
		boolean isBarpitite = false;
        for(int i = 0; i < adj.length; i++) {
        	if(colors[i] == COLOR_UNDETERMINED) isBarpitite = isBipartiteBFS(adj, colors, i);
        	if(!isBarpitite) return 0;
        }
        return isBarpitite ? 1:0;
    }

    private static boolean isBipartiteBFS(ArrayList<Integer>[] adj, int[] colors, int begin) {
		// TODO Auto-generated method stub
    	boolean isBipartite = true;
    	Queue<Integer> queue = new LinkedList<>();
    	queue.add(begin);
    	colors[begin] = COLOR_WHITE;
    	while(!queue.isEmpty()) {
    		int i = queue.remove();
    		List<Integer> neighbors = adj[i];
    		for(int j : neighbors) {
    			if(colors[j] == COLOR_UNDETERMINED) {
    				queue.add(j);
    				colors[j] = (colors[i] == COLOR_WHITE) ? COLOR_BLACK : COLOR_WHITE;
    			}
    			else if(colors[i] == colors[j]) isBipartite = false;
    		}
    	}
		return isBipartite;
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
        System.out.println(bipartite(adj));
    }
}


import java.util.*;

public class ShortestPaths {

    private static void shortestPaths(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, long[] distance,
    		int[] reachable, int[] shortest) {
      //write your code here
    	int n = adj.length;
    	int[] parentsTree = new int[n];
    	Arrays.fill(parentsTree, -1);
    	distance[s] = 0;
    	/**for(int v = 0; v < n; v++) {
    		BFSCycle(s, adj, cost, distance, parentsTree, reachable);
    	}**/
		BFSCycle(s, adj, cost, distance, parentsTree, reachable);
    	checkCycles(parentsTree, reachable, s, shortest);
    }

    private static void BFSCycle(int s, ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, long[] distance,
			int[] parentsTree, int[] reachable) {
		// TODO Auto-generated method stub
		int n = adj.length;
    	boolean[] enqueue = new boolean[adj.length];
		Queue<Integer> queue = new LinkedList<>();
		queue.add(s);
		enqueue[s] = true;
		reachable[s] = 1;
    	int nIter = 0;
		while(!queue.isEmpty() && nIter < n) {
			int u = queue.remove();
			List<Integer> nextNodes = adj[u];
			enqueue[u] = false;
			//System.out.println("size=" + nextNodes.size());
			for(int j = 0; j < nextNodes.size(); j++) {
				int v = nextNodes.get(j);
				reachable[v] = 1;
				//System.out.println("v=" + v + " reach=" + reachable[v]);
				long nextDistance = distance[u] + cost[u].get(j);
				if(distance[v] > nextDistance) {
					distance[v] = nextDistance;
					//System.out.println(distance[v]);
					parentsTree[v] = u;
					if(!enqueue[v]) {
						queue.add(v);
						enqueue[v] = true;
					}
				}
			}
			nIter++;
		}
		
	}

	private static void checkCycles(int[] parentsTree, int[] reachable, int s, int[] shortest) {
		// TODO Auto-generated method stub
    	for(int v = 0; v < reachable.length; v++) {
    		if(reachable[v] != 1) continue;
    		if(conformsCycle(v, parentsTree, reachable.length)) {
    			shortest[v] = 0;
    		}
    	}
	}

	private static boolean conformsCycle(int x, int[] parentsTree, int length) {
		// TODO Auto-generated method stub
		for(int v = 0; v < length; v++) {
        	if(x!=-1) {
        		x = parentsTree[x];
        	}
        	else break;
        }
        int y = x;
		if (y==-1) return false;
        for(int v = 0; v < length; v++) {
        	if(x!=-1) x = parentsTree[x];
        	if(x == y && x!=-1) {
        		return true;
        	}
        }
        return false;
	}
	/**
	private static void checkReachability(int[] parentsTree, int[] reachable, int s) {
		// TODO Auto-generated method stub
    	for(int v = 0; v < reachable.length; v++) {
    		if(v==s) {
    			reachable[v] = 1;
    			continue;
    		}
    		int i = 0;
    		int x = parentsTree[v];
    		while(x!=-1 && i < reachable.length) {
    			if(x==s) {
    				reachable[v] = 1;
    				break;
    			}
    			x = parentsTree[x];
    			i++;
    		}
    	}
	}

	private static void iterateEdges(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, long[] distance, int[] parentsTree) {
		// TODO Auto-generated method stub
		for(int u = 0; u < adj.length; u++) {
			List<Integer> nextEdges = adj[u];
			for(int j = 0; j < nextEdges.size(); j++) {
				int v = nextEdges.get(j);
				long newCost = distance[u] == Long.MAX_VALUE ? cost[u].get(j) : distance[u] + cost[u].get(j);
				if(distance[v] > newCost) {
					distance[v] = newCost;
					parentsTree[v] = u;
				}
			}
		}
	}
	**/
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
        int s = scanner.nextInt() - 1;
        long distance[] = new long[n];
        int reachable[] = new int[n];
        int shortest[] = new int[n];
        for (int i = 0; i < n; i++) {
            distance[i] = Long.MAX_VALUE;
            reachable[i] = 0;
            shortest[i] = 1;
        }
        shortestPaths(adj, cost, s, distance, reachable, shortest);
        for (int i = 0; i < n; i++) {
            //System.out.println(i + " reach=" + reachable[i]);
            //System.out.println(i + " shortest=" + shortest[i]);
        	if (reachable[i] == 0) {
                System.out.println('*');
            } else if (shortest[i] == 0) {
                System.out.println('-');
            } else {
                System.out.println(distance[i]);
            }
        }
    }
}


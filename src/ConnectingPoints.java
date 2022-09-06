import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ConnectingPoints {
		
	private static double minimumDistance(int[] x, int[] y) {
        //write your code here
        int nNodes = x.length;
        int nEdges = (int) (nNodes*(nNodes+1))/2 - x.length;
    	int[] tree = new int[nNodes];
    	//for(int i = 0; i < nNodes; i++) tree[i] = i;
    	Arrays.fill(tree, -1);
		Edge[] edges = new Edge[nEdges];
        int e = 0;
        for(int i = 0; i < nNodes; i++) {
        	for(int j = i; j < nNodes; j++) {
        		if(i == j) continue;
        		edges[e] = new Edge(i, j, getEuclideanDistance(x[i], x[j], y[i], y[j]));
            	e++;
        	}
        }
        List<Edge> mst = kruskall(edges, tree);
		double answer = sumDistances(mst);
        return answer;
    }
    
    private static double sumDistances(List<Edge> mst) {
		// TODO Auto-generated method stub
		double sum = 0;
    	for(Edge e : mst) {
			sum += e.getDistance();
		}
    	return sum;
	}

	private static List<Edge> kruskall(Edge[] edges, int[] tree) {
		// TODO Auto-generated method stub
    	List<Edge> mst = new ArrayList<>();
        Arrays.sort(edges, Comparator.comparingDouble(e -> e.getDistance()));
        for(Edge e : edges) {
        	int u = e.getU();
        	int v = e.getV();
        	if(find(tree, u) != find(tree, v)) {
        		tree[find(tree, v)] = find(tree, u);
        		mst.add(e);
        	}
        }
		return mst;
	}

	private static int find(int[] tree, int u) {
		int parent = tree[u];
		while(parent!=-1){
			u = parent;
			parent = tree[u];
		}
		return u;
	}

	public static double getEuclideanDistance(int x1, int x2, int y1, int y2) {
    	double xDist = Math.pow(Math.abs(x2-x1), 2);
    	double yDist = Math.pow(Math.abs(y2-y1), 2);
    	return Math.sqrt(xDist + yDist);
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextInt();
            y[i] = scanner.nextInt();
        }
        System.out.format("%.9f", minimumDistance(x, y));
		System.out.println();
    }
}
class Edge {
	   int u;
	   int v;
	   double distance;
	   
	   public int getU() {
		return u;
	}

	public int getV() {
		return v;
	}

	public double getDistance() {
		return distance;
	}

	public Edge(int u, int v, double distance) {
		   this.u = u;
		   this.v = v;
		   this.distance = distance;
	   }
}


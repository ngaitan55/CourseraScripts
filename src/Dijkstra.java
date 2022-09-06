import java.util.*;

public class Dijkstra {
    private static long distance(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, int t) {
    	int n = adj.length;
    	OrderedArrayHeap priorityQueue = new OrderedArrayHeap(n);
    	int[] distances = new int[n];
    	int[] parentsTree = new int[n];
    	Arrays.fill(distances, Integer.MAX_VALUE);
    	Arrays.fill(parentsTree, -1);
    	distances[s] = 0;
    	priorityQueue.changePriority(s, 0);
    	while(!priorityQueue.isEmpty()) {
        	Node next = priorityQueue.dequeue();
        	int i = next.getIdx();
        	List<Integer> neighbors = adj[i];
        	for(int jListIdx = 0; jListIdx < neighbors.size(); jListIdx++) {
            	int j = neighbors.get(jListIdx);
        		int newCost = distances[i] + cost[i].get(jListIdx);
        		if(distances[j] > newCost) {
        			distances[j] = newCost;
        			parentsTree[j] = i;
        			priorityQueue.changePriority(j, newCost);
        		}
        	}
    	}
    	int p = parentsTree[t];
    	while(p!=-1) {
    		if(p==s) return distances[t];
    		p = parentsTree[p];
    	}
    	return -1;
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
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, cost, x, y));
    }
}

class OrderedArrayHeap {
	Node[] priorityQueue;
	Node[] nodesByIdx;
	boolean hasChanged;
	
	OrderedArrayHeap(int n){
		initializeNodes(n);
		hasChanged = false;
	}
	
	private void initializeNodes(int n) {
		// TODO Auto-generated method stub
		this.priorityQueue = new Node[n];
		this.nodesByIdx = new Node[n];
		for(int i = 0; i < n; i++) {
			Node newNode = new Node(i, Integer.MAX_VALUE);
			priorityQueue[i] = newNode;
			nodesByIdx[i] = newNode;
		}
	}
	
	public void changePriority(int idx, int newKey) {
		nodesByIdx[idx].setKey(newKey);
		sort();
		hasChanged = true;
	}

	public Node dequeue() {
		Node answer = priorityQueue[0];
		answer.setKey(Integer.MAX_VALUE);
		sort();
		return answer;
	}
	
	public void sort() {
		Arrays.sort(priorityQueue, (u,v) -> u.getKey() - v.getKey());
	}
	
	public boolean isEmpty() {
		return hasChanged && priorityQueue[0].getKey() == Integer.MAX_VALUE;
	}
}


class Node{
	int idx;
	int key;
	
	Node(int idx, int firstKey){
		this.idx = idx;
		this.key = firstKey;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}
}


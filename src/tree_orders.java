import java.util.*;
import java.io.*;

public class tree_orders {
    class FastScanner {
		StringTokenizer tok = new StringTokenizer("");
		BufferedReader in;

		FastScanner() {
			in = new BufferedReader(new InputStreamReader(System.in));
		}

		String next() throws IOException {
			while (!tok.hasMoreElements())
				tok = new StringTokenizer(in.readLine());
			return tok.nextToken();
		}
	
		int nextInt() throws IOException {
			return Integer.parseInt(next());
		}
	}

	public class TreeOrders {
		int n;
		int[] key, left, right;
		
		void read() throws IOException {
			FastScanner in = new FastScanner();
			n = in.nextInt();
			key = new int[n];
			left = new int[n];
			right = new int[n];
			for (int i = 0; i < n; i++) { 
				key[i] = in.nextInt();
				left[i] = in.nextInt();
				right[i] = in.nextInt();
			}
		}

		List<Integer> inOrder() {
			ArrayList<Integer> result = new ArrayList<Integer>();
                        // Finish the implementation
                        // You may need to add a new recursive method to do that
            Stack<Integer> stack = new Stack<>();
            int root = 0;
            while(!stack.isEmpty() || root != -1) {
            	while(root != -1) {
            		stack.push(root);
            		root = left[root];
            	}
            	root = stack.pop();
            	result.add(key[root]);
            	root = right[root];
            }
			return result;
		}

		List<Integer> preOrder() {
			ArrayList<Integer> result = new ArrayList<Integer>();
                        // Finish the implementation
                        // You may need to add a new recursive method to do that
			Stack<Integer> stack = new Stack<>();
            int root = 0;
			stack.add(root);
            while(!stack.isEmpty()) {
            	root = stack.pop();
            	result.add(key[root]);
            	if(right[root] != -1) stack.push(right[root]);
            	if(left[root] != -1) stack.push(left[root]);
            }         
			return result;
		}

		List<Integer> postOrder() {
			ArrayList<Integer> result = new ArrayList<Integer>();
                        // Finish the implementation
                        // You may need to add a new recursive method to do that
			Stack<Integer> stack = new Stack<>();
			Stack<Integer> resultStack = new Stack<>();
            int root = 0;
			stack.add(root);
			while(!stack.isEmpty()) {
				root = stack.pop();
				resultStack.push(key[root]);
				if(left[root] != -1) stack.push(left[root]);
				if(right[root] != -1) stack.push(right[root]);
			}
			while(!resultStack.isEmpty()) {
				result.add(resultStack.pop());
			}
			return result;
		}
	}

	static public void main(String[] args) throws IOException {
            new Thread(null, new Runnable() {
                    public void run() {
                        try {
                            new tree_orders().run();
                        } catch (IOException e) {
                        }
                    }
                }, "1", 1 << 26).start();
	}

	public void print(List<Integer> x) {
		for (Integer a : x) {
			System.out.print(a + " ");
		}
		System.out.println();
	}

	public void run() throws IOException {
		TreeOrders tree = new TreeOrders();
		tree.read();
		print(tree.inOrder());
		print(tree.preOrder());
		print(tree.postOrder());
	}
}

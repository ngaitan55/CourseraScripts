import java.util.*;
import java.io.*;

public class is_bst_hard {
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

    public class IsBST {
        class Node {
            int key;
            int left;
            int right;

            Node(int key, int left, int right) {
                this.left = left;
                this.right = right;
                this.key = key;
            }
        }

        int nodes;
        Node[] tree;

        void read() throws IOException {
            FastScanner in = new FastScanner();
            nodes = in.nextInt();
            tree = new Node[nodes];
            for (int i = 0; i < nodes; i++) {
                tree[i] = new Node(in.nextInt(), in.nextInt(), in.nextInt());
            }
        }

        boolean isBinarySearchTree() {
          // Implement correct algorithm here
        	if(tree.length <= 1) return true;
        	Stack<Integer> stack = new Stack<>();
        	int root = 0;
        	int previous = Integer.MIN_VALUE;
        	int previousIdx = -1;
        	while(!stack.isEmpty() || root != -1) {
        		while(root != -1) {
                	stack.push(root);
        			root = tree[root].left;
        		}
        		root = stack.pop();
        		if(root == -1) break;
        		//System.out.println(root + " " + previousIdx + " " + tree[root].left);
        		if(previous < tree[root].key) {
        			previous = tree[root].key;
        			previousIdx = root;
        		}
        		else if(previous == tree[root].key && (previousIdx == -1 || tree[root].left != previousIdx)) {
        			previous = tree[root].key;
        			previousIdx = root;
        		}
        		else return false;
        		root = tree[root].right;
        		
        	}
        	return true;
        }
    }

    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new is_bst_hard().run();
                } catch (IOException e) {
                }
            }
        }, "1", 1 << 26).start();
    }
    public void run() throws IOException {
        IsBST tree = new IsBST();
        tree.read();
        if (tree.isBinarySearchTree()) {
            System.out.println("CORRECT");
        } else {
            System.out.println("INCORRECT");
        }
    }
}

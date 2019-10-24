package eight_puzzle;

import java.util.ArrayList;

public class Node implements Comparable<Node>{

	private int f;
	private int level;

	public String[][] input_state;
	
	public Node(String[][] a, int level) {
		int N = a.length;
		this.input_state = new String[N][N];
		
		// copy the input data to input matrix
		for (int i = 0; i <  N; i++) {
			for (int j = 0; j < N; j++) {
				this.input_state[i][j] = a[i][j];
			}
		}
		this.level = level;
		this.f = manhatten() + level;   // main f(x) = h(x) + g(x)
	}
	
	// find the index of particular element in the goal state 
	public int[] find_index(int element) {
		int index[] = new int[2];
		int N = Solution.goal_state.length;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(Solution.goal_state[i][j].isEmpty()) {
					continue;
				}
				if(Solution.goal_state[i][j].trim().equals(String.valueOf(element))) {
					index[0] = i;
					index[1] = j;
					return index;
				}
				
			}
		}
		return index;
	}
  
	// calculate manhatten distance
	private int manhatten() {
	    int sum = 0;
	    int N = Solution.goal_state.length;
	    int[] index = new int[2];
	     
	    // finding manhatten distance
	    for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				
				if(input_state[i][j].trim().isEmpty()) {
					continue;
				}
				index = find_index(Integer.parseInt(this.input_state[i][j]));
				sum += (Math.abs(i-index[0]) + Math.abs(j-index[1]));
			}
		}
		return sum;
	}

	// swap the tiles in puzzle
	private String[][] swap(String[][] str, int r1, int c1, int r2, int c2){
		String[][] copy = str;
		String temp = copy[r1][c1];
		copy[r1][c1] = copy[r2][c2];
		copy[r2][c2] = temp;
		return copy;
	}
	
	// expand the current node 
	public ArrayList<Node> expand (Node parent) {
		ArrayList<Node> successor = new ArrayList<Node>();
		int N = this.input_state.length;
		
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
				
					if(parent.input_state[i][j].trim().isEmpty()) {
						// four conditions for blank tile to move in four directions
						
						if(i-1 >= 0) {
							String[][] a = new String[N][N];
							
							for (int k = 0; k < N; k++) {
								for (int l = 0; l < N; l++) {
									a[k][l] = parent.input_state[k][l];
								}
							}
							a = swap(a, i, j, i-1, j);
							Node b = new Node(a, parent.level+1);
							successor.add(b);
						}
						if(j-1 >= 0) {
							String[][] a = new String[N][N];
							
							for (int k = 0; k < N; k++) {
								for (int l = 0; l < N; l++) {
									a[k][l] = parent.input_state[k][l];
								}
							}
							a = swap(a, i, j, i, j-1);
							Node b = new Node(a, parent.level+1);
							successor.add(b);
						}
						if(j+1 < N) {
							String[][] a = new String[N][N];
							
							for (int k = 0; k < N; k++) {
								for (int l = 0; l < N; l++) {
									a[k][l] = parent.input_state[k][l];
								}
							}
							a = swap(a, i, j, i, j+1);
							Node b = new Node(a, parent.level+1);
							successor.add(b);
						}
						if(i+1 < N) {
							String[][] a = new String[N][N];
							
							for (int k = 0; k < N; k++) {
								for (int l = 0; l < N; l++) {
									a[k][l] = parent.input_state[k][l];
								}
							}
							a = swap(a, i, j, i+1, j);
							Node b = new Node(a, parent.level+1);
							successor.add(b);
						}
					}
				
			}
		}
		return successor;
	}
	// sorting technique 
	@Override
	public int compareTo(Node o) {
		if(this.f == o.f) {
			return (this.manhatten() - o.manhatten());
		}
		return this.f - o.f;
	}
	
	public int getF() {
		return f;
	}

	public void setF(int f) {
		this.f = f;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String[][] getInput_state() {
		return input_state;
	}

	public void setInput_state(String[][] input_state) {
		this.input_state = input_state;
	}

}

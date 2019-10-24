package eight_puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Solution {
    static int N = 3;  // puzzle size
    public static String[][] goal_state;
    public static PriorityQueue<Node> pq = new PriorityQueue<Node>();
    public static ArrayList<Node> expanded_states = new ArrayList<Node>();  // stores info of expanded states
    
    public Solution(Node first_node) {
    	if(first_node == null) {
    		System.out.println("Please, provide the input state");
    	}
    	// first_node is not null then add it to pq
    	pq.add(first_node);
    	ArrayList<Node> list = new ArrayList<Node>();
    	
    	while(!pq.isEmpty()) {
    		int visited;
    		Node current = pq.poll();
    	    expanded_states.add(current);
    	    
    	    if(Arrays.deepEquals(current.input_state,goal_state)) {
    	    	break;
    	    }
    	    
    	    list = current.expand(current);
    	    
    	    for(Node l: list) {
    	    	visited = 0;
    	    	for(Node e: expanded_states) {
    	    		if(Arrays.deepEquals(l.input_state, e.input_state)) {
    	    			visited = 1;
    	    		}
    	    	}
    	    	if(visited == 1) {
    	    		continue;
    	    	}
    	    	pq.add(l);
    	    }
    	}
    }
	public static void main(String[] args) {
		
		// input matrix for input state
		String[][] input_state = new String[N][N];
		
		// goal state 
		 goal_state = new String[N][N];
		Scanner in = new Scanner(System.in);
		
		System.out.println("Enter the input state for 8-puzzle problem");
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				input_state[i][j] = in.nextLine();
				
				/*if(input_state[i][j].length() > 2 || input_state[i][j].charAt(0)<'1' &&  input_state[i][j].charAt(0)<' '||  input_state[i][j].charAt(0)>'15')
				{
					System.out.println("Error: Input should be any number between 1 to 8 or a single space\nProgram Terminated");
					return;
				}*/
			}
		}
		// printing the input_state
		System.out.println("Input state");
		System.out.println(" -------------");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(" | " + input_state[i][j]);
				
			}
			System.out.print(" |");
			System.out.println("\n -------------");
		}
		
     System.out.println("Enter the goal state for 8-puzzle problem");
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				goal_state[i][j] = in.nextLine();
				
				/*if(goal_state[i][j].length() != 1 || goal_state[i][j].charAt(0)<'1' &&  goal_state[i][j].charAt(0)<' '||  goal_state[i][j].charAt(0)>'8')
				{
					System.out.println("Error: Input should be any number between 1 to 8 or a single space\nProgram Terminated");
					return;
				}*/
			}
		}
		// printing the goal state
		System.out.println("Goal state ");
		System.out.println(" -------------");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(" | " + goal_state[i][j]);
				
			}
			System.out.print(" |");
			System.out.println("\n -------------");
		}
		// start the timer to calculate execution time
		long startTime = System.currentTimeMillis();
		
		// Now call the state function
		
		Node node = new Node(input_state, 0);
		new Solution(node);
		
		for(Node child_nodes : expanded_states) {
			System.out.println(" -------------");
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(" | " + child_nodes.input_state[i][j]);
					
				}
				System.out.print(" |");
				System.out.println("\n -------------");
			}
			System.out.println("f(n) = " + child_nodes.getF() );
			System.out.println("h(n) = " + (child_nodes.getF() - child_nodes.getLevel()));
		    System.out.println("g(n) = " + child_nodes.getLevel());
		    System.out.println();
		}
		
		System.out.println("Total nodes expanded : " + expanded_states.size());
	    System.out.println("Total nodes generated : " + (expanded_states.size()+pq.size()));
	    
	    long endTime = System.currentTimeMillis();
	    System.out.println("execution Time :" + (endTime-startTime));
		
		
		
	}

}

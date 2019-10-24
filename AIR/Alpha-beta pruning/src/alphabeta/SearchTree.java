package alphabeta;

import java.util.HashMap;

import alphabeta.Node.MinMax;

public class SearchTree {
	public HashMap<String, Node> gameTree = new HashMap<String, Node>();

	public SearchTree(){
		// Depth: 0, MAX
		Node A0 = new Node("A0", MinMax.MAX);
		// Depth: 1, MIN
		Node B0 = new Node("B0", MinMax.MIN);
		Node B1 = new Node("B1", MinMax.MIN);
		// Depth: 2, MAX (except one leaf node)
		Node C0 = new Node("C0", MinMax.MAX);
		Node C1 = new Node("C1", MinMax.MAX); // leaf
		Node C2 = new Node("C2", MinMax.MAX);
		Node C3 = new Node("C3", MinMax.MAX);

		// Depth: 3, MIN (except two leaf nodes)
		Node D0 = new Node("D0", 3);
		Node D1 = new Node("D1", -2);
		Node D2 = new Node("D2", 1);
		Node D3 = new Node("D3", -6);
		Node D4 = new Node("D4", 1);
		Node D5 = new Node("D5",4);
		Node D6 = new Node("D6",2); // leaf
		Node D7 = new Node("D7", 7);
		
		// Make connections
		A0.addChild(B0); A0.addChild(B1);
		B0.addChild(C0); B0.addChild(C1);
		B1.addChild(C2); B1.addChild(C3); //B1.addChild(C4);
		C0.addChild(D0); C0.addChild(D1); C1.addChild(D2);
		C1.addChild(D3); C2.addChild(D4);
		C2.addChild(D5); C3.addChild(D6);
		C3.addChild(D7);
		
		gameTree.put("A0", A0);
		gameTree.put("B0", B0); gameTree.put("B1", B1);
		gameTree.put("C0", C0); gameTree.put("C1", C1); gameTree.put("C2", C2);
		gameTree.put("C3", C3); 
		gameTree.put("D0", D0); gameTree.put("D1", D1); gameTree.put("D2", D2);
		gameTree.put("D3", D3); gameTree.put("D4", D4); gameTree.put("D5", D5);
		gameTree.put("D6", D6); gameTree.put("D7", D7); 
		
	}

	public HashMap<String, Node> getTree(){
		return gameTree;
	}
}

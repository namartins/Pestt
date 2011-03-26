package pt.ul.fc.di.gloss.pestt.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import pt.ul.fc.di.gloss.pestt.adts.DAG;
import pt.ul.fc.di.gloss.pestt.adts.Edge;
import pt.ul.fc.di.gloss.pestt.adts.Node;

public class CFG {
	// há-de ser uma classe linda que obtém 
	// informação a partir da AST do Java
	// para já é um main com os nós martelados 
	// à mão...
	
	private DAG<String> dag = new DAG<String>();
	
	private void fill () {
		// adiciona um metanível para um CFG
		int cfgLayer = dag.addMetadataLayer();
		
		// diamond
		Node<String> n1 = dag.addInitialNode("1");
		dag.addMetadata(n1, "x = 1");
		Node<String> n2 = dag.addNode("2");
		dag.addEdge(n1, n2);
		
		Node<String> n3 = dag.addNode("3");
		Edge<Node<String>> e23 = dag.addEdge(n2, n3);
		dag.addMetadata(e23, "numPrimes < n");
		
		Node<String> n10 = dag.addNode("10");
		Edge<Node<String>> e210 = dag.addEdge(n2, n10);
		dag.addMetadata(e210, "numPrimes >= n");
		
		Node<String> n4 = dag.addFinalNode("4");
		dag.addEdge(n3, n4);
		Node<String> n5 = dag.addFinalNode("5");
		Edge<Node<String>> e45 = dag.addEdge(n4, n5);
		dag.addMetadata(e45, "i<=numPrimes-1");
		Node<String> n7 = dag.addFinalNode("7");
		Edge<Node<String>> e57 = dag.addEdge(n5, n7);
		dag.addMetadata(e57, "!isDivisible");
		Edge<Node<String>> e74 = dag.addEdge(n7, n4);
		
		Node<String> n8 = dag.addFinalNode("8");
		Edge<Node<String>> e48 = dag.addEdge(n4, n8);
		dag.addMetadata(e48, "i>numPrimes-1");
		
		dag.addEdge(n8, n2);
	}
	
	public Collection<Node<String>> getNodes () {
		return dag.getNodes();
	}
	
	public CFG () {
		fill();
	}

	public Set<Node<String>> getDescendents(Node<String> node) {
		Set<Edge<Node<String>>> nodeEdges = dag.getNodeEdges(node);
		HashSet<Node<String>> descendents = new HashSet<Node<String>> ();
		for (Edge<Node<String>> e : nodeEdges) 
			descendents.add(e.getRightNode());
		return descendents;
	}

	public String getMetadata(Edge<Node<String>> edge) {
		return (String) dag.getMetadata(edge);
	}

	public Edge<Node<String>> getEdge(Node<String> source, Node<String> dest) {
		Set<Edge<Node<String>>> nodeEdges = dag.getNodeEdges(source);
		for (Edge<Node<String>> e : nodeEdges) 
			if (e.getRightNode()==dest)
				return e;
		return null;
	}
} 

package pt.ul.fc.di.gloss.pestt.adts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DAG<V> {
	private List<Node<V>> nodes;
	private Map<Node<V>, Set<Edge<Node<V>>>> edges;
	private Set<Node<V>> initialNodes;
	private Set<Node<V>> finalNodes;
	private List<GraphMetadataLayer> metadataLayers;
	private int currentLayer;
	
	public DAG () {
		nodes = new LinkedList<Node<V>> ();
		edges = new HashMap<Node<V>, Set<Edge<Node<V>>>> ();
		initialNodes = new HashSet<Node<V>> ();
		finalNodes = new HashSet<Node<V>> ();
		metadataLayers = new ArrayList<GraphMetadataLayer> ();
	}
	
	public Node<V> addNode (V value) {
		Node<V> node = new Node<V> (value);
		addNode (node);
		return node;
	}
	
	public void addNode (Node<V> n) {
		nodes.add(n);
		edges.put(n, new HashSet<Edge<Node<V>>>());
	}
	
	public void addInitialNode(Node<V> node) {
		addNode(node);
		initialNodes.add(node);
	}
	
	public Node<V> addInitialNode(V value) {
		Node<V> node = addNode(value);
		initialNodes.add(node);
		return node;
	}
	
	public Node<V> addFinalNode(V value) {
		Node<V> node = addNode (value);
		finalNodes.add(node);
		return node;
	}
	
	public void addEdge (Edge<Node<V>> e) {
		getNodeEdges(e.getLeftNode()).add (e);
	}
	
	public Edge<Node<V>> addEdge (Node<V> from, Node<V> to) {
		Edge<Node<V>> edge = new Edge<Node<V>> (from, to);
		addEdge (edge);
		return edge;
	}

	public Set<Edge<Node<V>>> getNodeEdges(Node<V> node) {
		Set<Edge<Node<V>>> nodeEdges = edges.get(node);
		if (nodeEdges == null) {
			addNode (node);
			nodeEdges = edges.get(node);
		}
		return nodeEdges;
	}
	
	public void removeEdge (Edge<Node<V>> e) {
		getNodeEdges(e.getLeftNode()).remove(e);
	}
	
	public void removeNode (V n) {
		nodes.remove(n);
		initialNodes.remove(n);
		finalNodes.remove(n);
		edges.remove(n);
	}
	
	public boolean containsNode (Node<V> n) {
		return nodes.contains(n);
	}
	
	public boolean containsEdge (Edge<Node<V>> e) {
		return getNodeEdges(e.getLeftNode()).contains(e);
	}
	
	public int addMetadataLayer() {
		metadataLayers.add(new GraphMetadataLayer ());
		currentLayer = metadataLayers.size() - 1;
		return currentLayer;
	}
	
	public void selectMetadataLayer (int i) {
		currentLayer = i;
	}
	
	public void addMetadata (Node<V> node, Object data) {
		getCurrentLayer().addMetadata(node, data);
	}
	
	public void addMetadata(Edge<Node<V>> edge, Object data) {
		getCurrentLayer().addEdgeMetadata(edge, data);
	}
	
	public Object getMetadata (Node<?> node) {
		return getCurrentLayer().getMetadata(node);
	}
	
	public Object getMetadata (Edge<?> edge) {
		return getCurrentLayer().getMetadata(edge);
	}
	
	private GraphMetadataLayer getCurrentLayer() {
		return metadataLayers.get(currentLayer);
	}
	
	public Collection<Node<V>> getNodes() {
		return nodes;
	}

}

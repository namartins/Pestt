package pt.ul.fc.di.gloss.pestt.adts;

public class Edge<N> {

	private N leftNode;
	private N rightNode;
		
	public Edge(N left, N right) {
		this.leftNode = left;
		this.rightNode = right;
	}
		
	public N getLeftNode() {
		return leftNode;
	}
	
	public N getRightNode() {
		return rightNode;
	}
		
}
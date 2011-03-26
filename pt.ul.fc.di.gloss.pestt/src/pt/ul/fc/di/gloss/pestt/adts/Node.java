package pt.ul.fc.di.gloss.pestt.adts;

public class Node<V> {
	private V value;
	
	public Node (V value) {
		this.value = value;
	}
	
	public V getValue () {
		return value;
	}
}

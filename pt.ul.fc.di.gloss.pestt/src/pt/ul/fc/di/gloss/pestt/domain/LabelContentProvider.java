package pt.ul.fc.di.gloss.pestt.domain;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.zest.core.viewers.EntityConnectionData;

import pt.ul.fc.di.gloss.pestt.adts.Edge;
import pt.ul.fc.di.gloss.pestt.adts.Node;

public class LabelContentProvider extends LabelProvider {

	private CFG model;
	
	public LabelContentProvider (CFG model) {
		this.model = model;
	}
	
	@Override
	public String getText(Object element) {
		if (element instanceof Node<?>) {
			Node<String> node = (Node<String>) element;
			return node.getValue();
		}
		
		if (element instanceof EntityConnectionData) {
			EntityConnectionData test = (EntityConnectionData) element;
			Edge<Node<String>> edge = model.getEdge ((Node<String>) test.source, (Node<String>) test.dest);
			return model.getMetadata(edge);
		}
		throw new RuntimeException("Wrong type: "
				+ element.getClass().toString());
	}
}
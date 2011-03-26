package pt.ul.fc.di.gloss.pestt.domain;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.zest.core.viewers.IGraphEntityContentProvider;

import pt.ul.fc.di.gloss.pestt.adts.Node;

public class NodeContentProvider extends ArrayContentProvider 
								 implements IGraphEntityContentProvider {

	private CFG model;
	
	public NodeContentProvider (CFG model) {
		this.model = model;
	}
	
	@Override
	public Object[] getConnectedTo(Object entity) {
		if (entity instanceof Node<?>) {
			return model.getDescendents((Node<String>) entity).toArray();
		}
		throw new RuntimeException("Type not supported");
	}
}

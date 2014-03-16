package newx.mod.menu;

import java.util.ArrayList;
import java.util.List;

public class RightTreeNode {
	
	private Right value = null;
	private List<RightTreeNode> children = new ArrayList<RightTreeNode>();
	
	public Right getValue() {
		return value;
	}
	public void setValue(Right value) {
		this.value = value;
	}
	public List<RightTreeNode> getChildren() {
		return children;
	}
	public void setChildren(List<RightTreeNode> children) {
		this.children = children;
	}
}
